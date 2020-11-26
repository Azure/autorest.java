ECHO OFF
set AZURE_ARGUMENTS=--android --v4 --java --use:.\ --output-folder=android-tests\app --sync-methods=all --generate-client-as-impl=true --generate-sync-async-clients=true
set INPUTSOURCE=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger
set NAMESPACE=com.azure.androidtest
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-array.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyarray --payload-flattening-threshold=1
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
set INPUTFILE=%INPUTSOURCE%/body-integer.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodyinteger --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-number.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodynumber --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/body-string.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.bodystring --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/custom-baseUrl.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.custombaseurl --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/url.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.url --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/xms-error-responses.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.errorResponse --payload-flattening-threshold=1
ECHO =======================
set INPUTFILE=%INPUTSOURCE%/report.json
ECHO %INPUTFILE%
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTFILE% --namespace=%NAMESPACE%.fixtures.report --payload-flattening-threshold=1
