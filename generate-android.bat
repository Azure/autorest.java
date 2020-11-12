set AZURE_ARGUMENTS=--android --v4 --java --use:.\ --output-folder=android-tests\app --sync-methods=all --generate-client-as-impl=true --generate-sync-async-clients=true
set INPUTSOURCE=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger
set NAMESPACE=com.azure.androidtest
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTSOURCE%/body-string.json --namespace=%NAMESPACE%.fixtures.bodystring --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTSOURCE%/body-integer.json --namespace=%NAMESPACE%.fixtures.bodyinteger --payload-flattening-threshold=1
call autorest %AZURE_ARGUMENTS% --input-file=%INPUTSOURCE%/url.json --namespace=%NAMESPACE%.fixtures.url --payload-flattening-threshold=1
