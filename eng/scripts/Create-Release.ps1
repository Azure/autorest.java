param (
  $releaseSha,
  $tagName,
  $title,
  $releaseNotes
)

$apiUrl = "https://api.github.com/repos/Azure/autorest.java"
Write-Host "Using API URL $apiUrl"

Write-Host "Creating release $tagName"

$url = "$apiUrl/releases"
$body = ConvertTo-Json @{
  tag_name         = $tagName
  target_commitish = $releaseSha
  name             = $title
  draft            = $False
  prerelease       = $True
  body             = $releaseNotes
}

Write-Host "Post Request Body:"
Write-Host $body

$headers = @{
  "Content-Type"  = "application/json"
  "Authorization" = "token $($env:GH_TOKEN)"
}

Invoke-RestMethod -Uri $url -Body $body -Headers $headers -Method "Post" -MaximumRetryCount 3 -RetryIntervalSec 10
