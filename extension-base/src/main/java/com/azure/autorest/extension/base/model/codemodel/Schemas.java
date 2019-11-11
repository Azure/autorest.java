
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * the full set of schemas for a given service, categorized into convenient collections
 * 
 */
public class Schemas {

    /**
     * a collection of items
     * 
     */
    private List<ArraySchema> arrays = new ArrayList<ArraySchema>();
    /**
     * an associative array (ie, dictionary, hashtable, etc)
     * 
     */
    private List<DictionarySchema> dictionaries = new ArrayList<DictionarySchema>();
    /**
     * a true or false value
     * 
     */
    private List<BooleanSchema> booleans = new ArrayList<BooleanSchema>();
    /**
     * a number value
     * 
     */
    private List<NumberSchema> numbers = new ArrayList<NumberSchema>();
    /**
     * an object of some type
     * 
     */
    private List<ObjectSchema> objects = new ArrayList<ObjectSchema>();
    /**
     * a string of characters
     * (Required)
     * 
     */
    private List<StringSchema> strings = new ArrayList<StringSchema>();
    /**
     * UnixTime
     * 
     */
    private List<UnixTimeSchema> unixtimes = new ArrayList<UnixTimeSchema>();
    /**
     * ByteArray -- an array of bytes
     * 
     */
    private List<ByteArraySchema> byteArrays = new ArrayList<ByteArraySchema>();
    private List<Schema> streams = new ArrayList<Schema>();
    /**
     * a single character
     * 
     */
    private List<CharSchema> chars = new ArrayList<CharSchema>();
    /**
     * a Date
     * 
     */
    private List<DateSchema> dates = new ArrayList<DateSchema>();
    /**
     * a DateTime
     * 
     */
    private List<DateTimeSchema> dateTimes = new ArrayList<DateTimeSchema>();
    /**
     * a Duration
     * 
     */
    private List<DurationSchema> durations = new ArrayList<DurationSchema>();
    /**
     * a universally unique identifier
     * 
     */
    private List<UuidSchema> uuids = new ArrayList<UuidSchema>();
    /**
     * an URI of some kind
     * 
     */
    private List<UriSchema> uris = new ArrayList<UriSchema>();
    /**
     * a password or credential
     * 
     */
    private List<CredentialSchema> credentials = new ArrayList<CredentialSchema>();
    /**
     * OData Query
     * 
     */
    private List<ODataQuerySchema> odataQueries = new ArrayList<ODataQuerySchema>();
    /**
     * - this is essentially can be thought of as an 'enum' 
     * that is a choice between one of several strings
     * 
     */
    private List<ChoiceSchema> choices = new ArrayList<ChoiceSchema>();
    private List<SealedChoiceSchema> sealedChoices = new ArrayList<SealedChoiceSchema>();
    private List<FlagSchema> flags = new ArrayList<FlagSchema>();
    /**
     * a constant value
     * 
     */
    private List<ConstantSchema> constants = new ArrayList<ConstantSchema>();
    private List<AndSchema> ands = new ArrayList<AndSchema>();
    private List<OrSchema> ors = new ArrayList<OrSchema>();
    private List<XorSchema> xors = new ArrayList<XorSchema>();
    /**
     * it's possible that we just may make this an error 
     * in representation.
     * 
     */
    private List<Schema> unknowns = new ArrayList<Schema>();
    private List<ParameterGroupSchema> parameterGroups = new ArrayList<ParameterGroupSchema>();

    /**
     * a collection of items
     * 
     */
    public List<ArraySchema> getArrays() {
        return arrays;
    }

    /**
     * a collection of items
     * 
     */
    public void setArrays(List<ArraySchema> arrays) {
        this.arrays = arrays;
    }

    /**
     * an associative array (ie, dictionary, hashtable, etc)
     * 
     */
    public List<DictionarySchema> getDictionaries() {
        return dictionaries;
    }

    /**
     * an associative array (ie, dictionary, hashtable, etc)
     * 
     */
    public void setDictionaries(List<DictionarySchema> dictionaries) {
        this.dictionaries = dictionaries;
    }

    /**
     * a true or false value
     * 
     */
    public List<BooleanSchema> getBooleans() {
        return booleans;
    }

    /**
     * a true or false value
     * 
     */
    public void setBooleans(List<BooleanSchema> booleans) {
        this.booleans = booleans;
    }

    /**
     * a number value
     * 
     */
    public List<NumberSchema> getNumbers() {
        return numbers;
    }

    /**
     * a number value
     * 
     */
    public void setNumbers(List<NumberSchema> numbers) {
        this.numbers = numbers;
    }

    /**
     * an object of some type
     * 
     */
    public List<ObjectSchema> getObjects() {
        return objects;
    }

