# Screen Implementation Guide

This guide explains how to implement a screen using the base classes.

## 1. Create the Contract

Create a contract file that extends the `BaseScreenContract`.

```kotlin
interface MyScreenContract : BaseScreenContract {
    sealed class Event : BaseEvent() {
        object MyEvent : Event()
    }

    data class State(val myData: String) : BaseState()

    sealed class SideEffect : BaseSideEffect() {
        object MySideEffect : SideEffect()
    }
}
```

## 2. Create the BLoC

Create a BLoC that extends the `BaseBloc`.

```kotlin
class MyScreenBloc : BaseBloc<MyScreenContract.Event, MyScreenContract.State, MyScreenContract.SideEffect>(
    initialState = MyScreenContract.State("initial data")
) {
    init {
        on(MyScreenContract.Event.MyEvent::class.java) { event, emitter ->
            // Handle event
        }
    }
}
```

## 3. Create the Screen

Create a screen that uses the `StatefulScreen` or `StatelessScreen`.

### Stateful Screen

```kotlin
@Composable
fun MyScreen(
    viewModel: MyScreenViewModel = hiltViewModel()
) {
    StatefulScreen(
        bloc = viewModel.bloc,
        onSideEffect = { sideEffect ->
            // Handle side effect
        }
    ) { state, onEvent ->
        // UI
    }
}
```

### Stateless Screen

```kotlin
@Composable
fun MyScreen(
    state: MyScreenContract.State,
    onEvent: (MyScreenContract.Event) -> Unit
) {
    StatelessScreen(
        state = state,
        onEvent = onEvent
    ) { state, onEvent ->
        // UI
    }
}
```