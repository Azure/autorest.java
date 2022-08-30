ECHO OFF
set AZURE_ARGUMENTS=--version=3.4.5 --android --v4 --java --use:. --output-folder=android-tests\app --sync-methods=all --generate-client-as-impl=true --generate-sync-async-clients=true
set INPUTSOURCE=https://raw.githubusercontent.com/Azure/autorest.testserver/main/swagger
set NAMESPACE=com.azure.androidtest

set INPUTFILE=%INPUTSOURCE%/additionalProperties.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.additionalproperties --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-array.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyarray --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-boolean.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyboolean --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-boolean.quirks.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodybooleanquirks --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-complex.json
REM "Require byte[] clone from android core"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodycomplex --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-file.json
REM "Null pointer exception, should be fixable"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyfile --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-string.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodystring --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/custom-baseUrl.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.custombaseurl --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/custom-baseUrl-more-options.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.custombaseurlmoreoptions --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/head.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.head --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/head-exceptions.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.headexceptions --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/header.json
REM "Requires byte[] clone from android core"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.header --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-dictionary.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydictionary --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-duration.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyduration --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-integer.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyinteger --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-number.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodynumber --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/httpInfrastructure.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.httpinfrastructure --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-byte.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodybyte --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-date.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydate --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-datetime.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydatetime --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/body-datetime-rfc1123.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodydatetimerfc1123 --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/paging.json
REM "Paging is not supported yet for Android"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.paging --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/url.json
REM "Require byte[] to string conversion"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.url --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/url-multi-collectionFormat.json
REM "Require parameter conversion from List<String> to String"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.urlmulticollectionformat --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/extensible-enums-swagger.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.extensibleenums --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/required-optional.json
REM "Parameter type is wrong, has Flux"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.requiredoptional --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/xml-service.json
REM "Need byte[] clone from android core, fix imports"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.xmlservice --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/parameter-flattening.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.parameterflattenting --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/model-flattening.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.modelflattening --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/media_types.json
REM "Parameter type is wrong, has Flux"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.mediatypes --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/validation.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.validation --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/non-string-enum.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.nonstringenum --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/multiple-inheritance.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.multipleinheritance --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/xms-error-responses.json
REM "no longer test this one"
REM call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.errorResponse --payload-flattening-threshold=1

set INPUTFILE=%INPUTSOURCE%/report.json
call :log-and-call-autorest autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.report --payload-flattening-threshold=1

exit /B %ERRORLEVEL%

:log-and-call-autorest
echo ========================
echo Running: %*
echo ========================
call %*
exit /B 0
