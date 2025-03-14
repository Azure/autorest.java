# Use case:
#
# The purpose of this script is to compact the steps required to regenerate TypeSpec into a single script.
#
# If 'com.azure.autorest.customization' tests fails, re-install 'customization-base'.
#
# Before running this script the 'tsp' profile must be built, 'mvn install -P local,tsp'.
#
# This script can only be ran from the root of the repository.

# Invokes the given expression and only writes the output of the expression if it failed.
param (
  [int] $Parallelization = [Environment]::ProcessorCount
)

function invokeExpressionAndCaptureOutput([string]$expression) {
  $output = Invoke-Expression $expression
  if ($LASTEXITCODE -ne 0) {
    $ExitCode = $LASTEXITCODE

    Write-Host $output

    exit $ExitCode
  }

  Write-Host $output
}

& ./Build-TypeSpec.ps1

Write-Host "Installing TypeSpec ('npm install -g @typespec/compiler')"
invokeExpressionAndCaptureOutput("npm install -g @typespec/compiler")

Write-Host "Changing directory to './typespec-tests'"
Push-Location ./typespec-tests

try {
  Write-Host "Generating code ('Generate.ps1' in './typespec-tests')"
  & ./Generate.ps1 -Parallelization $Parallelization

#   Write-Host "Checking format of generated code ('npm run check-format')"
#   invokeExpressionAndCaptureOutput("npm run check-format")
} finally {
  Pop-Location
}
