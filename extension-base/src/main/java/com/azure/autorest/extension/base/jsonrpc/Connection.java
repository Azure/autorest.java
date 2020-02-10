package com.azure.autorest.extension.base.jsonrpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Connection {
    private OutputStream writer;
    private PeekingBinaryReader reader;
    private boolean _isDisposed = false;
    private AtomicInteger requestId;
    private final Map<Integer, CallerResponse<?>> tasks = new ConcurrentHashMap<>();
    private ObjectMapper mapper = new ObjectMapper();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CompletableFuture<Void> _loop;

    public Connection(OutputStream writer, InputStream input) {
        this.writer = writer;
        this.reader = new PeekingBinaryReader(input);
        this._loop = CompletableFuture.runAsync(this::listen);
        this.requestId = new AtomicInteger(0);
    }

    private boolean isAlive = true;

//        => !_cancellationToken.IsCancellationRequested && writer != null && reader != null;

    public void stop() {
        isAlive = false;
        _loop.cancel(true);
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

    public <T> void dispatch(String path, Supplier<T> method)
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

    private List<JsonNode> readArguments(JsonNode input, int expectedArgs)
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

    public void dispatchNotification(String path, Runnable method)
    {
        _dispatch.put(path, input -> {
            method.run();
            return null;
        });
    }

    public <P1, T> void dispatch(String path, Function<P1, T> method)
    {
    }

    public <P1, P2, T> void dispatch(String path, BiFunction<P1, P2, T> method, Class<? extends P1> p1Class, Class<? extends P2> p2Class)
    {
        _dispatch.put(path, input -> {
            List<JsonNode> args = readArguments(input, 2);
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

    private boolean listen()
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
                    process(readJson());

                    // we're done here, start again.
                    continue;
                }

                // We're looking at headers
                Map<String, String> headers = new HashMap<>();
                String line = reader.readAsciiLine();
//                System.err.println("Incoming line: " + line);
                while (line != null && !line.isEmpty())
                {
                    String[] bits = line.split(":", 2);
                    headers.put(bits[0].trim(), bits[1].trim());
                    line = reader.readAsciiLine();
//                    System.err.println("Incoming line: " + line);
                }

                ch = reader.peekByte();
                // the next character had better be a { or [
                if ('{' == ch || '[' == ch)
                {
                    if (headers.containsKey("Content-Length")) {
                        String value = headers.get("Content-Length");
                        int contentLength = Integer.parseInt(value);
                        // don't wait for this to finish!
                        process(readJson(contentLength));
                        continue;
                    }
                    // looks like a json block or array. let's do this.
                    // don't wait for this to finish!
                    process(readJson());
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

    public void process(JsonNode content)
    {
//        System.err.println("JSON RPC receive: " + content.toString());
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
                            int id = jobject.get("id").asInt(-1);
                            // this is a method call.
                            // pass it to the service that is listening...
                            if (_dispatch.containsKey(method))
                            {
                                Function<JsonNode, String> fn = _dispatch.get(method);
                                JsonNode parameters = jobject.get("params");
                                String result = fn.apply(parameters);
                                if (id != -1)
                                {
                                    // if this is a request, send the response.
                                    respond(id, result);
                                }
                            }
                            return;
                        }
                        if (field.getKey().equals("result")) {
                            int id = jobject.get("id").asInt(-1);
                            if (id != -1)
                            {
                                CallerResponse<?> f;
                                synchronized (tasks)
                                {
                                    f = tasks.get(id);
                                    tasks.remove(id);
                                }
                                f.complete(mapper.convertValue(jobject.get("result"), f.type));
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
            for (Map.Entry<Integer, CallerResponse<?>> t : tasks.entrySet())
            {
                t.getValue().cancel(true);
            }

            writer.close();
            writer = null;
            reader.close();
            reader = null;
        }
    }

    private final Semaphore streamReady = new Semaphore(1);

    private void send(String text) {
//        System.err.println("JSON RPC send: " + text);
        try {
            synchronized (streamReady) {
                streamReady.acquire();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        byte[] buffer = text.getBytes(StandardCharsets.UTF_8);
        try {
//            System.err.write(("Content-Length: " + buffer.length + "\r\n\r\n").getBytes(StandardCharsets.US_ASCII));
            write(("Content-Length: " + buffer.length + "\r\n\r\n").getBytes(StandardCharsets.US_ASCII));
            write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        synchronized (streamReady) {
            streamReady.release();
        }
    }

    private void write(byte[] buffer) throws IOException {
//        System.err.write("Writing: ".getBytes(StandardCharsets.UTF_8));
//        System.err.write(buffer);
        writer.write(buffer, 0, buffer.length);
//        writer.flush();
    }

    public void sendError(int id, int code, String message)
    {
        JsonNode node = new ObjectNode(mapper.getNodeFactory())
                .put("jsonrpc", "2.0")
                .put("id", id)
                .put("message", message)
                .set("error", new ObjectNode(mapper.getNodeFactory()).put("code", code));
        send(node.toString());
    }
    public void respond(int id, String value)
    {
        JsonNode node = null;
        try {
            node = new ObjectNode(mapper.getNodeFactory())
                    .put("jsonrpc", "2.0")
                    .put("id", id)
                    .set("result", mapper.readTree(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        send(node.toString());
    }

    public void notify(String methodName, Object... values) {
//        System.err.println("JSON-RPC call: " + methodName);
        JsonNode node = new ObjectNode(mapper.getNodeFactory())
                .put("jsonrpc", "2.0")
                .put("method", methodName);
        if (values != null && values.length > 0 ) {
            node = ((ObjectNode) node).set("params", new ArrayNode(mapper.getNodeFactory(),
                    Arrays.stream(values).map(o -> mapper.convertValue(o, JsonNode.class)).collect(Collectors.toList())));
        }
        send(node.toString());
    }

    public void notifyWithObject(String methodName, Object parameter) {
//        System.err.println("JSON-RPC call: " + methodName);
        JsonNode node = new ObjectNode(mapper.getNodeFactory())
                .put("jsonrpc", "2.0")
                .put("method", methodName);
        if (parameter == null || parameter instanceof String || ProtocolUtils.isPrimitive(parameter)) {
            node = ((ObjectNode) node).set("params", new ArrayNode(mapper.getNodeFactory()));
        } else {
            node = ((ObjectNode) node).set("params", new ArrayNode(mapper.getNodeFactory(),
                    Collections.singletonList(mapper.convertValue(parameter, JsonNode.class))));
        }
        send(node.toString());
    }

    public <T> T request(JavaType type, String methodName, Object... values)
    {
        int id = requestId.getAndIncrement();
        CallerResponse<T> response = new CallerResponse<T>(id, type);
        tasks.put(id, response);
        JsonNode node = new ObjectNode(mapper.getNodeFactory())
                .put("jsonrpc", "2.0")
                .put("method", methodName)
                .put("id", id);
        if (values != null && values.length > 0 ) {
            node = ((ObjectNode) node).set("params", new ArrayNode(mapper.getNodeFactory(),
                    Arrays.stream(values).map(o -> mapper.convertValue(o, JsonNode.class)).collect(Collectors.toList())));
        }
        send(node.toString());
        try {
            return response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T RequestWithObject(JavaType type, String methodName, Object parameter)
    {
        int id = requestId.getAndIncrement();
        CallerResponse<T> response = new CallerResponse<T>(id, type);
        tasks.put(id, response);
        JsonNode node = new ObjectNode(mapper.getNodeFactory())
                .put("jsonrpc", "2.0")
                .put("method", methodName)
                .put("id", id);
        if (parameter == null || parameter instanceof String || ProtocolUtils.isPrimitive(parameter)) {
            node = ((ObjectNode) node).set("params", new ArrayNode(mapper.getNodeFactory()));
        } else {
            node = ((ObjectNode) node).set("params", new ArrayNode(mapper.getNodeFactory(),
                    Collections.singletonList(mapper.convertValue(parameter, JsonNode.class))));
        }
        send(node.toString());
        try {
            return response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void batch(List<String> calls) {
        send(new ArrayNode(mapper.getNodeFactory(), calls.stream().map(s -> {
            try {
                return mapper.readTree(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())).toString());
    }

    public void waitForAll() {
        try {
            _loop.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
