# Solve Compile Error

## "cannot find symbol" or "cannot be converted to"

There are a few reasons, and the mitigation:

- The case difference between .tsp model/property/method and Java code. Use `@@clientName` to mitigate it.
- The model is read-only in .tsp, but the Java code calls the setter method. Please double check whether the Java class indeed contains the corresponding getter method (e.g. `ipRules` for `withIpRules`). This can be mitigated by `@@usage(<model>, Azure.ClientGenerator.Core.Usage.input, "java");`.

## "type argument is not within bounds of type-variable InnerT"

You would need to customize the class.

1. Add option "customization-class: customization/src/main/java/<Service>Customization.java" to "tspconfig.yaml".
2. Create the "customization" module, write the "<Service>Customization.java" class in "sdk-path". Use [this](https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/keyvault/azure-resourcemanager-keyvault/customization) as reference.
3. Commit the changes to "customization" module. Whenever you make changes to the "customization" module, you need to commit it before you run another `python eng/automation/generate.py` command.
