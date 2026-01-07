param (
    [int] $Parallelization = [Environment]::ProcessorCount
)

# Regenerate code
if (Test-Path ./src/main/java/com/azure/mgmttest) {
    Remove-Item -Path ./src/main/java/com/azure/mgmttest -Recurse -Force | Out-Null
}

if (Test-Path ./src/main/java/com/azure/mgmtlitetest) {
    Remove-Item -Path ./src/main/java/com/azure/mgmtlitetest -Recurse -Force | Out-Null
}

if (Test-Path ./src/samples) {
    Remove-Item -Path ./src/samples -Recurse -Force | Out-Null
}

if (Test-Path ./src/test/java/com/azure/mgmtlitetest) {
    Remove-Item -Path ./src/test/java/com/azure/mgmtlitetest -Recurse -Force | Out-Null
}

$AUTOREST_CORE_VERSION="3.9.7"
$COMMON_ARGUMENTS="--java --use=../ --java.output-folder=./ --modelerfour.additional-checks=false --modelerfour.lenient-model-deduplication=true --azure-arm --java.license-header=MICROSOFT_MIT_SMALL"
$FLUENT_ARGUMENTS="$COMMON_ARGUMENTS --fluent"
$FLUENTLITE_ARGUMENTS="$COMMON_ARGUMENTS --fluent=lite --generate-samples --generate-tests"

$ExitCode = 0

$generateScript = {
    $input = $_

    $generateOutput = Invoke-Expression "autorest $input"
    $global:ExitCode = $global:ExitCode -bor $LASTEXITCODE

    if ($LASTEXITCODE -ne 0) {
        Write-Host "
========================
autorest $input
========================
$([String]::Join("`n", $generateOutput))
        "
    } else {
        Write-Host "
========================
autorest $input
========================
SUCCEEDED
        "
    }

    if ($global:ExitCode -ne 0) {
        throw "Failed to generate from $input"
    }
}

# function singleThreadGenerate($input) {
#     Write-Host "
# ========================
# autorest $input
# ========================
#     "
#     $generateOutput = Invoke-Expression "autorest $input"
#     $global:ExitCode = $global:ExitCode -bor $LASTEXITCODE

#     if ($LASTEXITCODE -ne 0) {
#         Write-Host $([String]::Join("`n", $generateOutput))
#     } else {
#         Write-Host "SUCCEEDED"
#     }

#     if ($global:ExitCode -ne 0) {
#         throw "Failed to generate from $input"
#     }
# }

