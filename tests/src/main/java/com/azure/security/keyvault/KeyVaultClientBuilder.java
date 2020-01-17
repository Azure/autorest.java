package com.azure.security.keyvault;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.annotation.ServiceMethod;
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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.security.keyvault.models.BackupCertificateResult;
import com.azure.security.keyvault.models.BackupKeyResult;
import com.azure.security.keyvault.models.BackupSecretResult;
import com.azure.security.keyvault.models.BackupStorageResult;
import com.azure.security.keyvault.models.CertificateBundle;
import com.azure.security.keyvault.models.CertificateCreateParameters;
import com.azure.security.keyvault.models.CertificateImportParameters;
import com.azure.security.keyvault.models.CertificateIssuerItem;
import com.azure.security.keyvault.models.CertificateIssuerSetParameters;
import com.azure.security.keyvault.models.CertificateIssuerUpdateParameters;
import com.azure.security.keyvault.models.CertificateItem;
import com.azure.security.keyvault.models.CertificateMergeParameters;
import com.azure.security.keyvault.models.CertificateOperation;
import com.azure.security.keyvault.models.CertificateOperationUpdateParameter;
import com.azure.security.keyvault.models.CertificatePolicy;
import com.azure.security.keyvault.models.CertificateRestoreParameters;
import com.azure.security.keyvault.models.CertificateUpdateParameters;
import com.azure.security.keyvault.models.Contacts;
import com.azure.security.keyvault.models.DeletedCertificateBundle;
import com.azure.security.keyvault.models.DeletedCertificateItem;
import com.azure.security.keyvault.models.DeletedKeyBundle;
import com.azure.security.keyvault.models.DeletedKeyItem;
import com.azure.security.keyvault.models.DeletedSasDefinitionBundle;
import com.azure.security.keyvault.models.DeletedSasDefinitionItem;
import com.azure.security.keyvault.models.DeletedSecretBundle;
import com.azure.security.keyvault.models.DeletedSecretItem;
import com.azure.security.keyvault.models.DeletedStorageAccountItem;
import com.azure.security.keyvault.models.DeletedStorageBundle;
import com.azure.security.keyvault.models.IssuerBundle;
import com.azure.security.keyvault.models.KeyBundle;
import com.azure.security.keyvault.models.KeyCreateParameters;
import com.azure.security.keyvault.models.KeyImportParameters;
import com.azure.security.keyvault.models.KeyItem;
import com.azure.security.keyvault.models.KeyOperationResult;
import com.azure.security.keyvault.models.KeyOperationsParameters;
import com.azure.security.keyvault.models.KeyRestoreParameters;
import com.azure.security.keyvault.models.KeySignParameters;
import com.azure.security.keyvault.models.KeyUpdateParameters;
import com.azure.security.keyvault.models.KeyVerifyParameters;
import com.azure.security.keyvault.models.KeyVerifyResult;
import com.azure.security.keyvault.models.SasDefinitionBundle;
import com.azure.security.keyvault.models.SasDefinitionCreateParameters;
import com.azure.security.keyvault.models.SasDefinitionItem;
import com.azure.security.keyvault.models.SasDefinitionUpdateParameters;
import com.azure.security.keyvault.models.SecretBundle;
import com.azure.security.keyvault.models.SecretItem;
import com.azure.security.keyvault.models.SecretRestoreParameters;
import com.azure.security.keyvault.models.SecretSetParameters;
import com.azure.security.keyvault.models.SecretUpdateParameters;
import com.azure.security.keyvault.models.StorageAccountCreateParameters;
import com.azure.security.keyvault.models.StorageAccountItem;
import com.azure.security.keyvault.models.StorageAccountRegenerteKeyParameters;
import com.azure.security.keyvault.models.StorageAccountUpdateParameters;
import com.azure.security.keyvault.models.StorageBundle;
import com.azure.security.keyvault.models.StorageRestoreParameters;
import reactor.core.publisher.Mono;

/**
 * A builder for creating a new instance of the KeyVaultClient type.
 */
@ServiceClientBuilder(serviceClients = KeyVaultClient.class)
public final class KeyVaultClientBuilder {
    /*
     * Api Version
     */
    private String apiVersion;

    /**
     * Sets Api Version.
     * 
     * @param apiVersion the apiVersion value.
     * @return the KeyVaultClientBuilder.
     */
    public KeyVaultClientBuilder apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /*
     * The HTTP pipeline to send requests through
     */
    private HttpPipeline pipeline;

    /**
     * Sets The HTTP pipeline to send requests through.
     * 
     * @param pipeline the pipeline value.
     * @return the KeyVaultClientBuilder.
     */
    public KeyVaultClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of KeyVaultClient with the provided parameters.
     * 
     * @return an instance of KeyVaultClient.
     */
    public KeyVaultClient build() {
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        KeyVaultClient client = new KeyVaultClient(pipeline);
        client.setApiVersion(this.apiVersion);
        return client;
    }
}
