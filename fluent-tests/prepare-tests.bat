REM add java11 to path on devops
ECHO "java11 home dir: %JAVA_HOME_11_X64%"
ECHO "add java11 to path"
SET "PATH=%JAVA_HOME_11_X64%\bin;%PATH%"

REM print java version
java -version

REM re-generate code
RMDIR /S /Q "src\main\java\com\azure\mgmttest"
RMDIR /S /Q "src\main\java\com\azure\mgmtlitetest"
RMDIR /S /Q "src\samples"
RMDIR /S /Q "src\test\java\com\azure\mgmtlitetest"

SET AUTOREST_CORE_VERSION=3.9.6
SET COMMON_ARGUMENTS=--java --use=../ --java.output-folder=./ --modelerfour.additional-checks=false --modelerfour.lenient-model-deduplication=true --azure-arm --java.license-header=MICROSOFT_MIT_SMALL
SET FLUENT_ARGUMENTS=%COMMON_ARGUMENTS% --fluent
SET FLUENTLITE_ARGUMENTS=%COMMON_ARGUMENTS% --fluent=lite --generate-samples --generate-tests

REM fluent premium
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources
if %errorlevel% neq 0 exit /b %errorlevel%

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% ./swagger/readme.storage.md --namespace=com.azure.mgmttest.storage
if %errorlevel% neq 0 exit /b %errorlevel%

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% ./swagger/readme.network.md --namespace=com.azure.mgmttest.network
if %errorlevel% neq 0 exit /b %errorlevel%

REM error response that is subclass of ManagementException
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% ./swagger/readme.appservice.md --namespace=com.azure.mgmttest.appservice
if %errorlevel% neq 0 exit /b %errorlevel%

REM multiple inheritance
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/cosmos-db/resource-manager/Microsoft.DocumentDB/stable/2019-12-12/cosmos-db.json --namespace=com.azure.mgmttest.cosmos
if %errorlevel% neq 0 exit /b %errorlevel%

REM flatten payload
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/compute/resource-manager/Microsoft.Compute/CloudserviceRP/stable/2021-03-01/cloudService.json --namespace=com.azure.mgmttest.compute
if %errorlevel% neq 0 exit /b %errorlevel%

REM error response that not conform to ARM
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/graphrbac/data-plane/Microsoft.GraphRbac/stable/1.6/graphrbac.json --namespace=com.azure.mgmttest.authorization
if %errorlevel% neq 0 exit /b %errorlevel%

REM client model flatten at autorest.java
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/compute/resource-manager/Microsoft.Compute/GalleryRP/stable/2020-09-30/sharedGallery.json --namespace=com.azure.mgmttest.computegallery
if %errorlevel% neq 0 exit /b %errorlevel%
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/network/resource-manager/Microsoft.Network/stable/2021-02-01/networkWatcher.json --namespace=com.azure.mgmttest.networkwatcher
if %errorlevel% neq 0 exit /b %errorlevel%
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/cdn/resource-manager/Microsoft.Cdn/stable/2020-09-01/afdx.json --namespace=com.azure.mgmttest.afdx
if %errorlevel% neq 0 exit /b %errorlevel%
REM nested x-ms-flatten from superclass in ExtendedProduct
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/azurestack/resource-manager/Microsoft.AzureStack/preview/2020-06-01-preview/Product.json --namespace=com.azure.mgmttest.azurestack
if %errorlevel% neq 0 exit /b %errorlevel%
REM conflict property name from 2 x-ms-flatten in LabDetails and LabProperties
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/education/resource-manager/Microsoft.Education/preview/2021-12-01-preview/education.json --namespace=com.azure.mgmttest.education
if %errorlevel% neq 0 exit /b %errorlevel%
REM do not flatten if polymorphic in DevicePropertiesFormat
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/hybridnetwork/resource-manager/Microsoft.HybridNetwork/stable/2021-05-01/device.json --namespace=com.azure.mgmttest.hybridnetwork
if %errorlevel% neq 0 exit /b %errorlevel%
REM flatten the empty model which has non-empty parent model
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://github.com/Azure/azure-rest-api-specs/blob/main/specification/monitor/resource-manager/Microsoft.Insights/preview/2021-09-01-preview/dataCollectionRules_API.json --namespace=com.azure.mgmttest.monitor
if %errorlevel% neq 0 exit /b %errorlevel%

REM swagger customed Resource and ProxyResource
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENT_ARGUMENTS% --input-file=https://github.com/Azure/azure-rest-api-specs/blob/main/specification/trafficmanager/resource-manager/Microsoft.Network/stable/2018-08-01/trafficmanager.json --namespace=com.azure.mgmttest.trafficmanager
if %errorlevel% neq 0 exit /b %errorlevel%

REM fluent lite
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --pom-file=pom_generated_resources.xml https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/resources/resource-manager/readme.md --tag=package-resources-2021-01 --java.namespace=com.azure.mgmtlitetest.resources
if %errorlevel% neq 0 exit /b %errorlevel%

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/da0cfefaa0e6c237e1e3819f1cb2e11d7606878d/specification/storage/resource-manager/readme.md --tag=package-2021-01 --java.namespace=com.azure.mgmtlitetest.storage
if %errorlevel% neq 0 exit /b %errorlevel%

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/advisor/resource-manager/readme.md --tag=package-2020-01 --java.namespace=com.azure.mgmtlitetest.advisor
if %errorlevel% neq 0 exit /b %errorlevel%

REM pageable + LRO
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/mediaservices/resource-manager/readme.md --tag=package-account-2023-01 --java.namespace=com.azure.mgmtlitetest.mediaservices
if %errorlevel% neq 0 exit /b %errorlevel%

CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/resources/resource-manager/readme.md --tag=package-policy-2020-09 --java.namespace=com.azure.mgmtlitetest.policy
if %errorlevel% neq 0 exit /b %errorlevel%

REM UUID subscriptionId
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false --input-file=https://github.com/Azure/azure-rest-api-specs/blob/0a2eb0d14f5132fcfd30222d584acf67713332ea/specification/containerregistry/resource-manager/Microsoft.ContainerRegistry/stable/2022-12-01/containerregistry.json --namespace=com.azure.mgmtlitetest.containerregistrylite
if %errorlevel% neq 0 exit /b %errorlevel%

REM multiple inheritance with conflict field
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/botservice/resource-manager/readme.md --tag=package-preview-2021-05 --java.namespace=com.azure.mgmtlitetest.botservice
if %errorlevel% neq 0 exit /b %errorlevel%

REM model inherit ErrorResponse
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/196886564583ff59186bd0ef44d923120aaf3f78/specification/managednetworkfabric/resource-manager/Microsoft.ManagedNetworkFabric/stable/2023-06-15/NetworkFabrics.json --java.namespace=com.azure.mgmtlitetest.managednetworkfabric
if %errorlevel% neq 0 exit /b %errorlevel%

REM schema clean-up
CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false --input-file=./swagger/schema-cleanup.json --java.namespace=com.azure.mgmtlitetest.schemacleanup
if %errorlevel% neq 0 exit /b %errorlevel%

REM CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/network/resource-manager/readme.md --tag=package-2020-06 --java.namespace=com.azure.mgmtlitetest.network
REM CALL autorest --version=%AUTOREST_CORE_VERSION% %FLUENTLITE_ARGUMENTS% --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/compute/resource-manager/readme.md --tag=package-2020-06-30 --java.namespace=com.azure.mgmtlitetest.compute

REM delete module-info as fluent-test is on java8
DEL "src\main\java\module-info.java"
