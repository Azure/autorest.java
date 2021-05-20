# Java Codegen Report
Generated at 2021-05-20T19:33:09.950439
## Success
<details>
<summary>RP count: 173</summary>

- EnterpriseKnowledgeGraph
- addons
- adp
- advisor
- agfood
- alertsmanagement
- analysisservices
- apimanagement
- appconfiguration
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
- changeanalysis
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
- deploymentmanager
- desktopvirtualization
- deviceprovisioningservices
- deviceupdate
- devops
- devspaces
- devtestlabs
- dfp
- digitaltwins
- dnc
- dns
- domainservices
- edgeorder
- edgeorderpartner
- elastic
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
- logz
- m365securityandcompliance
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
- migrate
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
- providerhub
- quantum
- recoveryservices
- recoveryservicesbackup
- recoveryservicessiterecovery
- redhatopenshift
- redis
- redisenterprise
- relay
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
- testbase
- timeseriesinsights
- trafficmanager
- videoanalyzer
- vmware
- vmwarecloudsimple
- web
- webpubsub
- windowsesu
- windowsiot
- workloadmonitor
</details>

## Failure at Codegen
- datafactory
- reservations
- security

## Failure at Build
- applicationinsights
- automation
- privatedns
- purview
- servicefabric
- softwareplan
- visualstudio

## Logs
<details>
<summary>adhybridhealthservice</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

```
**stderr**
```
FATAL: Error: Name is empty!
  Error: Plugin pre-namer reported failure.

