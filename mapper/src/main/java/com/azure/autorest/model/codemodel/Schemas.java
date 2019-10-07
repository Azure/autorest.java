package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * the full set of schemas for a given service, categorized into convenient collections
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "objects",
    "parameterGroups",
    "orCompounds",
    "andCompounds",
    "xorCompounds",
    "choices",
    "sealedChoices",
    "flags",
    "dictionaries",
    "constants",
    "primitives"
})
public class Schemas {

    /**
     * schemas that likely result in the creation of new objects during code generation
     * 
     */
    @JsonProperty("objects")
    @JsonPropertyDescription("schemas that likely result in the creation of new objects during code generation")
    private List<ObjectSchema> objects = new ArrayList<ObjectSchema>();
    /**
     * groups of parameters that can be built as an object
     * 
     */
    @JsonProperty("parameterGroups")
    @JsonPropertyDescription("groups of parameters that can be built as an object")
    private List<ParameterGroupSchema> parameterGroups = new ArrayList<ParameterGroupSchema>();
    /**
     * schemas that construct more complex schemas based on compound construction (ie, allOf, oneOf, anyOf)
     * 
     */
    @JsonProperty("orCompounds")
    @JsonPropertyDescription("schemas that construct more complex schemas based on compound construction (ie, allOf, oneOf, anyOf)")
    private List<OrSchema> orCompounds = new ArrayList<OrSchema>();
    @JsonProperty("andCompounds")
    private List<AndSchema> andCompounds = new ArrayList<AndSchema>();
    @JsonProperty("xorCompounds")
    private List<XorSchema> xorCompounds = new ArrayList<XorSchema>();
    /**
     * schemas that represent a set of choices (ie, 'enum')
     * 
     */
    @JsonProperty("choices")
    @JsonPropertyDescription("schemas that represent a set of choices (ie, 'enum')")
    private List<ChoiceSchema> choices = new ArrayList<ChoiceSchema>();
    /**
     * schemas that represent a set of choices that are sealed -- that can never have items added to the definition.
     * 
     */
    @JsonProperty("sealedChoices")
    @JsonPropertyDescription("schemas that represent a set of choices that are sealed -- that can never have items added to the definition.")
    private List<SealedChoiceSchema> sealedChoices = new ArrayList<SealedChoiceSchema>();
    @JsonProperty("flags")
    private List<FlagSchema> flags = new ArrayList<FlagSchema>();
    /**
     * schemas that represent key-value dictionaries used in the model.
     * 
     */
    @JsonProperty("dictionaries")
    @JsonPropertyDescription("schemas that represent key-value dictionaries used in the model.")
    private List<DictionarySchema> dictionaries = new ArrayList<DictionarySchema>();
    /**
     * constant values that are used in models and parameters
     * 
     */
    @JsonProperty("constants")
    @JsonPropertyDescription("constant values that are used in models and parameters")
    private List<ConstantSchema> constants = new ArrayList<ConstantSchema>();
    /**
     * primitive schemas that represent things that should be able to be represented without additional classes generated
     * 
     */
    @JsonProperty("primitives")
    @JsonPropertyDescription("primitive schemas that represent things that should be able to be represented without additional classes generated")
    private List<Object> primitives = new ArrayList<Object>();

    /**
     * schemas that likely result in the creation of new objects during code generation
     * 
     */
    @JsonProperty("objects")
    public List<ObjectSchema> getObjects() {
        return objects;
    }

    /**
     * schemas that likely result in the creation of new objects during code generation
     * 
     */
    @JsonProperty("objects")
    public void setObjects(List<ObjectSchema> objects) {
        this.objects = objects;
    }

    /**
     * groups of parameters that can be built as an object
     * 
     */
    @JsonProperty("parameterGroups")
    public List<ParameterGroupSchema> getParameterGroups() {
        return parameterGroups;
    }

    /**
     * groups of parameters that can be built as an object
     * 
     */
    @JsonProperty("parameterGroups")
    public void setParameterGroups(List<ParameterGroupSchema> parameterGroups) {
        this.parameterGroups = parameterGroups;
    }

