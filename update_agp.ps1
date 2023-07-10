$IDEA_AGP = 'agp = "7.4.0"'
$ANDROID_STUDIO_AGP = 'agp = "8.0.1"'
$FILE = ".\gradle\libs.versions.toml"

if (Test-Path $FILE)
{
    if ($args[0] -eq "idea")
    {
        (Get-Content $FILE) -replace [regex]::Escape($ANDROID_STUDIO_AGP), $IDEA_AGP | Set-Content $FILE
        Write-Host "Updated $FILE for IDEA."
    }
    elseif ($args[0] -eq "android-studio")
    {
        (Get-Content $FILE) -replace [regex]::Escape($IDEA_AGP), $ANDROID_STUDIO_AGP | Set-Content $FILE
        Write-Host "Updated $FILE for Android Studio."
    }
    else
    {
        Write-Host "Invalid argument. Please provide either 'idea' or 'android-studio'."
    }
}
else
{
    Write-Host "$FILE does not exist."
}
