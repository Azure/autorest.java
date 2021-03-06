# Java Codegen Report
Generated at 2021-03-06T19:30:35.792508
## Success
<details>
<summary>RP count: 167</summary>

- EnterpriseKnowledgeGraph
- addons
- adp
- advisor
- alertsmanagement
- analysisservices
- apimanagement
- appconfiguration
- applicationinsights
- appplatform
- attestation
- authorization
- automanage
- azure-kusto
- azureactivedirectory
- azuredata
- azurestack
- azurestackhci
- baremetalinfrastructure
- batch
- batchai
- billing
- blockchain
- blueprint
- botservice
- cdn
- cloudshell
- cognitiveservices
- commerce
- communication
- compute
- confidentialledger
- confluent
- consumption
- containerinstance
- containerregistry
- containerservice
- cosmos-db
- cost-management
- cpim
- customer-insights
- customerlockbox
- customproviders
- databox
- databoxedge
- databricks
- datacatalog
- datadog
- datalake-analytics
- datalake-store
- datamigration
- dataprotection
- datashare
- desktopvirtualization
- deviceprovisioningservices
- deviceupdate
- devops
- devspaces
- devtestlabs
- digitaltwins
- dnc
- dns
- domainservices
- edgeorder
- engagementfabric
- eventgrid
- eventhub
- extendedlocation
- frontdoor
- guestconfiguration
- hanaonazure
- hardwaresecuritymodules
- hdinsight
- healthbot
- healthcareapis
- hybridcompute
- hybriddatamanager
- hybridkubernetes
- hybridnetwork
- imagebuilder
- iotcentral
- iothub
- iotsecurity
- iotspaces
- keyvault
- kubernetesconfiguration
- labservices
- logic
- machinelearningcompute
- machinelearningexperimentation
- machinelearningservices
- maintenance
- managednetwork
- managedservices
- managementgroups
- managementpartner
- maps
- mariadb
- marketplace
- marketplaceordering
- mediaservices
- migrateprojects
- mixedreality
- monitor
- msi
- mysql
- netapp
- network
- notificationhubs
- operationalinsights
- operationsmanagement
- peering
- policyinsights
- portal
- postgresql
- postgresqlhsc
- powerbidedicated
- powerbiembedded
- powerplatform
- privatedns
- providerhub
- purview
- quantum
- recoveryservices
- recoveryservicesbackup
- recoveryservicessiterecovery
- redhatopenshift
- redis
- redisenterprise
- relay
- reservations
- resourcegraph
- resourcehealth
- resourcemover
- saas
- scheduler
- search
- securityandcompliance
- securityinsights
- serialconsole
- servicebus
- servicefabricmanagedclusters
- servicefabricmesh
- signalr
- softwareplan
- sql
- sqlvirtualmachine
- storSimple1200Series
- storage
- storagecache
- storageimportexport
- storagepool
- storagesync
- storsimple8000series
- streamanalytics
- subscription
- support
- synapse
- timeseriesinsights
- trafficmanager
- visualstudio
- vmware
- vmwarecloudsimple
- web
- windowsesu
- windowsiot
- workloadmonitor
</details>

## Failure at Codegen
- deploymentmanager
- security

## Failure at Build
- automation
- servicefabric

## Logs
<details>
<summary>adhybridhealthservice</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

```
**stderr**
```
FATAL: Error: Name is empty!
  Error: Plugin pre-namer reported failure.

```
</details>

<details>
<summary>automation</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] --< com.azure.resourcemanager:azure-resourcemanager-automation-generated >--
[INFO] Building Microsoft Azure SDK for Automation Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-automation-generated ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-automation-generated ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 452 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/azure/resourcemanager/automation/fluent/DscConfigurationsClient.java:[201,27] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in interface azure.resourcemanager.automation.fluent.DscConfigurationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1322,41] method updateAsync(java.lang.String,java.lang.String,java.lang.String) is already defined in class azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1348,34] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in class azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[INFO] 3 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.272 s
[INFO] Finished at: 2021-03-06T18:24:23Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-automation-generated: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/azure/resourcemanager/automation/fluent/DscConfigurationsClient.java:[201,27] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in interface azure.resourcemanager.automation.fluent.DscConfigurationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1322,41] method updateAsync(java.lang.String,java.lang.String,java.lang.String) is already defined in class azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1348,34] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in class azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException

```
**stderr**
```

```
</details>

<details>
<summary>changeanalysis</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProxyResource' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ErrorDetail' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ErrorResponse' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ErrorAdditionalInfo' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

```
**stderr**
```
FATAL: Error: Not able to process media type default at this moment.
  Error: Plugin modelerfour reported failure.

