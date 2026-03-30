# Project-specific notes: azure-resourcemanager-keyvault

## ManagedHsm (Mhsm) resources — DO NOT supplement

Do **not** add or supplement convenience layer properties for any ManagedHsm / Mhsm related resources. This includes:

- `ManagedHsm` — the top-level Managed HSM resource
- Any `Mhsm*` prefixed models (e.g., `MhsmPrivateEndpointConnection`, `MhsmPrivateLinkResource`, `MhsmGeoReplicatedRegion`)
- `ManagedHsmKeys` — keys under a Managed HSM

**Reason:** Managed HSM resources are no longer testable. Since tests are mandatory for all supplemented properties, and we cannot verify changes against a live or recorded Managed HSM environment, these resources must be skipped entirely.

## Resources that CAN be supplemented

- `Vault` — the standard Key Vault resource (testable)
- `Key` / `Secret` — data-plane resources accessed via Key Vault clients (note: these use data-plane inner models from `azure-security-keyvault-keys` / `azure-security-keyvault-secrets`, not ARM inner models)
