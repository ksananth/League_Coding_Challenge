# Android-code-challenge ![Build status](https://github.com/ksananth/League_Coding_Challenge/workflows/CI/badge.svg?branch=master)

## About

Blah blah

## Features

The android app lets you:
- Show user post with avatar.
- Show error screen when api fails.
- Show error screen api key is invalid.
- Show error screen when no interter connection
- Retry when there is an error.

## Screenshots

[<img src="/readme/Wallabag%20Reading%20List.png" align="left"
width="200"
    hspace="10" vspace="10">](/readme/Wallabag%20Reading%20List.png)
[<img src="/readme/Wallabag%20Article%20View.png" align="center"
width="200"
    hspace="10" vspace="10">](/readme/Wallabag%20Article%20View.png)

## Android components and libraries used

- Jetpack Compose.
- AndroidX Navigation Component.
- Coroutines.
- Flow.
- Coil - To Load image
- Retrofit
- Gson
- Kotest - Unitest framework
- Espresso
- Github Actions - For CI/CD

## Run Application
Make sure you connected device/emulator

./gradlew assembleDebug
./gradlew installDebug


## Run Unit tests and UI tests

./gradlew testDebugUnitTest
./gradlew connectedAndroidTest

