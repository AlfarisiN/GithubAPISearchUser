# GithubSearch

GithubSearch is an Android application that allows users to search for GitHub profiles and view detailed user information. The app is built using **Kotlin**, **Jetpack libraries**, **Coroutines**, **Retrofit**, and **Room** for local caching. Chucker is included for network debugging.

---

## Features

- Search GitHub users by username
- View user profile details
- Local caching with Room database
- Network request logging using Chucker
- Modern UI with Jetpack Compose and classic Android views

---

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM (ViewModel + Repository)
- **Networking:** Retrofit + OkHttp
- **Asynchronous Operations:** Kotlin Coroutines
- **Persistence:** Room Database
- **UI:** Jetpack Compose, Material3, AppCompat
- **Dependency Injection:** Dagger2
- **Debugging:** Chucker
- **Testing:** JUnit, Mockito, Espresso

---

## Prerequisites

- **Android Studio** Bumblebee or later (Arctic Fox recommended)
- **Gradle** 8.x
- **JDK 11**

---

## Getting Started

### 1. Clone the repository

```bash
    git clone https://github.com/yourusername/GithubSearch.git
    cd GithubSearch
```

### 2. Open in Android Studio
```
    Launch Android Studio
    Select File → Open
    Choose the project folder (GithubSearch)
    Allow Gradle to sync and download dependencies
```

### 3. Build the project
```
    Option 1: Click Build → Make Project in Android Studio
    Option 2: Use Gradle command in terminal:
    ./gradlew assembleDebug
```

### 4. Run the app
```
Connect an Android device or use an emulator

Click Run → Run 'app' in Android Studio

Select your device/emulator
```

### 5. Debugging network requests
```
Chucker is included and enabled for debug builds. To view network logs:
Run the app in debug mode
Open the Chucker UI via the notification
Inspect API requests and responses
```

## Testing
```
Unit Tests: Run with JUnit + Mockito
UI Tests: Run with Espresso or Compose Test
./gradlew test        # Run unit tests
./gradlew connectedAndroidTest  # Run instrumentation tests
```

## Dependencies
```
    Kotlin Coroutines
    Jetpack Compose & Material3
    AndroidX Lifecycle, ViewModel, Room
    Retrofit, OkHttp, Logging Interceptor
    Dagger2 for DI
    Chucker for network debugging
    Glide for image loading
    All dependencies are managed via Version Catalog (gradle/libs.versions.toml).
```