#!/bin/bash

IDEA_AGP="agp = \"7.4.0\""
ANDROID_STUDIO_AGP="agp = \"8.0.1\""
FILE="./gradle/libs.versions.toml"

if [ -f "$FILE" ]; then
    if [ "$1" == "idea" ]; then
        sed -i "s/$ANDROID_STUDIO_AGP/$IDEA_AGP/g" "$FILE"
        echo "Updated $FILE for IDEA."
    elif [ "$1" == "android-studio" ]; then
        sed -i "s/$IDEA_AGP/$ANDROID_STUDIO_AGP/g" "$FILE"
        echo "Updated $FILE for Android Studio."
    else
        echo "Invalid argument. Please provide either 'idea' or 'android-studio'."
    fi
else
    echo "$FILE does not exist."
fi
