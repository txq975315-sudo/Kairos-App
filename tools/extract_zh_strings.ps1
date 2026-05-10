# Extract user-visible Chinese from .kt / .xml under app/src and task-model/src
# Also scans app/src/main/res/values/*.xml for <string> resources containing Han.
$ErrorActionPreference = "Stop"
$root = if (Test-Path (Join-Path (Split-Path $PSScriptRoot -Parent) "app")) {
    Split-Path $PSScriptRoot -Parent
} else { "d:\KairosApplication" }

$outPath = Join-Path $root "translation_extract.txt"
$han = [regex]'[\u4e00-\u9fff]'
$skipLine = [regex]'^\s*//|^\s*\*|^\s*/\*|\bLog\.(?:d|e|i|v|w|wtf)\s*\(|\bprintln\s*\(|\bTimber\.|\bandroid\.util\.Log\.|^\s*package\s+|^\s*import\s+'
$xmlAttr = [regex]'(?i)(?:android:|app:|tools:)(?:text|hint|label|title|contentDescription|summary|subtitle|message|dialogTitle|dialogMessage|placeholder)\s*=\s*"([^"]*)"'
$i18nLine = [regex]'"([a-zA-Z0-9_]+)"\s*->\s*if\s*\(\s*zh\s*\)\s*"([^"]*)"'
$i18nBlockOpen = [regex]'"([a-zA-Z0-9_]+)"\s*->\s*if\s*\(\s*zh\s*\)\s*\{\s*$'
$i18nBlockString = [regex]'^\s*"([^"]*)"\s*$'
$stringRes = [regex]'(?i)<string\s+name="([^"]+)"[^>]*>([^<]*)</string>'

function Get-QuotedStrings([string]$line) {
    $results = [System.Collections.Generic.List[string]]::new()
    $i = 0
    while ($i -lt $line.Length) {
        if ($line[$i] -ne [char]34) { $i++; continue }
        $i++
        $sb = [System.Text.StringBuilder]::new()
        while ($i -lt $line.Length) {
            if ($line[$i] -eq [char]92) {
                if ($i + 1 -lt $line.Length) {
                    [void]$sb.Append($line[$i]).Append($line[$i+1])
                    $i += 2
                } else { $i++; break }
                continue
            }
            if ($line[$i] -eq [char]34) { $i++; break }
            [void]$sb.Append($line[$i])
            $i++
        }
        $s = $sb.ToString() -replace '\\n', "`n" -replace '\\t', "`t" -replace '\\"', '"'
        if ($han.IsMatch($s)) { $results.Add($s) }
    }
    return $results
}

function SlugId($prefix, $text, $seq) {
    $chars = $text.ToCharArray() | Where-Object { [char]::IsLetterOrDigit($_) -and [int]$_ -lt 128 }
    $ascii = -join ($chars | Select-Object -First 24)
    if (-not $ascii) {
        $ascii = "han_" + (($text.ToCharArray() | Select-Object -First 4 | ForEach-Object { '{0:x}' -f [int]$_ }) -join '')
    }
    $ascii = ($ascii -replace '[^a-zA-Z0-9_]+', '_').Trim('_').ToLowerInvariant()
    if (-not $ascii) { $ascii = "text" }
    return "${prefix}_${ascii}_$seq"
}

$rows = [System.Collections.Generic.List[string]]::new()
$seen = [System.Collections.Generic.HashSet[string]]::new()

function Add-Row($id, $zh, $loc) {
    $key = "$id|$zh|$loc"
    if (-not $script:seen.Add($key)) { return }
    $safe = $zh.Replace('|', [char]0xFF5C) -replace "`n", '\n'
    $script:rows.Add("$id | $safe | $loc")
}

# --- Kotlin + layout XML under app/src, task-model/src ---
$dirs = @(
    (Join-Path $root "app\src"),
    (Join-Path $root "task-model\src")
)

