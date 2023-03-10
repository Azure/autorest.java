# Use case:
#
# The purpose of this script is to compact the steps required to regenerate TypeSpec into a single script.
#
# Before running this script the 'cadl' profile must be built, 'mvn install -P local,cadl'.
#
# This script can only be ran from the root of the repository.

# Invokes the given expression and only writes the output of the expression if it failed.
function invokeExpressionAndCaptureOutput([string]$expression) {
    $output = Invoke-Expression $expression
    if ($LASTEXITCODE -ne 0) {
        Write-Host $output
    }
}

Write-Host "Changing directory to './cadl-extension'"
Set-Location ./cadl-extension

Write-Host "Installing dependencies for TypeSpec Java ('npm install')"
invokeExpressionAndCaptureOutput("npm install")

Write-Host "Building TypeSpec Java ('npm run build')"
invokeExpressionAndCaptureOutput("npm run build")

Write-Host "Linting TypeSpec Java ('npm run lint')"
invokeExpressionAndCaptureOutput("npm run lint")

Write-Host "Checking TypeSpec Java format ('npm run check-format')"
invokeExpressionAndCaptureOutput("npm run check-format")

Write-Host "Packing TypeSpec Java ('npm pack')"
invokeExpressionAndCaptureOutput("npm pack")

Write-Host "Returning to root directory ('..')"
Set-Location ..

Write-Host "Installing TypeSpec ('npm install -g @typespec/compiler')"
invokeExpressionAndCaptureOutput("npm install -g @typespec/compiler")

Write-Host "Changing directory to './cadl-tests'"
Set-Location ./cadl-tests

Write-Host "Generating code ('Generate.ps1' in './cadl-tests')"
invokeExpressionAndCaptureOutput("./Generate.ps1")

Write-Host "Checking format of generated code ('npm run check-format')"
invokeExpressionAndCaptureOutput("npm run check-format")

Set-Location ..
