rmdir /S /Q "src/main/java/com/azure/mgmttest"
autorest-beta --version=3.0.6198 --java --use:../fluentgen --input-file=https://raw.githubusercontent.com/Azure/azure-rest-api-specs/master/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/resources.json --namespace=com.azure.mgmttest.resources --output-folder=./ --sync-methods=all --azure-arm=true --fluent=true --generate-client-as-impl=true --implementation-subpackage=models
