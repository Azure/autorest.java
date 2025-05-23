// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage Service Properties.
 */
@JacksonXmlRootElement(localName = "StorageServiceProperties")
@Fluent
public final class StorageServiceProperties {
    /*
     * Azure Analytics Logging settings
     */
    @Generated
    @JsonProperty(value = "Logging")
    private Logging logging;

    /*
     * A summary of request statistics grouped by API in hourly aggregates for blobs
     */
    @Generated
    @JsonProperty(value = "HourMetrics")
    private Metrics hourMetrics;

    /*
     * a summary of request statistics grouped by API in minute aggregates for blobs
     */
    @Generated
    @JsonProperty(value = "MinuteMetrics")
    private Metrics minuteMetrics;

    static final class CorsWrapper {
        @JacksonXmlProperty(localName = "CorsRule")
        private final List<CorsRule> items;

        @JsonCreator
        private CorsWrapper(@JacksonXmlProperty(localName = "CorsRule") List<CorsRule> items) {
            this.items = items;
        }
    }

    /*
     * The set of CORS rules.
     */
    @Generated
    @JsonProperty(value = "Cors")
    private CorsWrapper cors;

    /*
     * The default version to use for requests to the Blob service if an incoming request's version is not specified.
     * Possible values include version 2008-10-27 and all more recent versions
     */
    @Generated
    @JsonProperty(value = "DefaultServiceVersion")
    private String defaultServiceVersion;

    /*
     * The Delete Retention Policy for the service
     */
    @Generated
    @JsonProperty(value = "DeleteRetentionPolicy")
    private RetentionPolicy deleteRetentionPolicy;

    /**
     * Creates an instance of StorageServiceProperties class.
     */
    @Generated
    public StorageServiceProperties() {
    }

    /**
     * Get the logging property: Azure Analytics Logging settings.
     * 
     * @return the logging value.
     */
    @Generated
    public Logging getLogging() {
        return this.logging;
    }

    /**
     * Set the logging property: Azure Analytics Logging settings.
     * 
     * @param logging the logging value to set.
     * @return the StorageServiceProperties object itself.
     */
    @Generated
    public StorageServiceProperties setLogging(Logging logging) {
        this.logging = logging;
        return this;
    }

    /**
     * Get the hourMetrics property: A summary of request statistics grouped by API in hourly aggregates for blobs.
     * 
     * @return the hourMetrics value.
     */
    @Generated
    public Metrics getHourMetrics() {
        return this.hourMetrics;
    }

    /**
     * Set the hourMetrics property: A summary of request statistics grouped by API in hourly aggregates for blobs.
     * 
     * @param hourMetrics the hourMetrics value to set.
     * @return the StorageServiceProperties object itself.
     */
    @Generated
    public StorageServiceProperties setHourMetrics(Metrics hourMetrics) {
        this.hourMetrics = hourMetrics;
        return this;
    }

    /**
     * Get the minuteMetrics property: a summary of request statistics grouped by API in minute aggregates for blobs.
     * 
     * @return the minuteMetrics value.
     */
    @Generated
    public Metrics getMinuteMetrics() {
        return this.minuteMetrics;
    }

    /**
     * Set the minuteMetrics property: a summary of request statistics grouped by API in minute aggregates for blobs.
     * 
     * @param minuteMetrics the minuteMetrics value to set.
     * @return the StorageServiceProperties object itself.
     */
    @Generated
    public StorageServiceProperties setMinuteMetrics(Metrics minuteMetrics) {
        this.minuteMetrics = minuteMetrics;
        return this;
    }

    /**
     * Get the cors property: The set of CORS rules.
     * 
     * @return the cors value.
     */
    @Generated
    public List<CorsRule> getCors() {
        if (this.cors == null) {
            this.cors = new CorsWrapper(new ArrayList<CorsRule>());
        }
        return this.cors.items;
    }

    /**
     * Set the cors property: The set of CORS rules.
     * 
     * @param cors the cors value to set.
     * @return the StorageServiceProperties object itself.
     */
    @Generated
    public StorageServiceProperties setCors(List<CorsRule> cors) {
        this.cors = new CorsWrapper(cors);
        return this;
    }

    /**
     * Get the defaultServiceVersion property: The default version to use for requests to the Blob service if an
     * incoming request's version is not specified. Possible values include version 2008-10-27 and all more recent
     * versions.
     * 
     * @return the defaultServiceVersion value.
     */
    @Generated
    public String getDefaultServiceVersion() {
        return this.defaultServiceVersion;
    }

    /**
     * Set the defaultServiceVersion property: The default version to use for requests to the Blob service if an
     * incoming request's version is not specified. Possible values include version 2008-10-27 and all more recent
     * versions.
     * 
     * @param defaultServiceVersion the defaultServiceVersion value to set.
     * @return the StorageServiceProperties object itself.
     */
    @Generated
    public StorageServiceProperties setDefaultServiceVersion(String defaultServiceVersion) {
        this.defaultServiceVersion = defaultServiceVersion;
        return this;
    }

    /**
     * Get the deleteRetentionPolicy property: The Delete Retention Policy for the service.
     * 
     * @return the deleteRetentionPolicy value.
     */
    @Generated
    public RetentionPolicy getDeleteRetentionPolicy() {
        return this.deleteRetentionPolicy;
    }

    /**
     * Set the deleteRetentionPolicy property: The Delete Retention Policy for the service.
     * 
     * @param deleteRetentionPolicy the deleteRetentionPolicy value to set.
     * @return the StorageServiceProperties object itself.
     */
    @Generated
    public StorageServiceProperties setDeleteRetentionPolicy(RetentionPolicy deleteRetentionPolicy) {
        this.deleteRetentionPolicy = deleteRetentionPolicy;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getLogging() != null) {
            getLogging().validate();
        }
        if (getHourMetrics() != null) {
            getHourMetrics().validate();
        }
        if (getMinuteMetrics() != null) {
            getMinuteMetrics().validate();
        }
        if (getCors() != null) {
            getCors().forEach(e -> e.validate());
        }
        if (getDeleteRetentionPolicy() != null) {
            getDeleteRetentionPolicy().validate();
        }
    }
}
