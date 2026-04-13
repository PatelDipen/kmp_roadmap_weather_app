# WeatherApp

Kotlin Multiplatform weather application built with **Compose Multiplatform** for **Android** and **iOS**.

This project demonstrates a clean-architecture setup for a multiplatform app with shared UI, dependency injection, networking, navigation, splash flow, weather forecast screens, and city autocomplete.

## Demo

https://github.com/user-attachments/assets/7acabf68-0320-481f-9fc0-15cb4a933618

## Features

- Shared UI with Compose Multiplatform
- Android + iOS targets from a single shared module
- Animated splash screen
- 10-day weather forecast list
- Forecast detail screen with hourly weather for the selected day
- City search with autocomplete suggestions
- Clean architecture: `data`, `domain`, `presentation`
- Dependency injection using Koin
- Ktor-based networking
- Type-safe navigation with Compose Navigation

## Tech Stack

- **Kotlin Multiplatform**
- **Compose Multiplatform**
- **Material 3**
- **Koin** for DI
- **Ktor Client** for API calls
- **Kotlinx Serialization** for JSON parsing
- **Kotlinx Datetime** for date utilities
- **AndroidX Lifecycle ViewModel**
- **Compose Navigation**
- **BuildKonfig** for config injection

## Project Structure

```text
kmp_roadmap_weather_app/
├── composeApp/
│   ├── src/
│   │   ├── commonMain/
│   │   │   └── kotlin/com/kmp/weather/
│   │   │       ├── data/          # DTOs, API services, repositories
│   │   │       ├── di/            # Koin modules
│   │   │       ├── domain/        # Models, repositories, use cases
│   │   │       ├── navigation/    # App navigation graph and routes
│   │   │       ├── presentation/  # Screens, view models, UI components
│   │   │       ├── utils/         # Date and weather-code helpers
│   │   │       └── App.kt         # Shared app entry
│   │   ├── androidMain/           # Android-specific entry points
│   │   └── iosMain/               # iOS-specific entry points
├── iosApp/                        # iOS host app / Xcode project
├── gradle/                        # Version catalog and wrapper config
└── README.md
```

## Architecture

The shared code follows a clean-architecture style:

### `presentation`
- Compose screens and reusable UI components
- ViewModels holding UI state and user interactions
- Navigation graph connecting splash, list, and detail flows

### `domain`
- App models
- Repository contracts
- Use cases such as fetching weather forecast and searching cities

### `data`
- API services
- DTOs and mapping logic
- Repository implementations

## Current App Flow

1. App starts on a splash screen
2. User is navigated to the 10-day forecast list
3. Default city loads on startup
4. User can search a city using autocomplete
5. Selecting a city reloads the forecast
6. Tapping a day opens the hourly detail screen for that day

## API Integration

This app currently uses **Open-Meteo** endpoints:

### Forecast API
- Base URL: `https://api.open-meteo.com/`
- Daily forecast endpoint used for 10-day summary
- Hourly forecast endpoint used for selected-day detail

### Geocoding API
- Base URL: `https://geocoding-api.open-meteo.com/`
- Used for city search and autocomplete suggestions

### Weather Code Handling
Open-Meteo returns **WMO weather codes**. The app maps those codes to user-friendly descriptions and icons in shared utilities.

## Configuration

The project reads values from `local.properties` and exposes them via `BuildKonfig`.

Add this to your root `local.properties` file:

```properties
OPEN_WEATHER_API_KEY=your_api_key_here
```

> Note: the key is already wired through `BuildKonfig`, but the current forecast and geocoding implementation uses Open-Meteo endpoints, which do not require the OpenWeather key for the requests shown in this project.

## Prerequisites

Before running the project, make sure you have:

- **JDK 11+**
- **Android Studio** with Kotlin Multiplatform / Compose support
- **Xcode** for iOS builds
- Android SDK configured
- CocoaPods/Xcode iOS tooling configured if needed on your machine

## Running the App

### Android

You can open the project in Android Studio and run the Android target, or use Gradle commands such as:

```bash
./gradlew :composeApp:assembleDebug
```

If you want to install the debug APK on a connected device/emulator:

```bash
./gradlew :composeApp:installDebug
```

### iOS

Open the iOS host app in Xcode:

```bash
open iosApp/iosApp.xcodeproj
```

Then select a simulator/device and run the `iosApp` target from Xcode.

## Important Modules

- `composeApp/src/commonMain/kotlin/com/kmp/weather/App.kt`  
  Shared Compose app entry point

- `composeApp/src/commonMain/kotlin/com/kmp/weather/navigation/AppNavGraph.kt`  
  Navigation flow between splash, forecast list, and forecast detail

- `composeApp/src/commonMain/kotlin/com/kmp/weather/di/NetworkModule.kt`  
  Shared Ktor client and API service wiring

- `composeApp/src/commonMain/kotlin/com/kmp/weather/data/remote/api/WeatherApiService.kt`  
  Daily and hourly forecast requests

- `composeApp/src/commonMain/kotlin/com/kmp/weather/data/remote/api/GeocodingApiService.kt`  
  City search/autocomplete requests

- `composeApp/src/androidMain/kotlin/com/kmp/weather/MainActivity.kt`  
  Android entry point

- `composeApp/src/iosMain/kotlin/com/kmp/weather/MainViewController.kt`  
  iOS Compose entry point

## Libraries and Versions

The project uses a Gradle version catalog in `gradle/libs.versions.toml`.

Key versions currently configured include:

- Kotlin `2.3.20`
- Compose Multiplatform `1.10.3`
- Ktor `3.4.2`
- Koin `4.2.1`
- Kotlinx Serialization `1.8.1`
- Kotlinx Datetime `0.6.1`

## Notes

- The iOS framework generated from the shared module is named `ComposeApp`
- Android uses the platform splash API via `androidx.core:core-splashscreen`
- Shared UI components are gradually being extracted into reusable component files under `presentation/forecast/components`

## Possible Next Improvements

- Add unit tests for use cases and mappers
- Add UI tests for list/detail flows
- Persist recent searches or last selected city
- Improve weather visuals with custom drawable/icon assets
- Add offline caching
- Add theme switching and accessibility refinements

## License

This project is for learning / demo purposes unless your team defines a separate license.
