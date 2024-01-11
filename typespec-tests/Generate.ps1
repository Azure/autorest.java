# Use case:
#
# The purpose of this script is to compact the steps required to regenerate TypeSpec into a single script.
#
# If 'com.azure.autorest.customization' tests fails, re-install 'customization-base'.
#
# Before running this script the 'tsp' profile must be built, 'mvn install -P local,tsp'.
param (
  [int] $Parallelization = [Environment]::ProcessorCount - 1
)

$ExitCode = 0

if ($Parallelization -lt 1) {
  $Parallelization = 1
}

Write-Host "Parallelization: $Parallelization"

$generateScript = {
  $tspFile = $_

  $tspClientFile = $tspFile -replace 'main.tsp', 'client.tsp'
  if (($tspClientFile -match 'client.tsp$') -and (Test-Path $tspClientFile)) {
    $tspFile = $tspClientFile
  }

  # With TypeSpec code generation being parallelized, we need to make sure that the output directory is unique
  # for each test run. We do this by appending a random number to the output directory.
  # Without this, we could have multiple runs trying to write to the same directory which introduces race conditions.
  $tspOptions = "--option ""@azure-tools/typespec-java.emitter-output-dir={project-root}/tsp-output/$(Get-Random)"""
  if ($tspFile -match "type[\\/]enum[\\/]extensible[\\/]") {
    # override namespace for reserved keyword "enum"
    $tspOptions += " --option ""@azure-tools/typespec-java.namespace=com.type.enums.extensible"""
  } elseif ($tspFile -match "type[\\/]enum[\\/]fixed[\\/]") {
    # override namespace for reserved keyword "enum"
    $tspOptions += " --option ""@azure-tools/typespec-java.namespace=com.type.enums.fixed"""
  } elseif ($tspFile -match "resiliency[\\/]srv-driven[\\/]old.tsp") {
    # override namespace for "resiliency/srv-driven/old.tsp" (make it different to that from "main.tsp")
    $tspOptions += " --option ""@azure-tools/typespec-java.namespace=com.resiliency.servicedriven.v1"""
  }

  # Test customization for one of the TypeSpec definitions - naming.tsp
  if ($tspFile -match "tsp[\\/]naming.tsp$") {
#     # since tsp-output directory will be cleaned up after each test tsp, we copy the customization
#     # code into output directory from customization directory in tsp-output/customization directory just before
#     # generating the code
#     Copy-Item -Path ./customization -Destination ./tsp-output/customization -Recurse -Force

    # Add the customization-class option for Java emitter
    $tspOptions += " --option ""@azure-tools/typespec-java.customization-class=../../customization/src/main/java/CustomizationTest.java"""
  } elseif ($tspFile -match "encode[\\/]bytes[\\/]main.tsp") {
    $tspOptions += " --option ""@azure-tools/typespec-java.customization-class=../../customization/src/main/java/CustomizationEncodeBytes.java"""
  }

  $tspTrace = "--trace import-resolution --trace projection --trace typespec-java"
  $tspCommand = "npx tsp compile $tspFile $tspOptions $tspTrace"

  $generateOutput = Invoke-Expression $tspCommand
  $global:ExitCode = $global:ExitCode -bor $LASTEXITCODE

  if ($LASTEXITCODE -ne 0) {
    Write-Host "
  ========================
  $tspCommand
  ========================
  $([String]::Join("`n", $generateOutput))
    "
  } else {
    Write-Host "
  ========================
  $tspCommand
  ========================
  SUCCEEDED
    "
  }

  if ($global:ExitCode -ne 0) {
    exit $global:ExitCode
  }
}

./Setup.ps1

New-Item -Path ./existingcode/src/main/java/com/cadl/ -ItemType Directory -Force | Out-Null

if (Test-Path ./src/main/java/com/cadl/partialupdate) {
  Copy-Item -Path ./src/main/java/com/cadl/partialupdate -Destination ./existingcode/src/main/java/com/cadl/partialupdate -Recurse -Force
}

if (Test-Path ./src/main) {
  Remove-Item ./src/main -Recurse -Force
}
if (Test-Path ./tsp-output) {
  Remove-Item ./tsp-output -Recurse -Force
}

# run other local tests except partial update
$job = (Get-Item ./tsp/* -Filter "*.tsp" -Exclude "*partialupdate*") | ForEach-Object -Parallel $generateScript -ThrottleLimit $Parallelization -AsJob

$job | Wait-Job -Timeout 600
$job | Receive-Job

# partial update test
npx tsp compile ./tsp/partialupdate.tsp --option="@azure-tools/typespec-java.emitter-output-dir={project-root}/existingcode"
Copy-Item -Path ./existingcode/src/main/java/com/cadl/partialupdate -Destination ./src/main/java/com/cadl/partialupdate -Recurse -Force
Remove-Item ./existingcode -Recurse -Force

# run cadl ranch tests sources
Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

$job = (Get-ChildItem ./http -Include "main.tsp","old.tsp" -File -Recurse) | ForEach-Object -Parallel $generateScript -ThrottleLimit $Parallelization -AsJob

$job | Wait-Job -Timeout 1200
$job | Receive-Job

Remove-Item ./http -Recurse -Force

Copy-Item -Path ./tsp-output/*/src -Destination ./ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

Remove-Item ./tsp-output -Recurse -Force
