# Android-code-challenge ![main branch](https://github.com/ksananth/League_Coding_Challenge/actions/workflows/android.yml/badge.svg?branch=main)

## Way of working

<img src="screenshots/way.jpeg" alt="drawing" width="500" width="400"/>

## Features

The android app lets you:
- Show user post with avatar.
- Show error screen when api fails.
- Show error screen api key is invalid.
- Show error screen when no interter connection
- Retry when there is an error.

## Screenshots

| Success   |      Loading      |  Api Error | Technical Error | No Internet connection |
|----------|:-------------:|------:|------:|------:|
| <img src="screenshots/success.png" alt="drawing" width="200"/> |  <img src="screenshots/loading.png" alt="drawing" width="200"/> | <img src="screenshots/api.png" alt="drawing" width="200"/> | <img src="screenshots/error.png" alt="drawing" width="200"/> | <img src="screenshots/internet.png" alt="drawing" width="200"/> |

## Android components and libraries used

- Jetpack Compose.
- AndroidX Navigation Component.
- Coroutines.
- Flow.
- Koin - Dependency injection
- Coil - To Load image
- Retrofit
- Gson
- Kotest - Unitest framework
- Espresso
- Github Actions - For CI/CD

## Run Application
Make sure you connected device/emulator

```ruby
./gradlew assembleDebug
./gradlew installDebug
```


## Run Unit tests and UI tests


```ruby
./gradlew testDebugUnitTest
./gradlew connectedAndroidTest
```