```
</details>

<details>
<summary>datafactory</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SubResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SubResourceDebugResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeDebugResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeStatusResponse' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryRepoUpdate' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GitHubAccessTokenRequest' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GitHubAccessTokenResponse' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'UserAccessPolicy' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AccessPolicyResponse' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'EncryptionConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CMKIdentityDefinition' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PipelineReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TriggerPipelineReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TriggerResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryUpdateParameters' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryIdentity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatasetReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatasetResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatasetDebugResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LinkedServiceReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LinkedServiceResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LinkedServiceDebugResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationListResponse' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Operation-display' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowStagingInfo' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowDebugResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedPrivateEndpointResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedVirtualNetworkReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedVirtualNetworkResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MappingDataFlowTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowSource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowSink' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonS3DatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AvroDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExcelDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ParquetDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DelimitedTextDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'JsonDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'XmlDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OrcDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlMITableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlDWTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CassandraTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbSqlApiCollectionDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DocumentDbCollectionDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsEntityDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsCrmEntityDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CommonDataServiceForAppsEntityDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataLakeStoreDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobFSDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Office365DatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FileShareDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbCollectionDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbAtlasCollectionDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbV2CollectionDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbMongoDbApiCollectionDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ODataResourceDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TeradataTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMySqlTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonRedshiftTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Db2TableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RelationalTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'InformixTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OdbcTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MySqlTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PostgreSqlTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MicrosoftAccessTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceObjectDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceServiceCloudObjectDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SybaseTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapCloudForCustomerResourceDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapEccResourceDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapHanaTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapOpenHubTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RestResourceDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapTableResourceDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSearchIndexDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HttpDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GenericDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzurePostgreSqlTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DrillDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleBigQueryDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GreenplumDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HiveDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ImpalaDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PhoenixDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PrestoDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SparkDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'NetezzaTableDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'VerticaDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsAXResourceDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SnowflakeDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SharePointOnlineListDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDatabricksDeltaLakeDatasetTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedIntegrationRuntimeOperationResult' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedIntegrationRuntimeNode' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedIntegrationRuntimeError' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SelfHostedIntegrationRuntimeNode' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureStorageLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobStorageLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlDWLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlDatabaseLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlMILinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBatchLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureKeyVaultLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsCrmLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CommonDataServiceForAppsLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FileServerLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFileStorageLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleCloudStorageLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMySqlLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MySqlLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PostgreSqlLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SybaseLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Db2LinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TeradataLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLServiceLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OdbcLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'InformixLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MicrosoftAccessLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HdfsLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ODataLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebAnonymousAuthentication' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebBasicAuthentication' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebClientCertificateAuthentication' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CassandraLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbAtlasLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbV2LinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbMongoDbApiLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataLakeStoreLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobFSLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Office365LinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceServiceCloudLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapCloudForCustomerLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapEccLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapOpenHubLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RestServiceLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonS3LinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonRedshiftLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSearchLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HttpLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FtpServerLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SftpServerLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapBWLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapHanaLinkedServiceProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonMWSLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzurePostgreSqlLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ConcurLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CouchbaseLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DrillLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'EloquaLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleBigQueryLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GreenplumLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HBaseLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HiveLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HubspotLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ImpalaLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'JiraLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MagentoLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MariaDBLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMariaDBLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MarketoLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PaypalLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PhoenixLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PrestoLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'QuickBooksLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceNowLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ShopifyLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SparkLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SquareLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'XeroLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ZohoLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'VerticaLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'NetezzaLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceMarketingCloudLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightOnDemandLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataLakeAnalyticsLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDatabricksLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDatabricksDetltaLakeLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ResponsysLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsAXLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleServiceCloudLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleAdWordsLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapTableLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFunctionLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SnowflakeLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SharePointOnlineListLinkedServiceTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ControlActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecutionActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CopyActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CopyActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightHiveActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightHiveActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightPigActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightPigActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightMapReduceActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightMapReduceActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightStreamingActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightStreamingActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightSparkActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightSparkActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteSSISPackageActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteSSISPackageActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomActivityReferenceObject' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerStoredProcedureActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerStoredProcedureActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecutePipelineActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecutePipelineActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeleteActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeleteActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerCommandActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerCommandActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LookupActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LookupActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebActivityAuthentication' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GetMetadataActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GetMetadataActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IfConditionActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IfConditionActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SwitchActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SwitchActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SwitchCase' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ForEachActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ForEachActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLBatchExecutionActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLBatchExecutionActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLUpdateResourceActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLUpdateResourceActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLExecutePipelineActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLExecutePipelineActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataLakeAnalyticsUSQLActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataLakeAnalyticsUSQLActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WaitActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WaitActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'UntilActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'UntilActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ValidationActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ValidationActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FilterActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FilterActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksNotebookActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksNotebookActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkJarActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkJarActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkPythonActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkPythonActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SetVariableActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SetVariableActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AppendVariableActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AppendVariableActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFunctionActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFunctionActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebHookActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebHookActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteDataFlowActivity' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteDataFlowActivityTypeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MultiplePipelineTrigger' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ScheduleTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'BlobTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'BlobEventsTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomEventsTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TumblingWindowTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RetryPolicy' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RerunTumblingWindowTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ChainingTrigger-typeProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

WARNING (PreCheck/DuplicateSchema): Duplicate Schema named BlobEventTypes -- type: undefined => "string",items: {"$ref":"#/components/schemas/BlobEventTypes"} => <none>,enum: undefined => ["Microsoft.Storage.BlobCreated","Microsoft.Storage.BlobDeleted"],x-ms-enum: undefined => {"name":"BlobEventTypes","modelAsString":true} 

```
**stderr**
```
FATAL: Error: Enum types of 'object' and format 'undefined' are not supported. Correct your input (JsonFormatFilePattern).
  Error: Plugin modelerfour reported failure.

```
</details>

<details>
<summary>deploymentmanager</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/SchemaMissingType): The schema 'RolloutRequest' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RolloutRequestProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StepGroup' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PrePostStep' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ArtifactSource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ArtifactSource-properties' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ArtifactSourceProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SasProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Rollout' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Rollout-properties' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RolloutProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RolloutOperationInfo' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceTopologyResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceTopologyResource-properties' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceTopologyProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceResource-properties' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Service' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceUnitResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceUnitResource-properties' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceUnit' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceUnitProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceUnitArtifacts' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RolloutStep' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StepOperationInfo' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ResourceOperation' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Message' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CloudError' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CloudErrorBody' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Operation' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationDetail' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StepResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TrackedResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

