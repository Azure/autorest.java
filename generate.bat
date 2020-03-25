set VANILLA_ARGUMENTS=--version=3.0.6257 --java --use:.\ --output-folder=vanilla-tests --sync-methods=all --client-side-validations=true --add-context-parameter=true
set AZURE_ARGUMENTS=--version=3.0.6257 --java --use:.\ --output-folder=azure-tests --sync-methods=all --client-side-validations=true --add-context-parameter=true

call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-array.json --namespace=fixtures.bodyarray
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.json --namespace=fixtures.bodyboolean
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-complex.json --namespace=fixtures.bodycomplex
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-file.json --namespace=fixtures.bodyfile
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-string.json --namespace=fixtures.bodystring
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl.json --namespace=fixtures.custombaseuri
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head.json --namespace=fixtures.head
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head-exceptions.json --namespace=fixtures.headexceptions
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/header.json --namespace=fixtures.header
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-dictionary.json --namespace=fixtures.bodydictionary
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-duration.json --namespace=fixtures.bodyduration
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-integer.json --namespace=fixtures.bodyinteger
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-number.json --namespace=fixtures.bodynumber
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-byte.json --namespace=fixtures.bodybyte
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-date.json --namespace=fixtures.bodydate
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-datetime.json --namespace=fixtures.bodydatetime
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/url.json --namespace=fixtures.url
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/extensible-enums-swagger.json --namespace=fixtures.extensibleenums
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/required-optional.json --namespace=fixtures.requiredoptional
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/xml-service.json --namespace=fixtures.xmlservice --enable-xml=true
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1
call autorest %VANILLA_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/report.json --namespace=fixtures.report --payload-flattening-threshold=1

call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/azure-report.json --namespace=fixtures.azurereport --payload-flattening-threshold=1