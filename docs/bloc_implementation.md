# BLoC State Management Guide

Welcome to the BLoC (Business Logic Component) state management guide for our Android application. This document provides a clear and easy-to-follow walkthrough of how to use our BLoC implementation, which is inspired by `flutter_bloc` but with some enhancements for Android development with Jetpack Compose.

## 🚀 Core Concepts

At its heart, our BLoC implementation revolves around a few key concepts:

-   **`Event`**: Represents an action or input from the UI that can trigger a change in the state. Think of these as "things that happen," like a button click or a text field entry.
-   **`State`**: Represents the data and condition of a UI component at a given moment. The UI should be a direct reflection of the current state.
-   **`Bloc`**: The central piece that manages the logic. It listens for `Event`s, processes them, and emits new `State`s.
-   **`SideEffect`**: A special type of emission from the BLoC for one-time actions that shouldn't be part of the state, like showing a toast, a dialog, or navigating to a new screen.

## 🛠️ How to Implement a BLoC

Let's walk through creating a simple counter feature.

### 1. Define the Contracts: State, Event, and SideEffect

First, define the "contract" for your feature by creating sealed classes for your states, events, and side effects. This provides strong typing and clarity.

```kotlin
// in src/.../counter/CounterContract.kt

// Events are the inputs to the BLoC
sealed class CounterEvent : Event() {
    object IncrementButtonPressed : CounterEvent()
    object DecrementButtonPressed : CounterEvent()
}

// State represents the data the UI will display
data class CounterState(val count: Int = 0) : State()

// SideEffects are for one-time actions
sealed class CounterSideEffect : SideEffect() {
    data class ShowToast(val message: String) : CounterSideEffect()
}
```

### 2. Create the BLoC

Next, create the `Bloc` itself. It will extend our base `Bloc` class and handle the logic for processing events.

```kotlin
// in src/.../counter/CounterBloc.kt

class CounterBloc : Bloc<CounterEvent, CounterState, CounterSideEffect>(
    initialState = CounterState(0)
) {
    init {
        // Register an event handler for the IncrementButtonPressed event
        on(IncrementButtonPressed::class.java) { event, emitter ->
            // Emit a new state with the updated count
            emitter.call(state.value.copy(count = state.value.count + 1))
            // Post a side effect to show a toast
            postSideEffect(ShowToast("Incremented!"))
        }

        // Register an event handler for the DecrementButtonPressed event
        on(DecrementButtonPressed::class.java) { event, emitter ->
            emitter.call(state.value.copy(count = state.value.count - 1))
            postSideEffect(ShowToast("Decremented!"))
        }
    }
}
```

## 🎨 Using the BLoC in Your UI (Jetpack Compose)

Now, let's connect our `CounterBloc` to a Composable screen.

### 1. Provide the BLoC(s)

First, you need to provide your `Bloc`(s) to the Composable tree. You can use `BlocProvider` for a single BLoC or `MultiBlocProvider` for multiple.

**Single BLoC:**
```kotlin
// In your NavHost or top-level Composable for the feature
BlocProvider(bloc = CounterBloc()) {
    CounterScreen()
}
```

```kotlin
val counterBloc = CounterBloc()
BlocProvider(
    bloc = counterBloc
) {
    // Your Composable tree here
}
```

**Multiple BLoCs:**
```kotlin
@Composable
fun AppRoot() {
    MultiBlocProvider(
        CounterBloc(),
        AuthenticationBloc()
    ) {
        MyScreen()
    }
}
```

### 2. Retrieve and Use a BLoC

Inside your composables, use the `bloc()` function to retrieve a provided BLoC instance in a type-safe way.

```kotlin
@Composable
fun CounterScreen() {
    // Retrieve the CounterBloc instance
    val bloc: CounterBloc = bloc()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // ...
    }
}
```

### 3. Build UI with `BlocBuilder`

Use `BlocBuilder` to listen to state changes and rebuild your UI accordingly.

```kotlin
@Composable
fun CounterScreen() {
    val bloc: CounterBloc = bloc()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // This Text will automatically update when the CounterState changes
        BlocBuilder(bloc = bloc) { state ->
            Text(text = "Count: ${state.count}", style = MaterialTheme.typography.h4)
        }

        Button(onClick = { bloc.add(CounterEvent.IncrementButtonPressed) }) {
            Text("Increment")
        }
    }
}
```

### 4. Handle Side Effects with `BlocListener`

Use `BlocListener` to handle one-time actions like showing toasts or navigating.

```kotlin
@Composable
fun CounterScreen() {
    val bloc: CounterBloc = bloc()
    val context = LocalContext.current

    // This will listen for side effects without rebuilding the UI
    BlocListener(bloc = bloc) { sideEffect ->
        when (sideEffect) {
            is CounterSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ... rest of your UI
}
```

### Advanced: Transforming Events

You can apply transformations like `debounce` or `distinctUntilChanged` to the event stream directly in your BLoC by passing an `EventTransformer`.

**Debounce Example (e.g., for a search field):**

```kotlin
// in SearchBloc.kt
on(
    SearchQueryChanged::class.java,
    { event, emitter ->
        // This will only execute after the user has stopped typing for 300ms
        val results = repository.search(event.query)
        emitter.call(SearchSuccess(results))
    },
    transformer = EventTransformers.debounce(300L)
)
```