# Use case:
#
# The purpose of this script is to compact the steps required # Before running this script the 'local' profile must be built, 'mvn install -P local'. And 'autorest' must be installed, 'npm install -g autorest'.
#
# If 'com.azure.autorest.customization' tests fails, re-install 'customization-base'.

param (
    [switch]$notimeout = $false
)

$AUTOREST_CORE_VERSION = "3.9.7"
$VANILLA_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=vanilla-tests --sync-methods=all --client-side-validations --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL"
$AZURE_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=azure-tests --sync-methods=all --client-side-validations --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL"
$PROTOCOL_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=protocol-tests --data-plane --generate-samples"
$PROTOCOL_RESILIENCE_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --data-plane"
$SWAGGER_PATH = "node_modules/@microsoft.azure/autorest.testserver/swagger"
$AZURE_DATAPLANE_ARGUMENTS = "--use=./ --output-folder=./azure-dataplane-tests"
$AZURE_DATAPLANE_PATH = "azure-dataplane-tests/swagger"
$AZURE_SDK_FOR_JAVA = "https://github.com/Azure/azure-sdk-for-java/blob/main/sdk"
$PARALLELIZATION = 4
if ($IsWindows) {
    $PARALLELIZATION = (Get-CIMInstance -Class 'CIM_Processor').NumberOfCores - 1
}

$ExitCode = 0

$generateScript = {
    $generateOutput = Invoke-Expression "autorest $_"
    $global:ExitCode = $global:ExitCode -bor $LASTEXITCODE

    if ($LASTEXITCODE -ne 0) {
        Write-Host "
========================
autorest $_
========================
$([String]::Join("`n", $generateOutput))
        "
    } else {
        Write-Host "
========================
autorest $_
========================
SUCCEEDED
        "
    }

    if ($global:ExitCode -ne 0) {
        exit $global:ExitCode
    }
}

function singleThreadGenerate($arguments) {
    Write-Host "
========================
autorest $arguments
========================
    "
    $generateOutput = Invoke-Expression "autorest $arguments"
    $global:ExitCode = $global:ExitCode -bor $LASTEXITCODE

    if ($LASTEXITCODE -ne 0) {
        Write-Host $([String]::Join("`n", $generateOutput))
    } else {
        Write-Host "SUCCEEDED"
    }
}

java -version

if (Test-Path ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java) {
    Move-Item -Path ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java -Destination ./vanilla-tests/swagger/CoverageReporter.java -Force | Out-Null
}

if (Test-Path ./vanilla-tests/src/main) {
    Remove-Item ./vanilla-tests/src/main -Recurse -Force | Out-Null
}

singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/additionalProperties.json --namespace=fixtures.additionalproperties"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-array.json --namespace=fixtures.bodyarray"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-boolean.json --namespace=fixtures.bodyboolean --client-logger"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.bodycomplex --required-fields-as-ctor-args --client-logger --output-model-immutable --generate-tests"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.streamstyleserialization --enable-sync-stack --stream-style-serialization --client-logger --pass-discriminator-to-child-deserialization"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.streamstyleserializationctorargs --enable-sync-stack --stream-style-serialization --required-fields-as-ctor-args --client-logger --pass-discriminator-to-child-deserialization"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-file.json --namespace=fixtures.bodyfile"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-string.json --namespace=fixtures.bodystring --generate-client-interfaces"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl.json --namespace=fixtures.custombaseuri"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/head.json --namespace=fixtures.head"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/head-exceptions.json --namespace=fixtures.headexceptions"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.header"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.nonamedresponsetypes --generic-response-type --no-custom-headers"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-dictionary.json --namespace=fixtures.bodydictionary --generate-sync-async-clients --generate-send-request-method"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-duration.json --namespace=fixtures.bodyduration"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-integer.json --namespace=fixtures.bodyinteger"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-number.json --namespace=fixtures.bodynumber --disable-client-builder"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/httpInfrastructure.json --namespace=fixtures.httpinfrastructure"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-byte.json --namespace=fixtures.bodybyte"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-date.json --namespace=fixtures.bodydate"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-datetime.json --namespace=fixtures.bodydatetime"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/url.json --namespace=fixtures.url"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/url-multi-collectionFormat.json --namespace=fixtures.url.multi"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/extensible-enums-swagger.json --namespace=fixtures.extensibleenums"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/required-optional.json --namespace=fixtures.requiredoptional"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/xml-service.json --namespace=fixtures.xmlservice --enable-xml --url-as-string=false"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/xml-service.json --namespace=fixtures.streamstylexmlserialization --stream-style-serialization --enable-xml --url-as-string=false"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1 --optional-constant-as-enum=true"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1 --modelerfour.lenient-model-deduplication"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/validation.json --namespace=fixtures.validation"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/non-string-enum.json --namespace=fixtures.nonstringenum --generate-sync-async-clients --disable-client-builder"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/multiple-inheritance.json --namespace=fixtures.multipleinheritance"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/report.json --namespace=fixtures.report --payload-flattening-threshold=1"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/body-formdata-urlencoded.json --namespace=fixtures.bodyformdataurlencoded"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=$SWAGGER_PATH/constants.json --namespace=fixtures.constants"
singleThreadGenerate "--version=$AUTOREST_CORE_VERSION --use=./ vanilla-tests/swagger/lro.md"
singleThreadGenerate "--version=$AUTOREST_CORE_VERSION --use=./ vanilla-tests/swagger/custom-http-exception-mapping.md"

