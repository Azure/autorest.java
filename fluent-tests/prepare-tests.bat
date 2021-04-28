REM add java11 to path on devops
ECHO "java11 home dir: %JAVA_HOME_11_X64%"
ECHO "add java11 to path"
SET "PATH=%JAVA_HOME_11_X64%\bin;%PATH%"

REM print java version
java -version

REM re-generate code
RMDIR /S /Q "src\main\java\com\azure\mgmttest"
RMDIR /S /Q "src\main\java\com\azure\mgmtlitetest"

SET AUTOREST_CORE_VERSION=3.1.3
SET MODELERFOUR_ARGUMENTS=--pipeline.modelerfour.additional-checks=false --pipeline.modelerfour.lenient-model-deduplication=true
SET COMMON_ARGUMENTS=--java --use=../ --java.output-folder=./ %MODELERFOUR_ARGUMENTS% --azure-arm --java.license-header=MICROSOFT_MIT_SMALL --use=@autorest/modelerfour@4.18.0
SET FLUENT_ARGUMENTS=%COMMON_ARGUMENTS% --fluent
SET FLUENTLITE_ARGUMENTS=%COMMON_ARGUMENTS% --fluent=lite

REM fluent premium
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --payload-flattening-threshold=2 ./swagger/readme.storage.md --namespace=com.azure.mgmttest.storage

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.network.md --namespace=com.azure.mgmttest.network

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.appservice.md --namespace=com.azure.mgmttest.appservice

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/cosmos-db/resource-manager/Microsoft.DocumentDB/stable/2019-12-12/cosmos-db.json --namespace=com.azure.mgmttest.cosmos

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --payload-flattening-threshold=1 ./swagger/readme.containerservice.md --namespace=com.azure.mgmttest.conainterservice

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --payload-flattening-threshold=1 --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/graphrbac/data-plane/Microsoft.GraphRbac/stable/1.6/graphrbac.json --namespace=com.azure.mgmttest.authorization

REM fluent lite
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --pom-file=pom_generated_resources.xml https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/resources/resource-manager/readme.md --tag=package-resources-2020-10 --java.namespace=com.azure.mgmtlitetest.resources

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/storage/resource-manager/readme.md --tag=package-2021-01 --java.namespace=com.azure.mgmtlitetest.storage

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/advisor/resource-manager/readme.md --tag=package-2020-01 --java.namespace=com.azure.mgmtlitetest.advisor

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/mediaservices/resource-manager/readme.md --tag=package-2021-05 --java.namespace=com.azure.mgmtlitetest.mediaservices

REM CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/network/resource-manager/readme.md --tag=package-2020-06 --java.namespace=com.azure.mgmtlitetest.network
REM CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/compute/resource-manager/readme.md --tag=package-2020-06-30 --java.namespace=com.azure.mgmtlitetest.compute

REM delete module-info as fluent-test is on java8
DEL "src\main\java\module-info.java"