WARNING (PreNamer/DeduplicateName): Deduplicating schema name: 'RolloutProperties' -> 'RolloutPropertiesAutoGenerated'

WARNING (PreNamer/DeduplicateName): Deduplicating schema name: 'ArtifactSourceProperties' -> 'ArtifactSourcePropertiesAutoGenerated'
INFORMATION (FluentNamer): Load fluent settings
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentNamer): Transform code model
INFORMATION (SchemaNameNormalization): Override default name, from 'timeStamp' to 'timestamp'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'ServiceTopologies'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'Steps'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'Rollouts'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'ArtifactSources'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'ServiceTopologyResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'TrackedResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'ServiceResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'ServiceUnitResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'StepResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'RolloutRequest'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'Rollout'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'ArtifactSource'
INFORMATION (ErrorTypeNormalization): Rename error from CloudError to ManagementError
INFORMATION (SchemaCleanup): Remove unused schema 'TrackedResource'
INFORMATION (SchemaCleanup): Remove unused schema 'Resource'
INFORMATION (FluentGen): Read YAML
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentGen): Map code model to client model
INFORMATION (FluentMapper): Add Inner to response types: [ArtifactSource, Rollout, RolloutRequest, OperationsList, ServiceTopologyResource, ServiceResource, ServiceUnitResource, StepResource]
INFORMATION (FluentGen): Java template for client model
INFORMATION (FluentGen): Process for Fluent Lite, SDK integration disabled
INFORMATION (ResourceCreate): ResourceCreate: Fluent model 'ServiceTopologyResource', method reference 'createOrUpdate', body parameter 'ServiceTopologyResourceInner'
INFORMATION (ResourceParser): Fluent model 'ServiceTopologyResource' as category RESOURCE_GROUP_AS_PARENT
INFORMATION (ResourceUpdate): ResourceUpdate: Fluent model 'ServiceTopologyResource', method reference 'createOrUpdate', body parameter 'ServiceTopologyResourceInner'
INFORMATION (ResourceRefresh): ResourceRefresh: Fluent model 'ServiceTopologyResource', method reference 'getByResourceGroup'
INFORMATION (ResourceDelete): ResourceDelete: Fluent model 'ServiceTopologyResource', method reference 'delete'
INFORMATION (ResourceCreate): ResourceCreate: Fluent model 'ServiceResource', method reference 'createOrUpdate', body parameter 'ServiceResourceInner'
INFORMATION (ResourceParser): Fluent model 'ServiceResource' as category NESTED_CHILD
INFORMATION (ResourceUpdate): ResourceUpdate: Fluent model 'ServiceResource', method reference 'createOrUpdate', body parameter 'ServiceResourceInner'
INFORMATION (ResourceRefresh): ResourceRefresh: Fluent model 'ServiceResource', method reference 'get'
INFORMATION (ResourceDelete): ResourceDelete: Fluent model 'ServiceResource', method reference 'delete'
INFORMATION (ResourceCreate): ResourceCreate: Fluent model 'ServiceUnitResource', method reference 'createOrUpdate', body parameter 'ServiceUnitResourceInner'
INFORMATION (ResourceParser): Fluent model 'ServiceUnitResource' as category NESTED_CHILD
INFORMATION (ResourceUpdate): ResourceUpdate: Fluent model 'ServiceUnitResource', method reference 'createOrUpdate', body parameter 'ServiceUnitResourceInner'
INFORMATION (ResourceRefresh): ResourceRefresh: Fluent model 'ServiceUnitResource', method reference 'get'
INFORMATION (ResourceDelete): ResourceDelete: Fluent model 'ServiceUnitResource', method reference 'delete'
INFORMATION (ResourceCreate): ResourceCreate: Fluent model 'StepResource', method reference 'createOrUpdate', body parameter 'StepResourceInner'
INFORMATION (ResourceParser): Fluent model 'StepResource' as category RESOURCE_GROUP_AS_PARENT
INFORMATION (ResourceUpdate): ResourceUpdate: Fluent model 'StepResource', method reference 'createOrUpdate', body parameter 'StepResourceInner'
INFORMATION (ResourceRefresh): ResourceRefresh: Fluent model 'StepResource', method reference 'getByResourceGroup'
INFORMATION (ResourceDelete): ResourceDelete: Fluent model 'StepResource', method reference 'delete'
INFORMATION (ResourceCreate): ResourceCreate: Fluent model 'RolloutRequest', method reference 'createOrUpdate', body parameter 'RolloutRequestInner'
INFORMATION (ResourceParser): Fluent model 'RolloutRequest' as category RESOURCE_GROUP_AS_PARENT
INFORMATION (ResourceUpdate): ResourceUpdate: Fluent model 'RolloutRequest', method reference 'createOrUpdate', body parameter 'RolloutRequestInner'
INFORMATION (ResourceDelete): ResourceDelete: Fluent model 'RolloutRequest', method reference 'delete'
INFORMATION (ResourceActions): ResourceActions: Fluent model 'RolloutRequest', action methods: [cancel, restart]
INFORMATION (ResourceCreate): ResourceCreate: Fluent model 'ArtifactSource', method reference 'createOrUpdate', body parameter 'ArtifactSourceInner'
INFORMATION (ResourceParser): Fluent model 'ArtifactSource' as category RESOURCE_GROUP_AS_PARENT
INFORMATION (ResourceUpdate): ResourceUpdate: Fluent model 'ArtifactSource', method reference 'createOrUpdate', body parameter 'ArtifactSourceInner'
INFORMATION (ResourceRefresh): ResourceRefresh: Fluent model 'ArtifactSource', method reference 'getByResourceGroup'
INFORMATION (ResourceDelete): ResourceDelete: Fluent model 'ArtifactSource', method reference 'delete'

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.IllegalStateException: Duplicate key subscriptionId (attempted merging values com.azure.autorest.model.clientmodel.ProxyMethodParameter@1b0e0632 and com.azure.autorest.model.clientmodel.ProxyMethodParameter@64ed9dc4)
java.lang.IllegalStateException: Duplicate key subscriptionId (attempted merging values com.azure.autorest.model.clientmodel.ProxyMethodParameter@1b0e0632 and com.azure.autorest.model.clientmodel.ProxyMethodParameter@64ed9dc4)
	at java.base/java.util.stream.Collectors.duplicateKeyException(Collectors.java:133)
	at java.base/java.util.stream.Collectors.lambda$uniqKeysMapAccumulator$1(Collectors.java:180)
	at java.base/java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:177)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation.getParametersByLocation(ResourceOperation.java:137)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation.getParametersByLocation(ResourceOperation.java:130)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation.getPathParameters(ResourceOperation.java:150)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh.getGetByIdCollectionMethods(ResourceRefresh.java:95)
	at com.azure.autorest.fluent.mapper.ResourceParser.lambda$processAdditionalCollectionMethods$8(ResourceParser.java:112)
	at java.base/java.util.stream.ReferencePipeline$7$1.accept(ReferencePipeline.java:271)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.fluent.mapper.ResourceParser.processAdditionalCollectionMethods(ResourceParser.java:113)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
	at com.azure.autorest.fluent.mapper.ResourceParser.processAdditionalMethods(ResourceParser.java:75)
	at com.azure.autorest.fluent.mapper.FluentMapper.map(FluentMapper.java:59)
	at com.azure.autorest.fluent.FluentGen.handleFluentLite(FluentGen.java:244)
	at com.azure.autorest.fluent.FluentGen.processInternal(FluentGen.java:92)
	at com.azure.autorest.extension.base.plugin.NewPlugin.process(NewPlugin.java:202)
	at com.azure.autorest.fluent.Main.lambda$main$1(Main.java:18)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$dispatch$2(Connection.java:151)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$process$3(Connection.java:270)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

