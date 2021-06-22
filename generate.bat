set VANILLA_ARGUMENTS=--version=3.1.3 --java --use=. --output-folder=vanilla-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods
set AZURE_ARGUMENTS=--version=3.1.3 --java --use=. --output-folder=azure-tests --sync-methods=all --client-side-validations --add-context-parameter --required-parameter-client-methods
set ARM_ARGUMENTS=--version=3.1.3 --java --use=. --output-folder=azure-tests --azure-arm --fluent=lite --regenerate-pom=false
set PROTOCOL_ARGUMENTS=--version=3.1.3 --java --use=./ --output-folder=protocol-tests --sync-methods=all --generate-client-as-impl --add-context-parameter --context-client-method-parameter --generate-sync-async-clients --low-level-client

call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/additionalProperties.json --namespace=fixtures.additionalproperties
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-array.json --namespace=fixtures.bodyarray
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.json --namespace=fixtures.bodyboolean --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-complex.json --namespace=fixtures.bodycomplex --required-fields-as-ctor-args --model-override-setter-from-superclass
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-file.json --namespace=fixtures.bodyfile --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-string.json --namespace=fixtures.bodystring --generate-client-interfaces
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl.json --namespace=fixtures.custombaseuri
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head.json --namespace=fixtures.head
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head-exceptions.json --namespace=fixtures.headexceptions
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/header.json --namespace=fixtures.header --context-client-method-parameter
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-dictionary.json --namespace=fixtures.bodydictionary --generate-sync-async-clients
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-duration.json --namespace=fixtures.bodyduration
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-integer.json --namespace=fixtures.bodyinteger
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-number.json --namespace=fixtures.bodynumber
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-byte.json --namespace=fixtures.bodybyte
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-date.json --namespace=fixtures.bodydate
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-datetime.json --namespace=fixtures.bodydatetime
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/url.json --namespace=fixtures.url
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/url-multi-collectionFormat.json --namespace=fixtures.url.multi
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/extensible-enums-swagger.json --namespace=fixtures.extensibleenums
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/required-optional.json --namespace=fixtures.requiredoptional
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/xml-service.json --namespace=fixtures.xmlservice --enable-xml
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1 --optional-constant-as-enum=true
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/validation.json --namespace=fixtures.validation
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/non-string-enum.json --namespace=fixtures.nonstringenum
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/multiple-inheritance.json --namespace=fixtures.multipleinheritance
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/report.json --namespace=fixtures.report --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-formdata-urlencoded.json --namespace=fixtures.bodyformdataurlencoded

rem local swagger
call autorest %VANILLA_ARGUMENTS% --input-file=vanilla-tests/swagger/discriminator-flattening.json --namespace=fixtures.discriminatorflattening

call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl-paging.json --namespace=fixtures.custombaseuri.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/azure-special-properties.json --namespace=fixtures.azurespecials --payload-flattening-threshold=1 --context-client-method-parameter
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/azure-parameter-grouping.json --namespace=fixtures.azureparametergrouping --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/subscriptionId-apiVersion.json --namespace=fixtures.subscriptionidapiversion --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1

rem call autorest %ARM_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/lro.json --namespace=fixtures.lro
rem call autorest %ARM_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/lro-parameterized-endpoints.json --namespace=fixtures.lroparameterizedendpoints
rem del azure-tests\src\main\java\module-info.java

call autorest $PROTOCOL_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-string.json --namespace=fixtures.bodystring

call autorest --use:. customization-tests/swagger

call autorest --use:. docs/samples/specification/azure_key_credential/readme.md
call autorest --use:. docs/samples/specification/basic/readme.md
call autorest --use:. docs/samples/specification/management/readme.md
