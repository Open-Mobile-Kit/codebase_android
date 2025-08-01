# Navigation Implementation Guide

This document provides a guide on how to implement and use the type-safe navigation system with guards in this project.

## 1. Overview

The navigation system is designed to be type-safe and extensible, allowing for the use of navigation guards to control navigation flow. The core components are:

-   `NavigationCommand`: A sealed class that defines the available navigation actions (e.g., `Go`, `Push`, `Replace`, `Pop`).
-   `NavigationGuard`: An interface that allows you to define conditions that must be met before navigating to a destination. If a guard fails, it can return a `NavigationCommand` to redirect the user.
-   `NavigationManager`: A class that manages the navigation state and commands.
-   `AppNavigation`: An interface that provides a simple API for navigating between screens.
-   `AppNavigationImpl`: The default implementation of the `AppNavigation` interface.
-   `AppNavHost`: A composable that hosts the navigation graph and listens for navigation commands.

## 2. Defining Screens

To define a new screen, you should create a new object that extends `AppScreen`. This object should define the route and any arguments the screen requires.

```kotlin
open class AppScreen(val route: String)
```

```kotlin
object ProfileScreen : AppScreen("profile/{userId}") {
    fun route(userId: String) = "profile/$userId"
}
```

## 3. Navigating Between Screens

To navigate between screens, you can inject the `AppNavigation` interface into your ViewModel or other class and use the `go`, `push`, `replace`, and `pop` functions.

-   `go`: Navigates to a new screen and clears the back stack.
-   `push`: Navigates to a new screen, adding it to the back stack.
-   `replace`: Replaces the current screen with a new one.
-   `pop`: Removes the current screen from the back stack.

```kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNavigation: AppNavigation
) : ViewModel() {

    fun onProfileClick(userId: String) {
        viewModelScope.launch {
            appNavigation.push(ProfileScreen.route(userId))
        }
    }
}
```

## 4. Implementing and Using Navigation Guards

To create a navigation guard, you need to implement the `NavigationGuard` interface. The `canNavigate` function should return `null` if navigation is allowed, or a `NavigationCommand` if navigation should be redirected.

```kotlin
class AuthGuard @Inject constructor(
    private val authRepository: AuthRepository
) : NavigationGuard {

    override suspend fun canNavigate(): NavigationCommand? {
        return if (authRepository.isLoggedIn()) {
            null
        } else {
            NavigationCommand.Go(LoginScreen.route)
        }
    }
}
```

To use a guard, you can pass it to the `go`, `push`, or `replace` functions.

```kotlin
fun onProfileClick(userId: String) {
    viewModelScope.launch {
        appNavigation.push(
            destination = ProfileScreen.route(userId),
            guards = listOf(authGuard)
        )
    }
}
```

## 5. Observing Navigation Events

To observe navigation events, you can create a class that implements the `NavigationObserver` interface. This is useful for logging, analytics, or other tasks that need to be performed when navigation occurs.

```kotlin
class LoggingNavigationObserver @Inject constructor() : NavigationObserver {
    override fun onNavigate(command: NavigationCommand) {
        // Log the navigation command
    }
}
```

You can then provide your observer using Hilt's multibinding feature.

```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationObserverModule {
    @Binds
    @IntoSet
    abstract fun bindLoggingNavigationObserver(
        loggingNavigationObserver: LoggingNavigationObserver
    ): NavigationObserver
}
```

## 6. Setting up the AppNavHost

The `AppNavHost` is the central point of the navigation system. You need to set it up in your main activity or other composable that hosts your app's content.

```kotlin
@Composable
fun App() {
    val navController = rememberNavController()
    val navigationManager = hiltViewModel<NavigationManager>()

    AppNavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        navigationManager = navigationManager
    ) {
        composable(HomeScreen.route) {
            HomeScreen()
        }
        composable(ProfileScreen.route) {
            ProfileScreen()
        }
        composable(LoginScreen.route) {
            LoginScreen()
        }
    }
}
```
