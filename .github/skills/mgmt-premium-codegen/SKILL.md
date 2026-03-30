---
name: mgmt-premium-codegen
description: '**WORKFLOW SKILL** - Generate convenience (premium/handwritten) layer code for Azure Resource Manager SDK. USE FOR: "mgmt premium codegen <project-name>".'
---

# Skill Instructions

## Request

The request would come in the form of:
- "mgmt premium codegen `<project-name>`, supplement properties for `<resource-name>`"
- "mgmt premium codegen `<project-name>`, add convenience layer for `<resource-name>`"

`<project-name>` is the name of the SDK project, e.g. "azure-resourcemanager-compute".
`<resource-name>` is one or more resource types, e.g. "VirtualMachine", "AvailabilitySet".

## Required repositories

- [Azure/azure-sdk-for-java](https://github.com/Azure/azure-sdk-for-java), cloned at "../azure-sdk-for-java"
  Call this folder "sdk repo" for short.

You have full access to this locally cloned repository/folder.

## Concepts

The management SDK has a layered architecture:

- **Generated layer (inner)**: Auto-generated code.
  - `com.azure.resourcemanager.<service>.fluent` — inner client interfaces (e.g. `VirtualMachinesClient`)
  - `com.azure.resourcemanager.<service>.fluent.models` — inner model classes (e.g. `VirtualMachineInner`)
- **Convenience layer (premium/handwritten)**: Higher-level, user-facing abstractions wrapping the generated layer.
  - `com.azure.resourcemanager.<service>.models` — resource interfaces (e.g. `VirtualMachine`) and collection interfaces (e.g. `VirtualMachines`)
  - `com.azure.resourcemanager.<service>.implementation` — resource implementations (e.g. `VirtualMachineImpl`) and collection implementations (e.g. `VirtualMachinesImpl`)
  - `com.azure.resourcemanager.<service>.<Service>Manager` — entry point class that wires up all collections

## Steps

### Find the project in sdk repo

Search for folder of `<project-name>` under "/sdk" in sdk repo. It should be in the form of `/sdk/<service-name>/<project-name>`.

`<project-path>` refers to the full path of the project folder.
`<service>` is derived from the project name (the part after "azure-resourcemanager-", e.g. "compute").
`<Service>` is `<service>` with the first letter capitalized (e.g. "Compute").

`<src-base>` refers to `<project-path>/src/main/java/com/azure/resourcemanager/<service>`.

### Understand the existing code

Examine the project structure under `<src-base>`:
- `fluent/` and `fluent/models/` — the generated inner layer
- `models/` — the convenience layer interfaces (may not exist yet for new resources)
- `implementation/` — the convenience layer implementations (may not exist yet for new resources)
- `<Service>Manager.java` — the manager entry point

Read the `<Service>Manager.java` to understand which resources already have convenience layers.

---

## Scenario 1: Supplement existing resource's missing properties

See [supplement-properties.md](./reference/supplement-properties.md) for detailed steps (includes adding/updating tests).

## Scenario 2: Add new convenience layer for new resource(s)

See [add-convenience-layer.md](./reference/add-convenience-layer.md) for detailed steps.

## Scenario 3: Generate tests for convenience layer

See [generate-tests.md](./reference/generate-tests.md) for detailed steps.

---

## Checklist

- [ ] Follow the code style and conventions of the existing convenience layer code in the same project.
- [ ] Use `@Fluent` annotation on all model and collection interfaces.
- [ ] Use `@Override` annotation on all implemented methods.
- [ ] Make implementation classes package-private (no `public` modifier) unless existing code uses `public`.
- [ ] Handle null values safely in property getters (check `innerModel().xxx()` for null before accessing nested properties).
- [ ] Wrap mutable collections with `Collections.unmodifiable*()` in getters.
- [ ] Add proper Javadoc for all public methods.
- [ ] Add necessary imports.
- [ ] Verify the code compiles by running `mvn compile -f <project-path>/pom.xml -pl .` (or a broader build if dependencies are involved).
- [ ] Generate tests for the convenience layer (see Scenario 3).
- [ ] Run tests in record mode and verify they pass.

## Notes

- The code templates above are for groupable (top-level ARM) resources. For child resources or proxy resources, adapt the base classes accordingly (e.g., use `IndependentChildImpl`, `ExternalChildResourceImpl`, or simpler wrappers).
- If the resource is read-only (no create/update), omit the `Creatable`, `Updatable`, `Appliable`, Definition, and Update interfaces.
- When in doubt about a pattern, always refer to an existing similar resource in the same project or in the `azure-resourcemanager-compute` project as the reference implementation.
