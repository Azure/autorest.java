package com.azure.autorest.extension.base.jsonrpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Connection {
    private OutputStream writer;
    private PeekingBinaryReader reader;
    private boolean _isDisposed = false;
    private int _requestId;
    private final Map<String, CallerResponse<?>> tasks = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Connection(OutputStream writer, InputStream input) {
        this.writer = writer;
        this.reader = new PeekingBinaryReader(input);
    }

    private boolean isAlive = true;

//        => !_cancellationToken.IsCancellationRequested && writer != null && reader != null;

    public void stop() {
        isAlive = false;
    }

    private JsonNode readJson() {
        String jsonText = "";
        JsonNode json;
        while (true)
        {
            try {
                jsonText += reader.readAsciiLine(); // + "\n";
            } catch (IOException e) {
                throw new RuntimeException("Cannot read JSON input");
            }
            try
            {
                json = mapper.readTree(jsonText);
                if (json != null) {
                    return json;
                }
            }
            catch (IOException e)
            {
                // not enough text?
            }
        }
    }

    private Map<String, Function<JsonNode, String>> _dispatch = new HashMap<>();

    public <T> void Dispatch(String path, Supplier<T> method)
    {
        _dispatch.put(path, input -> {
            T result = method.get();
            if (result == null) {
                return "null";
            }
            try {
                return mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

//    public void Dispatch(String path, Supplier<String> method)
//    {
//        _dispatch.put(path, input -> {
//            String result = method.get();
//            if (result == null) {
//                return "null";
//            }
//            return ProtocolUtils.quote(result);
//        });
//    }

    private List<JsonNode> ReadArguments(JsonNode input, int expectedArgs)
    {
        List<JsonNode> ret = new ArrayList<>();
        if (input instanceof ArrayNode) {
            for (JsonNode jsonNode : input) {
                ret.add(jsonNode);
            }
        } else if (input != null) {
            ret.add(input);
        }

        if (expectedArgs == 0)
        {
            if (ret.size() == 0)
            {
                // expected zero, received zero
                return new ArrayList<>();
            }

            throw new RuntimeException("Invalid number of arguments");
        }

        if (ret.size() == expectedArgs)
        {
            // passed as an array
            return ret;
        }

        throw new RuntimeException("Invalid number of arguments");
    }

    public void DispatchNotification(String path, Runnable method)
    {
        _dispatch.put(path, input -> {
            method.run();
            return null;
        });
    }

    public <P1, T> void Dispatch(String path, Function<P1, T> method)
    {
    }

    public <P1, P2, T> void Dispatch(String path, BiFunction<P1, P2, T> method, Class<? extends P1> p1Class, Class<? extends P2> p2Class)
    {
        _dispatch.put(path, input -> {
            List<JsonNode> args = ReadArguments(input, 2);
            try {
                P1 a1 = mapper.treeToValue(args.get(0), p1Class);
                P2 a2 = mapper.treeToValue(args.get(1), p2Class);

                T result = method.apply(a1, a2);
                if (result == null) {
                    return "null";
                }
                return mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private JsonNode readJson(int contentLength)
    {
        try {
            byte[] bytes = reader.readBytes(contentLength);
            return mapper.readTree(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private void Log(String text) => OnDebug?.Invoke(text);

    private boolean Listen()
    {
        while (isAlive)
        {
            try
            {
                int ch = reader.peekByte();
                if (-1 == ch)
                {
                    // didn't get anything. start again, it'll know if we're shutting down
                    break;
                }

                if ('{' == ch || '[' == ch)
                {
                    // looks like a json block or array. let's do this.
                    // don't wait for this to finish!
                    Process(readJson());

                    // we're done here, start again.
                    continue;
                }

                // We're looking at headers
                Map<String, String> headers = new HashMap<>();
                String line = reader.readAsciiLine();
                while (line != null && !line.isEmpty())
                {
                    String[] bits = line.split(":", 2);
                    headers.put(bits[0].trim(), bits[1].trim());
                    line = reader.readAsciiLine();
                }

                ch = reader.peekByte();
                // the next character had better be a { or [
                if ('{' == ch || '[' == ch)
                {
                    if (headers.containsKey("Content-Length")) {
                        String value = headers.get("Content-Length");
                        int contentLength = Integer.parseInt(value);
                        // don't wait for this to finish!
                        Process(readJson(contentLength));
                        continue;
                    }
                    // looks like a json block or array. let's do this.
                    // don't wait for this to finish!
                    Process(readJson());
                    // we're done here, start again.
                    continue;
                }

//                throw new RuntimeException("SHOULD NEVER GET HERE!");
                return false;

            }
            catch (Exception e)
            {
                if (isAlive)
                {
//                    throw new RuntimeException("Error during Listen");
                    continue;
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

    public void Process(JsonNode content)
    {
        if (content instanceof ObjectNode)
        {
            executorService.submit(() -> {
                ObjectNode jobject = (ObjectNode) content;
                try
                {
                    Iterator<Map.Entry<String, JsonNode>> fieldIterator = jobject.fields();
                    while (fieldIterator.hasNext()) {
                        Map.Entry<String, JsonNode> field = fieldIterator.next();
                        if (field.getKey().equals("method")) {
                            String method = field.getValue().asText();
                            String id = jobject.get("id").asText();
                            // this is a method call.
                            // pass it to the service that is listening...
                            if (_dispatch.containsKey(method))
                            {
                                Function<JsonNode, String> fn = _dispatch.get(method);
                                JsonNode parameters = jobject.get("params");
                                String result = fn.apply(parameters);
                                if (id != null)
                                {
                                    // if this is a request, send the response.
                                    Respond(id, result);
                                }
                            }
                            return;
                        }
                        if (field.getKey().equals("result")) {
                            String id = jobject.get("id").asText();
                            if (id == null || id.isEmpty())
                            {
                                CallerResponse<?> f;
                                synchronized (tasks)
                                {
                                    f = tasks.get(id);
                                    tasks.remove(id);
                                }
                                f.complete(jobject.get("result"));
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        }
        if (content instanceof ArrayNode)
        {
            System.err.println("Unhandled: Batch Request");
        }
    }

    protected void close() throws IOException
    {
        // ensure that we are in a cancelled state.
        isAlive = false;
        if (!_isDisposed)
        {
            // make sure we can't dispose twice
            _isDisposed = true;
            for (Map.Entry<String, CallerResponse<?>> t : tasks.entrySet())
            {
                t.getValue().cancel(true);
            }

            writer.close();
            writer = null;
            reader.close();
            reader = null;
        }
    }

    private final Semaphore _streamReady = new Semaphore(1);

    private void Send(String text) {
        try {
            _streamReady.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        byte[] buffer = text.getBytes(StandardCharsets.UTF_8);
        try {
            Write(("Content-Length: " + buffer.length + "\r\n\r\n").getBytes(StandardCharsets.US_ASCII));
            Write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        _streamReady.release();
    }
    private void Write(byte[] buffer) throws IOException {
        writer.write(buffer, 0, buffer.length);
    }

    public void SendError(String id, int code, String message)
    {
        Send(ProtocolExtensions.Error(id, code, message));
    }
    public void Respond(String id, String value)
    {
        Send(ProtocolExtensions.Response(id, value));
    }

    public void Notify(String methodName, Object... values) {
        Send (ProtocolExtensions.Notification(methodName, values));
    }
    public void NotifyWithObject(String methodName, Object parameter) {
        Send (ProtocolExtensions.NotificationWithObject(methodName, parameter));
    }

    public <T> T Request(String methodName, Object... values)
    {
        var id = Interlocked.Decrement(ref _requestId).ToString();
        var response = new CallerResponse<T>(id);
        lock( _tasks ) { _tasks.Add(id, response); }
        await Send(ProtocolExtensions.Request(id, methodName, values)).ConfigureAwait(false);
        return await response.Task.ConfigureAwait(false);
    }

    public <T> T RequestWithObject(String methodName, Object parameter)
    {
        var id = Interlocked.Decrement(ref _requestId).ToString();
        var response = new CallerResponse<T>(id);
        lock( _tasks ) { _tasks.Add(id, response); }
        await Send(ProtocolExtensions.Request(id, methodName, parameter)).ConfigureAwait(false);
        return await response.Task.ConfigureAwait(false);
    }

    public void Batch(List<String> calls) {
        Send(calls.JsonArray());
    }
}