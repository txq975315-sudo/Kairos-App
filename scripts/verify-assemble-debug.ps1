$ErrorActionPreference = "Stop"

Write-Host "==> Running :app:assembleDebug"
& "$PSScriptRoot/../gradlew.bat" ":app:assembleDebug"

if ($LASTEXITCODE -ne 0) {
    throw "assembleDebug failed with exit code $LASTEXITCODE"
}

Write-Host "==> Build check passed"
