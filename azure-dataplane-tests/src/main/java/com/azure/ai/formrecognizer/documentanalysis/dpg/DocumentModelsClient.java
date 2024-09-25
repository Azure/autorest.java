// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.dpg;

import com.azure.ai.formrecognizer.documentanalysis.dpg.implementation.DocumentModelsImpl;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.SyncPoller;

/**
 * Initializes a new instance of the synchronous FormRecognizerClient type.
 */
@ServiceClient(builder = DocumentModelsClientBuilder.class)
public final class DocumentModelsClient {
    @Generated
    private final DocumentModelsImpl serviceClient;

    /**
     * Initializes an instance of DocumentModelsClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DocumentModelsClient(DocumentModelsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Analyzes document with document model.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>pages</td><td>String</td><td>No</td><td>List of 1-based page numbers to analyze. Ex.
     * "1-3,5,7-9"</td></tr>
     * <tr><td>locale</td><td>String</td><td>No</td><td>Locale hint for text recognition and document analysis. Value
     * may contain only the language code (ex. "en", "fr") or BCP 47 language tag (ex. "en-US").</td></tr>
     * <tr><td>stringIndexType</td><td>String</td><td>No</td><td>Method used to compute string offset and length.
     * Allowed values: "textElements", "unicodeCodePoint", "utf16CodeUnit".</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * BinaryData
     * }
     * </pre>
     * 
     * @param modelId Unique document model name.
     * @param contentType Upload file type. Allowed values: "application/octet-stream", "application/pdf", "image/bmp",
     * "image/heif", "image/jpeg", "image/png", "image/tiff".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginAnalyzeDocument(String modelId, String contentType,
        RequestOptions requestOptions) {
        return this.serviceClient.beginAnalyzeDocument(modelId, contentType, requestOptions);
    }

    /**
     * Gets the result of document analysis.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     status: String(notStarted/running/failed/succeeded) (Required)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     *         innererror (Optional): {
     *             code: String (Required)
     *             message: String (Optional)
     *             innererror (Optional): (recursive schema, see innererror above)
     *         }
     *     }
     *     analyzeResult (Optional): {
     *         apiVersion: String(2022-08-31) (Required)
     *         modelId: String (Required)
     *         stringIndexType: String(textElements/unicodeCodePoint/utf16CodeUnit) (Required)
     *         content: String (Required)
     *         pages (Required): [
     *              (Required){
     *                 pageNumber: int (Required)
     *                 angle: Float (Optional)
     *                 width: Float (Optional)
     *                 height: Float (Optional)
     *                 unit: String(pixel/inch) (Optional)
     *                 spans (Required): [
     *                      (Required){
     *                         offset: int (Required)
     *                         length: int (Required)
     *                     }
     *                 ]
     *                 words (Optional): [
     *                      (Optional){
     *                         content: String (Required)
     *                         polygon (Optional): [
     *                             float (Optional)
     *                         ]
     *                         span (Required): (recursive schema, see span above)
     *                         confidence: float (Required)
     *                     }
     *                 ]
     *                 selectionMarks (Optional): [
     *                      (Optional){
     *                         state: String(selected/unselected) (Required)
     *                         polygon (Optional): [
     *                             float (Optional)
     *                         ]
     *                         span (Required): (recursive schema, see span above)
     *                         confidence: float (Required)
     *                     }
     *                 ]
     *                 lines (Optional): [
     *                      (Optional){
     *                         content: String (Required)
     *                         polygon (Optional): [
     *                             float (Optional)
     *                         ]
     *                         spans (Required): [
     *                             (recursive schema, see above)
     *                         ]
     *                     }
     *                 ]
     *             }
     *         ]
     *         paragraphs (Optional): [
     *              (Optional){
     *                 role: String(pageHeader/pageFooter/pageNumber/title/sectionHeading/footnote) (Optional)
     *                 content: String (Required)
     *                 boundingRegions (Optional): [
     *                      (Optional){
     *                         pageNumber: int (Required)
     *                         polygon (Required): [
     *                             float (Required)
     *                         ]
     *                     }
     *                 ]
     *                 spans (Required): [
     *                     (recursive schema, see above)
     *                 ]
     *             }
     *         ]
     *         tables (Optional): [
     *              (Optional){
     *                 rowCount: int (Required)
     *                 columnCount: int (Required)
     *                 cells (Required): [
     *                      (Required){
     *                         kind: String(content/rowHeader/columnHeader/stubHead/description) (Optional)
     *                         rowIndex: int (Required)
     *                         columnIndex: int (Required)
     *                         rowSpan: Integer (Optional)
     *                         columnSpan: Integer (Optional)
     *                         content: String (Required)
     *                         boundingRegions (Optional): [
     *                             (recursive schema, see above)
     *                         ]
     *                         spans (Required): [
     *                             (recursive schema, see above)
     *                         ]
     *                     }
     *                 ]
     *                 boundingRegions (Optional): [
     *                     (recursive schema, see above)
     *                 ]
     *                 spans (Required): [
     *                     (recursive schema, see above)
     *                 ]
     *             }
     *         ]
     *         keyValuePairs (Optional): [
     *              (Optional){
     *                 key (Required): {
     *                     content: String (Required)
     *                     boundingRegions (Optional): [
     *                         (recursive schema, see above)
     *                     ]
     *                     spans (Required): [
     *                         (recursive schema, see above)
     *                     ]
     *                 }
     *                 value (Optional): (recursive schema, see value above)
     *                 confidence: float (Required)
     *             }
     *         ]
     *         styles (Optional): [
     *              (Optional){
     *                 isHandwritten: Boolean (Optional)
     *                 spans (Required): [
     *                     (recursive schema, see above)
     *                 ]
     *                 confidence: float (Required)
     *             }
     *         ]
     *         languages (Optional): [
     *              (Optional){
     *                 locale: String (Required)
     *                 spans (Required): [
     *                     (recursive schema, see above)
     *                 ]
     *                 confidence: float (Required)
     *             }
     *         ]
     *         documents (Optional): [
     *              (Optional){
     *                 docType: String (Required)
     *                 boundingRegions (Optional): [
     *                     (recursive schema, see above)
     *                 ]
     *                 spans (Required): [
     *                     (recursive schema, see above)
     *                 ]
     *                 fields (Optional): {
     *                     String (Required): {
     *                         type: String(string/date/time/phoneNumber/number/integer/selectionMark/countryRegion/signature/array/object/currency/address) (Required)
     *                         valueString: String (Optional)
     *                         valueDate: String (Optional)
     *                         valueTime: String (Optional)
     *                         valuePhoneNumber: String (Optional)
     *                         valueNumber: Float (Optional)
     *                         valueInteger: Long (Optional)
     *                         valueSelectionMark: String(selected/unselected) (Optional)
     *                         valueSignature: String(signed/unsigned) (Optional)
     *                         valueCountryRegion: String (Optional)
     *                         valueArray (Optional): [
     *                             (recursive schema, see above)
     *                         ]
     *                         valueObject (Optional): {
     *                             String (Required): (recursive schema, see String above)
     *                         }
     *                         valueCurrency (Optional): {
     *                             amount: double (Required)
     *                             currencySymbol: String (Optional)
     *                         }
     *                         valueAddress (Optional): {
     *                             houseNumber: String (Optional)
     *                             poBox: String (Optional)
     *                             road: String (Optional)
     *                             city: String (Optional)
     *                             state: String (Optional)
     *                             postalCode: String (Optional)
     *                             countryRegion: String (Optional)
     *                             streetAddress: String (Optional)
     *                         }
     *                         content: String (Optional)
     *                         boundingRegions (Optional): [
     *                             (recursive schema, see above)
     *                         ]
     *                         spans (Optional): [
     *                             (recursive schema, see above)
     *                         ]
     *                         confidence: Float (Optional)
     *                     }
     *                 }
     *                 confidence: float (Required)
     *             }
     *         ]
     *     }
     * }
     * }
     * </pre>
     * 
     * @param modelId Unique document model name.
     * @param resultId Analyze operation result ID.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the result of document analysis along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getAnalyzeResultWithResponse(String modelId, String resultId,
        RequestOptions requestOptions) {
        return this.serviceClient.getAnalyzeResultWithResponse(modelId, resultId, requestOptions);
    }

    /**
     * Builds a custom document analysis model.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     modelId: String (Required)
     *     description: String (Optional)
     *     buildMode: String(template/neural) (Required)
     *     azureBlobSource (Optional): {
     *         containerUrl: String (Required)
     *         prefix: String (Optional)
     *     }
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param buildRequest Building request parameters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginBuildModel(BinaryData buildRequest, RequestOptions requestOptions) {
        return this.serviceClient.beginBuildModel(buildRequest, requestOptions);
    }

    /**
     * Creates a new document model from document types of existing document models.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     modelId: String (Required)
     *     description: String (Optional)
     *     componentModels (Required): [
     *          (Required){
     *             modelId: String (Required)
     *         }
     *     ]
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param composeRequest Compose request parameters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginComposeModel(BinaryData composeRequest,
        RequestOptions requestOptions) {
        return this.serviceClient.beginComposeModel(composeRequest, requestOptions);
    }

    /**
     * Generates authorization to copy a document model to this location with specified modelId and optional
     * description.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     modelId: String (Required)
     *     description: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     targetResourceId: String (Required)
     *     targetResourceRegion: String (Required)
     *     targetModelId: String (Required)
     *     targetModelLocation: String (Required)
     *     accessToken: String (Required)
     *     expirationDateTime: OffsetDateTime (Required)
     * }
     * }
     * </pre>
     * 
     * @param authorizeCopyRequest Authorize copy request parameters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return authorization to copy a document model to the specified target resource and modelId along with
     * {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> authorizeModelCopyWithResponse(BinaryData authorizeCopyRequest,
        RequestOptions requestOptions) {
        return this.serviceClient.authorizeModelCopyWithResponse(authorizeCopyRequest, requestOptions);
    }

    /**
     * Copies document model to the target resource, region, and modelId.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     targetResourceId: String (Required)
     *     targetResourceRegion: String (Required)
     *     targetModelId: String (Required)
     *     targetModelLocation: String (Required)
     *     accessToken: String (Required)
     *     expirationDateTime: OffsetDateTime (Required)
     * }
     * }
     * </pre>
     * 
     * @param modelId Unique document model name.
     * @param copyToRequest Copy to request parameters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginCopyModelTo(String modelId, BinaryData copyToRequest,
        RequestOptions requestOptions) {
        return this.serviceClient.beginCopyModelTo(modelId, copyToRequest, requestOptions);
    }

    /**
     * List all document models.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     modelId: String (Required)
     *     description: String (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list document models response object as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listModels(RequestOptions requestOptions) {
        return this.serviceClient.listModels(requestOptions);
    }

    /**
     * Gets detailed document model information.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     modelId: String (Required)
     *     description: String (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     *     docTypes (Optional): {
     *         String (Required): {
     *             description: String (Optional)
     *             buildMode: String(template/neural) (Optional)
     *             fieldSchema (Required): {
     *                 String (Required): {
     *                     type: String(string/date/time/phoneNumber/number/integer/selectionMark/countryRegion/signature/array/object/currency/address) (Required)
     *                     description: String (Optional)
     *                     example: String (Optional)
     *                     items (Optional): (recursive schema, see items above)
     *                     properties (Optional): {
     *                         String (Required): (recursive schema, see String above)
     *                     }
     *                 }
     *             }
     *             fieldConfidence (Optional): {
     *                 String: float (Required)
     *             }
     *         }
     *     }
     * }
     * }
     * </pre>
     * 
     * @param modelId Unique document model name.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return detailed document model information along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getModelWithResponse(String modelId, RequestOptions requestOptions) {
        return this.serviceClient.getModelWithResponse(modelId, requestOptions);
    }

    /**
     * Deletes document model.
     * 
     * @param modelId Unique document model name.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteModelWithResponse(String modelId, RequestOptions requestOptions) {
        return this.serviceClient.deleteModelWithResponse(modelId, requestOptions);
    }
}