FATAL (FluentGen): Unhandled error: Duplicate key subscriptionId (attempted merging values com.azure.autorest.model.clientmodel.ProxyMethodParameter@1b0e0632 and com.azure.autorest.model.clientmodel.ProxyMethodParameter@64ed9dc4)
java.lang.IllegalStateException: Duplicate key subscriptionId (attempted merging values com.azure.autorest.model.clientmodel.ProxyMethodParameter@1b0e0632 and com.azure.autorest.model.clientmodel.ProxyMethodParameter@64ed9dc4)
	at java.base/java.util.stream.Collectors.duplicateKeyException(Collectors.java:133)
	at java.base/java.util.stream.Collectors.lambda$uniqKeysMapAccumulator$1(Collectors.java:180)
	at java.base/java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:177)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation.getParametersByLocation(ResourceOperation.java:137)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation.getParametersByLocation(ResourceOperation.java:130)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation.getPathParameters(ResourceOperation.java:150)
	at com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh.getGetByIdCollectionMethods(ResourceRefresh.java:95)
	at com.azure.autorest.fluent.mapper.ResourceParser.lambda$processAdditionalCollectionMethods$8(ResourceParser.java:112)
	at java.base/java.util.stream.ReferencePipeline$7$1.accept(ReferencePipeline.java:271)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.fluent.mapper.ResourceParser.processAdditionalCollectionMethods(ResourceParser.java:113)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
	at com.azure.autorest.fluent.mapper.ResourceParser.processAdditionalMethods(ResourceParser.java:75)
	at com.azure.autorest.fluent.mapper.FluentMapper.map(FluentMapper.java:59)
	at com.azure.autorest.fluent.FluentGen.handleFluentLite(FluentGen.java:244)
	at com.azure.autorest.fluent.FluentGen.processInternal(FluentGen.java:92)
	at com.azure.autorest.extension.base.plugin.NewPlugin.process(NewPlugin.java:202)
	at com.azure.autorest.fluent.Main.lambda$main$1(Main.java:18)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$dispatch$2(Connection.java:151)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$process$3(Connection.java:270)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>intune</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Location' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LocationProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Error' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LocationCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GroupsCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GroupItem' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GroupProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ApplicationCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Application' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ApplicationProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IOSMAMPolicyCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AndroidMAMPolicyCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'iOSMAMPolicy' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AndroidMAMPolicy' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MAMPolicyAppIdOrGroupIdPayload' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MAMPolicyAppOrGroupIdProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MAMPolicyProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'iOSMAMPolicyProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AndroidMAMPolicyProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeviceCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Device' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeviceProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WipeDeviceOperationResult' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WipeDeviceOperationResultProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationResultCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationResult' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationResultProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'operationMetadataProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StatusesDefault' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StatusesProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedUserCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedUser' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedUserProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledAppCollection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledApp' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledAppProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledAppError' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.
