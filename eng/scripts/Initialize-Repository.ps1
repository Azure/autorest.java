#Requires -Version 7.0

param(
    [string] $BuildArtifactsPath,
    [switch] $UseTypeSpecNext
)

$ErrorActionPreference = 'Stop'
Set-StrictMode -Version 3.0
. "$PSScriptRoot/Command-InvocationHelpers.ps1"
Set-ConsoleEncoding

Push-Location $RepoRoot
try {
  #  TODO: pull tool versions from package.json
  if ($UseTypeSpecNext ) {
    invoke "npm install -g @typespec/compiler@next"
    invoke "npx @azure-tools/typespec-bump-deps typespec-extension/package.json typespec-tests/package.json --add-npm-overrides"
    invoke "npm install"

    Set-Location "$RepoRoot/typespec-extension"
    invoke "npm install"
  } else {
    invoke "npm install -g @typespec/compiler"
    invoke "npm ci"
    
    Set-Location "$RepoRoot/typespec-extension"
    invoke "npm ci"
  }
  
  $env:path = $env:path = "$RepoRoot/node_modules/.bin;$env:path"
}
finally {
    Pop-Location
}
