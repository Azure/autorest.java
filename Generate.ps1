$AUTOREST_CORE_VERSION = "3.8.4"
$VANILLA_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=vanilla-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL"
$AZURE_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=azure-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL"
# $ARM_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=azure-tests --azure-arm --fluent=lite --regenerate-pom=false"
$PROTOCOL_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=protocol-tests --data-plane --generate-samples"
$PROTOCOL_RESILIENCE_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --data-plane"
$SWAGGER_PATH = "node_modules/@microsoft.azure/autorest.testserver/swagger"
$ExitCode = 0

function generate($Arguments) {
    Write-Host "========================"
    Write-Host "autorest $Arguments"
    Write-Host "========================"

    autorest $Arguments
    $ExitCode = $ExitCode -bor $LASTEXITCODE
}

java -version

Move-Item -Path ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java -Destination ./vanilla-tests/swagger/CoverageReporter.java -Force
Remove-Item ./vanilla-tests/src/main -Recurse -Force

generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/additionalProperties.json --namespace=fixtures.additionalproperties")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-array.json --namespace=fixtures.bodyarray")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-boolean.json --namespace=fixtures.bodyboolean --context-client-method-parameter --client-logger")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.bodycomplex --required-fields-as-ctor-args --client-logger --output-model-immutable --generate-tests")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.streamstyleserialization --enable-sync-stack --stream-style-serialization --required-fields-as-ctor-args --client-logger --pass-discriminator-to-child-deserialization")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-file.json --namespace=fixtures.bodyfile --context-client-method-parameter")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-string.json --namespace=fixtures.bodystring --generate-client-interfaces")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl.json --namespace=fixtures.custombaseuri")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/head.json --namespace=fixtures.head")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/head-exceptions.json --namespace=fixtures.headexceptions")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.header --context-client-method-parameter")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.nonamedresponsetypes --context-client-method-parameter --generic-response-type")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-dictionary.json --namespace=fixtures.bodydictionary --generate-sync-async-clients --generate-send-request-method")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-duration.json --namespace=fixtures.bodyduration")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-integer.json --namespace=fixtures.bodyinteger")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-number.json --namespace=fixtures.bodynumber --disable-client-builder")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/httpInfrastructure.json --namespace=fixtures.httpinfrastructure")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-byte.json --namespace=fixtures.bodybyte")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-date.json --namespace=fixtures.bodydate")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-datetime.json --namespace=fixtures.bodydatetime")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/url.json --namespace=fixtures.url")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/url-multi-collectionFormat.json --namespace=fixtures.url.multi")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/extensible-enums-swagger.json --namespace=fixtures.extensibleenums")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/required-optional.json --namespace=fixtures.requiredoptional")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/xml-service.json --namespace=fixtures.xmlservice --enable-xml")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1 --optional-constant-as-enum=true")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1 --modelerfour.lenient-model-deduplication")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/validation.json --namespace=fixtures.validation")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/non-string-enum.json --namespace=fixtures.nonstringenum --generate-sync-async-clients --disable-client-builder")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/multiple-inheritance.json --namespace=fixtures.multipleinheritance")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/report.json --namespace=fixtures.report --payload-flattening-threshold=1")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-formdata-urlencoded.json --namespace=fixtures.bodyformdataurlencoded")
generate("$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/constants.json --namespace=fixtures.constants")
generate("--version=$AUTOREST_CORE_VERSION --use=./ vanilla-tests/swagger/lro.md")
generate("--version=$AUTOREST_CORE_VERSION --use=./ vanilla-tests/swagger/custom-http-exception-mapping.md")
Move-Item ./vanilla-tests/swagger/CoverageReporter.java ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java

# local swagger
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening --client-flattened-annotation-target=FIELD")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.requirexmsflattened --require-x-ms-flattened-to-flatten=true")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.noflatten --modelerfour.flatten-models=false")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.clientflatten --modelerfour.flatten-models=false --client-flattened-annotation-target=NONE")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.clientdefaultvalue --modelerfour.flatten-models=false")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.donotpassdiscriminator")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.passdiscriminator --pass-discriminator-to-child-deserialization=true")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.annotatedgettersandsetters --annotate-getters-and-setters-for-serialization=true")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/xml-tag-with-attribute-and-value.json --namespace=fixtures.complexxmltag --enable-xml")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/security-info.json --namespace=fixtures.securityinfo")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader")
generate("$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/required-fields-as-ctor-args-transformation.json --namespace=fixtures.requiredfieldsascotrargstransformation --required-fields-as-ctor-args=true --output-model-immutable")