INFORMATION (FluentNamer): Load fluent settings
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentNamer): Transform code model
INFORMATION (FluentTransformer): Rename ungrouped operation group to 'ResourceProvider'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'GetLocationByHostName' to 'GetLocationByHostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (ErrorTypeNormalization): Rename error from Error to ManagementError
INFORMATION (FluentGen): Read YAML
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentGen): Map code model to client model
INFORMATION (FluentMapper): Add Inner to response types: [WipeDeviceOperationResult, FlaggedEnrolledApp, FlaggedUser, GroupItem, IOsmamPolicy, OperationResult, Location, AndroidMamPolicy, Application, Device]

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.IllegalArgumentException: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
java.lang.IllegalArgumentException: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
	at com.azure.autorest.mapper.ClientMethodMapper.map(ClientMethodMapper.java:87)
	at com.azure.autorest.mapper.MethodGroupMapper.map(MethodGroupMapper.java:115)
	at com.azure.autorest.mapper.ServiceClientMapper.map(ServiceClientMapper.java:88)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:118)
	at com.azure.autorest.fluent.FluentGen.handleMap(FluentGen.java:145)
	at com.azure.autorest.fluent.FluentGen.processInternal(FluentGen.java:86)
	at com.azure.autorest.extension.base.plugin.NewPlugin.process(NewPlugin.java:202)
	at com.azure.autorest.fluent.Main.lambda$main$1(Main.java:18)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$dispatch$2(Connection.java:151)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$process$3(Connection.java:270)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

FATAL (FluentGen): Unhandled error: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
java.lang.IllegalArgumentException: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
	at com.azure.autorest.mapper.ClientMethodMapper.map(ClientMethodMapper.java:87)
	at com.azure.autorest.mapper.MethodGroupMapper.map(MethodGroupMapper.java:115)
	at com.azure.autorest.mapper.ServiceClientMapper.map(ServiceClientMapper.java:88)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:118)
	at com.azure.autorest.fluent.FluentGen.handleMap(FluentGen.java:145)
	at com.azure.autorest.fluent.FluentGen.processInternal(FluentGen.java:86)
	at com.azure.autorest.extension.base.plugin.NewPlugin.process(NewPlugin.java:202)
	at com.azure.autorest.fluent.Main.lambda$main$1(Main.java:18)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$dispatch$2(Connection.java:151)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$process$3(Connection.java:270)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>machinelearning</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)
[Exception] No input files provided.

Use --help to get help information.

```
**stderr**
```

```
</details>

<details>
<summary>migrate</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)
[Exception] No input files provided.

Use --help to get help information.

```
**stderr**
```

```
</details>

<details>
<summary>resources</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)
[Exception] No input files provided.

Use --help to get help information.

```
**stderr**
```

