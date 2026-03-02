# Solve Revapi Error

The revapi configuration file is "eng/lintingconfigs/revapi/track2/revapi.json" in sdk repo.

The goal in this process is not to suppress every revapi error. It is to mitigate the breaks from "client.tsp" in specs repo.
It is OK that you cannot solve all of them and give a summary.

## Error that can be suppressed

DO NOT suppress revapi, if it does not fall in these categories.

### "java.method.visibilityReduced" and "java.method.removed" on `<init>()` or setter (`with###` method in model class)

```json
{
  "code": "java.method.visibilityReduced",
  "old" : {
    "matcher": "regex",
    "match": "method void com\\.azure\\.resourcemanager\\.<sdk-service>\\.models\\..*\\:\\:\\<init\\>\\(\\)"
  },
  "justification": "Output-only immutable models' constructors are now private."
}
```

```json
{
  "code": "java.method.visibilityReduced",
  "old" : {
    "matcher": "regex",
    "match": "method .* com\\.azure\\.resourcemanager\\.<sdk-service>\\.models\\..*\\:\\:with.*\\(.*\\).*"
  },
  "justification": "Output-only immutable models' setters are now package-private if it's being used by child class."
}
```

```json
{
  "code": "java.method.removed",
  "old" : {
    "matcher": "regex",
    "match": "method .* com\\.azure\\.resourcemanager\\.<sdk-service>\\.models\\..*\\:\\:with.*\\(.*\\).*"
  },
  "justification": "Output-only immutable models' setters are removed if no explicit usage."
}
```

### "java.class.removed" on List/ListResult/Collection class

```json
{
  "code": "java.class.removed",
  "old" : {
    "matcher": "regex",
    "match": "class com\\.azure\\.resourcemanager\\.<sdk-service>\\.models\\..*ListResult"
  },
  "justification": "Pageable models moved to implementation package. Unlikely used by user."
}
```

### "java.missing.oldSuperType" and "java.missing.newSuperType"

Use a narrower regex on class, if possible. E.g. `.*Resource`.

```json
{
  "regex": true,
  "code": "java\\.missing\\.(oldSuperType|newSuperType)",
  "old" : "class com\\.azure\\.resourcemanager\\.<sdk-service>\\.models\\..*",
  "justification": "TypeSpec fix for base resource class."
}
```

## Error that are candidates for fix

### "java.class.removed" and "java.method.removed" and "java.field.removed"

Check if this is caused by a naming difference, that can be fixed by `@@clientName(<model-or-property-or-method>, "<correct-name>", "java");` in "client.tsp".

The "CHANGELOG.md" file can be helpful, if e.g. you see
```md
"models.<ModelName>" was removed, and there is a similar "models.<NewModelName>" was added
```
or
```md
"<PropertyName>()" was removed, and there is a similar "<NewPropertyName>()" was added, in same "<ModelName>"
```
This indicates a rename is necessary.

Particularly, if you see
```md
"java.lang.Float" -> "java.lang.Double"
```
in "CHANGELOG.md", add "float32-as-double: false" to "tspconfig.yaml".

A `git diff` with "main" branch can also help you see what's changed in a class.

Whenever you've fixed in "client.tsp" or "tspconfig.yaml", run "Generate the Java code" step to update Java code (so that "Build the Java lib" would now build on the updated Java code).

## Error that should be reported

### "java.class.nowFinal" and "java.class.noLongerInheritsFromClass"

This should be reported.
