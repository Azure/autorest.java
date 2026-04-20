## Development Workflows

#### Authenticating to the Azure DevOps Maven feed

<!-- TODO -->

#### Authenticating to the Azure DevOps npm feed

Before installing new dependencies, authenticate to the Azure Artifacts feed used by this repo by running the command below at the root
of the repo.

```
  npx artifacts-npm-credprovider -c typespec-extension/.npmrc
  npx artifacts-npm-credprovider -c typespec-tests/.npmrc
```
