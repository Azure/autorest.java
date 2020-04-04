REM add java11 to path on devops
JAVA11_LOCATION=C:\Program Files\Java\zulu-11-azure-jdk_11.33.15-11.0.4-win_x64
IF EXIST "%JAVA11_LOCATION%" (
    SET PATH=%JAVA11_LOCATION%\bin;%PATH%
    ECHO add java11 to path
    ECHO %PATH%
)

REM print java version
java -version

REM re-generate code
RMDIR /S /Q "src/main/java/com/azure/mgmttest"

SET AUTOREST_CORE_VERSION=3.0.6262
SET COMMON_ARGUMENTS=--java --use:../ --output-folder=./ --sync-methods=all --azure-arm=true --fluent=true --generate-client-as-impl=true --required-parameter-client-methods=true --track1-naming=true --implementation-subpackage=models

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=2 ./swagger/readme.storage.md --namespace=com.azure.mgmttest.storage

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.network.md --namespace=com.azure.mgmttest.network

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.appservice.md --namespace=com.azure.mgmttest.appservice

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/cosmos-db/resource-manager/Microsoft.DocumentDB/stable/2019-12-12/cosmos-db.json --namespace=com.azure.mgmttest.cosmos

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/containerservice/resource-manager/Microsoft.ContainerService/stable/2020-01-01/managedClusters.json --namespace=com.azure.mgmttest.conainterservice
