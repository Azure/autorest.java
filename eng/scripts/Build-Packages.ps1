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

$outputPath = $Output ? $Output : "$RepoRoot/artifacts/build"
$packagesPath = "$outputPath/packages"

# try to remove the artifacts folder if it exists
if (Test-Path $outputPath) {
  Remove-Item -Recurse -Force $outputPath
}

# create the output folders
$outputPath = New-Item -ItemType Directory -Force -Path $outputPath | Select-Object -ExpandProperty FullName
$packagesPath = New-Item -ItemType Directory -Force -Path $packagesPath | Select-Object -ExpandProperty FullName

Push-Location $RepoRoot
try {
    invoke "mvn -f pom.xml -P local,tsp -T 1C --no-transfer-progress clean verify package install"

    Push-Location "./typespec-extension"
    try {
      if ($Prerelease) {
        $emitterVersion = (npm pkg get version).Trim('"')
        $emitterVersion = "$emitterVersion-alpha.$BuildNumber"

        Write-Host "Updating emitter to preview version $emitterVersion"
        invoke "npm version $emitterVersion --no-git-tag-version"
      }
    
      Write-Host "Building TypeSpec Java"
      invoke "npm run build"
    
      # TODO: Move linting and format check to Test-Packages.ps1
      # Write-Host "Linting TypeSpec Java"
      # invoke "npm run lint"
    
      # Write-Host "Checking TypeSpec Java format"
      # invoke "npm run check-format"
    
      Write-Host "Packing TypeSpec Java"
      $file = invoke "npm pack -q"
      Copy-Item $file -Destination $packagesPath
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

if ($PublishInternal) {
  $feedUrl = "https://pkgs.dev.azure.com/azure-sdk/public/_packaging/azure-sdk-for-js-test-autorest/npm/registry"

  $overrides = @{
      "@azure-tools/typespec-java" = "$feedUrl/@azure-tools/typespec-java/-/typespec-java-$emitterVersion.tgz"
  }
} else {
  $overrides = @{}
}

$overrides | ConvertTo-Json | Set-Content "$outputPath/overrides.json"

$packageMatrix = [ordered]@{
  "emitter" = $emitterVersion
}

$packageMatrix | ConvertTo-Json | Set-Content "$outputPath/package-versions.json"
