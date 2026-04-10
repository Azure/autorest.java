# Project-specific notes: azure-resourcemanager-keyvault

## ManagedHsm (Mhsm) resources — DO NOT supplement

Do **not** add or supplement convenience layer properties for any ManagedHsm / Mhsm related resources. This includes:

- `ManagedHsm` — the top-level Managed HSM resource
- Any `Mhsm*` prefixed models (e.g., `MhsmPrivateEndpointConnection`, `MhsmPrivateLinkResource`, `MhsmGeoReplicatedRegion`)
- `ManagedHsmKeys` — keys under a Managed HSM

**Reason:** Managed HSM resources are no longer testable. Since tests are mandatory for all supplemented properties, and we cannot verify changes against a live or recorded Managed HSM environment, these resources must be skipped entirely.

## Resources that CAN be supplemented

- `Vault` — the standard Key Vault resource (testable, wraps ARM inner model `VaultInner`)
- `Key` / `Secret` — **IMPORTANT:** these use **data-plane** inner models, NOT ARM inner models (see architecture section below)

## Architecture: Key and Secret use data-plane SDK, not ARM

This is a critical architectural detail that affects what can be supplemented:

- **`Key`** convenience interface extends `HasInnerModel<KeyProperties>` where `KeyProperties` is from `com.azure.security.keyvault.keys.models.KeyProperties` (data-plane SDK). `KeyImpl` extends `CreatableUpdatableImpl<Key, KeyProperties, KeyImpl>` and delegates to `KeyAsyncClient` (data-plane). The ARM inner model `KeyInner` is **not** used by the convenience layer.
- **`Secret`** convenience interface extends `HasInnerModel<SecretProperties>` where `SecretProperties` is from `com.azure.security.keyvault.secrets.models.SecretProperties` (data-plane SDK). `SecretImpl` extends `CreatableUpdatableImpl<Secret, SecretProperties, SecretImpl>` and delegates to `SecretAsyncClient` (data-plane). The ARM inner model `SecretInner` is **not** used by the convenience layer.
- **`Vault`** is the only resource that wraps the ARM inner model (`VaultInner` / `VaultProperties`). Only Vault can be supplemented with ARM model properties.

**Consequence:** Properties on `KeyInner` or `SecretInner` (ARM models) should NOT be added to the Key/Secret convenience layers — they're fundamentally different abstractions.

## Existing convenience layers

| Resource | Interface | Impl | Collection | Collection Impl | Inner model used |
|---|---|---|---|---|---|
| Vault | `models/Vault.java` | `implementation/VaultImpl.java` | `models/Vaults.java` | `implementation/VaultsImpl.java` | ARM: `VaultInner` → `VaultProperties` |
| Key | `models/Key.java` | `implementation/KeyImpl.java` | `models/Keys.java` | `implementation/KeysImpl.java` | Data-plane: `KeyProperties` |
| Secret | `models/Secret.java` | `implementation/SecretImpl.java` | `models/Secrets.java` | `implementation/SecretsImpl.java` | Data-plane: `SecretProperties` |
| ManagedHsm | `models/ManagedHsm.java` | `implementation/ManagedHsmImpl.java` | `models/ManagedHsms.java` | `implementation/ManagedHsmsImpl.java` | ARM: `ManagedHsmInner` (**DO NOT supplement**) |
| AccessPolicy | `models/AccessPolicy.java` | `implementation/AccessPolicyImpl.java` | — | — | `AccessPolicyEntry` |
| DeletedVault | `models/DeletedVault.java` | `implementation/DeletedVaultImpl.java` | — | — | ARM: `DeletedVaultInner` |
| CheckNameAvailabilityResult | `models/CheckNameAvailabilityResult.java` | `implementation/CheckNameAvailabilityResultImpl.java` | — | — | ARM: `CheckNameAvailabilityResultInner` |

## KeyVaultManager wiring

Only two top-level collections are exposed on the manager:
- `vaults()` → `Vaults` (also injects `AuthorizationManager` and `tenantId`)
- `managedHsms()` → `ManagedHsms` (injects `tenantId`)

Keys and Secrets are accessed through `Vault.keys()` and `Vault.secrets()` respectively.

## Inner models WITHOUT convenience wrappers

These exist only as generated inner models (most are Mhsm-related and should be skipped):

| Inner model | Skip reason |
|---|---|
| `CheckMhsmNameAvailabilityResultInner` | Mhsm (skip) |
| `DeletedManagedHsmInner` | Mhsm (skip) |
| `ManagedHsmKeyInner` | Mhsm (skip) |
| `MhsmGeoReplicatedRegionInner` | Mhsm (skip) |
| `MhsmPrivateEndpointConnectionInner` | Mhsm (skip) |
| `MhsmPrivateLinkResourceListResultInner` | Mhsm (skip) |
| `OperationInner` | Metadata (not a resource) |
| `PrivateEndpointConnectionInner` | Infrastructure (skip) |
| `PrivateLinkResourceListResultInner` | Infrastructure (skip) |
| `VaultAccessPolicyParametersInner` | Internal parameter payload |

## Vault property supplementation status

Properties on `VaultProperties` (accessed via `VaultInner.properties()`):

| Property | Type | Exposed in Vault.java? | Notes |
|---|---|---|---|
| `tenantId()` | `UUID` | ✅ as `String` | |
| `sku()` | `Sku` | ✅ | |
| `accessPolicies()` | `List<AccessPolicyEntry>` | ✅ as `List<AccessPolicy>` | |
| `vaultUri()` | `String` | ✅ | |
| `hsmPoolResourceId()` | `String` | ❌ | Removed 2026-03-30 |
| `enabledForDeployment()` | `Boolean` | ✅ as `boolean` | |
| `enabledForDiskEncryption()` | `Boolean` | ✅ as `boolean` | |
| `enabledForTemplateDeployment()` | `Boolean` | ✅ as `boolean` | |
| `enableSoftDelete()` | `Boolean` | ✅ as `softDeleteEnabled()` | |
| `softDeleteRetentionInDays()` | `Integer` | ❌ | Removed 2026-03-30 |
| `enableRbacAuthorization()` | `Boolean` | ✅ as `roleBasedAccessControlEnabled()` | |
| `createMode()` | `CreateMode` | ✅ | |
| `enablePurgeProtection()` | `Boolean` | ✅ as `purgeProtectionEnabled()` | |
| `networkAcls()` | `NetworkRuleSet` | ✅ as `networkRuleSet()` | |
| `publicNetworkAccess()` | `String` | ✅ as `PublicNetworkAccess` | |
| `provisioningState()` | `VaultProvisioningState` | ❌ (skip) | Infrastructure property |
| `privateEndpointConnections()` | `List<PrivateEndpointConnectionItem>` | ❌ (skip) | Infrastructure property |

**Remaining unsupplemented properties:** `hsmPoolResourceId`, `softDeleteRetentionInDays`.

## Test files

| Test class | Covers |
|---|---|
| `VaultTests.java` | Vault CRUD, RBAC, soft delete, purge, public network access, supplemented properties |
| `KeyTests.java` | Key CRUD via data-plane |
| `SecretTests.java` | Secret CRUD via data-plane |
| `ManagedHsmTests.java` | ManagedHsm (do not modify) |
| `KeyVaultManagementTest.java` | Test base class |
