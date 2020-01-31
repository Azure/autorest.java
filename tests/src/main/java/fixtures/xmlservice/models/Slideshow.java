package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The Slideshow model.
 */
@JacksonXmlRootElement(localName = "slideshow")
@Fluent
public final class Slideshow {
    /*
     * The title property.
     */
    @JacksonXmlProperty(localName = "title", isAttribute = true)
    private String title;

    /*
     * The date property.
     */
    @JacksonXmlProperty(localName = "date", isAttribute = true)
    private String date;

    /*
     * The author property.
     */
    @JacksonXmlProperty(localName = "author", isAttribute = true)
    private String author;

    /*
     * The slides property.
     */
    @JsonProperty("slide")
    private List<Slide> slides = new ArrayList<>();

    /**
     * Get the title property: The title property.
     * 
     * @return the title value.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title property: The title property.
     * 
     * @param title the title value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the date property: The date property.
     * 
     * @return the date value.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Set the date property: The date property.
     * 
     * @param date the date value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Get the author property: The author property.
     * 
     * @return the author value.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Set the author property: The author property.
     * 
     * @param author the author value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * Get the slides property: The slides property.
     * 
     * @return the slides value.
     */
    public List<Slide> getSlides() {
        return this.slides;
    }

    /**
     * Set the slides property: The slides property.
     * 
     * @param slides the slides value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setSlides(List<Slide> slides) {
        this.slides = slides;
        return this;
    }

    public void validate() {
        if (getSlides() != null) {
            getSlides().forEach(e -> e.validate());
        }
    }
}
