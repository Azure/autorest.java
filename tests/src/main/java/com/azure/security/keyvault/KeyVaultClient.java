package com.azure.security.keyvault;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.security.keyvault.models.BackupCertificateResult;
import com.azure.security.keyvault.models.BackupKeyResult;
import com.azure.security.keyvault.models.BackupSecretResult;
import com.azure.security.keyvault.models.BackupStorageResult;
import com.azure.security.keyvault.models.CertificateBundle;
import com.azure.security.keyvault.models.CertificateCreateParameters;
import com.azure.security.keyvault.models.CertificateImportParameters;
import com.azure.security.keyvault.models.CertificateIssuerItem;
import com.azure.security.keyvault.models.CertificateIssuerListResult;
import com.azure.security.keyvault.models.CertificateIssuerSetParameters;
import com.azure.security.keyvault.models.CertificateIssuerUpdateParameters;
import com.azure.security.keyvault.models.CertificateItem;
import com.azure.security.keyvault.models.CertificateListResult;
import com.azure.security.keyvault.models.CertificateMergeParameters;
import com.azure.security.keyvault.models.CertificateOperation;
import com.azure.security.keyvault.models.CertificateOperationUpdateParameter;
import com.azure.security.keyvault.models.CertificatePolicy;
import com.azure.security.keyvault.models.CertificateRestoreParameters;
import com.azure.security.keyvault.models.CertificateUpdateParameters;
import com.azure.security.keyvault.models.Contacts;
import com.azure.security.keyvault.models.DeletedCertificateBundle;
import com.azure.security.keyvault.models.DeletedCertificateItem;
import com.azure.security.keyvault.models.DeletedCertificateListResult;
import com.azure.security.keyvault.models.DeletedKeyBundle;
import com.azure.security.keyvault.models.DeletedKeyItem;
import com.azure.security.keyvault.models.DeletedKeyListResult;
import com.azure.security.keyvault.models.DeletedSasDefinitionBundle;
import com.azure.security.keyvault.models.DeletedSasDefinitionItem;
import com.azure.security.keyvault.models.DeletedSasDefinitionListResult;
import com.azure.security.keyvault.models.DeletedSecretBundle;
import com.azure.security.keyvault.models.DeletedSecretItem;
import com.azure.security.keyvault.models.DeletedSecretListResult;
import com.azure.security.keyvault.models.DeletedStorageAccountItem;
import com.azure.security.keyvault.models.DeletedStorageBundle;
import com.azure.security.keyvault.models.DeletedStorageListResult;
import com.azure.security.keyvault.models.IssuerBundle;
import com.azure.security.keyvault.models.KeyBundle;
import com.azure.security.keyvault.models.KeyCreateParameters;
import com.azure.security.keyvault.models.KeyImportParameters;
import com.azure.security.keyvault.models.KeyItem;
import com.azure.security.keyvault.models.KeyListResult;
import com.azure.security.keyvault.models.KeyOperationResult;
import com.azure.security.keyvault.models.KeyOperationsParameters;
import com.azure.security.keyvault.models.KeyRestoreParameters;
import com.azure.security.keyvault.models.KeySignParameters;
import com.azure.security.keyvault.models.KeyUpdateParameters;
import com.azure.security.keyvault.models.KeyVaultErrorException;
import com.azure.security.keyvault.models.KeyVerifyParameters;
import com.azure.security.keyvault.models.KeyVerifyResult;
import com.azure.security.keyvault.models.SasDefinitionBundle;
import com.azure.security.keyvault.models.SasDefinitionCreateParameters;
import com.azure.security.keyvault.models.SasDefinitionItem;
import com.azure.security.keyvault.models.SasDefinitionListResult;
import com.azure.security.keyvault.models.SasDefinitionUpdateParameters;
import com.azure.security.keyvault.models.SecretBundle;
import com.azure.security.keyvault.models.SecretItem;
import com.azure.security.keyvault.models.SecretListResult;
import com.azure.security.keyvault.models.SecretRestoreParameters;
import com.azure.security.keyvault.models.SecretSetParameters;
import com.azure.security.keyvault.models.SecretUpdateParameters;
import com.azure.security.keyvault.models.StorageAccountCreateParameters;
import com.azure.security.keyvault.models.StorageAccountItem;
import com.azure.security.keyvault.models.StorageAccountRegenerteKeyParameters;
import com.azure.security.keyvault.models.StorageAccountUpdateParameters;
import com.azure.security.keyvault.models.StorageBundle;
import com.azure.security.keyvault.models.StorageListResult;
import com.azure.security.keyvault.models.StorageRestoreParameters;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the KeyVaultClient type.
 */
public final class KeyVaultClient {
    /**
     * The proxy service used to perform REST calls.
     */
    private KeyVaultClientService service;

    /**
     * Api Version.
     */
    private String apiVersion;

    /**
     * Gets Api Version.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Sets Api Version.
     * 
     * @param apiVersion the apiVersion value.
     * @return the service client itself.
     */
    KeyVaultClient setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * Initializes an instance of KeyVaultClient client.
     */
    public KeyVaultClient() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of KeyVaultClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public KeyVaultClient(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.service = RestProxy.create(KeyVaultClientService.class, this.httpPipeline);
    }

