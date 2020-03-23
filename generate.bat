set COMMON_ARGUMENTS=--version=3.0.6257 --java --use:.\ --output-folder=tests --sync-methods=all --client-side-validations=true --add-context-parameter=true

call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-array.json --namespace=fixtures.bodyarray
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.json --namespace=fixtures.bodyboolean
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.quirks.json --namespace=fixtures.bodyboolean.quirks
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-complex.json --namespace=fixtures.bodycomplex
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-file.json --namespace=fixtures.bodyfile
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-string.json --namespace=fixtures.bodystring
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl.json --namespace=fixtures.custombaseuri
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl-more-options.json --namespace=fixtures.custombaseuri.moreoptions
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head.json --namespace=fixtures.head
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head-exceptions.json --namespace=fixtures.headexceptions
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/header.json --namespace=fixtures.header
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-dictionary.json --namespace=fixtures.bodydictionary
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-duration.json --namespace=fixtures.bodyduration
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-integer.json --namespace=fixtures.bodyinteger
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-number.json --namespace=fixtures.bodynumber
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/httpInfrastructure.json --namespace=fixtures.httpinfrastructure
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/paging.json --namespace=fixtures.paging --payload-flattening-threshold=1
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-byte.json --namespace=fixtures.bodybyte
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-date.json --namespace=fixtures.bodydate
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-datetime.json --namespace=fixtures.bodydatetime
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-datetime-rfc1123.json --namespace=fixtures.bodydatetimerfc1123
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/url.json --namespace=fixtures.url
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/extensible-enums-swagger.json --namespace=fixtures.extensibleenums
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/required-optional.json --namespace=fixtures.requiredoptional
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/xml-service.json --namespace=fixtures.xmlservice --enable-xml=true
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/parameter-flattening.json --namespace=fixtures.parameterflattening --payload-flattening-threshold=1
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/model-flattening.json --namespace=fixtures.modelflattening --payload-flattening-threshold=1
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/media_types.json --namespace=fixtures.mediatypes --payload-flattening-threshold=1
call autorest %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/report.json --namespace=fixtures.report --payload-flattening-threshold=1
