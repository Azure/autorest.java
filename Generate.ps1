$AUTOREST_CORE_VERSION = "3.8.4"
$VANILLA_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=vanilla-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL"
$AZURE_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=azure-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL"
# $ARM_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=azure-tests --azure-arm --fluent=lite --regenerate-pom=false"
$PROTOCOL_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=protocol-tests --data-plane --generate-samples"
$PROTOCOL_RESILIENCE_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --data-plane"
$SWAGGER_PATH = "node_modules/@microsoft.azure/autorest.testserver/swagger"
$ExitCode = 0

function Generate([string]$Arguments) {
    $generateOutput = Invoke-Expression "autorest $Arguments"
    $global:ExitCode = $global:ExitCode -bor $LASTEXITCODE

    Write-Host "
========================
autorest $Arguments
========================
$([String]::Join("`n", $generateOutput))
    "
}

java -version

if (Test-Path ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java) {
    Move-Item -Path ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java -Destination ./vanilla-tests/swagger/CoverageReporter.java -Force
}

if (Test-Path ./vanilla-tests/src/main) {
    Remove-Item ./vanilla-tests/src/main -Recurse -Force
}

Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/additionalProperties.json --namespace=fixtures.additionalproperties"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-array.json --namespace=fixtures.bodyarray"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-boolean.json --namespace=fixtures.bodyboolean --context-client-method-parameter --client-logger"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.bodycomplex --required-fields-as-ctor-args --client-logger --output-model-immutable --generate-tests"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.streamstyleserialization --enable-sync-stack --stream-style-serialization --required-fields-as-ctor-args --client-logger --pass-discriminator-to-child-deserialization"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-file.json --namespace=fixtures.bodyfile --context-client-method-parameter"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-string.json --namespace=fixtures.bodystring --generate-client-interfaces"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl.json --namespace=fixtures.custombaseuri"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/head.json --namespace=fixtures.head"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/head-exceptions.json --namespace=fixtures.headexceptions"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.header --context-client-method-parameter"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.nonamedresponsetypes --context-client-method-parameter --generic-response-type"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-dictionary.json --namespace=fixtures.bodydictionary --generate-sync-async-clients --generate-send-request-method"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-duration.json --namespace=fixtures.bodyduration"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-integer.json --namespace=fixtures.bodyinteger"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-number.json --namespace=fixtures.bodynumber --disable-client-builder"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/httpInfrastructure.json --namespace=fixtures.httpinfrastructure"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-byte.json --namespace=fixtures.bodybyte"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-date.json --namespace=fixtures.bodydate"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-datetime.json --namespace=fixtures.bodydatetime"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/url.json --namespace=fixtures.url"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/url-multi-collectionFormat.json --namespace=fixtures.url.multi"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/extensible-enums-swagger.json --namespace=fixtures.extensibleenums"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/required-optional.json --namespace=fixtures.requiredoptional"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/xml-service.json --namespace=fixtures.xmlservice --enable-xml"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH%/xml-service.json --namespace=fixtures.streamstylexmlserialization --stream-style-serialization --enable-xml"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1 --optional-constant-as-enum=true"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1 --modelerfour.lenient-model-deduplication"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/validation.json --namespace=fixtures.validation"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/non-string-enum.json --namespace=fixtures.nonstringenum --generate-sync-async-clients --disable-client-builder"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/multiple-inheritance.json --namespace=fixtures.multipleinheritance"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/report.json --namespace=fixtures.report --payload-flattening-threshold=1"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-formdata-urlencoded.json --namespace=fixtures.bodyformdataurlencoded"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/constants.json --namespace=fixtures.constants"
Generate -Arguments "--version=$AUTOREST_CORE_VERSION --use=./ vanilla-tests/swagger/lro.md"
Generate -Arguments "--version=$AUTOREST_CORE_VERSION --use=./ vanilla-tests/swagger/custom-http-exception-mapping.md"

if (Test-Path ./vanilla-tests/swagger/CoverageReporter.java) {
    Move-Item ./vanilla-tests/swagger/CoverageReporter.java ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java -Force
}

# local swagger
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening --client-flattened-annotation-target=FIELD"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.requirexmsflattened --require-x-ms-flattened-to-flatten=true"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.noflatten --modelerfour.flatten-models=false"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.clientflatten --modelerfour.flatten-models=false --client-flattened-annotation-target=NONE"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.clientdefaultvalue --modelerfour.flatten-models=false"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.donotpassdiscriminator"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.passdiscriminator --pass-discriminator-to-child-deserialization=true"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.annotatedgettersandsetters --annotate-getters-and-setters-for-serialization=true"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/xml-tag-with-attribute-and-value.json --namespace=fixtures.complexxmltag --enable-xml"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/xml-tag-with-attribute-and-value.json --namespace=fixtures.complexstreamstylexmlserialization --stream-style-serialization --enable-xml"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/security-info.json --namespace=fixtures.securityinfo"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader"
Generate -Arguments "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/required-fields-as-ctor-args-transformation.json --namespace=fixtures.requiredfieldsascotrargstransformation --required-fields-as-ctor-args=true --output-model-immutable"

