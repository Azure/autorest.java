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
  [int] $Parallelization = [Environment]::ProcessorCount - 1
)

function invokeExpressionAndCaptureOutput([string]$expression) {
    $output = Invoke-Expression $expression
    if ($LASTEXITCODE -ne 0) {
        $ExitCode = $LASTEXITCODE

        Write-Host $output

        exit $ExitCode
    }
}

Write-Host "Changing directory to './typespec-extension'"
Push-Location ./typespec-extension

try {
  Write-Host "Installing dependencies for TypeSpec Java ('npm ci')"
  invokeExpressionAndCaptureOutput("npm ci")

  Write-Host "Building TypeSpec Java ('npm run build')"
  invokeExpressionAndCaptureOutput("npm run build")

  Write-Host "Linting TypeSpec Java ('npm run lint')"
  invokeExpressionAndCaptureOutput("npm run lint")

  Write-Host "Checking TypeSpec Java format ('npm run check-format')"
  invokeExpressionAndCaptureOutput("npm run check-format")

  Write-Host "Packing TypeSpec Java ('npm pack')"
  invokeExpressionAndCaptureOutput("npm pack")

  Write-Host "Returning to root directory ('..')"
} finally {
  Pop-Location
}

Write-Host "Installing TypeSpec ('npm install -g @typespec/compiler')"
invokeExpressionAndCaptureOutput("npm install -g @typespec/compiler")

Write-Host "Changing directory to './typespec-tests'"
Push-Location ./typespec-tests

try {
  Write-Host "Generating code ('Generate.ps1' in './typespec-tests')"
  invokeExpressionAndCaptureOutput("./Generate.ps1 -Parallelization $Parallelization")

  Write-Host "Checking format of generated code ('npm run check-format')"
  invokeExpressionAndCaptureOutput("npm run check-format")
} finally {
  Pop-Location
}
