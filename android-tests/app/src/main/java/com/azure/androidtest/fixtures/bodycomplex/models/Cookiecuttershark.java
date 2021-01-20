package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

/**
 * The Cookiecuttershark model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("cookiecuttershark")
@Immutable
public final class Cookiecuttershark extends Shark {
}
