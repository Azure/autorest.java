set VANILLA_ARGUMENTS=--version=3.4.5 --java --use=. --output-folder=vanilla-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods
set AZURE_ARGUMENTS=--version=3.4.5 --java --use=. --output-folder=azure-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods
set ARM_ARGUMENTS=--version=3.4.5 --java --use=. --output-folder=azure-tests --azure-arm --fluent=lite --regenerate-pom=false
set PROTOCOL_ARGUMENTS=--version=3.4.5 --java --use=. --output-folder=protocol-tests --low-level-client --generate-llc-samples
set PROTOCOL_RESILIENCE_ARGUMENTS=--version=3.4.5 --java --use=. --low-level-client

rem 3.0.37
set TEST_SERVER_COMMIT=c4d01ebca63c339b059a996deaa9ddca5887a099

call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/additionalProperties.json --namespace=fixtures.additionalproperties
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-array.json --namespace=fixtures.bodyarray
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-boolean.json --namespace=fixtures.bodyboolean --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-complex.json --namespace=fixtures.bodycomplex --required-fields-as-ctor-args --model-override-setter-from-superclass
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-file.json --namespace=fixtures.bodyfile --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-string.json --namespace=fixtures.bodystring --generate-client-interfaces
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/custom-baseUrl.json --namespace=fixtures.custombaseuri
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/head.json --namespace=fixtures.head
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/head-exceptions.json --namespace=fixtures.headexceptions
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/header.json --namespace=fixtures.header --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-dictionary.json --namespace=fixtures.bodydictionary --generate-sync-async-clients
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-duration.json --namespace=fixtures.bodyduration
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-integer.json --namespace=fixtures.bodyinteger
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-number.json --namespace=fixtures.bodynumber --disable-client-builder
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-byte.json --namespace=fixtures.bodybyte
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-date.json --namespace=fixtures.bodydate
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-datetime.json --namespace=fixtures.bodydatetime
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/url.json --namespace=fixtures.url
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/url-multi-collectionFormat.json --namespace=fixtures.url.multi
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/extensible-enums-swagger.json --namespace=fixtures.extensibleenums
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/required-optional.json --namespace=fixtures.requiredoptional
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/xml-service.json --namespace=fixtures.xmlservice --enable-xml
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1 --optional-constant-as-enum=true
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/validation.json --namespace=fixtures.validation
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/non-string-enum.json --namespace=fixtures.nonstringenum
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/multiple-inheritance.json --namespace=fixtures.multipleinheritance
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/report.json --namespace=fixtures.report --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-formdata-urlencoded.json --namespace=fixtures.bodyformdataurlencoded
call autorest --version=3.4.5 --use=./ vanilla-tests/swagger/lro.md

rem local swagger
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening --client-flattened-annotation-target=FIELD
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.requirexmsflattened --require-x-ms-flattened-to-flatten=true
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.noflatten --pipeline.modelerfour.flatten-models=false
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.clientflatten --pipeline.modelerfour.flatten-models=false --client-flattened-annotation-target=NONE
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.clientdefaultvalue --pipeline.modelerfour.flatten-models=false
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.donotpassdiscriminator
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.passdiscriminator --pass-discriminator-to-child-deserialization=true

rem Azure
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/custom-baseUrl-paging.json --namespace=fixtures.custombaseuri.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/azure-special-properties.json --namespace=fixtures.azurespecials --payload-flattening-threshold=1 --context-client-method-parameter
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/azure-parameter-grouping.json --namespace=fixtures.azureparametergrouping --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/subscriptionId-apiVersion.json --namespace=fixtures.subscriptionidapiversion --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1

rem call autorest %ARM_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/lro.json --namespace=fixtures.lro
rem call autorest %ARM_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/lro-parameterized-endpoints.json --namespace=fixtures.lroparameterizedendpoints
rem del azure-tests\src\main\java\module-info.java

rem Protocol
rmdir /S /Q "protocol-tests\src\main"
rmdir /S /Q "protocol-tests\src\samples"
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-string.json --namespace=fixtures.bodystring
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/paging.json --namespace=fixtures.paging
call autorest bodycomplex.md %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/body-complex.json --namespace=fixtures.bodycomplex
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/header.json --namespace=fixtures.header
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/media_types.json --namespace=fixtures.mediatypes
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/url.json --namespace=fixtures.url
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/llc_initial.json --namespace=fixtures.llcinitial
call autorest %PROTOCOL_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/llc_update1.json --namespace=fixtures.llcupdate1
del protocol-tests\src\main\java\module-info.java

rem Protocol resilience
call autorest %PROTOCOL_RESILIENCE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/llc_initial.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcinitial
call autorest %PROTOCOL_RESILIENCE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/%TEST_SERVER_COMMIT%/swagger/llc_update1.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcupdate1
del protocol-resilience-test\llcinitial\src\main\java\module-info.java
del protocol-resilience-test\llcupdate1\src\main\java\module-info.java

rem customization
call autorest --version=3.4.5 --use:. customization-tests/swagger

call autorest --use:. docs/samples/specification/azure_key_credential/readme.md
call autorest --use:. docs/samples/specification/basic/readme.md
call autorest --use:. docs/samples/specification/management/readme.md