if (Test-Path ./vanilla-tests/swagger/CoverageReporter.java) {
    Move-Item ./vanilla-tests/swagger/CoverageReporter.java ./vanilla-tests/src/main/java/fixtures/report/CoverageReporter.java -Force | Out-Null
}

# local swagger
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening --client-flattened-annotation-target=FIELD"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.requirexmsflattened --require-x-ms-flattened-to-flatten=true"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.noflatten --modelerfour.flatten-models=false"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.clientflatten --modelerfour.flatten-models=false --client-flattened-annotation-target=NONE"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.clientdefaultvalue --modelerfour.flatten-models=false"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.donotpassdiscriminator"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.passdiscriminator --pass-discriminator-to-child-deserialization=true"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.annotatedgettersandsetters --annotate-getters-and-setters-for-serialization=true"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/xml-tag-with-attribute-and-value.json --namespace=fixtures.complexxmltag --enable-xml"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/xml-tag-with-attribute-and-value.json --namespace=fixtures.complexstreamstylexmlserialization --stream-style-serialization --enable-xml"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/security-info.json --namespace=fixtures.securityinfo --use-key-credential"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader"
singleThreadGenerate "$VANILLA_ARGUMENTS --input-file=vanilla-tests/swagger/required-fields-as-ctor-args-transformation.json --namespace=fixtures.requiredfieldsascotrargstransformation --required-fields-as-ctor-args=true --output-model-immutable"

# Azure Data Plane
if (Test-Path ./azure-dataplane-tests/src/main) {
    Remove-Item ./azure-dataplane-tests/src/main -Recurse -Force | Out-Null
}

singleThreadGenerate "$AZURE_DATAPLANE_ARGUMENTS $AZURE_SDK_FOR_JAVA/schemaregistry/azure-data-schemaregistry/swagger/README.md"
singleThreadGenerate "$AZURE_DATAPLANE_ARGUMENTS $AZURE_SDK_FOR_JAVA/containerregistry/azure-containers-containerregistry/swagger/autorest.md"
# Form recognizer in Azure SDK for Java repo does not configure polling operations. So, using local configuration
# to generate polling methods.
singleThreadGenerate "$AZURE_DATAPLANE_ARGUMENTS $AZURE_DATAPLANE_PATH/form-recognizer.md"
singleThreadGenerate "$AZURE_DATAPLANE_ARGUMENTS $AZURE_DATAPLANE_PATH/form-recognizer-dpg.md"

Remove-Item ./azure-dataplane-tests/src/main/java/module-info.java -Force | Out-Null

# Azure
singleThreadGenerate "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1 --generate-sync-async-clients"
singleThreadGenerate "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/custom-baseUrl-paging.json --namespace=fixtures.custombaseuri.paging --payload-flattening-threshold=1"
singleThreadGenerate "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-special-properties.json --namespace=fixtures.azurespecials --payload-flattening-threshold=1"
singleThreadGenerate "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-parameter-grouping.json --namespace=fixtures.azureparametergrouping --payload-flattening-threshold=1"
singleThreadGenerate "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/subscriptionId-apiVersion.json --namespace=fixtures.subscriptionidapiversion --payload-flattening-threshold=1"
singleThreadGenerate "$AZURE_ARGUMENTS --input-file=$SWAGGER_PATH/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1"