    /**
     * an object of some type
     * 
     */
    public void setObjects(List<ObjectSchema> objects) {
        this.objects = objects;
    }

    /**
     * a string of characters
     * (Required)
     * 
     */
    public List<StringSchema> getStrings() {
        return strings;
    }

    /**
     * a string of characters
     * (Required)
     * 
     */
    public void setStrings(List<StringSchema> strings) {
        this.strings = strings;
    }

    /**
     * UnixTime
     * 
     */
    public List<UnixTimeSchema> getUnixtimes() {
        return unixtimes;
    }

    /**
     * UnixTime
     * 
     */
    public void setUnixtimes(List<UnixTimeSchema> unixtimes) {
        this.unixtimes = unixtimes;
    }

    /**
     * ByteArray -- an array of bytes
     * 
     */
    public List<ByteArraySchema> getByteArrays() {
        return byteArrays;
    }

    /**
     * ByteArray -- an array of bytes
     * 
     */
    public void setByteArrays(List<ByteArraySchema> byteArrays) {
        this.byteArrays = byteArrays;
    }

    public List<Schema> getStreams() {
        return streams;
    }

    public void setStreams(List<Schema> streams) {
        this.streams = streams;
    }

    /**
     * a single character
     * 
     */
    public List<CharSchema> getChars() {
        return chars;
    }

    /**
     * a single character
     * 
     */
    public void setChars(List<CharSchema> chars) {
        this.chars = chars;
    }

    /**
     * a Date
     * 
     */
    public List<DateSchema> getDates() {
        return dates;
    }

    /**
     * a Date
     * 
     */
    public void setDates(List<DateSchema> dates) {
        this.dates = dates;
    }

    /**
     * a DateTime
     * 
     */
    public List<DateTimeSchema> getDateTimes() {
        return dateTimes;
    }

    /**
     * a DateTime
     * 
     */
    public void setDateTimes(List<DateTimeSchema> dateTimes) {
        this.dateTimes = dateTimes;
    }

    /**
     * a Duration
     * 
     */
    public List<DurationSchema> getDurations() {
        return durations;
    }

    /**
     * a Duration
     * 
     */
    public void setDurations(List<DurationSchema> durations) {
        this.durations = durations;
    }

    /**
     * a universally unique identifier
     * 
     */
    public List<UuidSchema> getUuids() {
        return uuids;
    }

    /**
     * a universally unique identifier
     * 
     */
    public void setUuids(List<UuidSchema> uuids) {
        this.uuids = uuids;
    }

    /**
     * an URI of some kind
     * 
     */
    public List<UriSchema> getUris() {
        return uris;
    }

    /**
     * an URI of some kind
     * 
     */
    public void setUris(List<UriSchema> uris) {
        this.uris = uris;
    }

    /**
     * a password or credential
     * 
     */
    public List<CredentialSchema> getCredentials() {
        return credentials;
    }

    /**
     * a password or credential
     * 
     */
    public void setCredentials(List<CredentialSchema> credentials) {
        this.credentials = credentials;
    }

    /**
     * OData Query
     * 
     */
    public List<ODataQuerySchema> getOdataQueries() {
        return odataQueries;
    }

    /**
     * OData Query
     * 
     */
    public void setOdataQueries(List<ODataQuerySchema> odataQueries) {
        this.odataQueries = odataQueries;
    }

    /**
     * - this is essentially can be thought of as an 'enum' 
     * that is a choice between one of several strings
     * 
     */
    public List<ChoiceSchema> getChoices() {
        return choices;
    }

    /**
     * - this is essentially can be thought of as an 'enum' 
     * that is a choice between one of several strings
     * 
     */
    public void setChoices(List<ChoiceSchema> choices) {
        this.choices = choices;
    }

    public List<SealedChoiceSchema> getSealedChoices() {
        return sealedChoices;
    }

    public void setSealedChoices(List<SealedChoiceSchema> sealedChoices) {
        this.sealedChoices = sealedChoices;
    }

    public List<FlagSchema> getFlags() {
        return flags;
    }

    public void setFlags(List<FlagSchema> flags) {
        this.flags = flags;
    }

    /**
     * a constant value
     * 
     */
    public List<ConstantSchema> getConstants() {
        return constants;
    }

    /**
     * a constant value
     * 
     */
    public void setConstants(List<ConstantSchema> constants) {
        this.constants = constants;
    }

    public List<AndSchema> getAnds() {
        return ands;
    }

    public void setAnds(List<AndSchema> ands) {
        this.ands = ands;
    }

    public List<OrSchema> getOrs() {
        return ors;
    }

    public void setOrs(List<OrSchema> ors) {
        this.ors = ors;
    }

    public List<XorSchema> getXors() {
        return xors;
    }

    public void setXors(List<XorSchema> xors) {
        this.xors = xors;
    }

