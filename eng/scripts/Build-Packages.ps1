#Requires -Version 7.0

param(
    [string] $BuildNumber,
    [string] $Output,
    [switch] $Prerelease,
    [switch] $PublishInternal
)

$ErrorActionPreference = 'Stop'
Set-StrictMode -Version 3.0
. "$PSScriptRoot/Command-InvocationHelpers.ps1"
Set-ConsoleEncoding

Push-Location $RepoRoot
try {
    invoke "mvn -f pom.xml -P local,tsp -T 1C --no-transfer-progress clean verify package install"

    Push-Location "./typespec-extension"
    try {
      Write-Host "Installing dependencies for TypeSpec Java"
      invoke "npm install"
    
      Write-Host "Building TypeSpec Java"
      invoke "npm run build"
    
      # TODO: Move linting and format check to Test-Packages.ps1
      # Write-Host "Linting TypeSpec Java"
      # invoke "npm run lint"
    
      # Write-Host "Checking TypeSpec Java format"
      # invoke "npm run check-format"
    
      Write-Host "Packing TypeSpec Java"
      invoke "npm pack"
    } finally {
        Pop-Location
    }

    Write-Host "`n`n====================================="
    Write-Host "Print Package Dependencies"
    Write-Host "====================================="
    
    Write-Host "Dependencies of typespec-java"
    invoke "npm ls --all" -ExecutePath "$RepoRoot/typespec-extension"
}
finally {
    Pop-Location
}
