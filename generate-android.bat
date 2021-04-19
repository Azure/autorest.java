ECHO OFF
set AZURE_ARGUMENTS=--version=3.1.3 --android --v4 --java --use:. --output-folder=android-tests\app --sync-methods=all --generate-client-as-impl=true --generate-sync-async-clients=true
set INPUTSOURCE=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger
set NAMESPACE=com.azure.androidtest
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/additionalProperties.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.additionalproperties --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-array.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyarray --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-boolean.json
ECHO %INPUTFILE%
REM "ResponseCompletableFuture cannot use primitive type boolean"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyboolean --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-boolean.quirks.json
ECHO %INPUTFILE%
REM "ResponseCompletableFuture cannot use primitive type boolean"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodybooleanquirks --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-complex.json
ECHO %INPUTFILE%
REM "Require byte[] clone from android core"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodycomplex --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-file.json
ECHO %INPUTFILE%
REM "Null pointer exception, should be fixable"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyfile --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-string.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodystring --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/custom-baseUrl.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.custombaseurl --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/custom-baseUrl-more-options.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.custombaseurlmoreoptions --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/head.json
ECHO %INPUTFILE%
REM "ResponseCompletableFuture cannot use primitive type boolean"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.head --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/head-exceptions.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.headexceptions --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/header.json
ECHO %INPUTFILE%
REM "Need to fix imports"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.header --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-dictionary.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydictionary --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-duration.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyduration --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-integer.json
ECHO %INPUTFILE%
REM "ResponseCompletableFuture cannot use primitive type integer"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyinteger --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-number.json
ECHO %INPUTFILE%
REM "ResponseCompletableFuture cannot use primitive type float, double"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodynumber --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/httpInfrastructure.json
ECHO %INPUTFILE%
REM "Needs to fix imports"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.httpinfrastructure --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-byte.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodybyte --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-date.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydate --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-datetime.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydatetime --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-datetime-rfc1123.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydatetimerfc1123 --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/paging.json
ECHO %INPUTFILE%
REM "Paging is not supported yet for Android"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.paging --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/url.json
ECHO %INPUTFILE%
REM "Require byte[] to string conversion"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.url --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/url-multi-collectionFormat.json
ECHO %INPUTFILE%
REM "Require parameter conversion from List<String> to String"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.urlmulticollectionformat --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/extensible-enums-swagger.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.extensibleenums --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/required-optional.json
ECHO %INPUTFILE%
REM "Parameter type is wrong, has Flux"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.requiredoptional --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/xml-service.json
ECHO %INPUTFILE%
REM "Need byte[] clone from android core, fix imports"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.xmlservice --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/parameter-flattening.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.parameterflattenting --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/model-flattening.json
ECHO %INPUTFILE%
REM "Need to fix import of annotation"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.modelflattening --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/media_types.json
ECHO %INPUTFILE%
REM "Parameter type is wrong, has Flux"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.mediatypes --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/validation.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.validation --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/non-string-enum.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.nonstringenum --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/multiple-inheritance.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.multipleinheritance --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/xms-error-responses.json
ECHO %INPUTFILE%
REM "no longer test this one"
REM call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.errorResponse --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/report.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.report --payload-flattening-threshold=1