# # Azure but use Fluent
# $ARM_ARGUMENTS = "--version=$AUTOREST_CORE_VERSION --java --use=. --output-folder=azure-tests --azure-arm --fluent=lite --regenerate-pom=false"
# singleThreadGenerate "$ARM_ARGUMENTS --input-file=$SWAGGER_PATH/lro.json --namespace=fixtures.lro"
# singleThreadGenerate "$ARM_ARGUMENTS --input-file=$SWAGGER_PATH/lro-parameterized-endpoints.json --namespace=fixtures.lroparameterizedendpoints"
# Remove-Item ./azure-tests/src/main/java/module-info.java -Force

# Protocol
if (Test-Path ./protocol-tests/src/main) {
    Remove-Item ./protocol-tests/src/main -Recurse -Force | Out-Null
}
if (Test-Path ./protocol-tests/src/samples) {
    Remove-Item ./protocol-tests/src/samples -Recurse -Force | Out-Null
}

singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-string.json --namespace=fixtures.bodystring"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/paging.json --namespace=fixtures.paging"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-complex.json --namespace=fixtures.bodycomplex"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/header.json --namespace=fixtures.header"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/httpInfrastructure.json --namespace=fixtures.httpinfrastructure"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/media_types.json --namespace=fixtures.mediatypes"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/url.json --namespace=fixtures.url"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/url-multi-collectionFormat.json --namespace=fixtures.url.multi"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/lro.json --namespace=fixtures.lro"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-initial.json --namespace=fixtures.llcinitial"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-update1.json --namespace=fixtures.llcupdate1 --generate-send-request-method"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/parameterized-endpoint.json --namespace=fixtures.parameterizedendpoint --generate-send-request-method"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/constants.json --namespace=fixtures.constants"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=$SWAGGER_PATH/body-file.json --namespace=fixtures.bodyfile"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/required-header-query.json --namespace=fixtures.requiredheaderquery"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/constant-and-from-client-parameters.json --namespace=fixtures.constantandclientparam"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/multi-media-types.json --namespace=fixtures.multimediatypes"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/required-optional-body.json --namespace=fixtures.requiredoptionalbody"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/enums.json --namespace=fixtures.enums"
singleThreadGenerate "$PROTOCOL_ARGUMENTS --input-file=protocol-tests/swagger/endpoint-lro.json --namespace=fixtures.endpointlro --service-name=LroEndpoint"
singleThreadGenerate "--version=$AUTOREST_CORE_VERSION --use=./ protocol-tests/swagger/dpg-customization.md"
singleThreadGenerate "--version=$AUTOREST_CORE_VERSION --use=./ protocol-tests/swagger/custom-http-exception-mapping.md"

New-Item ./protocol-tests/src/main/java/fixtures/headexceptions/models -ItemType Directory -Force | Out-Null
Copy-Item -Path ./protocol-tests/swagger/CustomizedException.java -Destination ./protocol-tests/src/main/java/fixtures/headexceptions/models/CustomizedException.java -Force | Out-Null
Remove-Item ./protocol-tests/src/main/java/module-info.java -Force | Out-Null

# Protocol resilience
Remove-Item ./protocol-resilience-test/llcinitial/src/main -Recurse -Force | Out-Null
Remove-Item ./protocol-resilience-test/llcupdate1/src/main -Recurse -Force | Out-Null

singleThreadGenerate("$PROTOCOL_RESILIENCE_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-initial.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcinitial")
singleThreadGenerate("$PROTOCOL_RESILIENCE_ARGUMENTS --input-file=$SWAGGER_PATH/dpg-update1.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcupdate1")

Remove-Item ./protocol-resilience-test/llcinitial/src/main/java/module-info.java -Force | Out-Null
Remove-Item ./protocol-resilience-test/llcupdate1/src/main/java/module-info.java -Force | Out-Null

# customization
Remove-Item ./customization-tests/src -Recurse -Force | Out-Null
singleThreadGenerate("--version=$AUTOREST_CORE_VERSION --use:. customization-tests/swagger")

# partial update tests
singleThreadGenerate("--version=$AUTOREST_CORE_VERSION --use:. partial-update-tests/existing/swagger/README.md")
Remove-Item ./partial-update-tests/generated/src/main/java/module-info.java -Force | Out-Null

# docs
singleThreadGenerate("--use:. docs/samples/specification/azure_key_credential/readme.md")
singleThreadGenerate("--use:. docs/samples/specification/basic/readme.md")
singleThreadGenerate("--use:. docs/samples/specification/management/readme.md")

exit $ExitCode