    /**
     * The interface defining all the services for KeyVaultClient to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{vaultBaseUrl}")
    @ServiceInterface(name = "KeyVaultClient")
    private interface KeyVaultClientService {
        @Post("/keys/{key-name}/create")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyBundle>> createKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyCreateParameters parameters);

        @Put("/keys/{key-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyBundle>> importKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyImportParameters parameters);

        @Delete("/keys/{key-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedKeyBundle>> deleteKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion);

        @Patch("/keys/{key-name}/{key-version}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyBundle>> updateKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyUpdateParameters parameters);

        @Get("/keys/{key-name}/{key-version}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyBundle>> getKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion);

        @Get("/keys/{key-name}/versions")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyListResult>> getKeyVersions(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/keys")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyListResult>> getKeys(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Post("/keys/{key-name}/backup")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<BackupKeyResult>> backupKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion);

        @Post("/keys/restore")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyBundle>> restoreKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyRestoreParameters parameters);

        @Post("/keys/{key-name}/{key-version}/encrypt")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyOperationResult>> encrypt(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyOperationsParameters parameters);

        @Post("/keys/{key-name}/{key-version}/decrypt")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyOperationResult>> decrypt(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyOperationsParameters parameters);

        @Post("/keys/{key-name}/{key-version}/sign")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyOperationResult>> sign(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeySignParameters parameters);

        @Post("/keys/{key-name}/{key-version}/verify")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyVerifyResult>> verify(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyVerifyParameters parameters);

        @Post("/keys/{key-name}/{key-version}/wrapkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyOperationResult>> wrapKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyOperationsParameters parameters);

        @Post("/keys/{key-name}/{key-version}/unwrapkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyOperationResult>> unwrapKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @PathParam("key-version") String keyVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") KeyOperationsParameters parameters);

        @Get("/deletedkeys")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedKeyListResult>> getDeletedKeys(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedkeys/{key-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedKeyBundle>> getDeletedKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion);

        @Delete("/deletedkeys/{key-name}")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<Response<Void>> purgeDeletedKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion);

        @Post("/deletedkeys/{key-name}/recover")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyBundle>> recoverDeletedKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("key-name") String keyName, @QueryParam("api-version") String apiVersion);

        @Put("/secrets/{secret-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretBundle>> setSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") SecretSetParameters parameters);

        @Delete("/secrets/{secret-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSecretBundle>> deleteSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("api-version") String apiVersion);

        @Patch("/secrets/{secret-name}/{secret-version}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretBundle>> updateSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @PathParam("secret-version") String secretVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") SecretUpdateParameters parameters);

        @Get("/secrets/{secret-name}/{secret-version}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretBundle>> getSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @PathParam("secret-version") String secretVersion, @QueryParam("api-version") String apiVersion);

        @Get("/secrets")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretListResult>> getSecrets(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/secrets/{secret-name}/versions")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretListResult>> getSecretVersions(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedsecrets")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSecretListResult>> getDeletedSecrets(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedsecrets/{secret-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSecretBundle>> getDeletedSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("api-version") String apiVersion);

        @Delete("/deletedsecrets/{secret-name}")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<Response<Void>> purgeDeletedSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("api-version") String apiVersion);

        @Post("/deletedsecrets/{secret-name}/recover")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretBundle>> recoverDeletedSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("api-version") String apiVersion);

        @Post("/secrets/{secret-name}/backup")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<BackupSecretResult>> backupSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("secret-name") String secretName, @QueryParam("api-version") String apiVersion);

        @Post("/secrets/restore")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretBundle>> restoreSecret(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") SecretRestoreParameters parameters);

        @Get("/certificates")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateListResult>> getCertificates(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("includePending") Boolean includePending, @QueryParam("api-version") String apiVersion);

        @Delete("/certificates/{certificate-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedCertificateBundle>> deleteCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Put("/certificates/contacts")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<Contacts>> setCertificateContacts(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") Contacts contacts);

        @Get("/certificates/contacts")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<Contacts>> getCertificateContacts(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion);

        @Delete("/certificates/contacts")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<Contacts>> deleteCertificateContacts(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion);

        @Get("/certificates/issuers")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateIssuerListResult>> getCertificateIssuers(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Put("/certificates/issuers/{issuer-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<IssuerBundle>> setCertificateIssuer(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("issuer-name") String issuerName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateIssuerSetParameters parameter);

        @Patch("/certificates/issuers/{issuer-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<IssuerBundle>> updateCertificateIssuer(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("issuer-name") String issuerName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateIssuerUpdateParameters parameter);

        @Get("/certificates/issuers/{issuer-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<IssuerBundle>> getCertificateIssuer(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("issuer-name") String issuerName, @QueryParam("api-version") String apiVersion);

        @Delete("/certificates/issuers/{issuer-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<IssuerBundle>> deleteCertificateIssuer(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("issuer-name") String issuerName, @QueryParam("api-version") String apiVersion);

        @Post("/certificates/{certificate-name}/create")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateOperation>> createCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateCreateParameters parameters);

        @Post("/certificates/{certificate-name}/import")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateBundle>> importCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateImportParameters parameters);

        @Get("/certificates/{certificate-name}/versions")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateListResult>> getCertificateVersions(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/certificates/{certificate-name}/policy")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificatePolicy>> getCertificatePolicy(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Patch("/certificates/{certificate-name}/policy")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificatePolicy>> updateCertificatePolicy(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificatePolicy certificatePolicy);

        @Patch("/certificates/{certificate-name}/{certificate-version}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateBundle>> updateCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @PathParam("certificate-version") String certificateVersion, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateUpdateParameters parameters);

        @Get("/certificates/{certificate-name}/{certificate-version}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateBundle>> getCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @PathParam("certificate-version") String certificateVersion, @QueryParam("api-version") String apiVersion);

        @Patch("/certificates/{certificate-name}/pending")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateOperation>> updateCertificateOperation(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateOperationUpdateParameter certificateOperation);

        @Get("/certificates/{certificate-name}/pending")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateOperation>> getCertificateOperation(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Delete("/certificates/{certificate-name}/pending")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateOperation>> deleteCertificateOperation(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Post("/certificates/{certificate-name}/pending/merge")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateBundle>> mergeCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateMergeParameters parameters);

        @Post("/certificates/{certificate-name}/backup")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<BackupCertificateResult>> backupCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Post("/certificates/restore")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateBundle>> restoreCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") CertificateRestoreParameters parameters);

        @Get("/deletedcertificates")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedCertificateListResult>> getDeletedCertificates(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("includePending") Boolean includePending, @QueryParam("api-version") String apiVersion);

        @Get("/deletedcertificates/{certificate-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedCertificateBundle>> getDeletedCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Delete("/deletedcertificates/{certificate-name}")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<Response<Void>> purgeDeletedCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Post("/deletedcertificates/{certificate-name}/recover")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateBundle>> recoverDeletedCertificate(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("certificate-name") String certificateName, @QueryParam("api-version") String apiVersion);

        @Get("/storage")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageListResult>> getStorageAccounts(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedstorage")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedStorageListResult>> getDeletedStorageAccounts(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedstorage/{storage-account-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedStorageBundle>> getDeletedStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion);

        @Delete("/deletedstorage/{storage-account-name}")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<Response<Void>> purgeDeletedStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion);

        @Post("/deletedstorage/{storage-account-name}/recover")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageBundle>> recoverDeletedStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion);

        @Post("/storage/{storage-account-name}/backup")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<BackupStorageResult>> backupStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion);

        @Post("/storage/restore")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageBundle>> restoreStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") StorageRestoreParameters parameters);

        @Delete("/storage/{storage-account-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedStorageBundle>> deleteStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion);

        @Get("/storage/{storage-account-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageBundle>> getStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion);

        @Put("/storage/{storage-account-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageBundle>> setStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") StorageAccountCreateParameters parameters);

        @Patch("/storage/{storage-account-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageBundle>> updateStorageAccount(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") StorageAccountUpdateParameters parameters);

        @Post("/storage/{storage-account-name}/regeneratekey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageBundle>> regenerateStorageAccountKey(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") StorageAccountRegenerteKeyParameters parameters);

        @Get("/storage/{storage-account-name}/sas")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SasDefinitionListResult>> getSasDefinitions(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedstorage/{storage-account-name}/sas")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSasDefinitionListResult>> getDeletedSasDefinitions(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @QueryParam("maxresults") Integer maxresults, @QueryParam("api-version") String apiVersion);

        @Get("/deletedstorage/{storage-account-name}/sas/{sas-definition-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSasDefinitionBundle>> getDeletedSasDefinition(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @PathParam("sas-definition-name") String sasDefinitionName, @QueryParam("api-version") String apiVersion);

        @Post("/deletedstorage/{storage-account-name}/sas/{sas-definition-name}/recover")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SasDefinitionBundle>> recoverDeletedSasDefinition(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @PathParam("sas-definition-name") String sasDefinitionName, @QueryParam("api-version") String apiVersion);

        @Delete("/storage/{storage-account-name}/sas/{sas-definition-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSasDefinitionBundle>> deleteSasDefinition(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @PathParam("sas-definition-name") String sasDefinitionName, @QueryParam("api-version") String apiVersion);

        @Get("/storage/{storage-account-name}/sas/{sas-definition-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SasDefinitionBundle>> getSasDefinition(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @PathParam("sas-definition-name") String sasDefinitionName, @QueryParam("api-version") String apiVersion);

        @Put("/storage/{storage-account-name}/sas/{sas-definition-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SasDefinitionBundle>> setSasDefinition(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @PathParam("sas-definition-name") String sasDefinitionName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") SasDefinitionCreateParameters parameters);

        @Patch("/storage/{storage-account-name}/sas/{sas-definition-name}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SasDefinitionBundle>> updateSasDefinition(@HostParam("vaultBaseUrl") String vaultBaseUrl, @PathParam("storage-account-name") String storageAccountName, @PathParam("sas-definition-name") String sasDefinitionName, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") SasDefinitionUpdateParameters parameters);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyListResult>> getKeyVersionsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<KeyListResult>> getKeysNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedKeyListResult>> getDeletedKeysNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretListResult>> getSecretsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SecretListResult>> getSecretVersionsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSecretListResult>> getDeletedSecretsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateListResult>> getCertificatesNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateIssuerListResult>> getCertificateIssuersNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<CertificateListResult>> getCertificateVersionsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedCertificateListResult>> getDeletedCertificatesNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<StorageListResult>> getStorageAccountsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedStorageListResult>> getDeletedStorageAccountsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<SasDefinitionListResult>> getSasDefinitionsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(KeyVaultErrorException.class)
        Mono<SimpleResponse<DeletedSasDefinitionListResult>> getDeletedSasDefinitionsNext(@PathParam(value = "nextLink", encoded = true) String nextLink);
    }

    /**
     * The create key operation can be used to create any key type in Azure Key Vault. If the named key already exists, Azure Key Vault creates a new version of the key. It requires the keys/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyBundle>> createKeyWithResponseAsync(String vaultBaseUrl, String keyName, KeyCreateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.createKey(vaultBaseUrl, keyName, this.getApiVersion(), parameters);
    }

    /**
     * The create key operation can be used to create any key type in Azure Key Vault. If the named key already exists, Azure Key Vault creates a new version of the key. It requires the keys/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyBundle> createKeyAsync(String vaultBaseUrl, String keyName, KeyCreateParameters parameters) {
        return createKeyWithResponseAsync(vaultBaseUrl, keyName, parameters)
            .flatMap((SimpleResponse<KeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The create key operation can be used to create any key type in Azure Key Vault. If the named key already exists, Azure Key Vault creates a new version of the key. It requires the keys/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyBundle createKey(String vaultBaseUrl, String keyName, KeyCreateParameters parameters) {
        return createKeyAsync(vaultBaseUrl, keyName, parameters).block();
    }

    /**
     * The import key operation may be used to import any key type into an Azure Key Vault. If the named key already exists, Azure Key Vault creates a new version of the key. This operation requires the keys/import permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key import parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyBundle>> importKeyWithResponseAsync(String vaultBaseUrl, String keyName, KeyImportParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.importKey(vaultBaseUrl, keyName, this.getApiVersion(), parameters);
    }

    /**
     * The import key operation may be used to import any key type into an Azure Key Vault. If the named key already exists, Azure Key Vault creates a new version of the key. This operation requires the keys/import permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key import parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyBundle> importKeyAsync(String vaultBaseUrl, String keyName, KeyImportParameters parameters) {
        return importKeyWithResponseAsync(vaultBaseUrl, keyName, parameters)
            .flatMap((SimpleResponse<KeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The import key operation may be used to import any key type into an Azure Key Vault. If the named key already exists, Azure Key Vault creates a new version of the key. This operation requires the keys/import permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key import parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyBundle importKey(String vaultBaseUrl, String keyName, KeyImportParameters parameters) {
        return importKeyAsync(vaultBaseUrl, keyName, parameters).block();
    }

    /**
     * The delete key operation cannot be used to remove individual versions of a key. This operation removes the cryptographic material associated with the key, which means the key is not usable for Sign/Verify, Wrap/Unwrap or Encrypt/Decrypt operations. This operation requires the keys/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedKeyBundle>> deleteKeyWithResponseAsync(String vaultBaseUrl, String keyName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        return service.deleteKey(vaultBaseUrl, keyName, this.getApiVersion());
    }

    /**
     * The delete key operation cannot be used to remove individual versions of a key. This operation removes the cryptographic material associated with the key, which means the key is not usable for Sign/Verify, Wrap/Unwrap or Encrypt/Decrypt operations. This operation requires the keys/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedKeyBundle> deleteKeyAsync(String vaultBaseUrl, String keyName) {
        return deleteKeyWithResponseAsync(vaultBaseUrl, keyName)
            .flatMap((SimpleResponse<DeletedKeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The delete key operation cannot be used to remove individual versions of a key. This operation removes the cryptographic material associated with the key, which means the key is not usable for Sign/Verify, Wrap/Unwrap or Encrypt/Decrypt operations. This operation requires the keys/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedKeyBundle deleteKey(String vaultBaseUrl, String keyName) {
        return deleteKeyAsync(vaultBaseUrl, keyName).block();
    }

    /**
     * In order to perform this operation, the key must already exist in the Key Vault. Note: The cryptographic material of a key itself cannot be changed. This operation requires the keys/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyBundle>> updateKeyWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyUpdateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.updateKey(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * In order to perform this operation, the key must already exist in the Key Vault. Note: The cryptographic material of a key itself cannot be changed. This operation requires the keys/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyBundle> updateKeyAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyUpdateParameters parameters) {
        return updateKeyWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * In order to perform this operation, the key must already exist in the Key Vault. Note: The cryptographic material of a key itself cannot be changed. This operation requires the keys/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyBundle updateKey(String vaultBaseUrl, String keyName, String keyVersion, KeyUpdateParameters parameters) {
        return updateKeyAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * The get key operation is applicable to all key types. If the requested key is symmetric, then no key material is released in the response. This operation requires the keys/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyBundle>> getKeyWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        return service.getKey(vaultBaseUrl, keyName, keyVersion, this.getApiVersion());
    }

    /**
     * The get key operation is applicable to all key types. If the requested key is symmetric, then no key material is released in the response. This operation requires the keys/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyBundle> getKeyAsync(String vaultBaseUrl, String keyName, String keyVersion) {
        return getKeyWithResponseAsync(vaultBaseUrl, keyName, keyVersion)
            .flatMap((SimpleResponse<KeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The get key operation is applicable to all key types. If the requested key is symmetric, then no key material is released in the response. This operation requires the keys/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyBundle getKey(String vaultBaseUrl, String keyName, String keyVersion) {
        return getKeyAsync(vaultBaseUrl, keyName, keyVersion).block();
    }

    /**
     * The full key identifier, attributes, and tags are provided in the response. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<KeyItem>> getKeyVersionsSinglePageAsync(String vaultBaseUrl, String keyName, Integer maxresults) {
        return service.getKeyVersions(vaultBaseUrl, keyName, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The full key identifier, attributes, and tags are provided in the response. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<KeyItem> getKeyVersionsAsync(String vaultBaseUrl, String keyName, Integer maxresults) {
        return new PagedFlux<>(
            () -> getKeyVersionsSinglePageAsync(vaultBaseUrl, keyName, maxresults),
            nextLink -> getKeyVersionsNextSinglePageAsync(nextLink));
    }

    /**
     * The full key identifier, attributes, and tags are provided in the response. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<KeyItem> getKeyVersions(String vaultBaseUrl, String keyName, Integer maxresults) {
        return new PagedIterable<>(getKeyVersionsAsync(vaultBaseUrl, keyName, maxresults));
    }

    /**
     * Retrieves a list of the keys in the Key Vault as JSON Web Key structures that contain the public part of a stored key. The LIST operation is applicable to all key types, however only the base key identifier, attributes, and tags are provided in the response. Individual versions of a key are not listed in the response. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<KeyItem>> getKeysSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getKeys(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Retrieves a list of the keys in the Key Vault as JSON Web Key structures that contain the public part of a stored key. The LIST operation is applicable to all key types, however only the base key identifier, attributes, and tags are provided in the response. Individual versions of a key are not listed in the response. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<KeyItem> getKeysAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getKeysSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getKeysNextSinglePageAsync(nextLink));
    }

    /**
     * Retrieves a list of the keys in the Key Vault as JSON Web Key structures that contain the public part of a stored key. The LIST operation is applicable to all key types, however only the base key identifier, attributes, and tags are provided in the response. Individual versions of a key are not listed in the response. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<KeyItem> getKeys(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getKeysAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The Key Backup operation exports a key from Azure Key Vault in a protected form. Note that this operation does NOT return key material in a form that can be used outside the Azure Key Vault system, the returned key material is either protected to a Azure Key Vault HSM or to Azure Key Vault itself. The intent of this operation is to allow a client to GENERATE a key in one Azure Key Vault instance, BACKUP the key, and then RESTORE it into another Azure Key Vault instance. The BACKUP operation may be used to export, in protected form, any key type from Azure Key Vault. Individual versions of a key cannot be backed up. BACKUP / RESTORE can be performed within geographical boundaries only; meaning that a BACKUP from one geographical area cannot be restored to another geographical area. For example, a backup from the US geographical area cannot be restored in an EU geographical area. This operation requires the key/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BackupKeyResult>> backupKeyWithResponseAsync(String vaultBaseUrl, String keyName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        return service.backupKey(vaultBaseUrl, keyName, this.getApiVersion());
    }

    /**
     * The Key Backup operation exports a key from Azure Key Vault in a protected form. Note that this operation does NOT return key material in a form that can be used outside the Azure Key Vault system, the returned key material is either protected to a Azure Key Vault HSM or to Azure Key Vault itself. The intent of this operation is to allow a client to GENERATE a key in one Azure Key Vault instance, BACKUP the key, and then RESTORE it into another Azure Key Vault instance. The BACKUP operation may be used to export, in protected form, any key type from Azure Key Vault. Individual versions of a key cannot be backed up. BACKUP / RESTORE can be performed within geographical boundaries only; meaning that a BACKUP from one geographical area cannot be restored to another geographical area. For example, a backup from the US geographical area cannot be restored in an EU geographical area. This operation requires the key/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BackupKeyResult> backupKeyAsync(String vaultBaseUrl, String keyName) {
        return backupKeyWithResponseAsync(vaultBaseUrl, keyName)
            .flatMap((SimpleResponse<BackupKeyResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The Key Backup operation exports a key from Azure Key Vault in a protected form. Note that this operation does NOT return key material in a form that can be used outside the Azure Key Vault system, the returned key material is either protected to a Azure Key Vault HSM or to Azure Key Vault itself. The intent of this operation is to allow a client to GENERATE a key in one Azure Key Vault instance, BACKUP the key, and then RESTORE it into another Azure Key Vault instance. The BACKUP operation may be used to export, in protected form, any key type from Azure Key Vault. Individual versions of a key cannot be backed up. BACKUP / RESTORE can be performed within geographical boundaries only; meaning that a BACKUP from one geographical area cannot be restored to another geographical area. For example, a backup from the US geographical area cannot be restored in an EU geographical area. This operation requires the key/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BackupKeyResult backupKey(String vaultBaseUrl, String keyName) {
        return backupKeyAsync(vaultBaseUrl, keyName).block();
    }

    /**
     * Imports a previously backed up key into Azure Key Vault, restoring the key, its key identifier, attributes and access control policies. The RESTORE operation may be used to import a previously backed up key. Individual versions of a key cannot be restored. The key is restored in its entirety with the same key name as it had when it was backed up. If the key name is not available in the target Key Vault, the RESTORE operation will be rejected. While the key name is retained during restore, the final key identifier will change if the key is restored to a different vault. Restore will restore all versions and preserve version identifiers. The RESTORE operation is subject to security constraints: The target Key Vault must be owned by the same Microsoft Azure Subscription as the source Key Vault The user must have RESTORE permission in the target Key Vault. This operation requires the keys/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The key restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyBundle>> restoreKeyWithResponseAsync(String vaultBaseUrl, KeyRestoreParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.restoreKey(vaultBaseUrl, this.getApiVersion(), parameters);
    }

    /**
     * Imports a previously backed up key into Azure Key Vault, restoring the key, its key identifier, attributes and access control policies. The RESTORE operation may be used to import a previously backed up key. Individual versions of a key cannot be restored. The key is restored in its entirety with the same key name as it had when it was backed up. If the key name is not available in the target Key Vault, the RESTORE operation will be rejected. While the key name is retained during restore, the final key identifier will change if the key is restored to a different vault. Restore will restore all versions and preserve version identifiers. The RESTORE operation is subject to security constraints: The target Key Vault must be owned by the same Microsoft Azure Subscription as the source Key Vault The user must have RESTORE permission in the target Key Vault. This operation requires the keys/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The key restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyBundle> restoreKeyAsync(String vaultBaseUrl, KeyRestoreParameters parameters) {
        return restoreKeyWithResponseAsync(vaultBaseUrl, parameters)
            .flatMap((SimpleResponse<KeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Imports a previously backed up key into Azure Key Vault, restoring the key, its key identifier, attributes and access control policies. The RESTORE operation may be used to import a previously backed up key. Individual versions of a key cannot be restored. The key is restored in its entirety with the same key name as it had when it was backed up. If the key name is not available in the target Key Vault, the RESTORE operation will be rejected. While the key name is retained during restore, the final key identifier will change if the key is restored to a different vault. Restore will restore all versions and preserve version identifiers. The RESTORE operation is subject to security constraints: The target Key Vault must be owned by the same Microsoft Azure Subscription as the source Key Vault The user must have RESTORE permission in the target Key Vault. This operation requires the keys/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The key restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyBundle restoreKey(String vaultBaseUrl, KeyRestoreParameters parameters) {
        return restoreKeyAsync(vaultBaseUrl, parameters).block();
    }

    /**
     * The ENCRYPT operation encrypts an arbitrary sequence of bytes using an encryption key that is stored in Azure Key Vault. Note that the ENCRYPT operation only supports a single block of data, the size of which is dependent on the target key and the encryption algorithm to be used. The ENCRYPT operation is only strictly necessary for symmetric keys stored in Azure Key Vault since protection with an asymmetric key can be performed using public portion of the key. This operation is supported for asymmetric keys as a convenience for callers that have a key-reference but do not have access to the public key material. This operation requires the keys/encrypt permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyOperationResult>> encryptWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.encrypt(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * The ENCRYPT operation encrypts an arbitrary sequence of bytes using an encryption key that is stored in Azure Key Vault. Note that the ENCRYPT operation only supports a single block of data, the size of which is dependent on the target key and the encryption algorithm to be used. The ENCRYPT operation is only strictly necessary for symmetric keys stored in Azure Key Vault since protection with an asymmetric key can be performed using public portion of the key. This operation is supported for asymmetric keys as a convenience for callers that have a key-reference but do not have access to the public key material. This operation requires the keys/encrypt permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyOperationResult> encryptAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return encryptWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyOperationResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The ENCRYPT operation encrypts an arbitrary sequence of bytes using an encryption key that is stored in Azure Key Vault. Note that the ENCRYPT operation only supports a single block of data, the size of which is dependent on the target key and the encryption algorithm to be used. The ENCRYPT operation is only strictly necessary for symmetric keys stored in Azure Key Vault since protection with an asymmetric key can be performed using public portion of the key. This operation is supported for asymmetric keys as a convenience for callers that have a key-reference but do not have access to the public key material. This operation requires the keys/encrypt permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyOperationResult encrypt(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return encryptAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * The DECRYPT operation decrypts a well-formed block of ciphertext using the target encryption key and specified algorithm. This operation is the reverse of the ENCRYPT operation; only a single block of data may be decrypted, the size of this block is dependent on the target key and the algorithm to be used. The DECRYPT operation applies to asymmetric and symmetric keys stored in Azure Key Vault since it uses the private portion of the key. This operation requires the keys/decrypt permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyOperationResult>> decryptWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.decrypt(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * The DECRYPT operation decrypts a well-formed block of ciphertext using the target encryption key and specified algorithm. This operation is the reverse of the ENCRYPT operation; only a single block of data may be decrypted, the size of this block is dependent on the target key and the algorithm to be used. The DECRYPT operation applies to asymmetric and symmetric keys stored in Azure Key Vault since it uses the private portion of the key. This operation requires the keys/decrypt permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyOperationResult> decryptAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return decryptWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyOperationResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The DECRYPT operation decrypts a well-formed block of ciphertext using the target encryption key and specified algorithm. This operation is the reverse of the ENCRYPT operation; only a single block of data may be decrypted, the size of this block is dependent on the target key and the algorithm to be used. The DECRYPT operation applies to asymmetric and symmetric keys stored in Azure Key Vault since it uses the private portion of the key. This operation requires the keys/decrypt permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyOperationResult decrypt(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return decryptAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * The SIGN operation is applicable to asymmetric and symmetric keys stored in Azure Key Vault since this operation uses the private portion of the key. This operation requires the keys/sign permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyOperationResult>> signWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeySignParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.sign(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * The SIGN operation is applicable to asymmetric and symmetric keys stored in Azure Key Vault since this operation uses the private portion of the key. This operation requires the keys/sign permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyOperationResult> signAsync(String vaultBaseUrl, String keyName, String keyVersion, KeySignParameters parameters) {
        return signWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyOperationResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The SIGN operation is applicable to asymmetric and symmetric keys stored in Azure Key Vault since this operation uses the private portion of the key. This operation requires the keys/sign permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyOperationResult sign(String vaultBaseUrl, String keyName, String keyVersion, KeySignParameters parameters) {
        return signAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * The VERIFY operation is applicable to symmetric keys stored in Azure Key Vault. VERIFY is not strictly necessary for asymmetric keys stored in Azure Key Vault since signature verification can be performed using the public portion of the key but this operation is supported as a convenience for callers that only have a key-reference and not the public portion of the key. This operation requires the keys/verify permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key verify parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyVerifyResult>> verifyWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyVerifyParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.verify(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * The VERIFY operation is applicable to symmetric keys stored in Azure Key Vault. VERIFY is not strictly necessary for asymmetric keys stored in Azure Key Vault since signature verification can be performed using the public portion of the key but this operation is supported as a convenience for callers that only have a key-reference and not the public portion of the key. This operation requires the keys/verify permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key verify parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyVerifyResult> verifyAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyVerifyParameters parameters) {
        return verifyWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyVerifyResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The VERIFY operation is applicable to symmetric keys stored in Azure Key Vault. VERIFY is not strictly necessary for asymmetric keys stored in Azure Key Vault since signature verification can be performed using the public portion of the key but this operation is supported as a convenience for callers that only have a key-reference and not the public portion of the key. This operation requires the keys/verify permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key verify parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyVerifyResult verify(String vaultBaseUrl, String keyName, String keyVersion, KeyVerifyParameters parameters) {
        return verifyAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * The WRAP operation supports encryption of a symmetric key using a key encryption key that has previously been stored in an Azure Key Vault. The WRAP operation is only strictly necessary for symmetric keys stored in Azure Key Vault since protection with an asymmetric key can be performed using the public portion of the key. This operation is supported for asymmetric keys as a convenience for callers that have a key-reference but do not have access to the public key material. This operation requires the keys/wrapKey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyOperationResult>> wrapKeyWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.wrapKey(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * The WRAP operation supports encryption of a symmetric key using a key encryption key that has previously been stored in an Azure Key Vault. The WRAP operation is only strictly necessary for symmetric keys stored in Azure Key Vault since protection with an asymmetric key can be performed using the public portion of the key. This operation is supported for asymmetric keys as a convenience for callers that have a key-reference but do not have access to the public key material. This operation requires the keys/wrapKey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyOperationResult> wrapKeyAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return wrapKeyWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyOperationResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The WRAP operation supports encryption of a symmetric key using a key encryption key that has previously been stored in an Azure Key Vault. The WRAP operation is only strictly necessary for symmetric keys stored in Azure Key Vault since protection with an asymmetric key can be performed using the public portion of the key. This operation is supported for asymmetric keys as a convenience for callers that have a key-reference but do not have access to the public key material. This operation requires the keys/wrapKey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyOperationResult wrapKey(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return wrapKeyAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * The UNWRAP operation supports decryption of a symmetric key using the target key encryption key. This operation is the reverse of the WRAP operation. The UNWRAP operation applies to asymmetric and symmetric keys stored in Azure Key Vault since it uses the private portion of the key. This operation requires the keys/unwrapKey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyOperationResult>> unwrapKeyWithResponseAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (keyVersion == null) {
            throw new IllegalArgumentException("Parameter keyVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.unwrapKey(vaultBaseUrl, keyName, keyVersion, this.getApiVersion(), parameters);
    }

    /**
     * The UNWRAP operation supports decryption of a symmetric key using the target key encryption key. This operation is the reverse of the WRAP operation. The UNWRAP operation applies to asymmetric and symmetric keys stored in Azure Key Vault since it uses the private portion of the key. This operation requires the keys/unwrapKey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyOperationResult> unwrapKeyAsync(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return unwrapKeyWithResponseAsync(vaultBaseUrl, keyName, keyVersion, parameters)
            .flatMap((SimpleResponse<KeyOperationResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The UNWRAP operation supports decryption of a symmetric key using the target key encryption key. This operation is the reverse of the WRAP operation. The UNWRAP operation applies to asymmetric and symmetric keys stored in Azure Key Vault since it uses the private portion of the key. This operation requires the keys/unwrapKey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param keyVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The key operations parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyOperationResult unwrapKey(String vaultBaseUrl, String keyName, String keyVersion, KeyOperationsParameters parameters) {
        return unwrapKeyAsync(vaultBaseUrl, keyName, keyVersion, parameters).block();
    }

    /**
     * Retrieves a list of the keys in the Key Vault as JSON Web Key structures that contain the public part of a deleted key. This operation includes deletion-specific information. The Get Deleted Keys operation is applicable for vaults enabled for soft-delete. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedKeyItem>> getDeletedKeysSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getDeletedKeys(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Retrieves a list of the keys in the Key Vault as JSON Web Key structures that contain the public part of a deleted key. This operation includes deletion-specific information. The Get Deleted Keys operation is applicable for vaults enabled for soft-delete. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DeletedKeyItem> getDeletedKeysAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getDeletedKeysSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getDeletedKeysNextSinglePageAsync(nextLink));
    }

    /**
     * Retrieves a list of the keys in the Key Vault as JSON Web Key structures that contain the public part of a deleted key. This operation includes deletion-specific information. The Get Deleted Keys operation is applicable for vaults enabled for soft-delete. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DeletedKeyItem> getDeletedKeys(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getDeletedKeysAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The Get Deleted Key operation is applicable for soft-delete enabled vaults. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedKeyBundle>> getDeletedKeyWithResponseAsync(String vaultBaseUrl, String keyName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        return service.getDeletedKey(vaultBaseUrl, keyName, this.getApiVersion());
    }

    /**
     * The Get Deleted Key operation is applicable for soft-delete enabled vaults. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedKeyBundle> getDeletedKeyAsync(String vaultBaseUrl, String keyName) {
        return getDeletedKeyWithResponseAsync(vaultBaseUrl, keyName)
            .flatMap((SimpleResponse<DeletedKeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The Get Deleted Key operation is applicable for soft-delete enabled vaults. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedKeyBundle getDeletedKey(String vaultBaseUrl, String keyName) {
        return getDeletedKeyAsync(vaultBaseUrl, keyName).block();
    }

    /**
     * The Purge Deleted Key operation is applicable for soft-delete enabled vaults. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> purgeDeletedKeyWithResponseAsync(String vaultBaseUrl, String keyName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        return service.purgeDeletedKey(vaultBaseUrl, keyName, this.getApiVersion());
    }

    /**
     * The Purge Deleted Key operation is applicable for soft-delete enabled vaults. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> purgeDeletedKeyAsync(String vaultBaseUrl, String keyName) {
        return purgeDeletedKeyWithResponseAsync(vaultBaseUrl, keyName)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * The Purge Deleted Key operation is applicable for soft-delete enabled vaults. While the operation can be invoked on any vault, it will return an error if invoked on a non soft-delete enabled vault. This operation requires the keys/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void purgeDeletedKey(String vaultBaseUrl, String keyName) {
        purgeDeletedKeyAsync(vaultBaseUrl, keyName).block();
    }

    /**
     * The Recover Deleted Key operation is applicable for deleted keys in soft-delete enabled vaults. It recovers the deleted key back to its latest version under /keys. An attempt to recover an non-deleted key will return an error. Consider this the inverse of the delete operation on soft-delete enabled vaults. This operation requires the keys/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyBundle>> recoverDeletedKeyWithResponseAsync(String vaultBaseUrl, String keyName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        return service.recoverDeletedKey(vaultBaseUrl, keyName, this.getApiVersion());
    }

    /**
     * The Recover Deleted Key operation is applicable for deleted keys in soft-delete enabled vaults. It recovers the deleted key back to its latest version under /keys. An attempt to recover an non-deleted key will return an error. Consider this the inverse of the delete operation on soft-delete enabled vaults. This operation requires the keys/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyBundle> recoverDeletedKeyAsync(String vaultBaseUrl, String keyName) {
        return recoverDeletedKeyWithResponseAsync(vaultBaseUrl, keyName)
            .flatMap((SimpleResponse<KeyBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The Recover Deleted Key operation is applicable for deleted keys in soft-delete enabled vaults. It recovers the deleted key back to its latest version under /keys. An attempt to recover an non-deleted key will return an error. Consider this the inverse of the delete operation on soft-delete enabled vaults. This operation requires the keys/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param keyName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyBundle recoverDeletedKey(String vaultBaseUrl, String keyName) {
        return recoverDeletedKeyAsync(vaultBaseUrl, keyName).block();
    }

    /**
     * The SET operation adds a secret to the Azure Key Vault. If the named secret already exists, Azure Key Vault creates a new version of that secret. This operation requires the secrets/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The secret set parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SecretBundle>> setSecretWithResponseAsync(String vaultBaseUrl, String secretName, SecretSetParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.setSecret(vaultBaseUrl, secretName, this.getApiVersion(), parameters);
    }

    /**
     * The SET operation adds a secret to the Azure Key Vault. If the named secret already exists, Azure Key Vault creates a new version of that secret. This operation requires the secrets/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The secret set parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SecretBundle> setSecretAsync(String vaultBaseUrl, String secretName, SecretSetParameters parameters) {
        return setSecretWithResponseAsync(vaultBaseUrl, secretName, parameters)
            .flatMap((SimpleResponse<SecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The SET operation adds a secret to the Azure Key Vault. If the named secret already exists, Azure Key Vault creates a new version of that secret. This operation requires the secrets/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The secret set parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SecretBundle setSecret(String vaultBaseUrl, String secretName, SecretSetParameters parameters) {
        return setSecretAsync(vaultBaseUrl, secretName, parameters).block();
    }

    /**
     * The DELETE operation applies to any secret stored in Azure Key Vault. DELETE cannot be applied to an individual version of a secret. This operation requires the secrets/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedSecretBundle>> deleteSecretWithResponseAsync(String vaultBaseUrl, String secretName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        return service.deleteSecret(vaultBaseUrl, secretName, this.getApiVersion());
    }

    /**
     * The DELETE operation applies to any secret stored in Azure Key Vault. DELETE cannot be applied to an individual version of a secret. This operation requires the secrets/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedSecretBundle> deleteSecretAsync(String vaultBaseUrl, String secretName) {
        return deleteSecretWithResponseAsync(vaultBaseUrl, secretName)
            .flatMap((SimpleResponse<DeletedSecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The DELETE operation applies to any secret stored in Azure Key Vault. DELETE cannot be applied to an individual version of a secret. This operation requires the secrets/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedSecretBundle deleteSecret(String vaultBaseUrl, String secretName) {
        return deleteSecretAsync(vaultBaseUrl, secretName).block();
    }

    /**
     * The UPDATE operation changes specified attributes of an existing stored secret. Attributes that are not specified in the request are left unchanged. The value of a secret itself cannot be changed. This operation requires the secrets/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param secretVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The secret update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SecretBundle>> updateSecretWithResponseAsync(String vaultBaseUrl, String secretName, String secretVersion, SecretUpdateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        if (secretVersion == null) {
            throw new IllegalArgumentException("Parameter secretVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.updateSecret(vaultBaseUrl, secretName, secretVersion, this.getApiVersion(), parameters);
    }

    /**
     * The UPDATE operation changes specified attributes of an existing stored secret. Attributes that are not specified in the request are left unchanged. The value of a secret itself cannot be changed. This operation requires the secrets/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param secretVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The secret update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SecretBundle> updateSecretAsync(String vaultBaseUrl, String secretName, String secretVersion, SecretUpdateParameters parameters) {
        return updateSecretWithResponseAsync(vaultBaseUrl, secretName, secretVersion, parameters)
            .flatMap((SimpleResponse<SecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The UPDATE operation changes specified attributes of an existing stored secret. Attributes that are not specified in the request are left unchanged. The value of a secret itself cannot be changed. This operation requires the secrets/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param secretVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The secret update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SecretBundle updateSecret(String vaultBaseUrl, String secretName, String secretVersion, SecretUpdateParameters parameters) {
        return updateSecretAsync(vaultBaseUrl, secretName, secretVersion, parameters).block();
    }

    /**
     * The GET operation is applicable to any secret stored in Azure Key Vault. This operation requires the secrets/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param secretVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SecretBundle>> getSecretWithResponseAsync(String vaultBaseUrl, String secretName, String secretVersion) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        if (secretVersion == null) {
            throw new IllegalArgumentException("Parameter secretVersion is required and cannot be null.");
        }
        return service.getSecret(vaultBaseUrl, secretName, secretVersion, this.getApiVersion());
    }

    /**
     * The GET operation is applicable to any secret stored in Azure Key Vault. This operation requires the secrets/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param secretVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SecretBundle> getSecretAsync(String vaultBaseUrl, String secretName, String secretVersion) {
        return getSecretWithResponseAsync(vaultBaseUrl, secretName, secretVersion)
            .flatMap((SimpleResponse<SecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The GET operation is applicable to any secret stored in Azure Key Vault. This operation requires the secrets/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param secretVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SecretBundle getSecret(String vaultBaseUrl, String secretName, String secretVersion) {
        return getSecretAsync(vaultBaseUrl, secretName, secretVersion).block();
    }

    /**
     * The Get Secrets operation is applicable to the entire vault. However, only the base secret identifier and its attributes are provided in the response. Individual secret versions are not listed in the response. This operation requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<SecretItem>> getSecretsSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getSecrets(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The Get Secrets operation is applicable to the entire vault. However, only the base secret identifier and its attributes are provided in the response. Individual secret versions are not listed in the response. This operation requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<SecretItem> getSecretsAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getSecretsSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getSecretsNextSinglePageAsync(nextLink));
    }

    /**
     * The Get Secrets operation is applicable to the entire vault. However, only the base secret identifier and its attributes are provided in the response. Individual secret versions are not listed in the response. This operation requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<SecretItem> getSecrets(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getSecretsAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The full secret identifier and attributes are provided in the response. No values are returned for the secrets. This operations requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<SecretItem>> getSecretVersionsSinglePageAsync(String vaultBaseUrl, String secretName, Integer maxresults) {
        return service.getSecretVersions(vaultBaseUrl, secretName, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The full secret identifier and attributes are provided in the response. No values are returned for the secrets. This operations requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<SecretItem> getSecretVersionsAsync(String vaultBaseUrl, String secretName, Integer maxresults) {
        return new PagedFlux<>(
            () -> getSecretVersionsSinglePageAsync(vaultBaseUrl, secretName, maxresults),
            nextLink -> getSecretVersionsNextSinglePageAsync(nextLink));
    }

    /**
     * The full secret identifier and attributes are provided in the response. No values are returned for the secrets. This operations requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<SecretItem> getSecretVersions(String vaultBaseUrl, String secretName, Integer maxresults) {
        return new PagedIterable<>(getSecretVersionsAsync(vaultBaseUrl, secretName, maxresults));
    }

    /**
     * The Get Deleted Secrets operation returns the secrets that have been deleted for a vault enabled for soft-delete. This operation requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedSecretItem>> getDeletedSecretsSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getDeletedSecrets(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The Get Deleted Secrets operation returns the secrets that have been deleted for a vault enabled for soft-delete. This operation requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DeletedSecretItem> getDeletedSecretsAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getDeletedSecretsSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getDeletedSecretsNextSinglePageAsync(nextLink));
    }

    /**
     * The Get Deleted Secrets operation returns the secrets that have been deleted for a vault enabled for soft-delete. This operation requires the secrets/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DeletedSecretItem> getDeletedSecrets(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getDeletedSecretsAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The Get Deleted Secret operation returns the specified deleted secret along with its attributes. This operation requires the secrets/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedSecretBundle>> getDeletedSecretWithResponseAsync(String vaultBaseUrl, String secretName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        return service.getDeletedSecret(vaultBaseUrl, secretName, this.getApiVersion());
    }

    /**
     * The Get Deleted Secret operation returns the specified deleted secret along with its attributes. This operation requires the secrets/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedSecretBundle> getDeletedSecretAsync(String vaultBaseUrl, String secretName) {
        return getDeletedSecretWithResponseAsync(vaultBaseUrl, secretName)
            .flatMap((SimpleResponse<DeletedSecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The Get Deleted Secret operation returns the specified deleted secret along with its attributes. This operation requires the secrets/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedSecretBundle getDeletedSecret(String vaultBaseUrl, String secretName) {
        return getDeletedSecretAsync(vaultBaseUrl, secretName).block();
    }

    /**
     * The purge deleted secret operation removes the secret permanently, without the possibility of recovery. This operation can only be enabled on a soft-delete enabled vault. This operation requires the secrets/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> purgeDeletedSecretWithResponseAsync(String vaultBaseUrl, String secretName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        return service.purgeDeletedSecret(vaultBaseUrl, secretName, this.getApiVersion());
    }

    /**
     * The purge deleted secret operation removes the secret permanently, without the possibility of recovery. This operation can only be enabled on a soft-delete enabled vault. This operation requires the secrets/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> purgeDeletedSecretAsync(String vaultBaseUrl, String secretName) {
        return purgeDeletedSecretWithResponseAsync(vaultBaseUrl, secretName)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * The purge deleted secret operation removes the secret permanently, without the possibility of recovery. This operation can only be enabled on a soft-delete enabled vault. This operation requires the secrets/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void purgeDeletedSecret(String vaultBaseUrl, String secretName) {
        purgeDeletedSecretAsync(vaultBaseUrl, secretName).block();
    }

    /**
     * Recovers the deleted secret in the specified vault. This operation can only be performed on a soft-delete enabled vault. This operation requires the secrets/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SecretBundle>> recoverDeletedSecretWithResponseAsync(String vaultBaseUrl, String secretName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        return service.recoverDeletedSecret(vaultBaseUrl, secretName, this.getApiVersion());
    }

    /**
     * Recovers the deleted secret in the specified vault. This operation can only be performed on a soft-delete enabled vault. This operation requires the secrets/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SecretBundle> recoverDeletedSecretAsync(String vaultBaseUrl, String secretName) {
        return recoverDeletedSecretWithResponseAsync(vaultBaseUrl, secretName)
            .flatMap((SimpleResponse<SecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Recovers the deleted secret in the specified vault. This operation can only be performed on a soft-delete enabled vault. This operation requires the secrets/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SecretBundle recoverDeletedSecret(String vaultBaseUrl, String secretName) {
        return recoverDeletedSecretAsync(vaultBaseUrl, secretName).block();
    }

    /**
     * Requests that a backup of the specified secret be downloaded to the client. All versions of the secret will be downloaded. This operation requires the secrets/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BackupSecretResult>> backupSecretWithResponseAsync(String vaultBaseUrl, String secretName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (secretName == null) {
            throw new IllegalArgumentException("Parameter secretName is required and cannot be null.");
        }
        return service.backupSecret(vaultBaseUrl, secretName, this.getApiVersion());
    }

    /**
     * Requests that a backup of the specified secret be downloaded to the client. All versions of the secret will be downloaded. This operation requires the secrets/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BackupSecretResult> backupSecretAsync(String vaultBaseUrl, String secretName) {
        return backupSecretWithResponseAsync(vaultBaseUrl, secretName)
            .flatMap((SimpleResponse<BackupSecretResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Requests that a backup of the specified secret be downloaded to the client. All versions of the secret will be downloaded. This operation requires the secrets/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param secretName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BackupSecretResult backupSecret(String vaultBaseUrl, String secretName) {
        return backupSecretAsync(vaultBaseUrl, secretName).block();
    }

    /**
     * Restores a backed up secret, and all its versions, to a vault. This operation requires the secrets/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The secret restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SecretBundle>> restoreSecretWithResponseAsync(String vaultBaseUrl, SecretRestoreParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.restoreSecret(vaultBaseUrl, this.getApiVersion(), parameters);
    }

    /**
     * Restores a backed up secret, and all its versions, to a vault. This operation requires the secrets/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The secret restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SecretBundle> restoreSecretAsync(String vaultBaseUrl, SecretRestoreParameters parameters) {
        return restoreSecretWithResponseAsync(vaultBaseUrl, parameters)
            .flatMap((SimpleResponse<SecretBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Restores a backed up secret, and all its versions, to a vault. This operation requires the secrets/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The secret restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SecretBundle restoreSecret(String vaultBaseUrl, SecretRestoreParameters parameters) {
        return restoreSecretAsync(vaultBaseUrl, parameters).block();
    }

    /**
     * The GetCertificates operation returns the set of certificates resources in the specified key vault. This operation requires the certificates/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @param includePending MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<CertificateItem>> getCertificatesSinglePageAsync(String vaultBaseUrl, Integer maxresults, Boolean includePending) {
        return service.getCertificates(vaultBaseUrl, maxresults, includePending, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The GetCertificates operation returns the set of certificates resources in the specified key vault. This operation requires the certificates/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @param includePending MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<CertificateItem> getCertificatesAsync(String vaultBaseUrl, Integer maxresults, Boolean includePending) {
        return new PagedFlux<>(
            () -> getCertificatesSinglePageAsync(vaultBaseUrl, maxresults, includePending),
            nextLink -> getCertificatesNextSinglePageAsync(nextLink));
    }

    /**
     * The GetCertificates operation returns the set of certificates resources in the specified key vault. This operation requires the certificates/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @param includePending MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<CertificateItem> getCertificates(String vaultBaseUrl, Integer maxresults, Boolean includePending) {
        return new PagedIterable<>(getCertificatesAsync(vaultBaseUrl, maxresults, includePending));
    }

    /**
     * Deletes all versions of a certificate object along with its associated policy. Delete certificate cannot be used to remove individual versions of a certificate object. This operation requires the certificates/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedCertificateBundle>> deleteCertificateWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.deleteCertificate(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * Deletes all versions of a certificate object along with its associated policy. Delete certificate cannot be used to remove individual versions of a certificate object. This operation requires the certificates/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedCertificateBundle> deleteCertificateAsync(String vaultBaseUrl, String certificateName) {
        return deleteCertificateWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<DeletedCertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Deletes all versions of a certificate object along with its associated policy. Delete certificate cannot be used to remove individual versions of a certificate object. This operation requires the certificates/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedCertificateBundle deleteCertificate(String vaultBaseUrl, String certificateName) {
        return deleteCertificateAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * Sets the certificate contacts for the specified key vault. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param contacts The contacts for the vault certificates.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Contacts>> setCertificateContactsWithResponseAsync(String vaultBaseUrl, Contacts contacts) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (contacts == null) {
            throw new IllegalArgumentException("Parameter contacts is required and cannot be null.");
        } else {
            contacts.validate();
        }
        return service.setCertificateContacts(vaultBaseUrl, this.getApiVersion(), contacts);
    }

    /**
     * Sets the certificate contacts for the specified key vault. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param contacts The contacts for the vault certificates.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Contacts> setCertificateContactsAsync(String vaultBaseUrl, Contacts contacts) {
        return setCertificateContactsWithResponseAsync(vaultBaseUrl, contacts)
            .flatMap((SimpleResponse<Contacts> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Sets the certificate contacts for the specified key vault. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param contacts The contacts for the vault certificates.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Contacts setCertificateContacts(String vaultBaseUrl, Contacts contacts) {
        return setCertificateContactsAsync(vaultBaseUrl, contacts).block();
    }

    /**
     * The GetCertificateContacts operation returns the set of certificate contact resources in the specified key vault. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Contacts>> getCertificateContactsWithResponseAsync(String vaultBaseUrl) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        return service.getCertificateContacts(vaultBaseUrl, this.getApiVersion());
    }

    /**
     * The GetCertificateContacts operation returns the set of certificate contact resources in the specified key vault. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Contacts> getCertificateContactsAsync(String vaultBaseUrl) {
        return getCertificateContactsWithResponseAsync(vaultBaseUrl)
            .flatMap((SimpleResponse<Contacts> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The GetCertificateContacts operation returns the set of certificate contact resources in the specified key vault. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Contacts getCertificateContacts(String vaultBaseUrl) {
        return getCertificateContactsAsync(vaultBaseUrl).block();
    }

    /**
     * Deletes the certificate contacts for a specified key vault certificate. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Contacts>> deleteCertificateContactsWithResponseAsync(String vaultBaseUrl) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        return service.deleteCertificateContacts(vaultBaseUrl, this.getApiVersion());
    }

    /**
     * Deletes the certificate contacts for a specified key vault certificate. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Contacts> deleteCertificateContactsAsync(String vaultBaseUrl) {
        return deleteCertificateContactsWithResponseAsync(vaultBaseUrl)
            .flatMap((SimpleResponse<Contacts> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Deletes the certificate contacts for a specified key vault certificate. This operation requires the certificates/managecontacts permission.
     * 
     * @param vaultBaseUrl simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Contacts deleteCertificateContacts(String vaultBaseUrl) {
        return deleteCertificateContactsAsync(vaultBaseUrl).block();
    }

    /**
     * The GetCertificateIssuers operation returns the set of certificate issuer resources in the specified key vault. This operation requires the certificates/manageissuers/getissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<CertificateIssuerItem>> getCertificateIssuersSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getCertificateIssuers(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The GetCertificateIssuers operation returns the set of certificate issuer resources in the specified key vault. This operation requires the certificates/manageissuers/getissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<CertificateIssuerItem> getCertificateIssuersAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getCertificateIssuersSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getCertificateIssuersNextSinglePageAsync(nextLink));
    }

    /**
     * The GetCertificateIssuers operation returns the set of certificate issuer resources in the specified key vault. This operation requires the certificates/manageissuers/getissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<CertificateIssuerItem> getCertificateIssuers(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getCertificateIssuersAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The SetCertificateIssuer operation adds or updates the specified certificate issuer. This operation requires the certificates/setissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameter The certificate issuer set parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IssuerBundle>> setCertificateIssuerWithResponseAsync(String vaultBaseUrl, String issuerName, CertificateIssuerSetParameters parameter) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (issuerName == null) {
            throw new IllegalArgumentException("Parameter issuerName is required and cannot be null.");
        }
        if (parameter == null) {
            throw new IllegalArgumentException("Parameter parameter is required and cannot be null.");
        } else {
            parameter.validate();
        }
        return service.setCertificateIssuer(vaultBaseUrl, issuerName, this.getApiVersion(), parameter);
    }

    /**
     * The SetCertificateIssuer operation adds or updates the specified certificate issuer. This operation requires the certificates/setissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameter The certificate issuer set parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IssuerBundle> setCertificateIssuerAsync(String vaultBaseUrl, String issuerName, CertificateIssuerSetParameters parameter) {
        return setCertificateIssuerWithResponseAsync(vaultBaseUrl, issuerName, parameter)
            .flatMap((SimpleResponse<IssuerBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The SetCertificateIssuer operation adds or updates the specified certificate issuer. This operation requires the certificates/setissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameter The certificate issuer set parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IssuerBundle setCertificateIssuer(String vaultBaseUrl, String issuerName, CertificateIssuerSetParameters parameter) {
        return setCertificateIssuerAsync(vaultBaseUrl, issuerName, parameter).block();
    }

    /**
     * The UpdateCertificateIssuer operation performs an update on the specified certificate issuer entity. This operation requires the certificates/setissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameter The certificate issuer update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IssuerBundle>> updateCertificateIssuerWithResponseAsync(String vaultBaseUrl, String issuerName, CertificateIssuerUpdateParameters parameter) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (issuerName == null) {
            throw new IllegalArgumentException("Parameter issuerName is required and cannot be null.");
        }
        if (parameter == null) {
            throw new IllegalArgumentException("Parameter parameter is required and cannot be null.");
        } else {
            parameter.validate();
        }
        return service.updateCertificateIssuer(vaultBaseUrl, issuerName, this.getApiVersion(), parameter);
    }

    /**
     * The UpdateCertificateIssuer operation performs an update on the specified certificate issuer entity. This operation requires the certificates/setissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameter The certificate issuer update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IssuerBundle> updateCertificateIssuerAsync(String vaultBaseUrl, String issuerName, CertificateIssuerUpdateParameters parameter) {
        return updateCertificateIssuerWithResponseAsync(vaultBaseUrl, issuerName, parameter)
            .flatMap((SimpleResponse<IssuerBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The UpdateCertificateIssuer operation performs an update on the specified certificate issuer entity. This operation requires the certificates/setissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameter The certificate issuer update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IssuerBundle updateCertificateIssuer(String vaultBaseUrl, String issuerName, CertificateIssuerUpdateParameters parameter) {
        return updateCertificateIssuerAsync(vaultBaseUrl, issuerName, parameter).block();
    }

    /**
     * The GetCertificateIssuer operation returns the specified certificate issuer resources in the specified key vault. This operation requires the certificates/manageissuers/getissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IssuerBundle>> getCertificateIssuerWithResponseAsync(String vaultBaseUrl, String issuerName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (issuerName == null) {
            throw new IllegalArgumentException("Parameter issuerName is required and cannot be null.");
        }
        return service.getCertificateIssuer(vaultBaseUrl, issuerName, this.getApiVersion());
    }

    /**
     * The GetCertificateIssuer operation returns the specified certificate issuer resources in the specified key vault. This operation requires the certificates/manageissuers/getissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IssuerBundle> getCertificateIssuerAsync(String vaultBaseUrl, String issuerName) {
        return getCertificateIssuerWithResponseAsync(vaultBaseUrl, issuerName)
            .flatMap((SimpleResponse<IssuerBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The GetCertificateIssuer operation returns the specified certificate issuer resources in the specified key vault. This operation requires the certificates/manageissuers/getissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IssuerBundle getCertificateIssuer(String vaultBaseUrl, String issuerName) {
        return getCertificateIssuerAsync(vaultBaseUrl, issuerName).block();
    }

    /**
     * The DeleteCertificateIssuer operation permanently removes the specified certificate issuer from the vault. This operation requires the certificates/manageissuers/deleteissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IssuerBundle>> deleteCertificateIssuerWithResponseAsync(String vaultBaseUrl, String issuerName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (issuerName == null) {
            throw new IllegalArgumentException("Parameter issuerName is required and cannot be null.");
        }
        return service.deleteCertificateIssuer(vaultBaseUrl, issuerName, this.getApiVersion());
    }

    /**
     * The DeleteCertificateIssuer operation permanently removes the specified certificate issuer from the vault. This operation requires the certificates/manageissuers/deleteissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IssuerBundle> deleteCertificateIssuerAsync(String vaultBaseUrl, String issuerName) {
        return deleteCertificateIssuerWithResponseAsync(vaultBaseUrl, issuerName)
            .flatMap((SimpleResponse<IssuerBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The DeleteCertificateIssuer operation permanently removes the specified certificate issuer from the vault. This operation requires the certificates/manageissuers/deleteissuers permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param issuerName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IssuerBundle deleteCertificateIssuer(String vaultBaseUrl, String issuerName) {
        return deleteCertificateIssuerAsync(vaultBaseUrl, issuerName).block();
    }

    /**
     * If this is the first version, the certificate resource is created. This operation requires the certificates/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateOperation>> createCertificateWithResponseAsync(String vaultBaseUrl, String certificateName, CertificateCreateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.createCertificate(vaultBaseUrl, certificateName, this.getApiVersion(), parameters);
    }

    /**
     * If this is the first version, the certificate resource is created. This operation requires the certificates/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateOperation> createCertificateAsync(String vaultBaseUrl, String certificateName, CertificateCreateParameters parameters) {
        return createCertificateWithResponseAsync(vaultBaseUrl, certificateName, parameters)
            .flatMap((SimpleResponse<CertificateOperation> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * If this is the first version, the certificate resource is created. This operation requires the certificates/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateOperation createCertificate(String vaultBaseUrl, String certificateName, CertificateCreateParameters parameters) {
        return createCertificateAsync(vaultBaseUrl, certificateName, parameters).block();
    }

    /**
     * Imports an existing valid certificate, containing a private key, into Azure Key Vault. The certificate to be imported can be in either PFX or PEM format. If the certificate is in PEM format the PEM file must contain the key as well as x509 certificates. This operation requires the certificates/import permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate import parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateBundle>> importCertificateWithResponseAsync(String vaultBaseUrl, String certificateName, CertificateImportParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.importCertificate(vaultBaseUrl, certificateName, this.getApiVersion(), parameters);
    }

    /**
     * Imports an existing valid certificate, containing a private key, into Azure Key Vault. The certificate to be imported can be in either PFX or PEM format. If the certificate is in PEM format the PEM file must contain the key as well as x509 certificates. This operation requires the certificates/import permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate import parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateBundle> importCertificateAsync(String vaultBaseUrl, String certificateName, CertificateImportParameters parameters) {
        return importCertificateWithResponseAsync(vaultBaseUrl, certificateName, parameters)
            .flatMap((SimpleResponse<CertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Imports an existing valid certificate, containing a private key, into Azure Key Vault. The certificate to be imported can be in either PFX or PEM format. If the certificate is in PEM format the PEM file must contain the key as well as x509 certificates. This operation requires the certificates/import permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate import parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateBundle importCertificate(String vaultBaseUrl, String certificateName, CertificateImportParameters parameters) {
        return importCertificateAsync(vaultBaseUrl, certificateName, parameters).block();
    }

    /**
     * The GetCertificateVersions operation returns the versions of a certificate in the specified key vault. This operation requires the certificates/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<CertificateItem>> getCertificateVersionsSinglePageAsync(String vaultBaseUrl, String certificateName, Integer maxresults) {
        return service.getCertificateVersions(vaultBaseUrl, certificateName, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The GetCertificateVersions operation returns the versions of a certificate in the specified key vault. This operation requires the certificates/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<CertificateItem> getCertificateVersionsAsync(String vaultBaseUrl, String certificateName, Integer maxresults) {
        return new PagedFlux<>(
            () -> getCertificateVersionsSinglePageAsync(vaultBaseUrl, certificateName, maxresults),
            nextLink -> getCertificateVersionsNextSinglePageAsync(nextLink));
    }

    /**
     * The GetCertificateVersions operation returns the versions of a certificate in the specified key vault. This operation requires the certificates/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<CertificateItem> getCertificateVersions(String vaultBaseUrl, String certificateName, Integer maxresults) {
        return new PagedIterable<>(getCertificateVersionsAsync(vaultBaseUrl, certificateName, maxresults));
    }

    /**
     * The GetCertificatePolicy operation returns the specified certificate policy resources in the specified key vault. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificatePolicy>> getCertificatePolicyWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.getCertificatePolicy(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * The GetCertificatePolicy operation returns the specified certificate policy resources in the specified key vault. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificatePolicy> getCertificatePolicyAsync(String vaultBaseUrl, String certificateName) {
        return getCertificatePolicyWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<CertificatePolicy> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The GetCertificatePolicy operation returns the specified certificate policy resources in the specified key vault. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificatePolicy getCertificatePolicy(String vaultBaseUrl, String certificateName) {
        return getCertificatePolicyAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * Set specified members in the certificate policy. Leave others as null. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificatePolicy Management policy for a certificate.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificatePolicy>> updateCertificatePolicyWithResponseAsync(String vaultBaseUrl, String certificateName, CertificatePolicy certificatePolicy) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (certificatePolicy == null) {
            throw new IllegalArgumentException("Parameter certificatePolicy is required and cannot be null.");
        } else {
            certificatePolicy.validate();
        }
        return service.updateCertificatePolicy(vaultBaseUrl, certificateName, this.getApiVersion(), certificatePolicy);
    }

    /**
     * Set specified members in the certificate policy. Leave others as null. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificatePolicy Management policy for a certificate.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificatePolicy> updateCertificatePolicyAsync(String vaultBaseUrl, String certificateName, CertificatePolicy certificatePolicy) {
        return updateCertificatePolicyWithResponseAsync(vaultBaseUrl, certificateName, certificatePolicy)
            .flatMap((SimpleResponse<CertificatePolicy> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Set specified members in the certificate policy. Leave others as null. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificatePolicy Management policy for a certificate.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificatePolicy updateCertificatePolicy(String vaultBaseUrl, String certificateName, CertificatePolicy certificatePolicy) {
        return updateCertificatePolicyAsync(vaultBaseUrl, certificateName, certificatePolicy).block();
    }

    /**
     * The UpdateCertificate operation applies the specified update on the given certificate; the only elements updated are the certificate's attributes. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateBundle>> updateCertificateWithResponseAsync(String vaultBaseUrl, String certificateName, String certificateVersion, CertificateUpdateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (certificateVersion == null) {
            throw new IllegalArgumentException("Parameter certificateVersion is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.updateCertificate(vaultBaseUrl, certificateName, certificateVersion, this.getApiVersion(), parameters);
    }

    /**
     * The UpdateCertificate operation applies the specified update on the given certificate; the only elements updated are the certificate's attributes. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateBundle> updateCertificateAsync(String vaultBaseUrl, String certificateName, String certificateVersion, CertificateUpdateParameters parameters) {
        return updateCertificateWithResponseAsync(vaultBaseUrl, certificateName, certificateVersion, parameters)
            .flatMap((SimpleResponse<CertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The UpdateCertificate operation applies the specified update on the given certificate; the only elements updated are the certificate's attributes. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateBundle updateCertificate(String vaultBaseUrl, String certificateName, String certificateVersion, CertificateUpdateParameters parameters) {
        return updateCertificateAsync(vaultBaseUrl, certificateName, certificateVersion, parameters).block();
    }

    /**
     * Gets information about a specific certificate. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateBundle>> getCertificateWithResponseAsync(String vaultBaseUrl, String certificateName, String certificateVersion) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (certificateVersion == null) {
            throw new IllegalArgumentException("Parameter certificateVersion is required and cannot be null.");
        }
        return service.getCertificate(vaultBaseUrl, certificateName, certificateVersion, this.getApiVersion());
    }

    /**
     * Gets information about a specific certificate. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateBundle> getCertificateAsync(String vaultBaseUrl, String certificateName, String certificateVersion) {
        return getCertificateWithResponseAsync(vaultBaseUrl, certificateName, certificateVersion)
            .flatMap((SimpleResponse<CertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets information about a specific certificate. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateVersion MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateBundle getCertificate(String vaultBaseUrl, String certificateName, String certificateVersion) {
        return getCertificateAsync(vaultBaseUrl, certificateName, certificateVersion).block();
    }

    /**
     * Updates a certificate creation operation that is already in progress. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateOperation The certificate operation update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateOperation>> updateCertificateOperationWithResponseAsync(String vaultBaseUrl, String certificateName, CertificateOperationUpdateParameter certificateOperation) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (certificateOperation == null) {
            throw new IllegalArgumentException("Parameter certificateOperation is required and cannot be null.");
        } else {
            certificateOperation.validate();
        }
        return service.updateCertificateOperation(vaultBaseUrl, certificateName, this.getApiVersion(), certificateOperation);
    }

    /**
     * Updates a certificate creation operation that is already in progress. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateOperation The certificate operation update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateOperation> updateCertificateOperationAsync(String vaultBaseUrl, String certificateName, CertificateOperationUpdateParameter certificateOperation) {
        return updateCertificateOperationWithResponseAsync(vaultBaseUrl, certificateName, certificateOperation)
            .flatMap((SimpleResponse<CertificateOperation> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Updates a certificate creation operation that is already in progress. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param certificateOperation The certificate operation update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateOperation updateCertificateOperation(String vaultBaseUrl, String certificateName, CertificateOperationUpdateParameter certificateOperation) {
        return updateCertificateOperationAsync(vaultBaseUrl, certificateName, certificateOperation).block();
    }

    /**
     * Gets the creation operation associated with a specified certificate. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateOperation>> getCertificateOperationWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.getCertificateOperation(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * Gets the creation operation associated with a specified certificate. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateOperation> getCertificateOperationAsync(String vaultBaseUrl, String certificateName) {
        return getCertificateOperationWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<CertificateOperation> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets the creation operation associated with a specified certificate. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateOperation getCertificateOperation(String vaultBaseUrl, String certificateName) {
        return getCertificateOperationAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * Deletes the creation operation for a specified certificate that is in the process of being created. The certificate is no longer created. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateOperation>> deleteCertificateOperationWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.deleteCertificateOperation(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * Deletes the creation operation for a specified certificate that is in the process of being created. The certificate is no longer created. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateOperation> deleteCertificateOperationAsync(String vaultBaseUrl, String certificateName) {
        return deleteCertificateOperationWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<CertificateOperation> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Deletes the creation operation for a specified certificate that is in the process of being created. The certificate is no longer created. This operation requires the certificates/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateOperation deleteCertificateOperation(String vaultBaseUrl, String certificateName) {
        return deleteCertificateOperationAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * The MergeCertificate operation performs the merging of a certificate or certificate chain with a key pair currently available in the service. This operation requires the certificates/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate merge parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateBundle>> mergeCertificateWithResponseAsync(String vaultBaseUrl, String certificateName, CertificateMergeParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.mergeCertificate(vaultBaseUrl, certificateName, this.getApiVersion(), parameters);
    }

    /**
     * The MergeCertificate operation performs the merging of a certificate or certificate chain with a key pair currently available in the service. This operation requires the certificates/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate merge parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateBundle> mergeCertificateAsync(String vaultBaseUrl, String certificateName, CertificateMergeParameters parameters) {
        return mergeCertificateWithResponseAsync(vaultBaseUrl, certificateName, parameters)
            .flatMap((SimpleResponse<CertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The MergeCertificate operation performs the merging of a certificate or certificate chain with a key pair currently available in the service. This operation requires the certificates/create permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The certificate merge parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateBundle mergeCertificate(String vaultBaseUrl, String certificateName, CertificateMergeParameters parameters) {
        return mergeCertificateAsync(vaultBaseUrl, certificateName, parameters).block();
    }

    /**
     * Requests that a backup of the specified certificate be downloaded to the client. All versions of the certificate will be downloaded. This operation requires the certificates/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BackupCertificateResult>> backupCertificateWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.backupCertificate(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * Requests that a backup of the specified certificate be downloaded to the client. All versions of the certificate will be downloaded. This operation requires the certificates/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BackupCertificateResult> backupCertificateAsync(String vaultBaseUrl, String certificateName) {
        return backupCertificateWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<BackupCertificateResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Requests that a backup of the specified certificate be downloaded to the client. All versions of the certificate will be downloaded. This operation requires the certificates/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BackupCertificateResult backupCertificate(String vaultBaseUrl, String certificateName) {
        return backupCertificateAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * Restores a backed up certificate, and all its versions, to a vault. This operation requires the certificates/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The certificate restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateBundle>> restoreCertificateWithResponseAsync(String vaultBaseUrl, CertificateRestoreParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.restoreCertificate(vaultBaseUrl, this.getApiVersion(), parameters);
    }

    /**
     * Restores a backed up certificate, and all its versions, to a vault. This operation requires the certificates/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The certificate restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateBundle> restoreCertificateAsync(String vaultBaseUrl, CertificateRestoreParameters parameters) {
        return restoreCertificateWithResponseAsync(vaultBaseUrl, parameters)
            .flatMap((SimpleResponse<CertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Restores a backed up certificate, and all its versions, to a vault. This operation requires the certificates/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The certificate restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateBundle restoreCertificate(String vaultBaseUrl, CertificateRestoreParameters parameters) {
        return restoreCertificateAsync(vaultBaseUrl, parameters).block();
    }

    /**
     * The GetDeletedCertificates operation retrieves the certificates in the current vault which are in a deleted state and ready for recovery or purging. This operation includes deletion-specific information. This operation requires the certificates/get/list permission. This operation can only be enabled on soft-delete enabled vaults.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @param includePending MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedCertificateItem>> getDeletedCertificatesSinglePageAsync(String vaultBaseUrl, Integer maxresults, Boolean includePending) {
        return service.getDeletedCertificates(vaultBaseUrl, maxresults, includePending, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The GetDeletedCertificates operation retrieves the certificates in the current vault which are in a deleted state and ready for recovery or purging. This operation includes deletion-specific information. This operation requires the certificates/get/list permission. This operation can only be enabled on soft-delete enabled vaults.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @param includePending MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DeletedCertificateItem> getDeletedCertificatesAsync(String vaultBaseUrl, Integer maxresults, Boolean includePending) {
        return new PagedFlux<>(
            () -> getDeletedCertificatesSinglePageAsync(vaultBaseUrl, maxresults, includePending),
            nextLink -> getDeletedCertificatesNextSinglePageAsync(nextLink));
    }

    /**
     * The GetDeletedCertificates operation retrieves the certificates in the current vault which are in a deleted state and ready for recovery or purging. This operation includes deletion-specific information. This operation requires the certificates/get/list permission. This operation can only be enabled on soft-delete enabled vaults.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @param includePending MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DeletedCertificateItem> getDeletedCertificates(String vaultBaseUrl, Integer maxresults, Boolean includePending) {
        return new PagedIterable<>(getDeletedCertificatesAsync(vaultBaseUrl, maxresults, includePending));
    }

    /**
     * The GetDeletedCertificate operation retrieves the deleted certificate information plus its attributes, such as retention interval, scheduled permanent deletion and the current deletion recovery level. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedCertificateBundle>> getDeletedCertificateWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.getDeletedCertificate(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * The GetDeletedCertificate operation retrieves the deleted certificate information plus its attributes, such as retention interval, scheduled permanent deletion and the current deletion recovery level. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedCertificateBundle> getDeletedCertificateAsync(String vaultBaseUrl, String certificateName) {
        return getDeletedCertificateWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<DeletedCertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The GetDeletedCertificate operation retrieves the deleted certificate information plus its attributes, such as retention interval, scheduled permanent deletion and the current deletion recovery level. This operation requires the certificates/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedCertificateBundle getDeletedCertificate(String vaultBaseUrl, String certificateName) {
        return getDeletedCertificateAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * The PurgeDeletedCertificate operation performs an irreversible deletion of the specified certificate, without possibility for recovery. The operation is not available if the recovery level does not specify 'Purgeable'. This operation requires the certificate/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> purgeDeletedCertificateWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.purgeDeletedCertificate(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * The PurgeDeletedCertificate operation performs an irreversible deletion of the specified certificate, without possibility for recovery. The operation is not available if the recovery level does not specify 'Purgeable'. This operation requires the certificate/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> purgeDeletedCertificateAsync(String vaultBaseUrl, String certificateName) {
        return purgeDeletedCertificateWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * The PurgeDeletedCertificate operation performs an irreversible deletion of the specified certificate, without possibility for recovery. The operation is not available if the recovery level does not specify 'Purgeable'. This operation requires the certificate/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void purgeDeletedCertificate(String vaultBaseUrl, String certificateName) {
        purgeDeletedCertificateAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * The RecoverDeletedCertificate operation performs the reversal of the Delete operation. The operation is applicable in vaults enabled for soft-delete, and must be issued during the retention interval (available in the deleted certificate's attributes). This operation requires the certificates/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<CertificateBundle>> recoverDeletedCertificateWithResponseAsync(String vaultBaseUrl, String certificateName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (certificateName == null) {
            throw new IllegalArgumentException("Parameter certificateName is required and cannot be null.");
        }
        return service.recoverDeletedCertificate(vaultBaseUrl, certificateName, this.getApiVersion());
    }

    /**
     * The RecoverDeletedCertificate operation performs the reversal of the Delete operation. The operation is applicable in vaults enabled for soft-delete, and must be issued during the retention interval (available in the deleted certificate's attributes). This operation requires the certificates/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CertificateBundle> recoverDeletedCertificateAsync(String vaultBaseUrl, String certificateName) {
        return recoverDeletedCertificateWithResponseAsync(vaultBaseUrl, certificateName)
            .flatMap((SimpleResponse<CertificateBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The RecoverDeletedCertificate operation performs the reversal of the Delete operation. The operation is applicable in vaults enabled for soft-delete, and must be issued during the retention interval (available in the deleted certificate's attributes). This operation requires the certificates/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param certificateName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CertificateBundle recoverDeletedCertificate(String vaultBaseUrl, String certificateName) {
        return recoverDeletedCertificateAsync(vaultBaseUrl, certificateName).block();
    }

    /**
     * List storage accounts managed by the specified key vault. This operation requires the storage/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<StorageAccountItem>> getStorageAccountsSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getStorageAccounts(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * List storage accounts managed by the specified key vault. This operation requires the storage/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<StorageAccountItem> getStorageAccountsAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getStorageAccountsSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getStorageAccountsNextSinglePageAsync(nextLink));
    }

    /**
     * List storage accounts managed by the specified key vault. This operation requires the storage/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<StorageAccountItem> getStorageAccounts(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getStorageAccountsAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The Get Deleted Storage Accounts operation returns the storage accounts that have been deleted for a vault enabled for soft-delete. This operation requires the storage/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedStorageAccountItem>> getDeletedStorageAccountsSinglePageAsync(String vaultBaseUrl, Integer maxresults) {
        return service.getDeletedStorageAccounts(vaultBaseUrl, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The Get Deleted Storage Accounts operation returns the storage accounts that have been deleted for a vault enabled for soft-delete. This operation requires the storage/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DeletedStorageAccountItem> getDeletedStorageAccountsAsync(String vaultBaseUrl, Integer maxresults) {
        return new PagedFlux<>(
            () -> getDeletedStorageAccountsSinglePageAsync(vaultBaseUrl, maxresults),
            nextLink -> getDeletedStorageAccountsNextSinglePageAsync(nextLink));
    }

    /**
     * The Get Deleted Storage Accounts operation returns the storage accounts that have been deleted for a vault enabled for soft-delete. This operation requires the storage/list permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DeletedStorageAccountItem> getDeletedStorageAccounts(String vaultBaseUrl, Integer maxresults) {
        return new PagedIterable<>(getDeletedStorageAccountsAsync(vaultBaseUrl, maxresults));
    }

    /**
     * The Get Deleted Storage Account operation returns the specified deleted storage account along with its attributes. This operation requires the storage/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedStorageBundle>> getDeletedStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        return service.getDeletedStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion());
    }

    /**
     * The Get Deleted Storage Account operation returns the specified deleted storage account along with its attributes. This operation requires the storage/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedStorageBundle> getDeletedStorageAccountAsync(String vaultBaseUrl, String storageAccountName) {
        return getDeletedStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName)
            .flatMap((SimpleResponse<DeletedStorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The Get Deleted Storage Account operation returns the specified deleted storage account along with its attributes. This operation requires the storage/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedStorageBundle getDeletedStorageAccount(String vaultBaseUrl, String storageAccountName) {
        return getDeletedStorageAccountAsync(vaultBaseUrl, storageAccountName).block();
    }

    /**
     * The purge deleted storage account operation removes the secret permanently, without the possibility of recovery. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> purgeDeletedStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        return service.purgeDeletedStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion());
    }

    /**
     * The purge deleted storage account operation removes the secret permanently, without the possibility of recovery. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> purgeDeletedStorageAccountAsync(String vaultBaseUrl, String storageAccountName) {
        return purgeDeletedStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * The purge deleted storage account operation removes the secret permanently, without the possibility of recovery. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/purge permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void purgeDeletedStorageAccount(String vaultBaseUrl, String storageAccountName) {
        purgeDeletedStorageAccountAsync(vaultBaseUrl, storageAccountName).block();
    }

    /**
     * Recovers the deleted storage account in the specified vault. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageBundle>> recoverDeletedStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        return service.recoverDeletedStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion());
    }

    /**
     * Recovers the deleted storage account in the specified vault. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageBundle> recoverDeletedStorageAccountAsync(String vaultBaseUrl, String storageAccountName) {
        return recoverDeletedStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName)
            .flatMap((SimpleResponse<StorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Recovers the deleted storage account in the specified vault. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageBundle recoverDeletedStorageAccount(String vaultBaseUrl, String storageAccountName) {
        return recoverDeletedStorageAccountAsync(vaultBaseUrl, storageAccountName).block();
    }

    /**
     * Requests that a backup of the specified storage account be downloaded to the client. This operation requires the storage/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BackupStorageResult>> backupStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        return service.backupStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion());
    }

    /**
     * Requests that a backup of the specified storage account be downloaded to the client. This operation requires the storage/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BackupStorageResult> backupStorageAccountAsync(String vaultBaseUrl, String storageAccountName) {
        return backupStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName)
            .flatMap((SimpleResponse<BackupStorageResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Requests that a backup of the specified storage account be downloaded to the client. This operation requires the storage/backup permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BackupStorageResult backupStorageAccount(String vaultBaseUrl, String storageAccountName) {
        return backupStorageAccountAsync(vaultBaseUrl, storageAccountName).block();
    }

    /**
     * Restores a backed up storage account to a vault. This operation requires the storage/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The secret restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageBundle>> restoreStorageAccountWithResponseAsync(String vaultBaseUrl, StorageRestoreParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.restoreStorageAccount(vaultBaseUrl, this.getApiVersion(), parameters);
    }

    /**
     * Restores a backed up storage account to a vault. This operation requires the storage/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The secret restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageBundle> restoreStorageAccountAsync(String vaultBaseUrl, StorageRestoreParameters parameters) {
        return restoreStorageAccountWithResponseAsync(vaultBaseUrl, parameters)
            .flatMap((SimpleResponse<StorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Restores a backed up storage account to a vault. This operation requires the storage/restore permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param parameters The secret restore parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageBundle restoreStorageAccount(String vaultBaseUrl, StorageRestoreParameters parameters) {
        return restoreStorageAccountAsync(vaultBaseUrl, parameters).block();
    }

    /**
     * Deletes a storage account. This operation requires the storage/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedStorageBundle>> deleteStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        return service.deleteStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion());
    }

    /**
     * Deletes a storage account. This operation requires the storage/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedStorageBundle> deleteStorageAccountAsync(String vaultBaseUrl, String storageAccountName) {
        return deleteStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName)
            .flatMap((SimpleResponse<DeletedStorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Deletes a storage account. This operation requires the storage/delete permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedStorageBundle deleteStorageAccount(String vaultBaseUrl, String storageAccountName) {
        return deleteStorageAccountAsync(vaultBaseUrl, storageAccountName).block();
    }

    /**
     * Gets information about a specified storage account. This operation requires the storage/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageBundle>> getStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        return service.getStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion());
    }

    /**
     * Gets information about a specified storage account. This operation requires the storage/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageBundle> getStorageAccountAsync(String vaultBaseUrl, String storageAccountName) {
        return getStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName)
            .flatMap((SimpleResponse<StorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets information about a specified storage account. This operation requires the storage/get permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageBundle getStorageAccount(String vaultBaseUrl, String storageAccountName) {
        return getStorageAccountAsync(vaultBaseUrl, storageAccountName).block();
    }

    /**
     * Creates or updates a new storage account. This operation requires the storage/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageBundle>> setStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName, StorageAccountCreateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.setStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion(), parameters);
    }

    /**
     * Creates or updates a new storage account. This operation requires the storage/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageBundle> setStorageAccountAsync(String vaultBaseUrl, String storageAccountName, StorageAccountCreateParameters parameters) {
        return setStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName, parameters)
            .flatMap((SimpleResponse<StorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Creates or updates a new storage account. This operation requires the storage/set permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageBundle setStorageAccount(String vaultBaseUrl, String storageAccountName, StorageAccountCreateParameters parameters) {
        return setStorageAccountAsync(vaultBaseUrl, storageAccountName, parameters).block();
    }

    /**
     * Updates the specified attributes associated with the given storage account. This operation requires the storage/set/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageBundle>> updateStorageAccountWithResponseAsync(String vaultBaseUrl, String storageAccountName, StorageAccountUpdateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.updateStorageAccount(vaultBaseUrl, storageAccountName, this.getApiVersion(), parameters);
    }

    /**
     * Updates the specified attributes associated with the given storage account. This operation requires the storage/set/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageBundle> updateStorageAccountAsync(String vaultBaseUrl, String storageAccountName, StorageAccountUpdateParameters parameters) {
        return updateStorageAccountWithResponseAsync(vaultBaseUrl, storageAccountName, parameters)
            .flatMap((SimpleResponse<StorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Updates the specified attributes associated with the given storage account. This operation requires the storage/set/update permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageBundle updateStorageAccount(String vaultBaseUrl, String storageAccountName, StorageAccountUpdateParameters parameters) {
        return updateStorageAccountAsync(vaultBaseUrl, storageAccountName, parameters).block();
    }

    /**
     * Regenerates the specified key value for the given storage account. This operation requires the storage/regeneratekey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account key regenerate parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageBundle>> regenerateStorageAccountKeyWithResponseAsync(String vaultBaseUrl, String storageAccountName, StorageAccountRegenerteKeyParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.regenerateStorageAccountKey(vaultBaseUrl, storageAccountName, this.getApiVersion(), parameters);
    }

    /**
     * Regenerates the specified key value for the given storage account. This operation requires the storage/regeneratekey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account key regenerate parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageBundle> regenerateStorageAccountKeyAsync(String vaultBaseUrl, String storageAccountName, StorageAccountRegenerteKeyParameters parameters) {
        return regenerateStorageAccountKeyWithResponseAsync(vaultBaseUrl, storageAccountName, parameters)
            .flatMap((SimpleResponse<StorageBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Regenerates the specified key value for the given storage account. This operation requires the storage/regeneratekey permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The storage account key regenerate parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageBundle regenerateStorageAccountKey(String vaultBaseUrl, String storageAccountName, StorageAccountRegenerteKeyParameters parameters) {
        return regenerateStorageAccountKeyAsync(vaultBaseUrl, storageAccountName, parameters).block();
    }

    /**
     * List storage SAS definitions for the given storage account. This operation requires the storage/listsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<SasDefinitionItem>> getSasDefinitionsSinglePageAsync(String vaultBaseUrl, String storageAccountName, Integer maxresults) {
        return service.getSasDefinitions(vaultBaseUrl, storageAccountName, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * List storage SAS definitions for the given storage account. This operation requires the storage/listsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<SasDefinitionItem> getSasDefinitionsAsync(String vaultBaseUrl, String storageAccountName, Integer maxresults) {
        return new PagedFlux<>(
            () -> getSasDefinitionsSinglePageAsync(vaultBaseUrl, storageAccountName, maxresults),
            nextLink -> getSasDefinitionsNextSinglePageAsync(nextLink));
    }

    /**
     * List storage SAS definitions for the given storage account. This operation requires the storage/listsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<SasDefinitionItem> getSasDefinitions(String vaultBaseUrl, String storageAccountName, Integer maxresults) {
        return new PagedIterable<>(getSasDefinitionsAsync(vaultBaseUrl, storageAccountName, maxresults));
    }

    /**
     * The Get Deleted Sas Definitions operation returns the SAS definitions that have been deleted for a vault enabled for soft-delete. This operation requires the storage/listsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedSasDefinitionItem>> getDeletedSasDefinitionsSinglePageAsync(String vaultBaseUrl, String storageAccountName, Integer maxresults) {
        return service.getDeletedSasDefinitions(vaultBaseUrl, storageAccountName, maxresults, this.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * The Get Deleted Sas Definitions operation returns the SAS definitions that have been deleted for a vault enabled for soft-delete. This operation requires the storage/listsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DeletedSasDefinitionItem> getDeletedSasDefinitionsAsync(String vaultBaseUrl, String storageAccountName, Integer maxresults) {
        return new PagedFlux<>(
            () -> getDeletedSasDefinitionsSinglePageAsync(vaultBaseUrl, storageAccountName, maxresults),
            nextLink -> getDeletedSasDefinitionsNextSinglePageAsync(nextLink));
    }

    /**
     * The Get Deleted Sas Definitions operation returns the SAS definitions that have been deleted for a vault enabled for soft-delete. This operation requires the storage/listsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param maxresults MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DeletedSasDefinitionItem> getDeletedSasDefinitions(String vaultBaseUrl, String storageAccountName, Integer maxresults) {
        return new PagedIterable<>(getDeletedSasDefinitionsAsync(vaultBaseUrl, storageAccountName, maxresults));
    }

    /**
     * The Get Deleted SAS Definition operation returns the specified deleted SAS definition along with its attributes. This operation requires the storage/getsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedSasDefinitionBundle>> getDeletedSasDefinitionWithResponseAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (sasDefinitionName == null) {
            throw new IllegalArgumentException("Parameter sasDefinitionName is required and cannot be null.");
        }
        return service.getDeletedSasDefinition(vaultBaseUrl, storageAccountName, sasDefinitionName, this.getApiVersion());
    }

    /**
     * The Get Deleted SAS Definition operation returns the specified deleted SAS definition along with its attributes. This operation requires the storage/getsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedSasDefinitionBundle> getDeletedSasDefinitionAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return getDeletedSasDefinitionWithResponseAsync(vaultBaseUrl, storageAccountName, sasDefinitionName)
            .flatMap((SimpleResponse<DeletedSasDefinitionBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * The Get Deleted SAS Definition operation returns the specified deleted SAS definition along with its attributes. This operation requires the storage/getsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedSasDefinitionBundle getDeletedSasDefinition(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return getDeletedSasDefinitionAsync(vaultBaseUrl, storageAccountName, sasDefinitionName).block();
    }

    /**
     * Recovers the deleted SAS definition for the specified storage account. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SasDefinitionBundle>> recoverDeletedSasDefinitionWithResponseAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (sasDefinitionName == null) {
            throw new IllegalArgumentException("Parameter sasDefinitionName is required and cannot be null.");
        }
        return service.recoverDeletedSasDefinition(vaultBaseUrl, storageAccountName, sasDefinitionName, this.getApiVersion());
    }

    /**
     * Recovers the deleted SAS definition for the specified storage account. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SasDefinitionBundle> recoverDeletedSasDefinitionAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return recoverDeletedSasDefinitionWithResponseAsync(vaultBaseUrl, storageAccountName, sasDefinitionName)
            .flatMap((SimpleResponse<SasDefinitionBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Recovers the deleted SAS definition for the specified storage account. This operation can only be performed on a soft-delete enabled vault. This operation requires the storage/recover permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SasDefinitionBundle recoverDeletedSasDefinition(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return recoverDeletedSasDefinitionAsync(vaultBaseUrl, storageAccountName, sasDefinitionName).block();
    }

    /**
     * Deletes a SAS definition from a specified storage account. This operation requires the storage/deletesas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DeletedSasDefinitionBundle>> deleteSasDefinitionWithResponseAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (sasDefinitionName == null) {
            throw new IllegalArgumentException("Parameter sasDefinitionName is required and cannot be null.");
        }
        return service.deleteSasDefinition(vaultBaseUrl, storageAccountName, sasDefinitionName, this.getApiVersion());
    }

    /**
     * Deletes a SAS definition from a specified storage account. This operation requires the storage/deletesas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeletedSasDefinitionBundle> deleteSasDefinitionAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return deleteSasDefinitionWithResponseAsync(vaultBaseUrl, storageAccountName, sasDefinitionName)
            .flatMap((SimpleResponse<DeletedSasDefinitionBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Deletes a SAS definition from a specified storage account. This operation requires the storage/deletesas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DeletedSasDefinitionBundle deleteSasDefinition(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return deleteSasDefinitionAsync(vaultBaseUrl, storageAccountName, sasDefinitionName).block();
    }

    /**
     * Gets information about a SAS definition for the specified storage account. This operation requires the storage/getsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SasDefinitionBundle>> getSasDefinitionWithResponseAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (sasDefinitionName == null) {
            throw new IllegalArgumentException("Parameter sasDefinitionName is required and cannot be null.");
        }
        return service.getSasDefinition(vaultBaseUrl, storageAccountName, sasDefinitionName, this.getApiVersion());
    }

    /**
     * Gets information about a SAS definition for the specified storage account. This operation requires the storage/getsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SasDefinitionBundle> getSasDefinitionAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return getSasDefinitionWithResponseAsync(vaultBaseUrl, storageAccountName, sasDefinitionName)
            .flatMap((SimpleResponse<SasDefinitionBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets information about a SAS definition for the specified storage account. This operation requires the storage/getsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SasDefinitionBundle getSasDefinition(String vaultBaseUrl, String storageAccountName, String sasDefinitionName) {
        return getSasDefinitionAsync(vaultBaseUrl, storageAccountName, sasDefinitionName).block();
    }

    /**
     * Creates or updates a new SAS definition for the specified storage account. This operation requires the storage/setsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The SAS definition create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SasDefinitionBundle>> setSasDefinitionWithResponseAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName, SasDefinitionCreateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (sasDefinitionName == null) {
            throw new IllegalArgumentException("Parameter sasDefinitionName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.setSasDefinition(vaultBaseUrl, storageAccountName, sasDefinitionName, this.getApiVersion(), parameters);
    }

    /**
     * Creates or updates a new SAS definition for the specified storage account. This operation requires the storage/setsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The SAS definition create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SasDefinitionBundle> setSasDefinitionAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName, SasDefinitionCreateParameters parameters) {
        return setSasDefinitionWithResponseAsync(vaultBaseUrl, storageAccountName, sasDefinitionName, parameters)
            .flatMap((SimpleResponse<SasDefinitionBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Creates or updates a new SAS definition for the specified storage account. This operation requires the storage/setsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The SAS definition create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SasDefinitionBundle setSasDefinition(String vaultBaseUrl, String storageAccountName, String sasDefinitionName, SasDefinitionCreateParameters parameters) {
        return setSasDefinitionAsync(vaultBaseUrl, storageAccountName, sasDefinitionName, parameters).block();
    }

    /**
     * Updates the specified attributes associated with the given SAS definition. This operation requires the storage/setsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The SAS definition update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SasDefinitionBundle>> updateSasDefinitionWithResponseAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName, SasDefinitionUpdateParameters parameters) {
        if (vaultBaseUrl == null) {
            throw new IllegalArgumentException("Parameter vaultBaseUrl is required and cannot be null.");
        }
        if (storageAccountName == null) {
            throw new IllegalArgumentException("Parameter storageAccountName is required and cannot be null.");
        }
        if (sasDefinitionName == null) {
            throw new IllegalArgumentException("Parameter sasDefinitionName is required and cannot be null.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        } else {
            parameters.validate();
        }
        return service.updateSasDefinition(vaultBaseUrl, storageAccountName, sasDefinitionName, this.getApiVersion(), parameters);
    }

    /**
     * Updates the specified attributes associated with the given SAS definition. This operation requires the storage/setsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The SAS definition update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SasDefinitionBundle> updateSasDefinitionAsync(String vaultBaseUrl, String storageAccountName, String sasDefinitionName, SasDefinitionUpdateParameters parameters) {
        return updateSasDefinitionWithResponseAsync(vaultBaseUrl, storageAccountName, sasDefinitionName, parameters)
            .flatMap((SimpleResponse<SasDefinitionBundle> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Updates the specified attributes associated with the given SAS definition. This operation requires the storage/setsas permission.
     * 
     * @param vaultBaseUrl simple string.
     * @param storageAccountName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param sasDefinitionName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parameters The SAS definition update parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SasDefinitionBundle updateSasDefinition(String vaultBaseUrl, String storageAccountName, String sasDefinitionName, SasDefinitionUpdateParameters parameters) {
        return updateSasDefinitionAsync(vaultBaseUrl, storageAccountName, sasDefinitionName, parameters).block();
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<KeyItem>> getKeyVersionsNextSinglePageAsync(String nextLink) {
        return service.getKeyVersionsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<KeyItem>> getKeysNextSinglePageAsync(String nextLink) {
        return service.getKeysNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedKeyItem>> getDeletedKeysNextSinglePageAsync(String nextLink) {
        return service.getDeletedKeysNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<SecretItem>> getSecretsNextSinglePageAsync(String nextLink) {
        return service.getSecretsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<SecretItem>> getSecretVersionsNextSinglePageAsync(String nextLink) {
        return service.getSecretVersionsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedSecretItem>> getDeletedSecretsNextSinglePageAsync(String nextLink) {
        return service.getDeletedSecretsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<CertificateItem>> getCertificatesNextSinglePageAsync(String nextLink) {
        return service.getCertificatesNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<CertificateIssuerItem>> getCertificateIssuersNextSinglePageAsync(String nextLink) {
        return service.getCertificateIssuersNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<CertificateItem>> getCertificateVersionsNextSinglePageAsync(String nextLink) {
        return service.getCertificateVersionsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedCertificateItem>> getDeletedCertificatesNextSinglePageAsync(String nextLink) {
        return service.getDeletedCertificatesNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<StorageAccountItem>> getStorageAccountsNextSinglePageAsync(String nextLink) {
        return service.getStorageAccountsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedStorageAccountItem>> getDeletedStorageAccountsNextSinglePageAsync(String nextLink) {
        return service.getDeletedStorageAccountsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<SasDefinitionItem>> getSasDefinitionsNextSinglePageAsync(String nextLink) {
        return service.getSasDefinitionsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws KeyVaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<DeletedSasDefinitionItem>> getDeletedSasDefinitionsNextSinglePageAsync(String nextLink) {
        return service.getDeletedSasDefinitionsNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }
}