    /**
     * schemas that construct more complex schemas based on compound construction (ie, allOf, oneOf, anyOf)
     * 
     */
    @JsonProperty("orCompounds")
    public List<OrSchema> getOrCompounds() {
        return orCompounds;
    }

    /**
     * schemas that construct more complex schemas based on compound construction (ie, allOf, oneOf, anyOf)
     * 
     */
    @JsonProperty("orCompounds")
    public void setOrCompounds(List<OrSchema> orCompounds) {
        this.orCompounds = orCompounds;
    }

    @JsonProperty("andCompounds")
    public List<AndSchema> getAndCompounds() {
        return andCompounds;
    }

    @JsonProperty("andCompounds")
    public void setAndCompounds(List<AndSchema> andCompounds) {
        this.andCompounds = andCompounds;
    }

    @JsonProperty("xorCompounds")
    public List<XorSchema> getXorCompounds() {
        return xorCompounds;
    }

    @JsonProperty("xorCompounds")
    public void setXorCompounds(List<XorSchema> xorCompounds) {
        this.xorCompounds = xorCompounds;
    }

    /**
     * schemas that represent a set of choices (ie, 'enum')
     * 
     */
    @JsonProperty("choices")
    public List<ChoiceSchema> getChoices() {
        return choices;
    }

    /**
     * schemas that represent a set of choices (ie, 'enum')
     * 
     */
    @JsonProperty("choices")
    public void setChoices(List<ChoiceSchema> choices) {
        this.choices = choices;
    }

    /**
     * schemas that represent a set of choices that are sealed -- that can never have items added to the definition.
     * 
     */
    @JsonProperty("sealedChoices")
    public List<SealedChoiceSchema> getSealedChoices() {
        return sealedChoices;
    }

    /**
     * schemas that represent a set of choices that are sealed -- that can never have items added to the definition.
     * 
     */
    @JsonProperty("sealedChoices")
    public void setSealedChoices(List<SealedChoiceSchema> sealedChoices) {
        this.sealedChoices = sealedChoices;
    }

    @JsonProperty("flags")
    public List<FlagSchema> getFlags() {
        return flags;
    }

    @JsonProperty("flags")
    public void setFlags(List<FlagSchema> flags) {
        this.flags = flags;
    }

    /**
     * schemas that represent key-value dictionaries used in the model.
     * 
     */
    @JsonProperty("dictionaries")
    public List<DictionarySchema> getDictionaries() {
        return dictionaries;
    }

    /**
     * schemas that represent key-value dictionaries used in the model.
     * 
     */
    @JsonProperty("dictionaries")
    public void setDictionaries(List<DictionarySchema> dictionaries) {
        this.dictionaries = dictionaries;
    }

    /**
     * constant values that are used in models and parameters
     * 
     */
    @JsonProperty("constants")
    public List<ConstantSchema> getConstants() {
        return constants;
    }

    /**
     * constant values that are used in models and parameters
     * 
     */
    @JsonProperty("constants")
    public void setConstants(List<ConstantSchema> constants) {
        this.constants = constants;
    }

    /**
     * primitive schemas that represent things that should be able to be represented without additional classes generated
     * 
     */
    @JsonProperty("primitives")
    public List<Object> getPrimitives() {
        return primitives;
    }

