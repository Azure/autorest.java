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

### Check project-specific notes FIRST

Before exploring the project, check for a project-specific notes file in the `reference/` folder (e.g. `keyvault-notes.md`). These files contain:
- Architecture details (e.g. which inner models are used by the convenience layer)
- Rules and constraints (e.g. resources to skip)
- Existing convenience layer inventory
- Property supplementation status (which properties are already exposed, which are skipped)

If a notes file exists with a detailed gap analysis, **use it** instead of re-exploring the project from scratch. Only re-explore if the notes appear outdated (e.g. new inner models exist that aren't listed).

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

**IMPORTANT: Tests are MANDATORY** — never consider property supplementation complete without generating or updating tests for the new properties.

## Scenario 2: Add new convenience layer for new resource(s)

See [add-convenience-layer.md](./reference/add-convenience-layer.md) for detailed steps.

**IMPORTANT: Tests are MANDATORY** — never consider a new convenience layer complete without generating tests.

## Scenario 3: Generate tests for convenience layer

See [generate-tests.md](./reference/generate-tests.md) for detailed steps.

---

## Rules

- **DO NOT** generate convenience layers for deprecated classes (models, clients, or any type annotated with `@Deprecated`). If an inner model or inner client is deprecated, skip it entirely — do not create interfaces, implementations, or collection wrappers for it. When supplementing properties, skip any property whose type is a deprecated class.
- **DO NOT** supplement infrastructure/plumbing properties. Skip these entirely — they are handled by separate mechanisms (base interfaces, dedicated APIs), not as convenience property getters:
  - `provisioningState` — provisioning state of the resource
  - `privateEndpointConnections` — private endpoint connections list
  - `privateLinkResources` / `privateLinks` — private link resources
- **PREFER settable properties** when supplementing. A "settable" property is one that has a corresponding `withXxx()` setter method in the inner model (meaning it can be set during create or update). Settable properties are far more valuable because:
  - They can be tested with meaningful assertions (set a value, then assert it was persisted)
  - Read-only properties with no setter often return null or server-default values in newly created resources, leading to blank/useless `assertNotNull` checks
  - There is little value in exposing a property if we don't know what/how to set it
  - When presenting missing properties to the user, **list settable properties first** and clearly label which are settable vs read-only

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
- [ ] **[MANDATORY]** Generate or update tests for the convenience layer (see Scenario 3). This is NOT optional — the task is incomplete without tests.
- [ ] Run tests in record mode and verify they pass.

## Notes

- The code templates above are for groupable (top-level ARM) resources. For child resources or proxy resources, adapt the base classes accordingly (e.g., use `IndependentChildImpl`, `ExternalChildResourceImpl`, or simpler wrappers).
- If the resource is read-only (no create/update), omit the `Creatable`, `Updatable`, `Appliable`, Definition, and Update interfaces.
- When in doubt about a pattern, always refer to an existing similar resource in the same project or in the `azure-resourcemanager-compute` project as the reference implementation.

## Project-specific notes

Some projects have additional constraints and structural findings. **Always** check for a project-specific notes file in the `reference/` folder before starting work — it may contain architecture details, gap analysis, and supplementation status that saves significant exploration time.

- [keyvault-notes.md](./reference/keyvault-notes.md) — skip ManagedHsm/Mhsm resources (not testable); Key/Secret use data-plane SDK not ARM; full property supplementation status

### Updating project-specific notes

After completing work on a project, **update the project-specific notes file** to reflect:
- Any new convenience layers added
- Any properties supplemented (mark them as ✅ in the status table)
- Any new architectural findings discovered during analysis
- Any new inner models that appeared (e.g. from SDK regeneration)

This ensures future invocations of this skill don't need to re-explore the entire project from scratch.
