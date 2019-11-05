
package com.azure.autorest.model.codemodel;



/**
 * code model information
 * 
 */
public class Info {

    /**
     * the title of this service.
     * (Required)
     * 
     */
    private String title;
    /**
     * a text description of the service
     * 
     */
    private String description;
    /**
     * an uri to the terms of service specified to access the service
     * 
     */
    private String termsOfService;
    /**
     * contact information
     * 
     */
    private Contact contact;
    /**
     * license information
     * 
     */
    private License license;
    /**
     * a reference to external documentation
     * 
     */
    private ExternalDocumentation externalDocs;
    private DictionaryAny extensions;

    /**
     * the title of this service.
     * (Required)
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * the title of this service.
     * (Required)
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * a text description of the service
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * a text description of the service
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * an uri to the terms of service specified to access the service
     * 
     */
    public String getTermsOfService() {
        return termsOfService;
    }

    /**
     * an uri to the terms of service specified to access the service
     * 
     */
    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    /**
     * contact information
     * 
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * contact information
     * 
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * license information
     * 
     */
    public License getLicense() {
        return license;
    }

    /**
     * license information
     * 
     */
    public void setLicense(License license) {
        this.license = license;
    }

    /**
     * a reference to external documentation
     * 
     */
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * a reference to external documentation
     * 
     */
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public DictionaryAny getExtensions() {
        return extensions;
    }

    public void setExtensions(DictionaryAny extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Info.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("termsOfService");
        sb.append('=');
        sb.append(((this.termsOfService == null)?"<null>":this.termsOfService));
        sb.append(',');
        sb.append("contact");
        sb.append('=');
        sb.append(((this.contact == null)?"<null>":this.contact));
        sb.append(',');
        sb.append("license");
        sb.append('=');
        sb.append(((this.license == null)?"<null>":this.license));
        sb.append(',');
        sb.append("externalDocs");
        sb.append('=');
        sb.append(((this.externalDocs == null)?"<null>":this.externalDocs));
        sb.append(',');
        sb.append("extensions");
        sb.append('=');
        sb.append(((this.extensions == null)?"<null>":this.extensions));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.license == null)? 0 :this.license.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.contact == null)? 0 :this.contact.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.termsOfService == null)? 0 :this.termsOfService.hashCode()));
        result = ((result* 31)+((this.externalDocs == null)? 0 :this.externalDocs.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Info) == false) {
            return false;
        }
        Info rhs = ((Info) other);
        return ((((((((this.license == rhs.license)||((this.license!= null)&&this.license.equals(rhs.license)))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.contact == rhs.contact)||((this.contact!= null)&&this.contact.equals(rhs.contact))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.termsOfService == rhs.termsOfService)||((this.termsOfService!= null)&&this.termsOfService.equals(rhs.termsOfService))))&&((this.externalDocs == rhs.externalDocs)||((this.externalDocs!= null)&&this.externalDocs.equals(rhs.externalDocs))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))));
    }

}