# Azure
generate("$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1 --generate-sync-async-clients --context-client-method-parameter")
generate("$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl-paging.json --namespace=fixtures.custombaseuri.paging --payload-flattening-threshold=1")
generate("$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-special-properties.json --namespace=fixtures.azurespecials --payload-flattening-threshold=1 --context-client-method-parameter")
generate("$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-parameter-grouping.json --namespace=fixtures.azureparametergrouping --payload-flattening-threshold=1")
generate("$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/subscriptionId-apiVersion.json --namespace=fixtures.subscriptionidapiversion --payload-flattening-threshold=1")
generate("$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1")

# generate("$ARM_ARGUMENTS --input-file=$SWAGGER_PATH/lro.json --namespace=fixtures.lro")
# generate("$ARM_ARGUMENTS --input-file=$SWAGGER_PATH/lro-parameterized-endpoints.json --namespace=fixtures.lroparameterizedendpoints")
# Remove-Item ./azure-tests/src/main/java/module-info.java -Force

# Protocol
Remove-Item ./protocol-tests/src/main -Recurse -Force
Remove-Item ./protocol-tests/src/samples -Recurse -Force
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-string.json --namespace=fixtures.bodystring")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/paging.json --namespace=fixtures.paging")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.bodycomplex")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.header")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/httpInfrastructure.json --namespace=fixtures.httpinfrastructure")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/media_types.json --namespace=fixtures.mediatypes")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/url.json --namespace=fixtures.url")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/url-multi-collectionFormat.json --namespace=fixtures.url.multi")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/lro.json --namespace=fixtures.lro")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-initial.json --namespace=fixtures.llcinitial")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-update1.json --namespace=fixtures.llcupdate1 --generate-send-request-method")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/parameterized-endpoint.json --namespace=fixtures.parameterizedendpoint --generate-send-request-method")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/constants.json --namespace=fixtures.constants")
generate("$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-file.json --namespace=fixtures.bodyfile")
generate("$PROTOCOL_ARGUMENTS --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader")
generate("$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/required-header-query.json --namespace=fixtures.requiredheaderquery")
generate("$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/constant-and-from-client-parameters.json --namespace=fixtures.constantandclientparam")
generate("$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/multi-media-types.json --namespace=fixtures.multimediatypes")
generate("$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/required-optional-body.json --namespace=fixtures.requiredoptionalbody")
generate("$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/enums.json --namespace=fixtures.enums")
generate("$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/endpoint-lro.json --namespace=fixtures.endpointlro")
generate("--version=$AUTOREST_CORE_VERSION --use=./ protocol-tests/swagger/dpg-customization.md")
generate("--version=$AUTOREST_CORE_VERSION --use=./ protocol-tests/swagger/custom-http-exception-mapping.md")
New-Item ./protocol-tests/src/main/java/fixtures/headexceptions/models -ItemType Directory -Force
Move-Item -Path ./protocol-tests/swagger/CustomizedException.java -Destination ./protocol-tests/src/main/java/fixtures/headexceptions/models/CustomizedException.java -Force
Remove-Item ./protocol-tests/src/main/java/module-info.java -Force

# Protocol resilience
Remove-Item ./protocol-resilience-test/llcinitial/src/main -Recurse -Force
Remove-Item ./protocol-resilience-test/llcupdate1/src/main -Recurse -Force
generate("$PROTOCOL_RESILIENCE_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-initial.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcinitial")
generate("$PROTOCOL_RESILIENCE_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-update1.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcupdate1")
Remove-Item ./protocol-resilience-test/llcinitial/src/main/java/module-info.java -Force
Remove-Item ./protocol-resilience-test/llcupdate1/src/main/java/module-info.java -Force

# customization
generate("--version=$AUTOREST_CORE_VERSION --use:. customization-tests/swagger")

# partial update tests
generate("--version=$AUTOREST_CORE_VERSION --use:. partial-update-tests/existing/swagger/README.md")
Remove-Item ./partial-update-tests/generated/src/main/java/module-info.java -Force

# docs
generate("--use:. docs/samples/specification/azure_key_credential/readme.md")
generate("--use:. docs/samples/specification/basic/readme.md")
generate("--use:. docs/samples/specification/management/readme.md")

exit $ExitCode
