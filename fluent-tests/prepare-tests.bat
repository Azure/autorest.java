RMDIR /S /Q "src/main/java/com/azure/mgmttest"

SET AUTOREST_CORE_VERSION=3.0.6220
SET COMMON_ARGUMENTS=--java --use:../fluentgen --output-folder=./ --sync-methods=all --azure-arm=true --fluent=true --generate-client-as-impl=true --required-parameter-client-methods=true --implementation-subpackage=models

CALL autorest-beta --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources

CALL autorest-beta --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=2 ./swagger/readme.storage.md --namespace=com.azure.mgmttest.storage

CALL autorest-beta --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.network.md --namespace=com.azure.mgmttest.network