```
</details>

<details>
<summary>applicationinsights</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] --< com.azure.resourcemanager:azure-resourcemanager-applicationinsights >--
[INFO] Building Microsoft Azure SDK for ApplicationInsights Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-applicationinsights ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-applicationinsights ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 205 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/target/classes
[INFO] Some messages have been simplified; recompile with -Xdiags:verbose to get full output
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/fluent/OperationsClient.java:[46,39] method list() is already defined in interface com.azure.resourcemanager.applicationinsights.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/fluent/OperationsClient.java:[58,39] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.applicationinsights.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/models/Operations.java:[39,34] method list() is already defined in interface com.azure.resourcemanager.applicationinsights.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/models/Operations.java:[50,34] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.applicationinsights.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[42,41] method list() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[47,41] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[220,53] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[253,53] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[284,43] method listAsync() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[298,43] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[311,46] method list() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[325,46] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[78,46] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[43,76] incompatible types: com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner> cannot be converted to com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner>
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[48,76] incompatible types: com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner> cannot be converted to com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner>
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[233,42] incompatible types: bad return type in lambda expression
    cannot infer type arguments for com.azure.core.http.rest.PagedResponseBase<>
      reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[265,17] incompatible types: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[285,29] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedFlux<>
    reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[299,29] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedFlux<>
    reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[312,33] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedIterable<>
    reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[326,33] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedIterable<>
    reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[INFO] 21 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.298 s
[INFO] Finished at: 2021-05-20T18:20:59Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-applicationinsights: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/fluent/OperationsClient.java:[46,39] method list() is already defined in interface com.azure.resourcemanager.applicationinsights.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/fluent/OperationsClient.java:[58,39] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.applicationinsights.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/models/Operations.java:[39,34] method list() is already defined in interface com.azure.resourcemanager.applicationinsights.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/models/Operations.java:[50,34] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.applicationinsights.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[42,41] method list() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[47,41] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[220,53] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[253,53] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[284,43] method listAsync() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[298,43] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[311,46] method list() is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[325,46] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[78,46] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.applicationinsights.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[43,76] incompatible types: com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner> cannot be converted to com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner>
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsImpl.java:[48,76] incompatible types: com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner> cannot be converted to com.azure.core.http.rest.PagedIterable<com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner>
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[233,42] incompatible types: bad return type in lambda expression
[ERROR]     cannot infer type arguments for com.azure.core.http.rest.PagedResponseBase<>
[ERROR]       reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[265,17] incompatible types: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[285,29] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedFlux<>
[ERROR]     reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[299,29] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedFlux<>
[ERROR]     reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[312,33] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedIterable<>
[ERROR]     reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-applicationinsights/src/main/java/com/azure/resourcemanager/applicationinsights/implementation/OperationsClientImpl.java:[326,33] incompatible types: cannot infer type arguments for com.azure.core.http.rest.PagedIterable<>
[ERROR]     reason: inference variable T has incompatible equality constraints com.azure.resourcemanager.applicationinsights.fluent.models.OperationLiveInner,com.azure.resourcemanager.applicationinsights.fluent.models.OperationInner
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
<summary>automation</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] -----< com.azure.resourcemanager:azure-resourcemanager-automation >-----
[INFO] Building Microsoft Azure SDK for Automation Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-automation ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-automation ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 480 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/com/azure/resourcemanager/automation/fluent/DscConfigurationsClient.java:[201,27] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.automation.fluent.DscConfigurationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/com/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1322,41] method updateAsync(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/com/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1348,34] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[INFO] 3 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.667 s
[INFO] Finished at: 2021-05-20T18:23:04Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-automation: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/com/azure/resourcemanager/automation/fluent/DscConfigurationsClient.java:[201,27] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.automation.fluent.DscConfigurationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/com/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1322,41] method updateAsync(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-automation/src/main/java/com/azure/resourcemanager/automation/implementation/DscConfigurationsClientImpl.java:[1348,34] method update(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.automation.implementation.DscConfigurationsClientImpl
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
<summary>datafactory</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SubResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SubResourceDebugResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeDebugResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IntegrationRuntimeStatusResponse' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryRepoUpdate' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GitHubAccessTokenRequest' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GitHubAccessTokenResponse' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'UserAccessPolicy' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AccessPolicyResponse' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'EncryptionConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CMKIdentityDefinition' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PipelineReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TriggerPipelineReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TriggerResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryUpdateParameters' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FactoryIdentity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatasetReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatasetResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatasetDebugResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LinkedServiceReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LinkedServiceResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LinkedServiceDebugResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationListResponse' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Operation-display' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowStagingInfo' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowDebugResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedPrivateEndpointResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedVirtualNetworkReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedVirtualNetworkResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MappingDataFlowTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowSource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataFlowSink' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonS3DatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AvroDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExcelDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ParquetDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DelimitedTextDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'JsonDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'XmlDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OrcDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlMITableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlDWTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CassandraTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbSqlApiCollectionDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DocumentDbCollectionDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsEntityDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsCrmEntityDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CommonDataServiceForAppsEntityDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataLakeStoreDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobFSDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Office365DatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FileShareDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbCollectionDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbAtlasCollectionDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbV2CollectionDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbMongoDbApiCollectionDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ODataResourceDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TeradataTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMySqlTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonRedshiftTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Db2TableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RelationalTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'InformixTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OdbcTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MySqlTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PostgreSqlTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MicrosoftAccessTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceObjectDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceServiceCloudObjectDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SybaseTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapCloudForCustomerResourceDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapEccResourceDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapHanaTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapOpenHubTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RestResourceDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapTableResourceDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSearchIndexDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HttpDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GenericDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzurePostgreSqlTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DrillDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleBigQueryDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GreenplumDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HiveDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ImpalaDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PhoenixDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PrestoDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SparkDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'NetezzaTableDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'VerticaDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsAXResourceDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SnowflakeDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SharePointOnlineListDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDatabricksDeltaLakeDatasetTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedIntegrationRuntimeOperationResult' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedIntegrationRuntimeNode' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ManagedIntegrationRuntimeError' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SelfHostedIntegrationRuntimeNode' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureStorageLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobStorageLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlDWLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlDatabaseLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSqlMILinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlAlwaysEncryptedProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBatchLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureKeyVaultLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsCrmLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CommonDataServiceForAppsLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FileServerLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFileStorageLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonS3CompatibleLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleCloudStorageLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleCloudStorageLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMySqlLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MySqlLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PostgreSqlLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SybaseLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Db2LinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TeradataLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLServiceLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OdbcLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'InformixLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MicrosoftAccessLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HdfsLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ODataLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebAnonymousAuthentication' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebBasicAuthentication' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebClientCertificateAuthentication' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CassandraLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbAtlasLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MongoDbV2LinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CosmosDbMongoDbApiLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataLakeStoreLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureBlobFSLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Office365LinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceServiceCloudLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapCloudForCustomerLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapEccLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapOpenHubLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RestServiceLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonS3LinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonRedshiftLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureSearchLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HttpLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FtpServerLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SftpServerLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapBWLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapHanaLinkedServiceProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AmazonMWSLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzurePostgreSqlLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ConcurLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CouchbaseLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DrillLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'EloquaLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleBigQueryLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GreenplumLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HBaseLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HiveLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HubspotLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ImpalaLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'JiraLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MagentoLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MariaDBLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMariaDBLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MarketoLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PaypalLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PhoenixLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PrestoLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'QuickBooksLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ServiceNowLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ShopifyLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SparkLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SquareLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'XeroLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ZohoLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'VerticaLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'NetezzaLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SalesforceMarketingCloudLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightOnDemandLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataLakeAnalyticsLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDatabricksLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDatabricksDetltaLakeLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ResponsysLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DynamicsAXLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OracleServiceCloudLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GoogleAdWordsLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SapTableLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFunctionLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SnowflakeLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SharePointOnlineListLinkedServiceTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ControlActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecutionActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CopyActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CopyActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightHiveActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightHiveActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightPigActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightPigActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightMapReduceActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightMapReduceActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightStreamingActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightStreamingActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightSparkActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HDInsightSparkActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteSSISPackageActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteSSISPackageActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomActivityReferenceObject' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerStoredProcedureActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SqlServerStoredProcedureActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecutePipelineActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecutePipelineActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeleteActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeleteActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerCommandActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureDataExplorerCommandActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LookupActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LookupActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebActivityAuthentication' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GetMetadataActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GetMetadataActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IfConditionActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IfConditionActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SwitchActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SwitchActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SwitchCase' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ForEachActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ForEachActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLBatchExecutionActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLBatchExecutionActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLUpdateResourceActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLUpdateResourceActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLExecutePipelineActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureMLExecutePipelineActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataLakeAnalyticsUSQLActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DataLakeAnalyticsUSQLActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WaitActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WaitActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'UntilActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'UntilActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ValidationActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ValidationActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FilterActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FilterActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksNotebookActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksNotebookActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkJarActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkJarActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkPythonActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DatabricksSparkPythonActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SetVariableActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SetVariableActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AppendVariableActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AppendVariableActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFunctionActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureFunctionActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebHookActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WebHookActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteDataFlowActivity' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExecuteDataFlowActivityTypeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MultiplePipelineTrigger' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ScheduleTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'BlobTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'BlobEventsTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CustomEventsTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TumblingWindowTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RetryPolicy' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RerunTumblingWindowTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ChainingTrigger-typeProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

WARNING (PreCheck/DuplicateSchema): Duplicate Schema named BlobEventTypes -- type: undefined => "string",items: {"$ref":"#/components/schemas/BlobEventTypes"} => <none>,enum: undefined => ["Microsoft.Storage.BlobCreated","Microsoft.Storage.BlobDeleted"],x-ms-enum: undefined => {"name":"BlobEventTypes","modelAsString":true} 
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
INFORMATION (SchemaNameNormalization): Override default name, from 'WebHookActivity' to 'WebhookActivity'
INFORMATION (SchemaNameNormalization): Override default name, from 'eTag' to 'etag'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'catalogAdminUserName' to 'catalogAdminUsername'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'hostName' to 'hostname'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'allowHostNameCNMismatch' to 'allowHostnameCNMismatch'
INFORMATION (SchemaNameNormalization): Override default name, from 'allowHostNameCNMismatch' to 'allowHostnameCNMismatch'
INFORMATION (SchemaNameNormalization): Override default name, from 'allowHostNameCNMismatch' to 'allowHostnameCNMismatch'
INFORMATION (SchemaNameNormalization): Override default name, from 'allowHostNameCNMismatch' to 'allowHostnameCNMismatch'
INFORMATION (SchemaNameNormalization): Override default name, from 'allowHostNameCNMismatch' to 'allowHostnameCNMismatch'
INFORMATION (SchemaNameNormalization): Override default name, from 'allowHostNameCNMismatch' to 'allowHostnameCNMismatch'
INFORMATION (SchemaNameNormalization): Override default name, from 'clusterUserName' to 'clusterUsername'
INFORMATION (SchemaNameNormalization): Override default name, from 'clusterSshUserName' to 'clusterSshUsername'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'userName' to 'username'
INFORMATION (SchemaNameNormalization): Override default name, from 'WebHookActivityMethod' to 'WebhookActivityMethod'
INFORMATION (NamingConflictResolver): Rename operation group from 'PrivateEndpointConnection' to 'PrivateEndpointConnectionOperation'
INFORMATION (OperationNameNormalization): Rename operation from 'get' to 'getByResourceGroup', in operation group 'Factories'
INFORMATION (ErrorTypeNormalization): Rename error from 'CloudError' to 'ManagementError'
INFORMATION (SchemaCleanup): Remove unused object schema 'IntegrationRuntimeStatusListResponse'
INFORMATION (SchemaCleanup): Remove unused object schema 'DatasetDataElement'
INFORMATION (SchemaCleanup): Remove unused choice schema 'JsonFormatFilePattern'
INFORMATION (SchemaCleanup): Remove unused choice schema 'DatasetCompressionLevel'
INFORMATION (SchemaCleanup): Remove unused choice schema 'CompressionCodec'
INFORMATION (SchemaCleanup): Remove unused choice schema 'DynamicsDeploymentType'
INFORMATION (SchemaCleanup): Remove unused choice schema 'DynamicsAuthenticationType'
INFORMATION (SchemaCleanup): Remove unused choice schema 'ServicePrincipalCredentialType'
INFORMATION (SchemaCleanup): Remove unused choice schema 'HdiNodeTypes'
INFORMATION (SchemaCleanup): Remove unused choice schema 'JsonWriteFilePattern'
INFORMATION (SchemaCleanup): Remove unused choice schema 'CopyBehaviorType'
INFORMATION (SchemaCleanup): Remove unused choice schema 'SqlPartitionOption'
INFORMATION (SchemaCleanup): Remove unused choice schema 'SapHanaPartitionOption'
INFORMATION (SchemaCleanup): Remove unused choice schema 'SapTablePartitionOption'
INFORMATION (SchemaCleanup): Remove unused choice schema 'OraclePartitionOption'
INFORMATION (SchemaCleanup): Remove unused choice schema 'TeradataPartitionOption'
INFORMATION (SchemaCleanup): Remove unused choice schema 'NetezzaPartitionOption'
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
INFORMATION (FluentMapper): Add Inner to response types: [IntegrationRuntimeStatusResponse, LinkedServiceResource, AccessPolicyResponse, DataFlowDebugCommandResponse, TriggerQueryResponse, PipelineRun, IntegrationRuntimeNodeIpAddress, Operation, ExposureControlResponse, IntegrationRuntimeConnectionInfo, PipelineResource, PipelineRunsQueryResponse, ManagedPrivateEndpointResource, PrivateLinkResourcesWrapper, Factory, TriggerRunsQueryResponse, CreateDataFlowDebugSessionResponse, DatasetResource, CreateRunResponse, ManagedVirtualNetworkResource, DataFlowDebugSessionInfo, IntegrationRuntimeAuthKeys, AddDataFlowToDebugSessionResponse, TriggerResource, GitHubAccessTokenResponse, SsisObjectMetadataListResponse, ExposureControlBatchResponse, IntegrationRuntimeResource, ActivityRunsQueryResponse, SsisObjectMetadataStatusResponse, PrivateEndpointConnectionResource, IntegrationRuntimeMonitoringData, DataFlowResource, SelfHostedIntegrationRuntimeNode, TriggerSubscriptionOperationStatus]

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.IllegalArgumentException: discriminator not found in type ManagedVirtualNetwork and its parents
java.lang.IllegalArgumentException: discriminator not found in type ManagedVirtualNetwork and its parents
	at com.azure.autorest.util.SchemaUtil.getDiscriminatorSerializedName(SchemaUtil.java:101)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:165)
	at com.azure.autorest.mapper.ClientMapper.lambda$map$5(ClientMapper.java:100)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.DistinctOps$1$2.accept(DistinctOps.java:175)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:103)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

FATAL (FluentGen): Unhandled error: discriminator not found in type ManagedVirtualNetwork and its parents
java.lang.IllegalArgumentException: discriminator not found in type ManagedVirtualNetwork and its parents
	at com.azure.autorest.util.SchemaUtil.getDiscriminatorSerializedName(SchemaUtil.java:101)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:165)
	at com.azure.autorest.mapper.ClientMapper.lambda$map$5(ClientMapper.java:100)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.DistinctOps$1$2.accept(DistinctOps.java:175)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:103)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>intune</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Location' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LocationProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Error' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'LocationCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GroupsCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GroupItem' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'GroupProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ApplicationCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Application' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ApplicationProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IOSMAMPolicyCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AndroidMAMPolicyCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'iOSMAMPolicy' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AndroidMAMPolicy' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MAMPolicyAppIdOrGroupIdPayload' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MAMPolicyAppOrGroupIdProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MAMPolicyProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'iOSMAMPolicyProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AndroidMAMPolicyProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeviceCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Device' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeviceProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WipeDeviceOperationResult' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WipeDeviceOperationResultProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationResultCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationResult' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperationResultProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'operationMetadataProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StatusesDefault' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'StatusesProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedUserCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedUser' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedUserProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledAppCollection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledApp' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledAppProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'FlaggedEnrolledAppError' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

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
INFORMATION (ErrorTypeNormalization): Rename error from 'Error' to 'ManagementError'
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
INFORMATION (FluentMapper): Add Inner to response types: [FlaggedEnrolledApp, AndroidMamPolicy, Device, OperationResult, Location, WipeDeviceOperationResult, GroupItem, IOsmamPolicy, FlaggedUser, Application]

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.IllegalArgumentException: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
java.lang.IllegalArgumentException: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
	at com.azure.autorest.mapper.ClientMethodMapper.map(ClientMethodMapper.java:87)
	at com.azure.autorest.mapper.MethodGroupMapper.map(MethodGroupMapper.java:115)
	at com.azure.autorest.mapper.ServiceClientMapper.map(ServiceClientMapper.java:93)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:119)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

FATAL (FluentGen): Unhandled error: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
java.lang.IllegalArgumentException: [JavaCheck/SchemaError] item name value not found among properties of client model StatusesDefault
	at com.azure.autorest.mapper.ClientMethodMapper.map(ClientMethodMapper.java:87)
	at com.azure.autorest.mapper.MethodGroupMapper.map(MethodGroupMapper.java:115)
	at com.azure.autorest.mapper.ServiceClientMapper.map(ServiceClientMapper.java:93)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:119)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>machinelearning</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)
[Exception] No input files provided.

Use --help to get help information.

```
**stderr**
```

```
</details>