# Azure
Generate -Arguments "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1 --generate-sync-async-clients --context-client-method-parameter"
Generate -Arguments "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl-paging.json --namespace=fixtures.custombaseuri.paging --payload-flattening-threshold=1"
Generate -Arguments "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-special-properties.json --namespace=fixtures.azurespecials --payload-flattening-threshold=1 --context-client-method-parameter"
Generate -Arguments "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-parameter-grouping.json --namespace=fixtures.azureparametergrouping --payload-flattening-threshold=1"
Generate -Arguments "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/subscriptionId-apiVersion.json --namespace=fixtures.subscriptionidapiversion --payload-flattening-threshold=1"
Generate -Arguments "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1"

# Generate -Arguments "$ARM_ARGUMENTS --input-file=$SWAGGER_PATH/lro.json --namespace=fixtures.lro"
# Generate -Arguments "$ARM_ARGUMENTS --input-file=$SWAGGER_PATH/lro-parameterized-endpoints.json --namespace=fixtures.lroparameterizedendpoints"
# Remove-Item ./azure-tests/src/main/java/module-info.java -Force

# Protocol
Remove-Item ./protocol-tests/src/main -Recurse -Force
Remove-Item ./protocol-tests/src/samples -Recurse -Force
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-string.json --namespace=fixtures.bodystring"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/paging.json --namespace=fixtures.paging"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.bodycomplex"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.header"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/httpInfrastructure.json --namespace=fixtures.httpinfrastructure"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/media_types.json --namespace=fixtures.mediatypes"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/url.json --namespace=fixtures.url"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/url-multi-collectionFormat.json --namespace=fixtures.url.multi"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/lro.json --namespace=fixtures.lro"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-initial.json --namespace=fixtures.llcinitial"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-update1.json --namespace=fixtures.llcupdate1 --generate-send-request-method"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/parameterized-endpoint.json --namespace=fixtures.parameterizedendpoint --generate-send-request-method"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/constants.json --namespace=fixtures.constants"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-file.json --namespace=fixtures.bodyfile"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/required-header-query.json --namespace=fixtures.requiredheaderquery"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/constant-and-from-client-parameters.json --namespace=fixtures.constantandclientparam"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/multi-media-types.json --namespace=fixtures.multimediatypes"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/required-optional-body.json --namespace=fixtures.requiredoptionalbody"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/enums.json --namespace=fixtures.enums"
Generate -Arguments "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/endpoint-lro.json --namespace=fixtures.endpointlro"
Generate -Arguments "--version=$AUTOREST_CORE_VERSION --use=./ protocol-tests/swagger/dpg-customization.md"
Generate -Arguments "--version=$AUTOREST_CORE_VERSION --use=./ protocol-tests/swagger/custom-http-exception-mapping.md"
New-Item ./protocol-tests/src/main/java/fixtures/headexceptions/models -ItemType Directory -Force
Copy-Item -Path ./protocol-tests/swagger/CustomizedException.java -Destination ./protocol-tests/src/main/java/fixtures/headexceptions/models/CustomizedException.java -Force
Remove-Item ./protocol-tests/src/main/java/module-info.java -Force

# Protocol resilience
Remove-Item ./protocol-resilience-test/llcinitial/src/main -Recurse -Force
Remove-Item ./protocol-resilience-test/llcupdate1/src/main -Recurse -Force
Generate -Arguments "$PROTOCOL_RESILIENCE_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-initial.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcinitial"
Generate -Arguments "$PROTOCOL_RESILIENCE_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-update1.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcupdate1"
Remove-Item ./protocol-resilience-test/llcinitial/src/main/java/module-info.java -Force
Remove-Item ./protocol-resilience-test/llcupdate1/src/main/java/module-info.java -Force

# customization
Generate -Arguments "--version=$AUTOREST_CORE_VERSION --use:. customization-tests/swagger"

# partial update tests
Generate -Arguments "--version=$AUTOREST_CORE_VERSION --use:. partial-update-tests/existing/swagger/README.md"
Remove-Item ./partial-update-tests/generated/src/main/java/module-info.java -Force

# docs
Generate -Arguments "--use:. docs/samples/specification/azure_key_credential/readme.md"
Generate -Arguments "--use:. docs/samples/specification/basic/readme.md"
Generate -Arguments "--use:. docs/samples/specification/management/readme.md"

exit $ExitCode
