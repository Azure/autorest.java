REM add java11 to path on devops
DIR "C:\Program Files\Java"
SET JAVA11_LOCATION=C:\Program Files\Java\jdk-11.0.7+10
IF EXIST "%JAVA11_LOCATION%" (
    ECHO "add java11 to path"
    SET "PATH=%JAVA11_LOCATION%\bin;%PATH%"
)

REM print java version
java -version

REM re-generate code
RMDIR /S /Q "src/main/java/com/azure/mgmttest"

SET AUTOREST_CORE_VERSION=3.0.6282
SET COMMON_ARGUMENTS=--java --use:../ --output-folder=./ --sync-methods=all --azure-arm --fluent --required-parameter-client-methods --add-context-parameter --context-client-method-parameter --track1-naming --implementation-subpackage=fluent --client-side-validations --client-logger

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=2 ./swagger/readme.storage.md --namespace=com.azure.mgmttest.storage

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.network.md --namespace=com.azure.mgmttest.network

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.appservice.md --namespace=com.azure.mgmttest.appservice

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/cosmos-db/resource-manager/Microsoft.DocumentDB/stable/2019-12-12/cosmos-db.json --namespace=com.azure.mgmttest.cosmos

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/containerservice/resource-manager/Microsoft.ContainerService/stable/2020-01-01/managedClusters.json --namespace=com.azure.mgmttest.conainterservice

CALL autorest --version=%AUTOREST_CORE_VERSION% %COMMON_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/graphrbac/data-plane/Microsoft.GraphRbac/stable/1.6/graphrbac.json --namespace=com.azure.mgmttest.authorization
