$ErrorActionPreference = "Stop"

$wixCandidates = @(
    "C:\Program Files (x86)\WiX Toolset v3.14\bin",
    "C:\Program Files\WiX Toolset v3.14\bin"
)
$wixBin = $wixCandidates | Where-Object {
    (Test-Path (Join-Path $_ "candle.exe")) -and (Test-Path (Join-Path $_ "light.exe"))
} | Select-Object -First 1
if ($wixBin) {
    $env:Path = "$wixBin;$env:Path"
}
if (-not (Get-Command candle.exe -ErrorAction SilentlyContinue) -or
    -not (Get-Command light.exe -ErrorAction SilentlyContinue)) {
    throw "WiX Toolset was not found. Install WiX and ensure candle.exe and light.exe are available."
}

$project = Split-Path -Parent $MyInvocation.MyCommand.Path
$source = Join-Path $project "src\Assignment"
$connector = Join-Path $project "src\mysql-connector-j-26.7.0.jar"
$build = Join-Path $project "build\windows"
$classes = Join-Path $build "classes"
$driver = Join-Path $build "driver"
$input = Join-Path $build "input"
$dist = Join-Path $project "dist"
$applicationJar = Join-Path $input "Wanga_Malinda_pims.jar"

if (-not (Test-Path $connector)) {
    throw "MySQL Connector/J was not found at $connector"
}

Remove-Item -LiteralPath $build -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -LiteralPath $dist -Recurse -Force -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force -Path $classes, $driver, $input, $dist | Out-Null

$javaSources = Get-ChildItem -LiteralPath $source -Filter "*.java" -Recurse |
    ForEach-Object { $_.FullName }
javac -encoding UTF-8 -cp $connector -d $classes $javaSources
if ($LASTEXITCODE -ne 0) {
    throw "Java compilation failed."
}

Push-Location $driver
try {
    jar --extract --file $connector
}
finally {
    Pop-Location
}
Remove-Item -LiteralPath (Join-Path $driver "META-INF\MANIFEST.MF") -Force -ErrorAction SilentlyContinue
Get-ChildItem -LiteralPath (Join-Path $driver "META-INF") -Filter "*.SF" -Recurse |
    Remove-Item -Force -ErrorAction SilentlyContinue
Get-ChildItem -LiteralPath (Join-Path $driver "META-INF") -Filter "*.RSA" -Recurse |
    Remove-Item -Force -ErrorAction SilentlyContinue
Get-ChildItem -LiteralPath (Join-Path $driver "META-INF") -Filter "*.DSA" -Recurse |
    Remove-Item -Force -ErrorAction SilentlyContinue
Copy-Item -Path (Join-Path $driver "*") -Destination $classes -Recurse -Force
jar --create --file $applicationJar --main-class Assignment.PharmacyApplication -C $classes .

jpackage `
    --type exe `
    --name Wanga_Malinda_pims `
    --app-version 1.0.0 `
    --vendor "Wanga Malinda" `
    --input $input `
    --main-jar Wanga_Malinda_pims.jar `
    --main-class Assignment.PharmacyApplication `
    --dest $dist `
    --win-menu `
    --win-shortcut `
    --win-dir-chooser `
    --description "Pharmacy Inventory Management System"

if ($LASTEXITCODE -ne 0) {
    throw "jpackage could not create the EXE. Install WiX Toolset 3 or later and add candle.exe and light.exe to PATH."
}

Write-Host ""
Write-Host "Created: $dist\Wanga_Malinda_pims-1.0.0.exe"
Write-Host "Start MySQL in XAMPP before launching the application."