```
</details>

<details>
<summary>security</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/SchemaMissingType): The schema 'ComplianceResultList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PricingList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdvancedThreatProtectionProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TagsResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionsList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionAnalyticsModelProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionAnalyticsModelProperties-devicesMetricsItem' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionAnalyticsModelList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecurityAggregatedAlertList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecurityAggregatedRecommendationList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecurityAggregatedAlertProperties-topDevicesListItem' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TagsResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WorkspaceSettingList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RegulatoryComplianceStandardList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RegulatoryComplianceControlList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RegulatoryComplianceAssessmentList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AutomationList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Rule' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'EffectiveNetworkSecurityGroups' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdaptiveNetworkHardeningProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdaptiveNetworkHardening' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdaptiveNetworkHardeningsList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExternalSecuritySolutionList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeviceList' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ErrorAdditionalInfo' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

WARNING (PreCheck/DuplicateSchema): Duplicate Schema named ExternalSecuritySolutionKind -- type: undefined => "string",properties: {"kind":{"description":"The kind of the external solution","$ref":"#/components/schemas/ExternalSecuritySolutionKind"}} => <none>,enum: undefined => ["CEF","ATA","AAD"],x-ms-enum: undefined => {"name":"ExternalSecuritySolutionKind","modelAsString":true,"values":[{"value":"CEF"},{"value":"ATA"},{"value":"AAD"}]} 

WARNING (PreCheck/DuplicateSchema): Duplicate Schema named AadConnectivityState -- type: undefined => "string",properties: {"connectivityState":{"$ref":"#/components/schemas/AadConnectivityState"}} => <none>,title: undefined => "The connectivity state of the external AAD solution ",enum: undefined => ["Discovered","NotLicensed","Connected"],x-ms-enum: undefined => {"name":"AadConnectivityState","modelAsString":true,"values":[{"value":"Discovered"},{"value":"NotLicensed"},{"value":"Connected"}]} 
INFORMATION (FluentNamer): Load fluent settings
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentNamer): Transform code model

WARNING (FluentTransformer): Modify parameter 'ascLocation' implementation from CLIENT to METHOD
INFORMATION (SchemaNameNormalization): Override default name, from 'ETag' to 'Etag'

WARNING (SchemaNameNormalization): Rename schema from 'Enum3' to 'SettingsSettingName', based on operation group 'Settings'

WARNING (SchemaNameNormalization): Rename schema from 'Enum18' to 'TasksTaskUpdateActionType', based on operation group 'Tasks'

WARNING (SchemaNameNormalization): Rename schema from 'Enum20' to 'InformationProtectionPoliciesInformationProtectionPolicyName', based on operation group 'InformationProtectionPolicies'

WARNING (SchemaNameNormalization): Rename schema from 'Enum40' to 'AdaptiveApplicationControlsIncludePathRecommendations', based on operation group 'AdaptiveApplicationControls'

WARNING (SchemaNameNormalization): Rename schema from 'Enum41' to 'AdaptiveApplicationControlsSummary', based on operation group 'AdaptiveApplicationControls'

WARNING (NamingConflictResolver): Name conflict of choice with object 'Protocol'
INFORMATION (NamingConflictResolver): Rename choice from 'Protocol' to 'ProtocolValue'

WARNING (NamingConflictResolver): Name conflict of choice with object 'ExternalSecuritySolutionKind'
INFORMATION (NamingConflictResolver): Rename choice from 'ExternalSecuritySolutionKind' to 'ExternalSecuritySolutionKindValue'

WARNING (NamingConflictResolver): Name conflict of choice with object 'Kind'
INFORMATION (NamingConflictResolver): Rename choice from 'Kind' to 'KindValue'

WARNING (NamingConflictResolver): Name conflict of choice with object 'AadConnectivityState'
INFORMATION (NamingConflictResolver): Rename choice from 'AadConnectivityState' to 'AadConnectivityStateValue'
INFORMATION (OperationNameNormalization): Rename operation from 'listBySubscription' to 'list', in operation group 'IotSecuritySolution'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'IotSecuritySolution'

WARNING (OperationNameNormalization): Conflict operation name found after attempted rename '[get, list]', in operation group 'IotAlertTypes'

WARNING (OperationNameNormalization): Conflict operation name found after attempted rename '[get, list]', in operation group 'IotAlerts'

WARNING (OperationNameNormalization): Conflict operation name found after attempted rename '[get, list]', in operation group 'IotRecommendationTypes'

WARNING (OperationNameNormalization): Conflict operation name found after attempted rename '[get, list]', in operation group 'IotRecommendations'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'Automations'

WARNING (OperationNameNormalization): Conflict operation name found after attempted rename '[list]', in operation group 'AssessmentsMetadata'

WARNING (OperationNameNormalization): Conflict operation name found after attempted rename '[list]', in operation group 'SecureScoreControlDefinitions'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'ComplianceResult'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Pricing'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Setting'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AdvancedThreatProtectionSetting'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'DeviceSecurityGroup'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IoTSecuritySolutionModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IoTSecuritySolutionAnalyticsModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IoTSecurityAggregatedAlert'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IoTSecurityAggregatedRecommendation'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotAlertType'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotAlert'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotRecommendationType'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotRecommendation'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AscLocation'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecurityTask'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AutoProvisioningSetting'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Compliance'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'InformationProtectionPolicy'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecurityContact'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'WorkspaceSetting'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'RegulatoryComplianceStandard'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'RegulatoryComplianceControl'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'RegulatoryComplianceAssessment'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecuritySubAssessment'
INFORMATION (ResourceTypeNormalization): Change parent from 'TrackedResource' to 'Resource', for 'Automation'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'TrackedResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AlertsSuppressionRule'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'ServerVulnerabilityAssessment'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecurityAssessmentMetadata'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecurityAssessment'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AdaptiveApplicationControlGroup'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AdaptiveNetworkHardening'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'AllowedConnectionsResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'TopologyResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'JitNetworkAccessPolicy'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'DiscoveredSecuritySolution'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecuritySolutionsReferenceData'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'ExternalSecuritySolution'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecureScoreItem'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecureScoreControlDetails'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecureScoreControlDefinitionItem'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'SecuritySolution'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'ConnectorSetting'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Scan'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'ScanResult'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'RuleResults'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotDefenderSettingsModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotSensorsModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Device'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'OnPremiseIotSensor'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotSitesModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotAlertModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IotRecommendationModel'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Alert'
INFORMATION (ErrorTypeNormalization): Rename error from CloudError to ManagementError
INFORMATION (SchemaCleanup): Remove unused schema 'Resource'
INFORMATION (SchemaCleanup): Remove unused schema 'SecureScoreControlScore'
INFORMATION (FluentGen): Read YAML
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentGen): Map code model to client model
INFORMATION (FluentMapper): Add Inner to response types: [AdaptiveApplicationControlGroups, ScanResult, Device, IotSensorsModel, ConnectorSetting, AllowedConnectionsResource, ScanResults, Setting, AdaptiveNetworkHardening, PackageDownloads, IoTSecuritySolutionAnalyticsModelList, Operation, Scan, SecuritySolutionsReferenceDataList, JitNetworkAccessPolicy, ComplianceResult, IotAlertType, OnPremiseIotSensorsList, IotDefenderSettingsModel, AutomationValidationStatus, IoTSecuritySolutionAnalyticsModel, OnPremiseIotSensor, AutoProvisioningSetting, Scans, TopologyResource, InformationProtectionPolicy, SecureScoreControlDetails, IoTSecuritySolutionModel, RegulatoryComplianceStandard, RegulatoryComplianceControl, WorkspaceSetting, Compliance, ServerVulnerabilityAssessmentsList, IotAlertTypeList, JitNetworkAccessRequest, SecurityTask, DeviceSecurityGroup, IotSensorsList, Pricing, SecurityAssessment, IotRecommendation, SecurityAssessmentMetadata, DiscoveredSecuritySolution, Automation, IotDefenderSettingsList, IoTSecurityAggregatedAlert, IotAlert, IotAlertModel, ServerVulnerabilityAssessment, RegulatoryComplianceAssessment, PricingList, AdvancedThreatProtectionSetting, SecurityContact, SecuritySubAssessment, IotRecommendationTypeList, SecuritySolution, IotRecommendationModel, AscLocation, IoTSecurityAggregatedRecommendation, ExternalSecuritySolution, RulesResults, IotSitesList, SecureScoreControlDefinitionItem, SecureScoreItem, Alert, IotRecommendationType, AlertsSuppressionRule, AdaptiveApplicationControlGroup, IotSitesModel, RuleResults]
INFORMATION (FluentMapper): Add Inner for type 'SecureScoreControlDefinitionItem': []

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.IllegalArgumentException: discriminator not found in type ExternalSecuritySolution and its parents
java.lang.IllegalArgumentException: discriminator not found in type ExternalSecuritySolution and its parents
	at com.azure.autorest.util.SchemaUtil.getDiscriminatorSerializedName(SchemaUtil.java:100)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:166)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:182)
	at com.azure.autorest.mapper.ClientMapper.lambda$map$5(ClientMapper.java:99)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.DistinctOps$1$2.accept(DistinctOps.java:175)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:102)
	at com.azure.autorest.fluent.FluentGen.handleMap(FluentGen.java:145)
	at com.azure.autorest.fluent.FluentGen.processInternal(FluentGen.java:86)
	at com.azure.autorest.extension.base.plugin.NewPlugin.process(NewPlugin.java:202)
	at com.azure.autorest.fluent.Main.lambda$main$1(Main.java:18)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$dispatch$2(Connection.java:151)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$process$3(Connection.java:270)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

FATAL (FluentGen): Unhandled error: discriminator not found in type ExternalSecuritySolution and its parents
java.lang.IllegalArgumentException: discriminator not found in type ExternalSecuritySolution and its parents
	at com.azure.autorest.util.SchemaUtil.getDiscriminatorSerializedName(SchemaUtil.java:100)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:166)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:182)
	at com.azure.autorest.mapper.ClientMapper.lambda$map$5(ClientMapper.java:99)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.DistinctOps$1$2.accept(DistinctOps.java:175)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:102)
	at com.azure.autorest.fluent.FluentGen.handleMap(FluentGen.java:145)
	at com.azure.autorest.fluent.FluentGen.processInternal(FluentGen.java:86)
	at com.azure.autorest.extension.base.plugin.NewPlugin.process(NewPlugin.java:202)
	at com.azure.autorest.fluent.Main.lambda$main$1(Main.java:18)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$dispatch$2(Connection.java:151)
	at com.azure.autorest.extension.base.jsonrpc.Connection.lambda$process$3(Connection.java:270)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>service-map</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.1.2; node: v14.16.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist' (3.0.6350)
   Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
   Installing AutoRest extension '@microsoft.azure/classic-openapi-validator' (~1.0.9)
   Installed AutoRest extension '@microsoft.azure/classic-openapi-validator' (~1.0.9->1.0.14)
   Installing AutoRest extension '@microsoft.azure/openapi-validator' (~1.0.2)
   Installed AutoRest extension '@microsoft.azure/openapi-validator' (~1.0.2->1.0.4)
   Installing AutoRest extension 'oav' (~0.4.20)
   Installed AutoRest extension 'oav' (~0.4.20->0.4.70)
   Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
   Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
   Loading AutoRest extension '@autorest/modelerfour' (4.17.1->4.17.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ResourceReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineReference' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PortReference' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineReferenceWithHints' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ClientGroupReference' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CoreResource' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Machine' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Process' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Port' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ClientGroup' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ClientGroupMember' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineGroup' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Summary' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachinesSummary' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Relationship' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Connection' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Acceptor' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RelationshipProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ConnectionProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AcceptorProperties' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Ipv4NetworkInterface' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Ipv6NetworkInterface' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'NetworkConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AgentConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Timezone' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperatingSystemConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineResourcesConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'VirtualMachineConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HypervisorConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HostingConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureHostingConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureCloudServiceConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureVmScaleSetConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureServiceFabricClusterConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ImageConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessUser' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessDetails' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessHostedService' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessHostingConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureProcessHostingConfiguration' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SingleMachineDependencyMapRequest' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MultipleMachinesMapRequest' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineListMapRequest' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineGroupMapRequest' with an undefined type and decalared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

WARNING (PreCheck/PropertyRedeclarationWarning): Schema 'MachineGroup' has a property 'etag' that is already declared the parent schema 'CoreResource' but isn't significantly different. The property has been removed from MachineGroup

WARNING (PostOperationIdContainsUrlVerb/R2066/SDKViolation): OperationId should contain the verb: 'generatemap' in:'Maps_Generate'. Consider updating the operationId
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:4591:4

WARNING (PostOperationIdContainsUrlVerb/R2066/SDKViolation): OperationId should contain the verb: 'machinegroups' in:'MachineGroups_Create'. Consider updating the operationId
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:6557:4

WARNING (EnumInsteadOfBoolean/R3018/ARMViolation): Booleans are not descriptive and make them hard to use. Consider using string enums with allowed set of values defined. Property: live
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:58:6

WARNING (EnumInsteadOfBoolean/R3018/ARMViolation): Booleans are not descriptive and make them hard to use. Consider using string enums with allowed set of values defined. Property: live
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:2097:6

WARNING (OperationIdNounVerb/R1001/SDKViolation): Per the Noun_Verb convention for Operation Ids, the noun 'Machines' should not appear after the underscore. Note: If you have already shipped an SDK on top of this spec, fixing this warning may introduce a breaking change.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:3618:4

WARNING (PutInOperationName/R1006/SDKViolation): 'PUT' operation 'MachineGroups_Update' should use method name 'Create'. Note: If you have already shipped an SDK on top of this spec, fixing this warning may introduce a breaking change.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:6750:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: kind
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:6937:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: kind
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7070:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: kind
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7434:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: provider
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8039:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: kind
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8046:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: provider
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8306:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: kind
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8313:4

WARNING (XmsEnumValidation/R2018/SDKViolation): The enum types should have x-ms-enum type extension set with appropriate options. Property name: kind
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8450:4

WARNING (DescriptionAndTitleMissing/R4000/SDKViolation): 'properties' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7419:4

WARNING (DescriptionAndTitleMissing/R4000/SDKViolation): 'properties' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7456:4

WARNING (DescriptionAndTitleMissing/R4000/SDKViolation): 'properties' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7471:4

WARNING (DescriptionAndTitleMissing/R4000/SDKViolation): 'nodes' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8398:4

WARNING (DescriptionAndTitleMissing/R4000/SDKViolation): 'edges' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8401:4

WARNING (EnumInsteadOfBoolean/R3018/ARMViolation): Booleans are not descriptive and make them hard to use. Consider using string enums with allowed set of values defined. Property: live
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8424:4

WARNING (EnumInsteadOfBoolean/R3018/ARMViolation): Booleans are not descriptive and make them hard to use. Consider using string enums with allowed set of values defined. Property: filterProcesses
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8490:4
INFORMATION (FluentNamer): Load fluent settings
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentNamer): Transform code model
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Create sealed choice 'HostingConfigurationProvider'
INFORMATION (SchemaNameNormalization): Create sealed choice 'ProcessHostingConfigurationProvider'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'CoreResource'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Relationship'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'ClientGroupMember'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Summary'
INFORMATION (ErrorTypeNormalization): Rename error from ErrorResponse to ManagementError
INFORMATION (SchemaCleanup): Remove unused schema 'Resource'
INFORMATION (FluentGen): Read YAML
INFORMATION (FluentJavaSettings): Option, string, add-inner : null
INFORMATION (FluentJavaSettings): Option, string, remove-inner : null
INFORMATION (FluentJavaSettings): Option, string, rename-model : null
INFORMATION (FluentJavaSettings): Option, string, remove-model : null
INFORMATION (FluentJavaSettings): Option, string, name-for-ungrouped-operations : null
INFORMATION (FluentJavaSettings): Option, string, pom-file : null
INFORMATION (FluentJavaSettings): Option, string, package-version : null
INFORMATION (FluentJavaSettings): Option, boolean, sdk-integration : null
INFORMATION (FluentGen): Map code model to client model
INFORMATION (FluentMapper): Add Inner to response types: [MachinesSummary, Machine, MapResponse, Connection, ClientGroupMembersCount, Liveness, Process, ClientGroupMember, ClientGroup, Port, MachineGroup]
INFORMATION (FluentGen): Java template for client model
INFORMATION (FluentGen): Process for Fluent Lite, SDK integration disabled
INFORMATION (FluentJavaSettings): Option, string, tag : package-2015-11-preview
INFORMATION (FluentJavaSettings): Option, string, base-folder : .
INFORMATION (FluentJavaSettings): Option, string, output-folder : /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-service-map
INFORMATION (FluentJavaSettings): Option, string, azure-libraries-for-java-folder : null
INFORMATION (FluentJavaSettings): List of input files : [Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json]
INFORMATION (FluentGen): Write Java
INFORMATION (JavaFormatter): Java version: 11.0.10
INFORMATION (JavaFormatter): Java formatter enabled
INFORMATION (FluentGen): Write Xml
INFORMATION (FluentGen): Write Text

```
**stderr**
```
FATAL: Failed validating: 'file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json', error encountered: TypeError: Cannot read property 'properties' of undefined
FATAL: [object Object]
(node:24845) UnhandledPromiseRejectionWarning: Error: Plugin model-validator reported failure.
    at /home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist/lib/pipeline/plugins/external.js:27:19
    at async ScheduleNode (/home/runner/.autorest/@autorest_core@3.0.6350/node_modules/@autorest/core/dist/lib/pipeline/pipeline.js:314:33)
(Use `node --trace-warnings ...` to show where the warning was created)
(node:24845) UnhandledPromiseRejectionWarning: Unhandled promise rejection. This error originated either by throwing inside of an async function without a catch block, or by rejecting a promise which was not handled with .catch(). To terminate the node process on unhandled promise rejection, use the CLI flag `--unhandled-rejections=strict` (see https://nodejs.org/api/cli.html#cli_unhandled_rejections_mode). (rejection id: 60)
(node:24845) [DEP0018] DeprecationWarning: Unhandled promise rejections are deprecated. In the future, promise rejections that are not handled will terminate the Node.js process with a non-zero exit code.
  Error: Plugin model-validator reported failure.

```
</details>