<details>
<summary>privatedns</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] -----< com.azure.resourcemanager:azure-resourcemanager-privatedns >-----
[INFO] Building Microsoft Azure SDK for PrivateDns Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-privatedns ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-privatedns ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 54 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/target/classes
[INFO] Some messages have been simplified; recompile with -Xdiags:verbose to get full output
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[284,20] method createOrUpdate(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[312,30] method createOrUpdateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[337,20] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[362,30] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[384,10] method delete(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[403,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[424,20] method get(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[441,30] method getWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[460,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[479,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[498,35] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[516,35] method list(java.lang.String,java.lang.String,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[535,20] method createOrUpdate(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[563,30] method createOrUpdateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[588,20] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[613,30] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[635,10] method delete(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[654,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[675,20] method get(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[692,30] method getWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[711,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[730,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[749,35] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[767,35] method list(java.lang.String,java.lang.String,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[436,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[463,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[490,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[511,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[536,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[561,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[585,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[609,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[629,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[652,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[676,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[696,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[718,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[733,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[751,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[770,29] method get(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[785,39] method getWithResponse(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[799,44] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[815,44] method list(java.lang.String,java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[836,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[863,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[890,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[911,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[936,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[961,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[985,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1009,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1029,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1052,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1076,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1096,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1118,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1133,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1151,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1170,29] method get(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1185,39] method getWithResponse(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1199,44] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1215,44] method list(java.lang.String,java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[588,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[605,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[621,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[634,10] method delete(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[650,10] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[664,35] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[679,45] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[690,50] method list() is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[703,50] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[715,50] method listByResourceGroup(java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[729,50] method listByResourceGroup(java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[950,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[967,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[983,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[996,10] method delete(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1012,10] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1026,36] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1041,46] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1052,51] method list() is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1065,51] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1077,51] method listByResourceGroup(java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1091,51] method listByResourceGroup(java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[91,17] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[95,17] method delete(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[99,17] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[103,37] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[112,47] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[127,52] method list() is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[132,52] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[137,52] method listByResourceGroup(java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[143,52] method listByResourceGroup(java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[150,17] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[154,17] method delete(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[158,17] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[162,38] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[171,48] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[186,53] method list() is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[191,53] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[INFO] 100 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.767 s
[INFO] Finished at: 2021-05-20T19:05:17Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-privatedns: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[284,20] method createOrUpdate(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[312,30] method createOrUpdateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[337,20] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[362,30] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[384,10] method delete(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[403,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[424,20] method get(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[441,30] method getWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[460,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[479,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[498,35] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[516,35] method list(java.lang.String,java.lang.String,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[535,20] method createOrUpdate(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[563,30] method createOrUpdateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[588,20] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[613,30] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.RecordSetInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[635,10] method delete(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[654,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[675,20] method get(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[692,30] method getWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[711,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[730,35] method listByType(java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.models.RecordType,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[749,35] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/RecordSetsClient.java:[767,35] method list(java.lang.String,java.lang.String,java.lang.Integer,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.RecordSetsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[436,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[463,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[490,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[511,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[536,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[561,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[585,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[609,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[629,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[652,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[676,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[696,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[718,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[733,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[751,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[770,29] method get(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[785,39] method getWithResponse(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[799,44] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[815,44] method list(java.lang.String,java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[836,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[863,78] method beginCreateOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[890,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[911,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[936,29] method createOrUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[961,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[985,78] method beginUpdate(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1009,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1029,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1052,29] method update(java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.privatedns.fluent.models.VirtualNetworkLinkInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1076,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1096,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1118,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1133,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1151,10] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1170,29] method get(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1185,39] method getWithResponse(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1199,44] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/VirtualNetworkLinksClient.java:[1215,44] method list(java.lang.String,java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.VirtualNetworkLinksClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[588,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[605,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[621,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[634,10] method delete(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[650,10] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[664,35] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[679,45] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[690,50] method list() is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[703,50] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[715,50] method listByResourceGroup(java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[729,50] method listByResourceGroup(java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[950,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[967,40] method beginDelete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[983,10] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[996,10] method delete(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1012,10] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1026,36] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1041,46] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1052,51] method list() is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1065,51] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1077,51] method listByResourceGroup(java.lang.String) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/fluent/PrivateZonesClient.java:[1091,51] method listByResourceGroup(java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.privatedns.fluent.PrivateZonesClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[91,17] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[95,17] method delete(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[99,17] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[103,37] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[112,47] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[127,52] method list() is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[132,52] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[137,52] method listByResourceGroup(java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[143,52] method listByResourceGroup(java.lang.String,java.lang.Integer,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[150,17] method delete(java.lang.String,java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[154,17] method delete(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[158,17] method delete(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[162,38] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[171,48] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[186,53] method list() is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-privatedns/src/main/java/com/azure/resourcemanager/privatedns/implementation/PrivateZonesImpl.java:[191,53] method list(java.lang.Integer,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.privatedns.implementation.PrivateZonesImpl
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
<summary>purview</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ------< com.azure.resourcemanager:azure-resourcemanager-purview >-------
[INFO] Building Microsoft Azure SDK for Purview Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-purview ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-purview ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 83 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/java/com/azure/resourcemanager/purview/models/AccountPropertiesSystemData.java:[14,56] cannot inherit from final com.azure.core.management.SystemData
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/java/com/azure/resourcemanager/purview/models/AccountPropertiesSystemData.java:[22,5] method does not override or implement a method from a supertype
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/java/com/azure/resourcemanager/purview/models/AccountPropertiesSystemData.java:[24,14] cannot find symbol
  symbol: method validate()
[INFO] 3 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.960 s
[INFO] Finished at: 2021-05-20T19:05:56Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-purview: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/java/com/azure/resourcemanager/purview/models/AccountPropertiesSystemData.java:[14,56] cannot inherit from final com.azure.core.management.SystemData
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/java/com/azure/resourcemanager/purview/models/AccountPropertiesSystemData.java:[22,5] method does not override or implement a method from a supertype
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-purview/src/main/java/com/azure/resourcemanager/purview/models/AccountPropertiesSystemData.java:[24,14] cannot find symbol
[ERROR]   symbol: method validate()
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
<summary>reservations</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)

WARNING (PreCheck/SchemaMissingType): The schema 'ResourceName' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'QuotaLimits' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'QuotaLimitsResponse' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/CheckDuplicateSchemas): Checking for duplicate schemas, this could take a (long) while.  Run with --verbose for more detail.

WARNING (Modeler/MissingType): The enum schema 'ResourceType' with an undefined type and enum values is ambigious. This has been auto-corrected to 'type:string'

WARNING (Modeler/MissingType): The enum schema 'QuotaRequestState' with an undefined type and enum values is ambigious. This has been auto-corrected to 'type:string'
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
INFORMATION (SchemaNameNormalization): Override response header, from 'ETag' to 'Etag'
INFORMATION (SchemaNameNormalization): Override response header, from 'ETag' to 'Etag'
INFORMATION (ResourceTypeNormalization): Add parent ProxyResource, for 'ReservationOrderResponse'
INFORMATION (ResourceTypeNormalization): Add parent ProxyResource, for 'ReservationResponse'
INFORMATION (ResourceTypeNormalization): Add parent ProxyResource, for 'QuotaRequestOneResourceSubmitResponse'
INFORMATION (ResourceTypeNormalization): Add parent ProxyResource, for 'QuotaRequestDetails'
INFORMATION (ResourceTypeNormalization): Add parent ProxyResource, for 'QuotaRequestSubmitResponse'
INFORMATION (ErrorTypeNormalization): Rename error from 'Error' to 'ManagementError'
INFORMATION (ErrorTypeNormalization): Rename error from 'ExceptionResponse' to 'ManagementError'
INFORMATION (SchemaCleanup): Remove unused object schema 'ManagementError'
INFORMATION (SchemaCleanup): Remove unused object schema 'ManagementError'
INFORMATION (SchemaCleanup): Remove unused object schema 'ServiceErrorDetail'
INFORMATION (SchemaCleanup): Remove unused object schema 'QuotaLimitsResponse'
INFORMATION (SchemaCleanup): Remove unused object schema 'CreateGenericQuotaRequestParameters'
INFORMATION (SchemaCleanup): Remove unused object schema 'QuotaRequestSubmitResponse'
INFORMATION (SchemaCleanup): Remove unused choice schema 'ErrorResponseCode'
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
INFORMATION (FluentMapper): Add Inner to response types: [QuotaRequestOneResourceSubmitResponse, QuotaRequestSubmitResponse201, CurrentQuotaLimitBase, QuotaRequestDetails, Catalog, ReservationOrderResponse, CalculateExchangeOperationResultResponse, ReservationResponse, AvailableScopeProperties, OperationResponse, CalculatePriceResponse, ExchangeOperationResultResponse, AppliedReservations]

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.NullPointerException
java.lang.NullPointerException
	at com.azure.autorest.mapper.ChoiceMapper.lambda$map$0(ChoiceMapper.java:64)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:176)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.ReduceOps$5.evaluateSequential(ReduceOps.java:257)
	at java.base/java.util.stream.ReduceOps$5.evaluateSequential(ReduceOps.java:248)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.count(ReferencePipeline.java:605)
	at com.azure.autorest.mapper.ChoiceMapper.map(ChoiceMapper.java:64)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:60)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

FATAL (FluentGen): Unhandled error: null
java.lang.NullPointerException
	at com.azure.autorest.mapper.ChoiceMapper.lambda$map$0(ChoiceMapper.java:64)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:176)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.ReduceOps$5.evaluateSequential(ReduceOps.java:257)
	at java.base/java.util.stream.ReduceOps$5.evaluateSequential(ReduceOps.java:248)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.count(ReferencePipeline.java:605)
	at com.azure.autorest.mapper.ChoiceMapper.map(ChoiceMapper.java:64)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:60)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>resources</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)
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
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)

WARNING (PreCheck/SchemaMissingType): The schema 'ComplianceResultList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PricingList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdvancedThreatProtectionProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TagsResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionsList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionAnalyticsModelProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionAnalyticsModelProperties-devicesMetricsItem' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecuritySolutionAnalyticsModelList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecurityAggregatedAlertList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecurityAggregatedRecommendationList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IoTSecurityAggregatedAlertProperties-topDevicesListItem' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'TagsResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'WorkspaceSettingList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RegulatoryComplianceStandardList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RegulatoryComplianceControlList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RegulatoryComplianceAssessmentList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AutomationList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Rule' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'EffectiveNetworkSecurityGroups' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdaptiveNetworkHardeningProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdaptiveNetworkHardening' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AdaptiveNetworkHardeningsList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ExternalSecuritySolutionList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'DeviceList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'IngestionSettingList' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ConnectionStrings' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SoftwareProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ErrorAdditionalInfo' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

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
INFORMATION (OperationNameNormalization): Rename operation from 'listBySubscription' to 'list', in operation group 'SoftwareInventories'
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
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'IngestionSetting'
INFORMATION (ResourceTypeNormalization): Change parent from 'Resource' to 'ProxyResource', for 'Software'
INFORMATION (ErrorTypeNormalization): Rename error from 'CloudError' to 'ManagementError'
INFORMATION (SchemaCleanup): Remove unused object schema 'Resource'
INFORMATION (SchemaCleanup): Remove unused object schema 'SecureScoreControlScore'
INFORMATION (SchemaCleanup): Remove unused choice schema 'CreatedByType'
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
INFORMATION (FluentMapper): Add Inner to response types: [IoTSecuritySolutionAnalyticsModelList, RegulatoryComplianceAssessment, IotSensorsModel, Device, IngestionSetting, ServerVulnerabilityAssessment, OnPremiseIotSensor, DiscoveredSecuritySolution, IotRecommendation, Software, Operation, AdvancedThreatProtectionSetting, Pricing, IotAlert, Scans, IoTSecuritySolutionModel, SecureScoreControlDefinitionItem, IotDefenderSettingsList, AlertsSuppressionRule, ExternalSecuritySolution, PackageDownloads, SecureScoreControlDetails, WorkspaceSetting, AutomationValidationStatus, IotSensorsList, IoTSecurityAggregatedAlert, SecurityContact, AdaptiveApplicationControlGroup, RulesResults, PricingList, IotSitesModel, AdaptiveApplicationControlGroups, SecurityTask, Scan, AllowedConnectionsResource, AscLocation, DeviceSecurityGroup, AutoProvisioningSetting, IotAlertType, IotRecommendationType, SecuritySubAssessment, IotAlertModel, RegulatoryComplianceControl, AdaptiveNetworkHardening, IoTSecurityAggregatedRecommendation, SecuritySolutionsReferenceDataList, SecuritySolution, ConnectorSetting, ServerVulnerabilityAssessmentsList, IoTSecuritySolutionAnalyticsModel, IngestionSettingToken, TopologyResource, ScanResult, IotRecommendationTypeList, RuleResults, ComplianceResult, RegulatoryComplianceStandard, OnPremiseIotSensorsList, JitNetworkAccessPolicy, IotAlertTypeList, SecurityAssessmentMetadata, Alert, Setting, InformationProtectionPolicy, Compliance, JitNetworkAccessRequest, Automation, IotDefenderSettingsModel, ScanResults, IotSitesList, ConnectionStrings, SecurityAssessment, IotRecommendationModel, SecureScoreItem]
INFORMATION (FluentMapper): Add Inner for type 'SecureScoreControlDefinitionItem': []

```
**stderr**
```

ERROR (FluentGen): Failed to successfully run fluentgen plugin java.lang.IllegalArgumentException: discriminator not found in type ExternalSecuritySolution and its parents
java.lang.IllegalArgumentException: discriminator not found in type ExternalSecuritySolution and its parents
	at com.azure.autorest.util.SchemaUtil.getDiscriminatorSerializedName(SchemaUtil.java:101)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:165)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:181)
	at com.azure.autorest.mapper.ClientMapper.lambda$map$5(ClientMapper.java:100)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.DistinctOps$1$2.accept(DistinctOps.java:175)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:103)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

FATAL (FluentGen): Unhandled error: discriminator not found in type ExternalSecuritySolution and its parents
java.lang.IllegalArgumentException: discriminator not found in type ExternalSecuritySolution and its parents
	at com.azure.autorest.util.SchemaUtil.getDiscriminatorSerializedName(SchemaUtil.java:101)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:165)
	at com.azure.autorest.mapper.ModelMapper.map(ModelMapper.java:181)
	at com.azure.autorest.mapper.ClientMapper.lambda$map$5(ClientMapper.java:100)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.DistinctOps$1$2.accept(DistinctOps.java:175)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at com.azure.autorest.mapper.ClientMapper.map(ClientMapper.java:103)
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
	at java.base/java.lang.Thread.run(Thread.java:829)

  Error: Plugin fluentgen reported failure.

```
</details>

<details>
<summary>service-map</summary>

**stdout**
```
AutoRest code generation utility [cli version: 3.2.0; node: v14.17.0, max-memory: 2048 MB]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest
   Loading AutoRest core      '/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist' (3.1.3)
INFORMATION: > Loading local AutoRest extension '@autorest/java' (/home/runner/work/autorest.java/autorest.java)
INFORMATION: > Installing AutoRest extension 'oav' (~0.4.20)
INFORMATION: > Installed AutoRest extension 'oav' (~0.4.20->0.4.70)
INFORMATION: > Installing AutoRest extension '@microsoft.azure/classic-openapi-validator' (~1.1.5)
INFORMATION: > Installed AutoRest extension '@microsoft.azure/classic-openapi-validator' (~1.1.5->1.1.6)
INFORMATION: > Installing AutoRest extension '@microsoft.azure/openapi-validator' (~1.7.0)
INFORMATION: > Installed AutoRest extension '@microsoft.azure/openapi-validator' (~1.7.0->1.7.0)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluent' (/home/runner/work/autorest.java/autorest.java/fluentgen)
INFORMATION: > Loading local AutoRest extension '@autorest/java.fluentnamer' (/home/runner/work/autorest.java/autorest.java/fluentgen/../fluentnamer)
INFORMATION: > Loading AutoRest extension '@autorest/modelerfour' (4.18.1->4.18.1)

WARNING (PreCheck/SchemaMissingType): The schema 'Resource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ResourceReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineReference' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'PortReference' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineReferenceWithHints' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ClientGroupReference' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'CoreResource' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Machine' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Process' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Port' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ClientGroup' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ClientGroupMember' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineGroup' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Summary' with an undefined type and 'allOf'/'anyOf'/'oneOf' is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachinesSummary' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Relationship' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Connection' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Acceptor' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'RelationshipProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ConnectionProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AcceptorProperties' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Ipv4NetworkInterface' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Ipv6NetworkInterface' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'NetworkConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AgentConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'Timezone' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'OperatingSystemConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineResourcesConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'VirtualMachineConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HypervisorConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'HostingConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureHostingConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureCloudServiceConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureVmScaleSetConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureServiceFabricClusterConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ImageConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessUser' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessDetails' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessHostedService' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'ProcessHostingConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'AzureProcessHostingConfiguration' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'SingleMachineDependencyMapRequest' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MultipleMachinesMapRequest' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineListMapRequest' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

WARNING (PreCheck/SchemaMissingType): The schema 'MachineGroupMapRequest' with an undefined type and declared properties is a bit ambigious. This has been auto-corrected to 'type:object'

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

WARNING (PreviewVersionOverOneYear/R4024/SDKViolation): The API version:2015-11-01-preview having been in a preview state over one year , please move it to GA or retire.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:6:2

WARNING (DescriptionAndTitleMissing/R4021/SDKViolation): 'properties' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7419:4

WARNING (DescriptionAndTitleMissing/R4021/SDKViolation): 'properties' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7456:4

WARNING (DescriptionAndTitleMissing/R4021/SDKViolation): 'properties' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:7471:4

WARNING (DescriptionAndTitleMissing/R4021/SDKViolation): 'nodes' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:8398:4

WARNING (DescriptionAndTitleMissing/R4021/SDKViolation): 'edges' model/property lacks 'description' and 'title' property. Consider adding a 'description'/'title' element. Accurate description/title is essential for maintaining reference documentation.
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
INFORMATION (ErrorTypeNormalization): Rename error from 'ErrorResponse' to 'ManagementError'
INFORMATION (SchemaCleanup): Remove unused object schema 'Resource'
INFORMATION (SchemaCleanup): Remove unused object schema 'ManagementError'
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
INFORMATION (FluentMapper): Add Inner to response types: [Liveness, ClientGroupMember, ClientGroup, Machine, Port, MapResponse, MachineGroup, MachinesSummary, Process, ClientGroupMembersCount, Connection]
INFORMATION (FluentGen): Java template for client model
INFORMATION (FluentGen): Process for Fluent Lite, SDK integration disabled
INFORMATION (FluentJavaSettings): Option, string, tag : package-2015-11-preview
INFORMATION (FluentJavaSettings): Option, string, base-folder : .
INFORMATION (FluentJavaSettings): Option, string, output-folder : /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-service-map
INFORMATION (FluentJavaSettings): Option, string, azure-libraries-for-java-folder : null
INFORMATION (FluentJavaSettings): List of input files : [Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json]
INFORMATION (FluentGen): Write Java
INFORMATION (JavaFormatter): Java version: 11.0.11
INFORMATION (JavaFormatter): Java formatter enabled
INFORMATION (FluentGen): Write Xml
INFORMATION (FluentGen): Write Text

```
**stderr**
```
FATAL: Failed validating: 'file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json', error encountered: TypeError: Cannot read property 'properties' of undefined
FATAL: [object Object]
(node:24380) UnhandledPromiseRejectionWarning: Error: Plugin model-validator reported failure.
    at /home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist/src_lib_autorest-core_ts.js:3015:19
    at ScheduleNode (/home/runner/.autorest/@autorest_core@3.1.3/node_modules/@autorest/core/dist/src_lib_autorest-core_ts.js:1809:33)
(Use `node --trace-warnings ...` to show where the warning was created)
(node:24380) UnhandledPromiseRejectionWarning: Unhandled promise rejection. This error originated either by throwing inside of an async function without a catch block, or by rejecting a promise which was not handled with .catch(). To terminate the node process on unhandled promise rejection, use the CLI flag `--unhandled-rejections=strict` (see https://nodejs.org/api/cli.html#cli_unhandled_rejections_mode). (rejection id: 60)
(node:24380) [DEP0018] DeprecationWarning: Unhandled promise rejections are deprecated. In the future, promise rejections that are not handled will terminate the Node.js process with a non-zero exit code.

ERROR (DeleteOperationResponses/R4011/ARMViolation): The delete operation is defined without a 200 or 204 error response implementation,please add it.'
    - file:///home/runner/work/autorest.java/autorest.java/azure-rest-api-specs/specification/service-map/resource-manager/Microsoft.OperationalInsights/preview/2015-11-01-preview/arm-service-map.json:6869:4
  Error: Plugin model-validator reported failure.

```
</details>

<details>
<summary>servicefabric</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ---< com.azure.resourcemanager:azure-resourcemanager-servicefabric >----
[INFO] Building Microsoft Azure SDK for ServiceFabric Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-servicefabric ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-servicefabric ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 184 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[45,41] method list() is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[57,41] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[67,41] method list() is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[79,41] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[89,41] method list() is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[101,41] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[39,36] method list() is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[50,36] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[59,36] method list() is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[70,36] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[79,36] method list() is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[90,36] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[238,55] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[271,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[302,45] method listAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[316,45] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[329,48] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[343,48] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[355,55] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[388,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[419,45] method listAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[433,45] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[446,48] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[460,48] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[472,55] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[505,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[536,45] method listAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[550,45] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[563,48] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[577,48] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[76,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[86,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[96,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[39,43] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[44,43] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[49,43] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[54,43] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[59,43] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[64,43] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[INFO] 39 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.025 s
[INFO] Finished at: 2021-05-20T19:15:02Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-servicefabric: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[45,41] method list() is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[57,41] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[67,41] method list() is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[79,41] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[89,41] method list() is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/fluent/OperationsClient.java:[101,41] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[39,36] method list() is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[50,36] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[59,36] method list() is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[70,36] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[79,36] method list() is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/models/Operations.java:[90,36] method list(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[238,55] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[271,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[302,45] method listAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[316,45] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[329,48] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[343,48] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[355,55] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[388,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[419,45] method listAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[433,45] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[446,48] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[460,48] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[472,55] method listSinglePageAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[505,55] method listSinglePageAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[536,45] method listAsync() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[550,45] method listAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[563,48] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[577,48] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[76,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[86,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsClientImpl.java:[96,45] method list(java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.servicefabric.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[39,43] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[44,43] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[49,43] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[54,43] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[59,43] method list() is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-servicefabric/src/main/java/com/azure/resourcemanager/servicefabric/implementation/OperationsImpl.java:[64,43] method list(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.servicefabric.implementation.OperationsImpl
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
<summary>softwareplan</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ----< com.azure.resourcemanager:azure-resourcemanager-softwareplan >----
[INFO] Building Microsoft Azure SDK for SoftwarePlan Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-softwareplan ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-softwareplan ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 38 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[72,49] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[77,49] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[82,34] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[91,44] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[108,27] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/OperationsClient.java:[53,43] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/OperationsClient.java:[67,43] method list(java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitRevisionsClient.java:[56,47] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitRevisionsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitRevisionsClient.java:[71,47] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitRevisionsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/SoftwarePlansClient.java:[42,10] method register() is already defined in interface com.azure.resourcemanager.softwareplan.fluent.SoftwarePlansClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/SoftwarePlansClient.java:[54,20] method registerWithResponse(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.SoftwarePlansClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/Operations.java:[47,38] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/Operations.java:[60,38] method list(java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefitRevisions.java:[50,42] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefitRevisions
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefitRevisions.java:[64,42] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefitRevisions
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/SoftwarePlans.java:[37,10] method register() is already defined in interface com.azure.resourcemanager.softwareplan.models.SoftwarePlans
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/SoftwarePlans.java:[48,20] method registerWithResponse(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.SoftwarePlans
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[176,47] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[191,47] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[206,32] method create(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[222,42] method createWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[238,32] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[254,42] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[269,32] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[284,42] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[297,10] method delete(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[312,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[102,42] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[116,42] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[129,27] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[143,37] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[169,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansImpl.java:[35,17] method register() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansImpl.java:[39,27] method registerWithResponse(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsImpl.java:[40,49] method list(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsImpl.java:[45,49] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsImpl.java:[39,45] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsImpl.java:[44,45] method list(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[262,61] method listSinglePageAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[304,61] method listSinglePageAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[346,51] method listAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[363,51] method listAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[380,54] method list(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[397,54] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[80,52] method list(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl.HybridUseBenefitRevisionsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[186,34] method registerWithResponseAsync() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[220,34] method registerWithResponseAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[248,24] method registerAsync() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[259,17] method register() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[273,27] method registerWithResponse(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[71,30] method register(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl.SoftwarePlansService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[244,57] method listSinglePageAsync(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[282,57] method listSinglePageAsync(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[319,47] method listAsync(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[335,47] method listAsync(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[351,50] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[367,50] method list(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[77,39] method list(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[889,61] method listSinglePageAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[928,61] method listSinglePageAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[967,51] method listAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[982,51] method listAsync(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1000,51] method listAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1016,54] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1034,54] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1051,56] method createWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1092,56] method createWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1130,46] method createAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1155,39] method create(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1173,49] method createWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1191,56] method updateWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1232,56] method updateWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1270,46] method updateAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1295,39] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1313,49] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1330,56] method getWithResponseAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1363,56] method getWithResponseAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1395,46] method getAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1419,39] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1436,49] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1452,34] method deleteWithResponseAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1486,34] method deleteWithResponseAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1517,24] method deleteAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1532,17] method delete(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1549,27] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[132,52] method list(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[144,52] method create(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[157,52] method update(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[170,52] method get(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[182,30] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[INFO] 90 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.157 s
[INFO] Finished at: 2021-05-20T19:16:24Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-softwareplan: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[72,49] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[77,49] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[82,34] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[91,44] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsImpl.java:[108,27] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/OperationsClient.java:[53,43] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/OperationsClient.java:[67,43] method list(java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.OperationsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitRevisionsClient.java:[56,47] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitRevisionsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitRevisionsClient.java:[71,47] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitRevisionsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/SoftwarePlansClient.java:[42,10] method register() is already defined in interface com.azure.resourcemanager.softwareplan.fluent.SoftwarePlansClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/SoftwarePlansClient.java:[54,20] method registerWithResponse(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.SoftwarePlansClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/Operations.java:[47,38] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/Operations.java:[60,38] method list(java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.Operations
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefitRevisions.java:[50,42] method list(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefitRevisions
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefitRevisions.java:[64,42] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefitRevisions
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/SoftwarePlans.java:[37,10] method register() is already defined in interface com.azure.resourcemanager.softwareplan.models.SoftwarePlans
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/SoftwarePlans.java:[48,20] method registerWithResponse(com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.SoftwarePlans
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[176,47] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[191,47] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[206,32] method create(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[222,42] method createWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[238,32] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[254,42] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[269,32] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[284,42] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[297,10] method delete(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/fluent/HybridUseBenefitsClient.java:[312,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.fluent.HybridUseBenefitsClient
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[102,42] method list(java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[116,42] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[129,27] method get(java.lang.String,java.lang.String) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[143,37] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/models/HybridUseBenefits.java:[169,20] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.models.HybridUseBenefits
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansImpl.java:[35,17] method register() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansImpl.java:[39,27] method registerWithResponse(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsImpl.java:[40,49] method list(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsImpl.java:[45,49] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsImpl.java:[39,45] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsImpl.java:[44,45] method list(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[262,61] method listSinglePageAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[304,61] method listSinglePageAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[346,51] method listAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[363,51] method listAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[380,54] method list(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[397,54] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitRevisionsClientImpl.java:[80,52] method list(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitRevisionsClientImpl.HybridUseBenefitRevisionsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[186,34] method registerWithResponseAsync() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[220,34] method registerWithResponseAsync(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[248,24] method registerAsync() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[259,17] method register() is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[273,27] method registerWithResponse(com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/SoftwarePlansClientImpl.java:[71,30] method register(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.SoftwarePlansClientImpl.SoftwarePlansService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[244,57] method listSinglePageAsync(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[282,57] method listSinglePageAsync(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[319,47] method listAsync(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[335,47] method listAsync(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[351,50] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[367,50] method list(java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/OperationsClientImpl.java:[77,39] method list(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.OperationsClientImpl.OperationsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[889,61] method listSinglePageAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[928,61] method listSinglePageAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[967,51] method listAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[982,51] method listAsync(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1000,51] method listAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1016,54] method list(java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1034,54] method list(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1051,56] method createWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1092,56] method createWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1130,46] method createAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1155,39] method create(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1173,49] method createWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1191,56] method updateWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1232,56] method updateWithResponseAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1270,46] method updateAsync(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1295,39] method update(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1313,49] method updateWithResponse(java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1330,56] method getWithResponseAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1363,56] method getWithResponseAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1395,46] method getAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1419,39] method get(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1436,49] method getWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1452,34] method deleteWithResponseAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1486,34] method deleteWithResponseAsync(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1517,24] method deleteAsync(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1532,17] method delete(java.lang.String,java.lang.String) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[1549,27] method deleteWithResponse(java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in class com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[132,52] method list(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[144,52] method create(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[157,52] method update(java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.resourcemanager.softwareplan.fluent.models.HybridUseBenefitModelInner,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[170,52] method get(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-softwareplan/src/main/java/com/azure/resourcemanager/softwareplan/implementation/HybridUseBenefitsClientImpl.java:[182,30] method delete(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,com.azure.core.util.Context) is already defined in interface com.azure.resourcemanager.softwareplan.implementation.HybridUseBenefitsClientImpl.HybridUseBenefitsService
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
<summary>visualstudio</summary>

**stdout**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ----< com.azure.resourcemanager:azure-resourcemanager-visualstudio >----
[INFO] Building Microsoft Azure SDK for VisualStudio Management 1.0.0-beta.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ azure-resourcemanager-visualstudio ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ azure-resourcemanager-visualstudio ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 59 source files to /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[13,50] <identifier> expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[17,5] ',', '}', or ';' expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[17,9] '}' expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[37,19] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[39,5] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[41,5] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[50,19] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[52,9] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[55,13] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[58,5] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[62,12] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[64,5] class, interface, or enum expected
[INFO] 12 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.932 s
[INFO] Finished at: 2021-05-20T19:27:26Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project azure-resourcemanager-visualstudio: Compilation failure: Compilation failure: 
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[13,50] <identifier> expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[17,5] ',', '}', or ';' expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[17,9] '}' expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[37,19] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[39,5] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[41,5] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[50,19] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[52,9] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[55,13] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[58,5] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[62,12] class, interface, or enum expected
[ERROR] /home/runner/work/autorest.java/autorest.java/fluent_generated/azure-resourcemanager-visualstudio/src/main/java/com/azure/resourcemanager/visualstudio/models/AccountResourceRequestOperationType.java:[64,5] class, interface, or enum expected
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
