$RepoRoot = (Resolve-Path "$PSScriptRoot/../..").Path.Replace('\', '/')

function Invoke-LoggedCommand($Command, $ExecutePath) {
  $startTime = Get-Date

  Push-Location $ExecutePath

  try {
    Write-Host "`n============================================================"
    Write-Host "From $PWD"
    Write-Host "> $Command"
    Write-Host "============================================================"

    Invoke-Expression $Command

    $duration = (Get-Date) - $startTime

    Write-Host "------------------------------------------------------------"

    if ($LastExitCode -ne 0) {
      Write-Host "##[error]Command failed: $command"
    }
    else {
      Write-Host "End: $command"
    }

    Write-Host "Exit code $LastExitCode, Duration: $($Duration.TotalSeconds.ToString("f3"))s"
    Write-Host "------------------------------------------------------------`n"

    if ($LastExitCode) {
      exit $LastExitCode
    }
  }
  finally {
    Pop-Location
  }
}

function Set-ConsoleEncoding {
  [CmdletBinding()]
  param
  (
    [string] $Encoding = 'utf-8'
  )

  $outputEncoding = [System.Text.Encoding]::GetEncoding($Encoding)
  [Console]::OutputEncoding = $outputEncoding
  [Console]::InputEncoding = $outputEncoding
}

Set-Alias -Name invoke -Value Invoke-LoggedCommand