<details>
<summary>servicefabric</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] --< com.azure.resourcemanager:azure-resourcemanager-servicefabric-generated >--
[INFO] Building Microsoft Azure SDK for ServiceFabric Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-servicefabric-generated ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-servicefabric-generated ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 184 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[45,41] method list() is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[57,41] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[67,41] method list() is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[79,41] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[89,41] method list() is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[101,41] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[39,36] method list() is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[50,36] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[59,36] method list() is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[70,36] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[79,36] method list() is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[90,36] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[238,55] method listSinglePageAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[271,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[302,45] method listAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[316,45] method listAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[329,48] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[343,48] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[355,55] method listSinglePageAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[388,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[419,45] method listAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[433,45] method listAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[446,48] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[460,48] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[472,55] method listSinglePageAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[505,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[536,45] method listAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[550,45] method listAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[563,48] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[577,48] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[76,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[86,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[96,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[39,43] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[44,43] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[49,43] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[54,43] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[59,43] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[64,43] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[INFO] 39 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.459 s
[INFO] Finished at: 2021-03-06T19:15:10Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-servicefabric-generated: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[45,41] method list() is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[57,41] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[67,41] method list() is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[79,41] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[89,41] method list() is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[101,41] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[39,36] method list() is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[50,36] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[59,36] method list() is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[70,36] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[79,36] method list() is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/models/Operations.java:[90,36] method list(com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[238,55] method listSinglePageAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[271,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[302,45] method listAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[316,45] method listAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[329,48] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[343,48] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[355,55] method listSinglePageAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[388,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[419,45] method listAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[433,45] method listAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[446,48] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[460,48] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[472,55] method listSinglePageAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[505,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[536,45] method listAsync() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[550,45] method listAsync(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[563,48] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[577,48] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[76,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[86,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[96,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[39,43] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[44,43] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[49,43] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[54,43] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[59,43] method list() is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[64,43] method list(com.azure.core.util.Context) is already defined in class azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException

```
**stderr**
```

```
</details>