$job = @(
    # fluent premium
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources --enable-sync-stack=false",
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS ./swagger/readme.storage.md --namespace=com.azure.mgmttest.storage --enable-sync-stack=false",
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS ./swagger/readme.network.md --namespace=com.azure.mgmttest.network --enable-sync-stack=false",

    # error response that is subclass of ManagementException
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS ./swagger/readme.appservice.md --namespace=com.azure.mgmttest.appservice --enable-sync-stack=false",

    # multiple inheritance
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/cosmos-db/resource-manager/Microsoft.DocumentDB/DocumentDB/stable/2019-08-01/cosmos-db.json --namespace=com.azure.mgmttest.cosmos --enable-sync-stack=false",

    # flatten payload
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/compute/resource-manager/Microsoft.Compute/CloudserviceRP/stable/2021-03-01/cloudService.json --namespace=com.azure.mgmttest.compute --enable-sync-stack=false",

    # error response that not conform to ARM
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/e3dcc10ab749ed6731255aebec7d3214eda10da0/specification/graphrbac/data-plane/GraphRbac/stable/1.6/graphrbac.json --namespace=com.azure.mgmttest.authorization --enable-sync-stack=false",

    # client model flatten at autorest.java
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/compute/resource-manager/Microsoft.Compute/GalleryRP/stable/2020-09-30/sharedGallery.json --namespace=com.azure.mgmttest.computegallery --enable-sync-stack=false",
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/network/resource-manager/Microsoft.Network/stable/2021-02-01/networkWatcher.json --namespace=com.azure.mgmttest.networkwatcher --enable-sync-stack=false",
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/cdn/resource-manager/Microsoft.Cdn/Cdn/stable/2020-09-01/afdx.json --namespace=com.azure.mgmttest.afdx --enable-sync-stack=false",

    # nested x-ms-flatten from superclass in ExtendedProduct
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/azurestack/resource-manager/Microsoft.AzureStack/AzureStack/preview/2020-06-01-preview/Product.json --namespace=com.azure.mgmttest.azurestack --stream-style-serialization=false",

    # conflict property name from 2 x-ms-flatten in LabDetails and LabProperties
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/education/resource-manager/Microsoft.Education/Education/preview/2021-12-01-preview/education.json --namespace=com.azure.mgmttest.education",

    # do not flatten if polymorphic in DevicePropertiesFormat
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/hybridnetwork/resource-manager/Microsoft.HybridNetwork/stable/2021-05-01/device.json --namespace=com.azure.mgmttest.hybridnetwork",
    # flatten the empty model which has non-empty parent model
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/monitor/resource-manager/Microsoft.Insights/preview/2021-09-01-preview/dataCollectionRules_API.json --namespace=com.azure.mgmttest.monitor --stream-style-serialization=false --enable-sync-stack=false",

    # extract systemData from Resource
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/29f3116d3ce31f2125d1e2cfb92d6511fcb01c41/specification/postgresqlhsc/resource-manager/Microsoft.DBforPostgreSQL/stable/2022-11-08/postgresqlhsc.json --java.namespace=com.azure.mgmttest.postgresqlhsc",
    # swagger customized Resource and ProxyResource
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/trafficmanager/resource-manager/Microsoft.Network/TrafficManager/stable/2018-08-01/trafficmanager.json --namespace=com.azure.mgmttest.trafficmanager --enable-sync-stack=false",

    # ErrorDetails shared in exception and output
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/8fa9b5051129dd4808c9be1f5b753af226b044db/specification/iothub/resource-manager/Microsoft.Devices/stable/2023-06-30/iothub.json --namespace=com.azure.mgmttest.iothub",

    # resource with writable name
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/2548fe40102c9b5aa27a75a126c8367f55cb9e7d/specification/sql/resource-manager/Microsoft.Sql/stable/2021-11-01/FirewallRules.json --java.namespace=com.azure.mgmttest.resourcewithwritablename --enable-sync-stack=false"
    # non-string expandable enum
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/926540515b9d8059904f023d38c45dda8ba87c9f/specification/monitor/resource-manager/Microsoft.Insights/stable/2023-12-01/scheduledQueryRule_API.json --java.namespace=com.azure.mgmttest.nonstringexpandableenum --enable-sync-stack=false"

    # fluent lite
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --pom-file=pom_generated_resources.xml https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/resources/resource-manager/readme.md --tag=package-resources-2021-01 --java.namespace=com.azure.mgmtlitetest.resources",
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/da0cfefaa0e6c237e1e3819f1cb2e11d7606878d/specification/storage/resource-manager/readme.md --tag=package-2021-01 --java.namespace=com.azure.mgmtlitetest.storage",
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/advisor/resource-manager/Microsoft.Advisor/Advisor/readme.md --tag=package-2020-01 --java.namespace=com.azure.mgmtlitetest.advisor",

    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/resources/resource-manager/readme.md --tag=package-policy-2020-09 --java.namespace=com.azure.mgmtlitetest.policy",
    # UUID subscriptionId
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/0a2eb0d14f5132fcfd30222d584acf67713332ea/specification/containerregistry/resource-manager/Microsoft.ContainerRegistry/stable/2022-12-01/containerregistry.json --namespace=com.azure.mgmtlitetest.containerregistrylite",

    # multiple inheritance with conflict field
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/botservice/resource-manager/Microsoft.BotService/BotService/readme.md --tag=package-preview-2021-05 --java.namespace=com.azure.mgmtlitetest.botservice",

    # model inherit ErrorResponse
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/196886564583ff59186bd0ef44d923120aaf3f78/specification/managednetworkfabric/resource-manager/Microsoft.ManagedNetworkFabric/stable/2023-06-15/NetworkFabrics.json --java.namespace=com.azure.mgmtlitetest.managednetworkfabric",
    # polymorphic models in different packages
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/64337ca106a20055b389f9652ea8cf942aa94252/specification/consumption/resource-manager/Microsoft.Consumption/stable/2023-11-01/consumption.json --java.namespace=com.azure.mgmtlitetest.consumption --generate-tests=false"

    # schema clean-up
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=./swagger/schema-cleanup.json --java.namespace=com.azure.mgmtlitetest.schemacleanup"

    # stream-style-serialization
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=./swagger/stream-style-serialization.json --java.namespace=com.azure.mgmtlitetest.streamstyleserialization --stream-style-serialization=true"
    # child model with flattened property from parent
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/692cb8b5eb71505afa267cfbbee322d520eb15ff/specification/streamanalytics/resource-manager/Microsoft.StreamAnalytics/stable/2020-03-01/functions.json --java.namespace=com.azure.mgmtlitetest.streamanalytics --generate-tests=false"

    # empty-model
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=./swagger/empty-model.json --java.namespace=com.azure.mgmtlitetest.emptymodel"
    # pageable model with inheritance
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/0ed912fdfaffd5c81eb86b79df0e3b31d978d795/specification/billing/resource-manager/Microsoft.Billing/stable/2024-04-01/billingSavingsPlan.json --java.namespace=com.azure.mgmtlitetest.pageablewithinheritance"
    # empty byte array in client implementation
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/f840c84013f12b701aac7065ceeb13a59b26051b/specification/deviceprovisioningservices/resource-manager/Microsoft.Devices/stable/2022-02-05/iotdps.json --java.namespace=com.azure.mgmtlitetest.emptybytearrayinclients"

    # sync-stack
    # premium
    "--version=$AUTOREST_CORE_VERSION $FLUENT_ARGUMENTS --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/8fa9b5051129dd4808c9be1f5b753af226b044db/specification/iothub/resource-manager/Microsoft.Devices/stable/2023-06-30/iothub.json --namespace=com.azure.mgmttest.iothubsync --enable-sync-stack=true"
    # special cases
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=./swagger/sync-stack.json --namespace=com.azure.mgmtlitetest.syncstack --enable-sync-stack=true"

    # multiple PUTs under the same collection
    "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/980d7b8ba06697dd77b287a38aab617ad095f353/specification/providerhub/resource-manager/Microsoft.ProviderHub/stable/2024-09-01/providerhub.json --namespace=com.azure.mgmtlitetest.providerhub"

    # "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/network/resource-manager/readme.md --tag=package-2020-06 --java.namespace=com.azure.mgmtlitetest.network"
    # "--version=$AUTOREST_CORE_VERSION $FLUENTLITE_ARGUMENTS --regenerate-pom=false https://raw.githubusercontent.com/Azure/azure-rest-api-specs/113b466bf76cd86da547beee5300d2ef5210cfb3/specification/compute/resource-manager/readme.md --tag=package-2020-06-30 --java.namespace=com.azure.mgmtlitetest.compute"
) | ForEach-Object -Parallel $generateScript -ThrottleLimit $Parallelization -AsJob

$job | Wait-Job -Timeout 3600
$job | Receive-Job

# delete module-info as fluent-test is on java8
if (Test-Path ./src/main/java/module-info.java) {
    Remove-Item -Path ./src/main/java/module-info.java -Force -verbose
    $ExitCode = $ExitCode -bor $LASTEXITCODE
}

if ($ExitCode -ne 0) {
  throw "Failed to generate from fluent"
}
