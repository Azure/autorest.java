// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.payload.multipart;

import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.util.BinaryData;
import com.payload.FileUtils;
import com.payload.multipart.models.Address;
import com.payload.multipart.models.BinaryArrayPartsRequest;
import com.payload.multipart.models.ComplexPartsRequest;
import com.payload.multipart.models.JsonArrayPartsRequest;
import com.payload.multipart.models.JsonPartRequest;
import com.payload.multipart.models.MultiBinaryPartsRequest;
import com.payload.multipart.models.MultiPartRequest;
import com.payload.multipart.models.PictureFileDetails;
import com.payload.multipart.models.PicturesFileDetails;
import com.payload.multipart.models.ProfileImageFileDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultipartTests {

    private final MultipartFilenameValidationPolicy validationPolicy = new MultipartFilenameValidationPolicy();
    private final MultiPartClient client = new MultiPartClientBuilder()
            .addPolicy(validationPolicy)
            .buildClient();
    private final MultiPartAsyncClient asyncClient = new MultiPartClientBuilder()
            .addPolicy(validationPolicy)
            .buildAsyncClient();

    private static final Path FILE = FileUtils.getJpgFile();

    private static final Path PNG_FILE = FileUtils.getPngFile();

    private final static class KpmAlgorithm {
        private static int indexOf(byte[] data, int start, int stop, byte[] pattern) {
            if (data == null || pattern == null)
                return -1;

            int[] failure = computeFailure(pattern);

            int j = 0;

            for (int i = start; i < stop; i++) {
                while (j > 0 && ( pattern[j] != '*' && pattern[j] != data[i])) {
                    j = failure[j - 1];
                }
                if (pattern[j] == '*' || pattern[j] == data[i]) {
                    j++;
                }
                if (j == pattern.length) {
                    return i - pattern.length + 1;
                }
            }
            return -1;
        }

        private static int[] computeFailure(byte[] pattern) {
            int[] failure = new int[pattern.length];

            int j = 0;
            for (int i = 1; i < pattern.length; i++) {
                while (j > 0 && pattern[j] != pattern[i]) {
                    j = failure[j - 1];
                }
                if (pattern[j] == pattern[i]) {
                    j++;
                }
                failure[i] = j;
            }

            return failure;
        }
    }

    private final static class MultipartFilenameValidationPolicy implements HttpPipelinePolicy {
        private final List<String> filenames = new ArrayList<>();
        private final List<String> contentTypes = new ArrayList<>();

        private final static Pattern FILENAME_PATTERN = Pattern.compile("filename=\"(.*?)\"");
        private final static Pattern CONTENT_TYPE_PATTERN = Pattern.compile("Content-Type:\\s*(.*)");

        @Override
        public Mono<HttpResponse> process(HttpPipelineCallContext context, HttpPipelineNextPolicy nextPolicy) {
            filenames.clear();
            byte[] body = context.getHttpRequest().getBodyAsBinaryData().toBytes();
            int start = 0;
            int stop = body.length;
            byte[] pattern = "Content-Disposition:".getBytes(StandardCharsets.UTF_8);

            int index;
            while ((index = KpmAlgorithm.indexOf(body, start, stop, pattern)) >= 0) {
                int posNewLine;
                for (posNewLine = index; posNewLine < stop; ++posNewLine) {
                    if (body[posNewLine] == 10 || body[posNewLine] == 13) {
                        // newline
                        String line = new String(body, index, posNewLine - index, StandardCharsets.UTF_8);
                        Matcher matcher = FILENAME_PATTERN.matcher(line);
                        if (matcher.find()) {
                            filenames.add(matcher.group(1));
                        }
                        break;
                    }
                }
                start = posNewLine + 1;
            }

            start = 0;
            byte[] contentTypePattern = "Content-Type:".getBytes(StandardCharsets.UTF_8);

            while ((index = KpmAlgorithm.indexOf(body, start, stop, contentTypePattern)) >= 0) {
                int posNewLine;
                for (posNewLine = index; posNewLine < stop; ++posNewLine) {
                    if (body[posNewLine] == 10 || body[posNewLine] == 13) {
                        // newline
                        String line = new String(body, index, posNewLine - index, StandardCharsets.UTF_8);
                        Matcher matcher = CONTENT_TYPE_PATTERN.matcher(line);
                        if (matcher.find()) {
                            contentTypes.add(matcher.group(1));
                        }
                        break;
                    }
                }
                start = posNewLine + 1;
            }

            // reset the body to compensate here consuming all the data
            context.getHttpRequest().setBody(body);
            return nextPolicy.process();
        }

        private void validateFilenames(String... filenames) {
            Assertions.assertEquals(Arrays.asList(filenames), this.filenames);
        }

        private void validateContentTypes(String... contentTypes) {
            Assertions.assertEquals(Arrays.asList(contentTypes), this.contentTypes);
        }
    }

    @Test
    public void testBasic() {
        MultiPartRequest request = new MultiPartRequest(
                "123",
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)));

        client.basic(request);

        request.getProfileImage().setFilename("image.jpg");
        asyncClient.basic(request).block();
    }

    @Test
    public void testJson() {
        client.jsonPart(new JsonPartRequest(
                new Address("X"),
                new ProfileImageFileDetails(BinaryData.fromFile(FILE))));
    }

    @Test
    public void testJsonArray() {
        client.jsonArrayParts(new JsonArrayPartsRequest(
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)).setFilename("image.jpg"),
                Arrays.asList(new Address("Y"), new Address("Z"))));
    }

    @Test
    public void testMultipleFiles() {
        client.multiBinaryParts(new MultiBinaryPartsRequest(
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)))
                .setPicture(new PictureFileDetails(BinaryData.fromFile(FileUtils.getPngFile()))));

        validationPolicy.validateFilenames("profileImage", "picture");

        // "picture" be optional
        asyncClient.multiBinaryParts(new MultiBinaryPartsRequest(
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)))).block();

        validationPolicy.validateFilenames("profileImage");
    }

    @Test
    public void testFileArray() {
        // provide no filename
        client.binaryArrayParts(new BinaryArrayPartsRequest(
                "123",
                Arrays.asList(
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE)),
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE))
                )));

        validationPolicy.validateContentTypes("application/octet-stream", "application/octet-stream");

        // provide only 1 filename, when there are 2 files
        // filename contains non-ASCII
        asyncClient.binaryArrayParts(new BinaryArrayPartsRequest(
                "123",
                Arrays.asList(
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE)).setFilename("voilà.jpg"),
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE))
                ))).block();

        validationPolicy.validateFilenames("voila.jpg", "pictures2");
    }

    @Test
    public void testFilenameAndContentType() {
        client.checkFileNameAndContentType(new MultiPartRequest(
                "123",
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)).setFilename("hello.jpg").setContentType("image/jpg")));
    }

    @Test
    public void testComplex() {
        client.complex(new ComplexPartsRequest(
                "123",
                new Address("X"),
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)),
                Arrays.asList(new Address("Y"), new Address("Z")),
                Arrays.asList(
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE)),
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE))
                )));

        validationPolicy.validateFilenames("profileImage", "pictures1", "pictures2");

        // provide 3 filenames, when there are 2 files
        asyncClient.complex(new ComplexPartsRequest(
                "123",
                new Address("X"),
                new ProfileImageFileDetails(BinaryData.fromFile(FILE)),
                Arrays.asList(new Address("Y"), new Address("Z")),
                Arrays.asList(
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE)),
                        new PicturesFileDetails(BinaryData.fromFile(PNG_FILE)).setFilename("picture2")
                ))).block();

        validationPolicy.validateFilenames("profileImage", "pictures1", "picture2");
    }
}
