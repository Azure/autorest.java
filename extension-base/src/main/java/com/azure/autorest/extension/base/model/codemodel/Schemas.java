// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

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
    private List<BinarySchema> binaries = new ArrayList<>();
    private List<ObjectSchema> groups = new ArrayList<>();
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

    public List<ObjectSchema> getGroups() {
        return groups;
    }

    public void setGroups(List<ObjectSchema> groups) {
        this.groups = groups;
    }

    public List<BinarySchema> getBinaries() {
        return binaries;
    }

    public void setBinaries(List<BinarySchema> binaries) {
        this.binaries = binaries;
    }
}
