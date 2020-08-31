package com.azure.autorest.extension.base.customization;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PostProcessorTests {
    private final Map<String, String> files;

    @Test
    public void testMoveFile() {

    }

    public PostProcessorTests() {
        files = new HashMap<>();
        files.put("AdditionalPropertiesClient.java", "package fixtures.additionalproperties;\n" +
                "\n" +
                "import com.azure.core.http.HttpPipeline;\n" +
                "import com.azure.core.http.HttpPipelineBuilder;\n" +
                "import com.azure.core.http.policy.CookiePolicy;\n" +
                "import com.azure.core.http.policy.RetryPolicy;\n" +
                "import com.azure.core.http.policy.UserAgentPolicy;\n" +
                "import com.azure.core.util.serializer.JacksonAdapter;\n" +
                "import com.azure.core.util.serializer.SerializerAdapter;\n" +
                "\n" +
                "/** Initializes a new instance of the AdditionalPropertiesClient type. */\n" +
                "public final class AdditionalPropertiesClient {\n" +
                "    /** server parameter. */\n" +
                "    private final String host;\n" +
                "\n" +
                "    /**\n" +
                "     * Gets server parameter.\n" +
                "     *\n" +
                "     * @return the host value.\n" +
                "     */\n" +
                "    public String getHost() {\n" +
                "        return this.host;\n" +
                "    }\n" +
                "\n" +
                "    /** The HTTP pipeline to send requests through. */\n" +
                "    private final HttpPipeline httpPipeline;\n" +
                "\n" +
                "    /**\n" +
                "     * Gets The HTTP pipeline to send requests through.\n" +
                "     *\n" +
                "     * @return the httpPipeline value.\n" +
                "     */\n" +
                "    public HttpPipeline getHttpPipeline() {\n" +
                "        return this.httpPipeline;\n" +
                "    }\n" +
                "\n" +
                "    /** The serializer to serialize an object into a string. */\n" +
                "    private final SerializerAdapter serializerAdapter;\n" +
                "\n" +
                "    /**\n" +
                "     * Gets The serializer to serialize an object into a string.\n" +
                "     *\n" +
                "     * @return the serializerAdapter value.\n" +
                "     */\n" +
                "    public SerializerAdapter getSerializerAdapter() {\n" +
                "        return this.serializerAdapter;\n" +
                "    }\n" +
                "\n" +
                "    /** The Pets object to access its operations. */\n" +
                "    private final Pets pets;\n" +
                "\n" +
                "    /**\n" +
                "     * Gets the Pets object to access its operations.\n" +
                "     *\n" +
                "     * @return the Pets object.\n" +
                "     */\n" +
                "    public Pets getPets() {\n" +
                "        return this.pets;\n" +
                "    }\n" +
                "\n" +
                "    /** Initializes an instance of AdditionalPropertiesClient client. */\n" +
                "    AdditionalPropertiesClient(String host) {\n" +
                "        this(\n" +
                "                new HttpPipelineBuilder()\n" +
                "                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())\n" +
                "                        .build(),\n" +
                "                JacksonAdapter.createDefaultSerializerAdapter(),\n" +
                "                host);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Initializes an instance of AdditionalPropertiesClient client.\n" +
                "     *\n" +
                "     * @param httpPipeline The HTTP pipeline to send requests through.\n" +
                "     */\n" +
                "    AdditionalPropertiesClient(HttpPipeline httpPipeline, String host) {\n" +
                "        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Initializes an instance of AdditionalPropertiesClient client.\n" +
                "     *\n" +
                "     * @param httpPipeline The HTTP pipeline to send requests through.\n" +
                "     * @param serializerAdapter The serializer to serialize an object into a string.\n" +
                "     */\n" +
                "    AdditionalPropertiesClient(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {\n" +
                "        this.httpPipeline = httpPipeline;\n" +
                "        this.serializerAdapter = serializerAdapter;\n" +
                "        this.host = host;\n" +
                "        this.pets = new Pets(this);\n" +
                "    }\n" +
                "}\n");
        files.put("AdditionalPropertiesClientBuilder.java", "package fixtures.additionalproperties;\n" +
                "\n" +
                "import com.azure.core.annotation.ServiceClientBuilder;\n" +
                "import com.azure.core.http.HttpPipeline;\n" +
                "import com.azure.core.http.HttpPipelineBuilder;\n" +
                "import com.azure.core.http.policy.CookiePolicy;\n" +
                "import com.azure.core.http.policy.RetryPolicy;\n" +
                "import com.azure.core.http.policy.UserAgentPolicy;\n" +
                "import com.azure.core.util.serializer.JacksonAdapter;\n" +
                "import com.azure.core.util.serializer.SerializerAdapter;\n" +
                "\n" +
                "/** A builder for creating a new instance of the AdditionalPropertiesClient type. */\n" +
                "@ServiceClientBuilder(serviceClients = {AdditionalPropertiesClient.class})\n" +
                "public final class AdditionalPropertiesClientBuilder {\n" +
                "    /*\n" +
                "     * server parameter\n" +
                "     */\n" +
                "    private String host;\n" +
                "\n" +
                "    /**\n" +
                "     * Sets server parameter.\n" +
                "     *\n" +
                "     * @param host the host value.\n" +
                "     * @return the AdditionalPropertiesClientBuilder.\n" +
                "     */\n" +
                "    public AdditionalPropertiesClientBuilder host(String host) {\n" +
                "        this.host = host;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /*\n" +
                "     * The HTTP pipeline to send requests through\n" +
                "     */\n" +
                "    private HttpPipeline pipeline;\n" +
                "\n" +
                "    /**\n" +
                "     * Sets The HTTP pipeline to send requests through.\n" +
                "     *\n" +
                "     * @param pipeline the pipeline value.\n" +
                "     * @return the AdditionalPropertiesClientBuilder.\n" +
                "     */\n" +
                "    public AdditionalPropertiesClientBuilder pipeline(HttpPipeline pipeline) {\n" +
                "        this.pipeline = pipeline;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /*\n" +
                "     * The serializer to serialize an object into a string\n" +
                "     */\n" +
                "    private SerializerAdapter serializerAdapter;\n" +
                "\n" +
                "    /**\n" +
                "     * Sets The serializer to serialize an object into a string.\n" +
                "     *\n" +
                "     * @param serializerAdapter the serializerAdapter value.\n" +
                "     * @return the AdditionalPropertiesClientBuilder.\n" +
                "     */\n" +
                "    public AdditionalPropertiesClientBuilder serializerAdapter(SerializerAdapter serializerAdapter) {\n" +
                "        this.serializerAdapter = serializerAdapter;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Builds an instance of AdditionalPropertiesClient with the provided parameters.\n" +
                "     *\n" +
                "     * @return an instance of AdditionalPropertiesClient.\n" +
                "     */\n" +
                "    public AdditionalPropertiesClient buildClient() {\n" +
                "        if (host == null) {\n" +
                "            this.host = \"http://localhost:3000\";\n" +
                "        }\n" +
                "        if (pipeline == null) {\n" +
                "            this.pipeline =\n" +
                "                    new HttpPipelineBuilder()\n" +
                "                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())\n" +
                "                            .build();\n" +
                "        }\n" +
                "        if (serializerAdapter == null) {\n" +
                "            this.serializerAdapter = JacksonAdapter.createDefaultSerializerAdapter();\n" +
                "        }\n" +
                "        AdditionalPropertiesClient client = new AdditionalPropertiesClient(pipeline, serializerAdapter, host);\n" +
                "        return client;\n" +
                "    }\n" +
                "}\n");
        files.put("models/CatAPTrue.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "\n" +
                "/** The CatAPTrue model. */\n" +
                "@Fluent\n" +
                "public final class CatAPTrue extends PetAPTrue {\n" +
                "    /*\n" +
                "     * The friendly property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"friendly\")\n" +
                "    private Boolean friendly;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the friendly property: The friendly property.\n" +
                "     *\n" +
                "     * @return the friendly value.\n" +
                "     */\n" +
                "    public Boolean isFriendly() {\n" +
                "        return this.friendly;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the friendly property: The friendly property.\n" +
                "     *\n" +
                "     * @param friendly the friendly value to set.\n" +
                "     * @return the CatAPTrue object itself.\n" +
                "     */\n" +
                "    public CatAPTrue setFriendly(Boolean friendly) {\n" +
                "        this.friendly = friendly;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    @Override\n" +
                "    public void validate() {\n" +
                "        super.validate();\n" +
                "    }\n" +
                "}\n");
        files.put("models/Error.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "\n" +
                "/** The Error model. */\n" +
                "@Fluent\n" +
                "public final class Error {\n" +
                "    /*\n" +
                "     * The status property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"status\")\n" +
                "    private Integer status;\n" +
                "\n" +
                "    /*\n" +
                "     * The message property.\n" +
                "     * \n" +
                "     */\n" +
                "    @JsonProperty(value = \"message\")\n" +
                "    private String message;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the status property: The status property.\n" +
                "     *\n" +
                "     * @return the status value.\n" +
                "     */\n" +
                "    public Integer getStatus() {\n" +
                "        return this.status;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the status property: The status property.\n" +
                "     *\n" +
                "     * @param status the status value to set.\n" +
                "     * @return the Error object itself.\n" +
                "     */\n" +
                "    public Error setStatus(Integer status) {\n" +
                "        this.status = status;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the message property: The message property.\n" +
                "     *\n" +
                "     * @return the message value.\n" +
                "     */\n" +
                "    public String getMessage() {\n" +
                "        return this.message;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the message property: The message property.\n" +
                "     *\n" +
                "     * @param message the message value to set.\n" +
                "     * @return the Error object itself.\n" +
                "     */\n" +
                "    public Error setMessage(String message) {\n" +
                "        this.message = message;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    public void validate() {}\n" +
                "}\n");
        files.put("ErrorException.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.exception.HttpResponseException;\n" +
                "import com.azure.core.http.HttpResponse;\n" +
                "\n" +
                "/** Exception thrown for an invalid response with Error information. */\n" +
                "public final class ErrorException extends HttpResponseException {\n" +
                "    /**\n" +
                "     * Initializes a new instance of the ErrorException class.\n" +
                "     *\n" +
                "     * @param message the exception message or the response content if a message is not available.\n" +
                "     * @param response the HTTP response.\n" +
                "     */\n" +
                "    public ErrorException(String message, HttpResponse response) {\n" +
                "        super(message, response);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Initializes a new instance of the ErrorException class.\n" +
                "     *\n" +
                "     * @param message the exception message or the response content if a message is not available.\n" +
                "     * @param response the HTTP response.\n" +
                "     * @param value the deserialized response value.\n" +
                "     */\n" +
                "    public ErrorException(String message, HttpResponse response, Error value) {\n" +
                "        super(message, response, value);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Error getValue() {\n" +
                "        return (Error) super.getValue();\n" +
                "    }\n" +
                "}\n");
        files.put("PetAPInProperties.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/** The PetAPInProperties model. */\n" +
                "@Fluent\n" +
                "public final class PetAPInProperties {\n" +
                "    /*\n" +
                "     * The id property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"id\", required = true)\n" +
                "    private int id;\n" +
                "\n" +
                "    /*\n" +
                "     * The name property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"name\")\n" +
                "    private String name;\n" +
                "\n" +
                "    /*\n" +
                "     * The status property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"status\", access = JsonProperty.Access.WRITE_ONLY)\n" +
                "    private Boolean status;\n" +
                "\n" +
                "    /*\n" +
                "     * Dictionary of <number>\n" +
                "     */\n" +
                "    @JsonProperty(value = \"additionalProperties\")\n" +
                "    private Map<String, Float> additionalProperties;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the id property: The id property.\n" +
                "     *\n" +
                "     * @return the id value.\n" +
                "     */\n" +
                "    public int getId() {\n" +
                "        return this.id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the id property: The id property.\n" +
                "     *\n" +
                "     * @param id the id value to set.\n" +
                "     * @return the PetAPInProperties object itself.\n" +
                "     */\n" +
                "    public PetAPInProperties setId(int id) {\n" +
                "        this.id = id;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the name property: The name property.\n" +
                "     *\n" +
                "     * @return the name value.\n" +
                "     */\n" +
                "    public String getName() {\n" +
                "        return this.name;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the name property: The name property.\n" +
                "     *\n" +
                "     * @param name the name value to set.\n" +
                "     * @return the PetAPInProperties object itself.\n" +
                "     */\n" +
                "    public PetAPInProperties setName(String name) {\n" +
                "        this.name = name;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the status property: The status property.\n" +
                "     *\n" +
                "     * @return the status value.\n" +
                "     */\n" +
                "    public Boolean isStatus() {\n" +
                "        return this.status;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the additionalProperties property: Dictionary of &lt;number&gt;.\n" +
                "     *\n" +
                "     * @return the additionalProperties value.\n" +
                "     */\n" +
                "    public Map<String, Float> getAdditionalProperties() {\n" +
                "        return this.additionalProperties;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the additionalProperties property: Dictionary of &lt;number&gt;.\n" +
                "     *\n" +
                "     * @param additionalProperties the additionalProperties value to set.\n" +
                "     * @return the PetAPInProperties object itself.\n" +
                "     */\n" +
                "    public PetAPInProperties setAdditionalProperties(Map<String, Float> additionalProperties) {\n" +
                "        this.additionalProperties = additionalProperties;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    public void validate() {}\n" +
                "}\n");
        files.put("PetAPInPropertiesWithAPString.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnyGetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnySetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/** The PetAPInPropertiesWithAPString model. */\n" +
                "@Fluent\n" +
                "public final class PetAPInPropertiesWithAPString {\n" +
                "    /*\n" +
                "     * The id property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"id\", required = true)\n" +
                "    private int id;\n" +
                "\n" +
                "    /*\n" +
                "     * The name property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"name\")\n" +
                "    private String name;\n" +
                "\n" +
                "    /*\n" +
                "     * The status property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"status\", access = JsonProperty.Access.WRITE_ONLY)\n" +
                "    private Boolean status;\n" +
                "\n" +
                "    /*\n" +
                "     * The @odata.location property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"@odata.location\", required = true)\n" +
                "    private String odataLocation;\n" +
                "\n" +
                "    /*\n" +
                "     * Dictionary of <number>\n" +
                "     */\n" +
                "    @JsonProperty(value = \"additionalProperties\")\n" +
                "    private Map<String, Float> AdditionalPropertiesProperty;\n" +
                "\n" +
                "    /*\n" +
                "     * Dictionary of <string>\n" +
                "     */\n" +
                "    @JsonIgnore private Map<String, String> additionalProperties;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the id property: The id property.\n" +
                "     *\n" +
                "     * @return the id value.\n" +
                "     */\n" +
                "    public int getId() {\n" +
                "        return this.id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the id property: The id property.\n" +
                "     *\n" +
                "     * @param id the id value to set.\n" +
                "     * @return the PetAPInPropertiesWithAPString object itself.\n" +
                "     */\n" +
                "    public PetAPInPropertiesWithAPString setId(int id) {\n" +
                "        this.id = id;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the name property: The name property.\n" +
                "     *\n" +
                "     * @return the name value.\n" +
                "     */\n" +
                "    public String getName() {\n" +
                "        return this.name;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the name property: The name property.\n" +
                "     *\n" +
                "     * @param name the name value to set.\n" +
                "     * @return the PetAPInPropertiesWithAPString object itself.\n" +
                "     */\n" +
                "    public PetAPInPropertiesWithAPString setName(String name) {\n" +
                "        this.name = name;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the status property: The status property.\n" +
                "     *\n" +
                "     * @return the status value.\n" +
                "     */\n" +
                "    public Boolean isStatus() {\n" +
                "        return this.status;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the odataLocation property: The @odata.location property.\n" +
                "     *\n" +
                "     * @return the odataLocation value.\n" +
                "     */\n" +
                "    public String getOdataLocation() {\n" +
                "        return this.odataLocation;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the odataLocation property: The @odata.location property.\n" +
                "     *\n" +
                "     * @param odataLocation the odataLocation value to set.\n" +
                "     * @return the PetAPInPropertiesWithAPString object itself.\n" +
                "     */\n" +
                "    public PetAPInPropertiesWithAPString setOdataLocation(String odataLocation) {\n" +
                "        this.odataLocation = odataLocation;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the AdditionalPropertiesProperty property: Dictionary of &lt;number&gt;.\n" +
                "     *\n" +
                "     * @return the AdditionalPropertiesProperty value.\n" +
                "     */\n" +
                "    public Map<String, Float> getAdditionalPropertiesProperty() {\n" +
                "        return this.AdditionalPropertiesProperty;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the AdditionalPropertiesProperty property: Dictionary of &lt;number&gt;.\n" +
                "     *\n" +
                "     * @param AdditionalPropertiesProperty the AdditionalPropertiesProperty value to set.\n" +
                "     * @return the PetAPInPropertiesWithAPString object itself.\n" +
                "     */\n" +
                "    public PetAPInPropertiesWithAPString setAdditionalPropertiesProperty(\n" +
                "            Map<String, Float> AdditionalPropertiesProperty) {\n" +
                "        this.AdditionalPropertiesProperty = AdditionalPropertiesProperty;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the additionalProperties property: Dictionary of &lt;string&gt;.\n" +
                "     *\n" +
                "     * @return the additionalProperties value.\n" +
                "     */\n" +
                "    @JsonAnyGetter\n" +
                "    public Map<String, String> getAdditionalProperties() {\n" +
                "        return this.additionalProperties;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the additionalProperties property: Dictionary of &lt;string&gt;.\n" +
                "     *\n" +
                "     * @param additionalProperties the additionalProperties value to set.\n" +
                "     * @return the PetAPInPropertiesWithAPString object itself.\n" +
                "     */\n" +
                "    public PetAPInPropertiesWithAPString setAdditionalProperties(Map<String, String> additionalProperties) {\n" +
                "        this.additionalProperties = additionalProperties;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @JsonAnySetter\n" +
                "    void setAdditionalProperties(String key, String value) {\n" +
                "        if (additionalProperties == null) {\n" +
                "            additionalProperties = new HashMap<>();\n" +
                "        }\n" +
                "        additionalProperties.put(key, value);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    public void validate() {\n" +
                "        if (getOdataLocation() == null) {\n" +
                "            throw new IllegalArgumentException(\n" +
                "                    \"Missing required property odataLocation in model PetAPInPropertiesWithAPString\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n");
        files.put("PetAPObject.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnyGetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnySetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/** The PetAPObject model. */\n" +
                "@Fluent\n" +
                "public final class PetAPObject {\n" +
                "    /*\n" +
                "     * The id property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"id\", required = true)\n" +
                "    private int id;\n" +
                "\n" +
                "    /*\n" +
                "     * The name property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"name\")\n" +
                "    private String name;\n" +
                "\n" +
                "    /*\n" +
                "     * The status property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"status\", access = JsonProperty.Access.WRITE_ONLY)\n" +
                "    private Boolean status;\n" +
                "\n" +
                "    /*\n" +
                "     * Dictionary of <any>\n" +
                "     */\n" +
                "    @JsonIgnore private Map<String, Object> additionalProperties;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the id property: The id property.\n" +
                "     *\n" +
                "     * @return the id value.\n" +
                "     */\n" +
                "    public int getId() {\n" +
                "        return this.id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the id property: The id property.\n" +
                "     *\n" +
                "     * @param id the id value to set.\n" +
                "     * @return the PetAPObject object itself.\n" +
                "     */\n" +
                "    public PetAPObject setId(int id) {\n" +
                "        this.id = id;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the name property: The name property.\n" +
                "     *\n" +
                "     * @return the name value.\n" +
                "     */\n" +
                "    public String getName() {\n" +
                "        return this.name;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the name property: The name property.\n" +
                "     *\n" +
                "     * @param name the name value to set.\n" +
                "     * @return the PetAPObject object itself.\n" +
                "     */\n" +
                "    public PetAPObject setName(String name) {\n" +
                "        this.name = name;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the status property: The status property.\n" +
                "     *\n" +
                "     * @return the status value.\n" +
                "     */\n" +
                "    public Boolean isStatus() {\n" +
                "        return this.status;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the additionalProperties property: Dictionary of &lt;any&gt;.\n" +
                "     *\n" +
                "     * @return the additionalProperties value.\n" +
                "     */\n" +
                "    @JsonAnyGetter\n" +
                "    public Map<String, Object> getAdditionalProperties() {\n" +
                "        return this.additionalProperties;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the additionalProperties property: Dictionary of &lt;any&gt;.\n" +
                "     *\n" +
                "     * @param additionalProperties the additionalProperties value to set.\n" +
                "     * @return the PetAPObject object itself.\n" +
                "     */\n" +
                "    public PetAPObject setAdditionalProperties(Map<String, Object> additionalProperties) {\n" +
                "        this.additionalProperties = additionalProperties;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @JsonAnySetter\n" +
                "    void setAdditionalProperties(String key, Object value) {\n" +
                "        if (additionalProperties == null) {\n" +
                "            additionalProperties = new HashMap<>();\n" +
                "        }\n" +
                "        additionalProperties.put(key, value);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    public void validate() {}\n" +
                "}\n");
        files.put("PetAPString.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnyGetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnySetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/** The PetAPString model. */\n" +
                "@Fluent\n" +
                "public final class PetAPString {\n" +
                "    /*\n" +
                "     * The id property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"id\", required = true)\n" +
                "    private int id;\n" +
                "\n" +
                "    /*\n" +
                "     * The name property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"name\")\n" +
                "    private String name;\n" +
                "\n" +
                "    /*\n" +
                "     * The status property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"status\", access = JsonProperty.Access.WRITE_ONLY)\n" +
                "    private Boolean status;\n" +
                "\n" +
                "    /*\n" +
                "     * Dictionary of <string>\n" +
                "     */\n" +
                "    @JsonIgnore private Map<String, String> additionalProperties;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the id property: The id property.\n" +
                "     *\n" +
                "     * @return the id value.\n" +
                "     */\n" +
                "    public int getId() {\n" +
                "        return this.id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the id property: The id property.\n" +
                "     *\n" +
                "     * @param id the id value to set.\n" +
                "     * @return the PetAPString object itself.\n" +
                "     */\n" +
                "    public PetAPString setId(int id) {\n" +
                "        this.id = id;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the name property: The name property.\n" +
                "     *\n" +
                "     * @return the name value.\n" +
                "     */\n" +
                "    public String getName() {\n" +
                "        return this.name;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the name property: The name property.\n" +
                "     *\n" +
                "     * @param name the name value to set.\n" +
                "     * @return the PetAPString object itself.\n" +
                "     */\n" +
                "    public PetAPString setName(String name) {\n" +
                "        this.name = name;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the status property: The status property.\n" +
                "     *\n" +
                "     * @return the status value.\n" +
                "     */\n" +
                "    public Boolean isStatus() {\n" +
                "        return this.status;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the additionalProperties property: Dictionary of &lt;string&gt;.\n" +
                "     *\n" +
                "     * @return the additionalProperties value.\n" +
                "     */\n" +
                "    @JsonAnyGetter\n" +
                "    public Map<String, String> getAdditionalProperties() {\n" +
                "        return this.additionalProperties;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the additionalProperties property: Dictionary of &lt;string&gt;.\n" +
                "     *\n" +
                "     * @param additionalProperties the additionalProperties value to set.\n" +
                "     * @return the PetAPString object itself.\n" +
                "     */\n" +
                "    public PetAPString setAdditionalProperties(Map<String, String> additionalProperties) {\n" +
                "        this.additionalProperties = additionalProperties;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @JsonAnySetter\n" +
                "    void setAdditionalProperties(String key, String value) {\n" +
                "        if (additionalProperties == null) {\n" +
                "            additionalProperties = new HashMap<>();\n" +
                "        }\n" +
                "        additionalProperties.put(key, value);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    public void validate() {}\n" +
                "}\n");
        files.put("PetAPTrue.java", "package fixtures.additionalproperties.models;\n" +
                "\n" +
                "import com.azure.core.annotation.Fluent;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnyGetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonAnySetter;\n" +
                "import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/** The PetAPTrue model. */\n" +
                "@Fluent\n" +
                "public class PetAPTrue {\n" +
                "    /*\n" +
                "     * The id property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"id\", required = true)\n" +
                "    private int id;\n" +
                "\n" +
                "    /*\n" +
                "     * The name property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"name\")\n" +
                "    private String name;\n" +
                "\n" +
                "    /*\n" +
                "     * The status property.\n" +
                "     */\n" +
                "    @JsonProperty(value = \"status\", access = JsonProperty.Access.WRITE_ONLY)\n" +
                "    private Boolean status;\n" +
                "\n" +
                "    /*\n" +
                "     * Dictionary of <any>\n" +
                "     */\n" +
                "    @JsonIgnore private Map<String, Object> additionalProperties;\n" +
                "\n" +
                "    /**\n" +
                "     * Get the id property: The id property.\n" +
                "     *\n" +
                "     * @return the id value.\n" +
                "     */\n" +
                "    public int getId() {\n" +
                "        return this.id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the id property: The id property.\n" +
                "     *\n" +
                "     * @param id the id value to set.\n" +
                "     * @return the PetAPTrue object itself.\n" +
                "     */\n" +
                "    public PetAPTrue setId(int id) {\n" +
                "        this.id = id;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the name property: The name property.\n" +
                "     *\n" +
                "     * @return the name value.\n" +
                "     */\n" +
                "    public String getName() {\n" +
                "        return this.name;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the name property: The name property.\n" +
                "     *\n" +
                "     * @param name the name value to set.\n" +
                "     * @return the PetAPTrue object itself.\n" +
                "     */\n" +
                "    public PetAPTrue setName(String name) {\n" +
                "        this.name = name;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the status property: The status property.\n" +
                "     *\n" +
                "     * @return the status value.\n" +
                "     */\n" +
                "    public Boolean isStatus() {\n" +
                "        return this.status;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Get the additionalProperties property: Dictionary of &lt;any&gt;.\n" +
                "     *\n" +
                "     * @return the additionalProperties value.\n" +
                "     */\n" +
                "    @JsonAnyGetter\n" +
                "    public Map<String, Object> getAdditionalProperties() {\n" +
                "        return this.additionalProperties;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the additionalProperties property: Dictionary of &lt;any&gt;.\n" +
                "     *\n" +
                "     * @param additionalProperties the additionalProperties value to set.\n" +
                "     * @return the PetAPTrue object itself.\n" +
                "     */\n" +
                "    public PetAPTrue setAdditionalProperties(Map<String, Object> additionalProperties) {\n" +
                "        this.additionalProperties = additionalProperties;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    @JsonAnySetter\n" +
                "    void setAdditionalProperties(String key, Object value) {\n" +
                "        if (additionalProperties == null) {\n" +
                "            additionalProperties = new HashMap<>();\n" +
                "        }\n" +
                "        additionalProperties.put(key, value);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Validates the instance.\n" +
                "     *\n" +
                "     * @throws IllegalArgumentException thrown if the instance is not valid.\n" +
                "     */\n" +
                "    public void validate() {}\n" +
                "}\n");
    }
}
