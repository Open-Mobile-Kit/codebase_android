# App Structure Suggestion

This document outlines the suggested application structure for this project. The structure is based on Clean Architecture principles and is designed to be scalable, maintainable, and testable.

The project is divided into several layers, each with its own set of responsibilities.

## Layers

### 1. Domain Layer

The `domain` layer is the core of the application. It contains the business logic and is independent of any other layer.

- **`domain/entity`**: Contains the business objects of the application (e.g., `User`, `Product`). These are plain Kotlin data classes. `IEntity` is a marker interface for entities, and `ValueObject` is a base class for value objects.
- **`domain/repository`**: Defines the interfaces for the data sources (e.g., `IUserRepository`). These interfaces are implemented in the `data` layer.
- **`domain/usecase`**: Contains the application-specific business rules. Each use case should have a single responsibility (e.g., `GetUserUseCase`). `IUseCase` is a base interface for use cases.

### 2. Data Layer

The `data` layer is responsible for providing data to the `domain` layer. It implements the repository interfaces defined in the `domain` layer.

- **`data/repository`**: Implementation of the repository interfaces from the `domain` layer. This is where the actual data fetching logic resides.
- **`data/remote`**: Contains the code for accessing remote data sources, such as a REST API. It uses `HttpClient` from the `infrastructure` layer. `RequestHeaderInterceptor` can be used to add headers to requests.
- **`data/local`**: (Suggestion) This package would contain the code for accessing local data sources, such as a database (e.g., Room).
- **`data/mapper`**: Contains mapper classes that convert data from one format to another (e.g., from a network response to a domain entity). `IMapper` is a base interface for mappers.
- **`data/exception`**: Contains custom exception classes. `ExceptionBase` is a base class for exceptions.

### 3. Presentation Layer

The `presentation` layer is responsible for displaying the UI to the user and handling user input. It uses the `domain` layer to perform business operations.

- **`presentation/screen`**: Contains the UI screens of the application. Each screen consists of a `Composable` function. `BaseScreen`, `StatefulScreen`, and `StatelessScreen` provide base implementations for screens. `BaseScreenContract` defines the contract between the screen and its view model/bloc.
- **`presentation/state_management/bloc`**: Implements the BLoC (Business Logic Component) pattern for state management.
    - `Bloc`: The core class of the BLoC pattern.
    - `Event`: Represents user actions or other events.
    - `State`: Represents the state of the UI.
    - `SideEffect`: Represents one-time events that should be handled by the UI (e.g., showing a toast).
- **`presentation/navigation`**: Handles navigation between screens.
    - `AppNavigation`: Defines the navigation graph.
    - `NavigationManager`: Manages navigation commands.
    - `AppScreen`: Defines the screens of the application.

### 4. Application Layer

The `application` layer contains application-wide components and configuration.

- **`ICommand`**: Represents a command that can be executed.
- **`IService`**: Represents a service that can be used throughout the application.
- **`MainApplication.kt`**: The main application class.

### 5. Infrastructure Layer

The `infrastructure` layer contains technical details and implementations for external concerns.

- **`infrastructure/remote`**: Contains the implementation of the `HttpClient` for making network requests.

## Modules

- **`app`**: The main application module, containing the implementation of the application.
- **`example`**: An example module that demonstrates how to use the components from the `app` module.

This structure promotes separation of concerns and makes the codebase easier to understand, test, and maintain.

## Example App Folder Tree

Here is a visual representation of the `example` app's folder structure, which demonstrates how to use the codebase:

```
example/
├── build.gradle.kts
├── proguard-rules.pro
└── src/
    ├── main/
    │   ├── AndroidManifest.xml
    │   ├── java/
    │   │   └── com/
    │   │       └── open_mobile_kit/
    │   │           └── android/
    │   │               └── codebase/
    │   │                   └── example/
    │   │                       ├── ExampleApplication.kt
    │   │                       ├── MainActivity.kt
    │   │                       └── features/
    │   │                           └── greeting/
    │   │                               ├── GreetingBloc.kt
    │   │                               ├── GreetingContract.kt
    │   │                               └── GreetingScreen.kt
    │   └── res/
    │       ├── drawable/
    │       ├── layout/
    │       └── values/
    │           ├── colors.xml
    │           ├── strings.xml
    │           └── themes.xml
    ├── test/
    │   └── java/
    │       └── com/
    │           └── open_mobile_kit/
    │               └── android/
    │                   └── codebase/
    │                       └── example/
    │                           └── ExampleUnitTest.kt
    └── androidTest/
        └── java/
            └── com/
                └── open_mobile_kit/
                    └── android/
                        └── codebase/
                            └── example/
                                └── ExampleInstrumentedTest.kt
```
