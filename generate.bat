set VANILLA_ARGUMENTS=--version=3.8.1 --java --use=. --output-folder=vanilla-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL
set AZURE_ARGUMENTS=--version=3.8.1 --java --use=. --output-folder=azure-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods --license-header=MICROSOFT_MIT_SMALL
set ARM_ARGUMENTS=--version=3.8.1 --java --use=. --output-folder=azure-tests --azure-arm --fluent=lite --regenerate-pom=false
set PROTOCOL_ARGUMENTS=--version=3.8.1 --java --use=. --output-folder=protocol-tests --data-plane --generate-samples
set PROTOCOL_RESILIENCE_ARGUMENTS=--version=3.8.1 --java --use=. --data-plane
set SWAGGER_PATH=node_modules/@microsoft.azure/autorest.testserver/swagger

call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/additionalProperties.json --namespace=fixtures.additionalproperties
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-array.json --namespace=fixtures.bodyarray
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-boolean.json --namespace=fixtures.bodyboolean --context-client-method-parameter --client-logger
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-complex.json --namespace=fixtures.bodycomplex --required-fields-as-ctor-args --model-override-setter-from-superclass --client-logger --generate-tests
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-file.json --namespace=fixtures.bodyfile --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-string.json --namespace=fixtures.bodystring --generate-client-interfaces
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/custom-baseUrl.json --namespace=fixtures.custombaseuri
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/head.json --namespace=fixtures.head
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/head-exceptions.json --namespace=fixtures.headexceptions
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/header.json --namespace=fixtures.header --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/header.json --namespace=fixtures.customheaderdeserialization --context-client-method-parameter --custom-strongly-typed-header-deserialization
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/header.json --namespace=fixtures.nonamedresponsetypes --context-client-method-parameter --generic-response-type
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-dictionary.json --namespace=fixtures.bodydictionary --generate-sync-async-clients --generate-send-request-method
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-duration.json --namespace=fixtures.bodyduration
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-integer.json --namespace=fixtures.bodyinteger
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-number.json --namespace=fixtures.bodynumber --disable-client-builder
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-byte.json --namespace=fixtures.bodybyte
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-date.json --namespace=fixtures.bodydate
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-datetime.json --namespace=fixtures.bodydatetime
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/url.json --namespace=fixtures.url
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/url-multi-collectionFormat.json --namespace=fixtures.url.multi
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/extensible-enums-swagger.json --namespace=fixtures.extensibleenums
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/required-optional.json --namespace=fixtures.requiredoptional
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/xml-service.json --namespace=fixtures.xmlservice --enable-xml
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1 --optional-constant-as-enum=true
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1 --modelerfour.lenient-model-deduplication
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/validation.json --namespace=fixtures.validation
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/non-string-enum.json --namespace=fixtures.nonstringenum --generate-sync-async-clients --disable-client-builder
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/multiple-inheritance.json --namespace=fixtures.multipleinheritance
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/report.json --namespace=fixtures.report --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-formdata-urlencoded.json --namespace=fixtures.bodyformdataurlencoded
call autorest --version=3.8.1 --use=./ vanilla-tests/swagger/lro.md
call autorest --version=3.8.1 --use=./ vanilla-tests/swagger/custom-http-exception-mapping.md

rem local swagger
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening --client-flattened-annotation-target=FIELD
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.requirexmsflattened --require-x-ms-flattened-to-flatten=true
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.noflatten --pipeline.modelerfour.flatten-models=false
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening.clientflatten --pipeline.modelerfour.flatten-models=false --client-flattened-annotation-target=NONE
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.clientdefaultvalue --pipeline.modelerfour.flatten-models=false
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.donotpassdiscriminator
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.inheritance.passdiscriminator --pass-discriminator-to-child-deserialization=true
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/client-default-value.json --namespace=fixtures.annotatedgettersandsetters --annotate-getters-and-setters-for-serialization=true
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/xml-tag-with-attribute-and-value.json --namespace=fixtures.complexxmltag --enable-xml
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/security-info.json --namespace=fixtures.securityinfo
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader

