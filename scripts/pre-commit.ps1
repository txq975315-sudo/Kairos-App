# Optional: run from repo root before commit (same checks as .githooks/pre-commit).
Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"
Set-Location (Resolve-Path (Join-Path $PSScriptRoot ".."))
& .\gradlew.bat :app:lintDebug :app:scanHardcodedText --no-daemon
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