foreach ($dir in $dirs) {
    if (-not (Test-Path $dir)) { continue }
    $files = Get-ChildItem -Path $dir -Recurse -Include *.kt,*.xml -File | Where-Object {
        $_.FullName -notmatch '[\\/]build[\\/]'
    }
    foreach ($file in $files) {
        $rel = $file.FullName.Substring($root.Length + 1).Replace('\', '/')
        $isXml = ($file.Extension -eq ".xml")
        $isLocalizedStrings = $rel -match 'LocalizedStrings\.kt$'
        $prefix = if ($rel -match 'kairosapplication') { "app" } elseif ($rel -match 'taskmodel') { "taskmodel" } else { "src" }
        $lineNo = 0
        $pendingI18nKey = $null
        foreach ($line in [System.IO.File]::ReadAllLines($file.FullName, [System.Text.UTF8Encoding]::new($false))) {
            $lineNo++
            $zhFromI18n = [System.Collections.Generic.HashSet[string]]::new()
            # LocalizedStrings: capture `if (zh) {` even when the line has no Han (e.g. key-only line).
            if ($isLocalizedStrings) {
                if ($line -match $i18nBlockOpen) {
                    $pendingI18nKey = $matches[1]
                }
                elseif ($pendingI18nKey -and $line -match $i18nBlockString) {
                    $bzh = $matches[1]
                    if ($han.IsMatch($bzh)) {
                        [void]$zhFromI18n.Add($bzh)
                        Add-Row "i18n_$pendingI18nKey" $bzh "$rel`:$lineNo"
                    }
                    $pendingI18nKey = $null
                }
                elseif ($pendingI18nKey -and $line.Trim() -ne '' -and $line -match '\}\s*else') {
                    $pendingI18nKey = $null
                }
            }
            if (-not $han.IsMatch($line)) { continue }

            if ($isXml) {
                if ($line -match '^\s*<!--') { continue }
                foreach ($x in $xmlAttr.Matches($line)) {
                    $inner = $x.Groups[1].Value
                    if (-not $han.IsMatch($inner)) { continue }
                    $id = SlugId "xml_ui" $inner $lineNo
                    Add-Row $id $inner "$rel`:$lineNo"
                }
                continue
            }

            if ($skipLine.IsMatch($line)) { continue }

            if ($isLocalizedStrings) {
                foreach ($m in $i18nLine.Matches($line)) {
                    $k = $m.Groups[1].Value
                    $zh = $m.Groups[2].Value
                    if ($han.IsMatch($zh)) {
                        [void]$zhFromI18n.Add($zh)
                        Add-Row "i18n_$k" $zh "$rel`:$lineNo"
                    }
                }
            }

            $strs = Get-QuotedStrings $line
            $uniq = $strs | Select-Object -Unique
            $si = 0
            foreach ($lit in $uniq) {
                $si++
                if (-not $han.IsMatch($lit)) { continue }
                if ($isLocalizedStrings -and $zhFromI18n.Contains($lit)) { continue }
                $id = SlugId $prefix $lit ($lineNo * 100 + $si)
                Add-Row $id $lit "$rel`:$lineNo"
            }
        }
    }
}

# --- values/*.xml string resources (app) ---
$resDir = Join-Path $root "app\src\main\res\values"
if (Test-Path $resDir) {
    Get-ChildItem -Path $resDir -Filter "*.xml" -File | ForEach-Object {
        $rel = $_.FullName.Substring($root.Length + 1).Replace('\', '/')
        $lineNo = 0
        foreach ($line in [System.IO.File]::ReadAllLines($_.FullName, [System.Text.UTF8Encoding]::new($false))) {
            $lineNo++
            foreach ($m in $stringRes.Matches($line)) {
                $name = $m.Groups[1].Value
                $content = $m.Groups[2].Value.Trim()
                if (-not $han.IsMatch($content)) { continue }
                Add-Row "res_string_$name" $content "$rel`:$lineNo"
            }
        }
    }
}

$header = @(
    "# translation_extract.txt - user-visible Chinese in .kt / .xml / string resources",
    "# Format: unique_id | chinese_string | file:line",
    "# Excludes: // comment-only lines, Log/println/Timber, package/import lines.",
    '# LocalizedStrings.kt: id is i18n_<key> (same-line zh branch or block if (zh) { multiline }).',
    ""
)
[System.IO.File]::WriteAllLines($outPath, $header + $rows + "", [System.Text.UTF8Encoding]::new($true))
Write-Host "Wrote $($rows.Count) entries to $outPath"