    /**
     * it's possible that we just may make this an error 
     * in representation.
     * 
     */
    public List<Schema> getUnknowns() {
        return unknowns;
    }

    /**
     * it's possible that we just may make this an error 
     * in representation.
     * 
     */
    public void setUnknowns(List<Schema> unknowns) {
        this.unknowns = unknowns;
    }

    public List<ParameterGroupSchema> getParameterGroups() {
        return parameterGroups;
    }

    public void setParameterGroups(List<ParameterGroupSchema> parameterGroups) {
        this.parameterGroups = parameterGroups;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Schemas.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("arrays");
        sb.append('=');
        sb.append(((this.arrays == null)?"<null>":this.arrays));
        sb.append(',');
        sb.append("dictionaries");
        sb.append('=');
        sb.append(((this.dictionaries == null)?"<null>":this.dictionaries));
        sb.append(',');
        sb.append("booleans");
        sb.append('=');
        sb.append(((this.booleans == null)?"<null>":this.booleans));
        sb.append(',');
        sb.append("numbers");
        sb.append('=');
        sb.append(((this.numbers == null)?"<null>":this.numbers));
        sb.append(',');
        sb.append("objects");
        sb.append('=');
        sb.append(((this.objects == null)?"<null>":this.objects));
        sb.append(',');
        sb.append("strings");
        sb.append('=');
        sb.append(((this.strings == null)?"<null>":this.strings));
        sb.append(',');
        sb.append("unixtimes");
        sb.append('=');
        sb.append(((this.unixtimes == null)?"<null>":this.unixtimes));
        sb.append(',');
        sb.append("byteArrays");
        sb.append('=');
        sb.append(((this.byteArrays == null)?"<null>":this.byteArrays));
        sb.append(',');
        sb.append("streams");
        sb.append('=');
        sb.append(((this.streams == null)?"<null>":this.streams));
        sb.append(',');
        sb.append("chars");
        sb.append('=');
        sb.append(((this.chars == null)?"<null>":this.chars));
        sb.append(',');
        sb.append("dates");
        sb.append('=');
        sb.append(((this.dates == null)?"<null>":this.dates));
        sb.append(',');
        sb.append("dateTimes");
        sb.append('=');
        sb.append(((this.dateTimes == null)?"<null>":this.dateTimes));
        sb.append(',');
        sb.append("durations");
        sb.append('=');
        sb.append(((this.durations == null)?"<null>":this.durations));
        sb.append(',');
        sb.append("uuids");
        sb.append('=');
        sb.append(((this.uuids == null)?"<null>":this.uuids));
        sb.append(',');
        sb.append("uris");
        sb.append('=');
        sb.append(((this.uris == null)?"<null>":this.uris));
        sb.append(',');
        sb.append("credentials");
        sb.append('=');
        sb.append(((this.credentials == null)?"<null>":this.credentials));
        sb.append(',');
        sb.append("odataQueries");
        sb.append('=');
        sb.append(((this.odataQueries == null)?"<null>":this.odataQueries));
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
        sb.append("constants");
        sb.append('=');
        sb.append(((this.constants == null)?"<null>":this.constants));
        sb.append(',');
        sb.append("ands");
        sb.append('=');
        sb.append(((this.ands == null)?"<null>":this.ands));
        sb.append(',');
        sb.append("ors");
        sb.append('=');
        sb.append(((this.ors == null)?"<null>":this.ors));
        sb.append(',');
        sb.append("xors");
        sb.append('=');
        sb.append(((this.xors == null)?"<null>":this.xors));
        sb.append(',');
        sb.append("unknowns");
        sb.append('=');
        sb.append(((this.unknowns == null)?"<null>":this.unknowns));
        sb.append(',');
        sb.append("parameterGroups");
        sb.append('=');
        sb.append(((this.parameterGroups == null)?"<null>":this.parameterGroups));
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
        result = ((result* 31)+((this.xors == null)? 0 :this.xors.hashCode()));
        result = ((result* 31)+((this.odataQueries == null)? 0 :this.odataQueries.hashCode()));
        result = ((result* 31)+((this.credentials == null)? 0 :this.credentials.hashCode()));
        result = ((result* 31)+((this.unknowns == null)? 0 :this.unknowns.hashCode()));
        result = ((result* 31)+((this.objects == null)? 0 :this.objects.hashCode()));
        result = ((result* 31)+((this.numbers == null)? 0 :this.numbers.hashCode()));
        result = ((result* 31)+((this.flags == null)? 0 :this.flags.hashCode()));
        result = ((result* 31)+((this.ands == null)? 0 :this.ands.hashCode()));
        result = ((result* 31)+((this.parameterGroups == null)? 0 :this.parameterGroups.hashCode()));
        result = ((result* 31)+((this.strings == null)? 0 :this.strings.hashCode()));
        result = ((result* 31)+((this.byteArrays == null)? 0 :this.byteArrays.hashCode()));
        result = ((result* 31)+((this.booleans == null)? 0 :this.booleans.hashCode()));
        result = ((result* 31)+((this.durations == null)? 0 :this.durations.hashCode()));
        result = ((result* 31)+((this.constants == null)? 0 :this.constants.hashCode()));
        result = ((result* 31)+((this.streams == null)? 0 :this.streams.hashCode()));
        result = ((result* 31)+((this.sealedChoices == null)? 0 :this.sealedChoices.hashCode()));
        result = ((result* 31)+((this.dates == null)? 0 :this.dates.hashCode()));
        result = ((result* 31)+((this.unixtimes == null)? 0 :this.unixtimes.hashCode()));
        result = ((result* 31)+((this.uris == null)? 0 :this.uris.hashCode()));
        result = ((result* 31)+((this.ors == null)? 0 :this.ors.hashCode()));
        result = ((result* 31)+((this.dateTimes == null)? 0 :this.dateTimes.hashCode()));
        result = ((result* 31)+((this.arrays == null)? 0 :this.arrays.hashCode()));
        result = ((result* 31)+((this.choices == null)? 0 :this.choices.hashCode()));
        result = ((result* 31)+((this.chars == null)? 0 :this.chars.hashCode()));
        result = ((result* 31)+((this.dictionaries == null)? 0 :this.dictionaries.hashCode()));
        result = ((result* 31)+((this.uuids == null)? 0 :this.uuids.hashCode()));
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
        return (((((((((((((((((((((((((((this.xors == rhs.xors)||((this.xors!= null)&&this.xors.equals(rhs.xors)))&&((this.odataQueries == rhs.odataQueries)||((this.odataQueries!= null)&&this.odataQueries.equals(rhs.odataQueries))))&&((this.credentials == rhs.credentials)||((this.credentials!= null)&&this.credentials.equals(rhs.credentials))))&&((this.unknowns == rhs.unknowns)||((this.unknowns!= null)&&this.unknowns.equals(rhs.unknowns))))&&((this.objects == rhs.objects)||((this.objects!= null)&&this.objects.equals(rhs.objects))))&&((this.numbers == rhs.numbers)||((this.numbers!= null)&&this.numbers.equals(rhs.numbers))))&&((this.flags == rhs.flags)||((this.flags!= null)&&this.flags.equals(rhs.flags))))&&((this.ands == rhs.ands)||((this.ands!= null)&&this.ands.equals(rhs.ands))))&&((this.parameterGroups == rhs.parameterGroups)||((this.parameterGroups!= null)&&this.parameterGroups.equals(rhs.parameterGroups))))&&((this.strings == rhs.strings)||((this.strings!= null)&&this.strings.equals(rhs.strings))))&&((this.byteArrays == rhs.byteArrays)||((this.byteArrays!= null)&&this.byteArrays.equals(rhs.byteArrays))))&&((this.booleans == rhs.booleans)||((this.booleans!= null)&&this.booleans.equals(rhs.booleans))))&&((this.durations == rhs.durations)||((this.durations!= null)&&this.durations.equals(rhs.durations))))&&((this.constants == rhs.constants)||((this.constants!= null)&&this.constants.equals(rhs.constants))))&&((this.streams == rhs.streams)||((this.streams!= null)&&this.streams.equals(rhs.streams))))&&((this.sealedChoices == rhs.sealedChoices)||((this.sealedChoices!= null)&&this.sealedChoices.equals(rhs.sealedChoices))))&&((this.dates == rhs.dates)||((this.dates!= null)&&this.dates.equals(rhs.dates))))&&((this.unixtimes == rhs.unixtimes)||((this.unixtimes!= null)&&this.unixtimes.equals(rhs.unixtimes))))&&((this.uris == rhs.uris)||((this.uris!= null)&&this.uris.equals(rhs.uris))))&&((this.ors == rhs.ors)||((this.ors!= null)&&this.ors.equals(rhs.ors))))&&((this.dateTimes == rhs.dateTimes)||((this.dateTimes!= null)&&this.dateTimes.equals(rhs.dateTimes))))&&((this.arrays == rhs.arrays)||((this.arrays!= null)&&this.arrays.equals(rhs.arrays))))&&((this.choices == rhs.choices)||((this.choices!= null)&&this.choices.equals(rhs.choices))))&&((this.chars == rhs.chars)||((this.chars!= null)&&this.chars.equals(rhs.chars))))&&((this.dictionaries == rhs.dictionaries)||((this.dictionaries!= null)&&this.dictionaries.equals(rhs.dictionaries))))&&((this.uuids == rhs.uuids)||((this.uuids!= null)&&this.uuids.equals(rhs.uuids))));
    }

}