rem Azure
call autorest %AZURE_ARGUMENTS% --input-file=%SWAGGER_PATH%/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=%SWAGGER_PATH%/custom-baseUrl-paging.json --namespace=fixtures.custombaseuri.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=%SWAGGER_PATH%/azure-special-properties.json --namespace=fixtures.azurespecials --payload-flattening-threshold=1 --context-client-method-parameter
call autorest %AZURE_ARGUMENTS% --input-file=%SWAGGER_PATH%/azure-parameter-grouping.json --namespace=fixtures.azureparametergrouping --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=%SWAGGER_PATH%/subscriptionId-apiVersion.json --namespace=fixtures.subscriptionidapiversion --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=%SWAGGER_PATH%/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1

rem call autorest %ARM_ARGUMENTS% --input-file=%SWAGGER_PATH%/lro.json --namespace=fixtures.lro
rem call autorest %ARM_ARGUMENTS% --input-file=%SWAGGER_PATH%/lro-parameterized-endpoints.json --namespace=fixtures.lroparameterizedendpoints
rem del azure-tests\src\main\java\module-info.java

rem Protocol
rmdir /S /Q "protocol-tests\src\main"
rmdir /S /Q "protocol-tests\src\samples"
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-string.json --namespace=fixtures.bodystring
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/paging.json --namespace=fixtures.paging
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/body-complex.json --namespace=fixtures.bodycomplex
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/header.json --namespace=fixtures.header
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/media_types.json --namespace=fixtures.mediatypes
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/url.json --namespace=fixtures.url
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/url-multi-collectionFormat.json --namespace=fixtures.url.multi
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/lro.json --namespace=fixtures.lro
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/dpg_initial.json --namespace=fixtures.llcinitial
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/dpg_update1.json --namespace=fixtures.llcupdate1 --generate-send-request-method
call autorest %PROTOCOL_ARGUMENTS% --input-file=%SWAGGER_PATH%/dpg-customization.json --namespace=fixtures.dpgcustomization --generate-send-request-method --generate-models
call autorest %PROTOCOL_ARGUMENTS% --input-file=vanilla-tests/swagger/special-header.json --namespace=fixtures.specialheader
call autorest --version=3.6.6 --use=./ protocol-tests/swagger/custom-http-exception-mapping.md
mkdir protocol-tests\src\main\java\fixtures\headexceptions\models
copy protocol-tests\swagger\CustomizedException.java protocol-tests\src\main\java\fixtures\headexceptions\models\CustomizedException.java
del protocol-tests\src\main\java\module-info.java

rem Protocol resilience
rmdir /S /Q "protocol-resilience-test\llcinitial\src\main"
rmdir /S /Q "protocol-resilience-test\llcupdate1\src\main"
call autorest %PROTOCOL_RESILIENCE_ARGUMENTS% --input-file=%SWAGGER_PATH%/dpg_initial.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcinitial
call autorest %PROTOCOL_RESILIENCE_ARGUMENTS% --input-file=%SWAGGER_PATH%/dpg_update1.json --namespace=fixtures.llcresi --output-folder=protocol-resilience-test/llcupdate1
del protocol-resilience-test\llcinitial\src\main\java\module-info.java
del protocol-resilience-test\llcupdate1\src\main\java\module-info.java

rem customization
call autorest --version=3.8.1 --use:. customization-tests/swagger

rem partial update tests
call autorest --version=3.8.1 --use:. partial-update-tests/existing/swagger/README.md
del partial-update-tests\generated\src\main\java\module-info.java

rem docs
call autorest --use:. docs/samples/specification/azure_key_credential/readme.md
call autorest --use:. docs/samples/specification/basic/readme.md
call autorest --use:. docs/samples/specification/management/readme.md
