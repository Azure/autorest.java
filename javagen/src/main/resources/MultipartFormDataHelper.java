import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

// DO NOT modify this helper class

public final class MultipartFormDataHelper {
    /**
     * Line separator for the multipart HTTP request.
     */
    private static final String CRLF = "\r\n";

    /**
     * Value to be used as part of the divider for the multipart requests.
     */
    private final String boundary;

    /**
     * The actual part separator in the request. This is obtained by prepending "--" to the "boundary".
     */
    private final String partSeparator;

    /**
     * The marker for the ending of a multipart request. This is obtained by post-pending "--" to the "partSeparator".
     */
    private final String endMarker;

    /**
     * Charset used for encoding the multipart HTTP request.
     */
    private final Charset encoderCharset = StandardCharsets.UTF_8;

    private InputStream requestDataStream = new ByteArrayInputStream(new byte[0]);
    private long requestLength = 0;

    private RequestOptions requestOptions;
    private BinaryData requestBody;

    /**
     * Default constructor used in the code. The boundary is a random value.
     */
    public MultipartFormDataHelper(RequestOptions requestOptions) {
        this(requestOptions, UUID.randomUUID().toString().substring(0, 16));
    }

    private MultipartFormDataHelper(RequestOptions requestOptions, String boundary) {
        this.requestOptions = requestOptions;
        this.boundary = boundary;
        this.partSeparator = "--" + boundary;
        this.endMarker = this.partSeparator + "--";
    }

    public BinaryData getRequestBody() {
        return requestBody;
    }

    // text/plain
    /**
     * This method formats a text/plain field for a multipart HTTP request and returns its byte[] representation.
     *
     * @param fieldName the field name
     * @param value the value of the text/plain field
     */
    public MultipartFormDataHelper serializeField(String fieldName, String value) {
        String serialized = partSeparator
                + CRLF + "Content-Disposition: form-data; name=\""
                + fieldName + "\"" + CRLF + CRLF
                + value
                + CRLF;
        byte[] data = serialized.getBytes(encoderCharset);
        appendBytes(data);

        return this;
    }

    // application/octet-stream
    public MultipartFormDataHelper serializeField(String fieldName, BinaryData file, String filename) {
        // Multipart preamble
        String fileFieldPreamble = partSeparator
                + CRLF + "Content-Disposition: form-data; name=\"" + fieldName
                + "\"; filename=\"" + filename + "\""
                + CRLF + "Content-Type: application/octet-stream" + CRLF + CRLF;
        byte[] data = fileFieldPreamble.getBytes(encoderCharset);
        appendBytes(data);

        // Writing the file into the request as a byte stream
        requestLength += file.getLength();
        requestDataStream = new SequenceInputStream(requestDataStream, file.toStream());

        // CRLF
        data = CRLF.getBytes(encoderCharset);
        appendBytes(data);

        return this;
    }

    public MultipartFormDataHelper end() {
        byte[] data = endMarker.getBytes(encoderCharset);
        appendBytes(data);

        requestBody = BinaryData.fromStream(requestDataStream, requestLength).toReplayableBinaryData();

        requestOptions
                .setHeader(HttpHeaderName.CONTENT_TYPE, "multipart/form-data; boundary=" + this.boundary)
                .setHeader(HttpHeaderName.CONTENT_LENGTH, String.valueOf(requestLength));

        return this;
    }

    private void appendBytes(byte[] bytes) {
        requestLength += bytes.length;
        requestDataStream = new SequenceInputStream(requestDataStream, new ByteArrayInputStream(bytes));
    }

    // TODO application/json
}