    /**
     * primitive schemas that represent things that should be able to be represented without additional classes generated
     * 
     */
    @JsonProperty("primitives")
    public void setPrimitives(List<Object> primitives) {
        this.primitives = primitives;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Schemas.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("objects");
        sb.append('=');
        sb.append(((this.objects == null)?"<null>":this.objects));
        sb.append(',');
        sb.append("parameterGroups");
        sb.append('=');
        sb.append(((this.parameterGroups == null)?"<null>":this.parameterGroups));
        sb.append(',');
        sb.append("orCompounds");
        sb.append('=');
        sb.append(((this.orCompounds == null)?"<null>":this.orCompounds));
        sb.append(',');
        sb.append("andCompounds");
        sb.append('=');
        sb.append(((this.andCompounds == null)?"<null>":this.andCompounds));
        sb.append(',');
        sb.append("xorCompounds");
        sb.append('=');
        sb.append(((this.xorCompounds == null)?"<null>":this.xorCompounds));
        sb.append(',');
        sb.append("choices");
        sb.append('=');
        sb.append(((this.choices == null)?"<null>":this.choices));
        sb.append(',');
        sb.append("sealedChoices");
        sb.append('=');
        sb.append(((this.sealedChoices == null)?"<null>":this.sealedChoices));
        sb.append(',');
        sb.append("flags");
        sb.append('=');
        sb.append(((this.flags == null)?"<null>":this.flags));
        sb.append(',');
        sb.append("dictionaries");
        sb.append('=');
        sb.append(((this.dictionaries == null)?"<null>":this.dictionaries));
        sb.append(',');
        sb.append("constants");
        sb.append('=');
        sb.append(((this.constants == null)?"<null>":this.constants));
        sb.append(',');
        sb.append("primitives");
        sb.append('=');
        sb.append(((this.primitives == null)?"<null>":this.primitives));
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
        result = ((result* 31)+((this.parameterGroups == null)? 0 :this.parameterGroups.hashCode()));
        result = ((result* 31)+((this.primitives == null)? 0 :this.primitives.hashCode()));
        result = ((result* 31)+((this.objects == null)? 0 :this.objects.hashCode()));
        result = ((result* 31)+((this.orCompounds == null)? 0 :this.orCompounds.hashCode()));
        result = ((result* 31)+((this.sealedChoices == null)? 0 :this.sealedChoices.hashCode()));
        result = ((result* 31)+((this.andCompounds == null)? 0 :this.andCompounds.hashCode()));
        result = ((result* 31)+((this.flags == null)? 0 :this.flags.hashCode()));
        result = ((result* 31)+((this.xorCompounds == null)? 0 :this.xorCompounds.hashCode()));
        result = ((result* 31)+((this.constants == null)? 0 :this.constants.hashCode()));
        result = ((result* 31)+((this.choices == null)? 0 :this.choices.hashCode()));
        result = ((result* 31)+((this.dictionaries == null)? 0 :this.dictionaries.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Schemas) == false) {
            return false;
        }
        Schemas rhs = ((Schemas) other);
        return ((((((((((((this.parameterGroups == rhs.parameterGroups)||((this.parameterGroups!= null)&&this.parameterGroups.equals(rhs.parameterGroups)))&&((this.primitives == rhs.primitives)||((this.primitives!= null)&&this.primitives.equals(rhs.primitives))))&&((this.objects == rhs.objects)||((this.objects!= null)&&this.objects.equals(rhs.objects))))&&((this.orCompounds == rhs.orCompounds)||((this.orCompounds!= null)&&this.orCompounds.equals(rhs.orCompounds))))&&((this.sealedChoices == rhs.sealedChoices)||((this.sealedChoices!= null)&&this.sealedChoices.equals(rhs.sealedChoices))))&&((this.andCompounds == rhs.andCompounds)||((this.andCompounds!= null)&&this.andCompounds.equals(rhs.andCompounds))))&&((this.flags == rhs.flags)||((this.flags!= null)&&this.flags.equals(rhs.flags))))&&((this.xorCompounds == rhs.xorCompounds)||((this.xorCompounds!= null)&&this.xorCompounds.equals(rhs.xorCompounds))))&&((this.constants == rhs.constants)||((this.constants!= null)&&this.constants.equals(rhs.constants))))&&((this.choices == rhs.choices)||((this.choices!= null)&&this.choices.equals(rhs.choices))))&&((this.dictionaries == rhs.dictionaries)||((this.dictionaries!= null)&&this.dictionaries.equals(rhs.dictionaries))));
    }

}
