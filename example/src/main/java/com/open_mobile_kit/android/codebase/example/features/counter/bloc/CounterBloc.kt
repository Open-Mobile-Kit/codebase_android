package com.open_mobile_kit.android.codebase.example.features.counter.bloc

import com.open_mobile_kit.android.codebase.presentation.navigation.NavigationManager
import com.open_mobile_kit.android.codebase.presentation.screen.BaseBloc
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Emitter

class CounterBloc(
    private val navigationManager: NavigationManager
) :
    BaseBloc<CounterContract.Event, CounterContract.State, CounterContract.SideEffect>(
        CounterContract.State()
    ) {
    init {
        on(
            CounterContract.Event.OnIncrement::class.java,
            handler = ::onIncrement
        )
        on(
            CounterContract.Event.OnDecrement::class.java,
            handler = ::onDecrement
        )

        on(
            CounterContract.Event.OnBack::class.java,
            handler = { event, emitter ->
                navigationManager.pop()
            }
        )
    }

    private fun onIncrement(
        event: CounterContract.Event.OnIncrement,
        emitter: Emitter<CounterContract.State>
    ) {

        emitter.call(
            state.value.copy(
                count = state.value.count + 1
            )
        )

        postSideEffect(CounterContract.SideEffect.ShowToast("Incremented!"))
    }

    private fun onDecrement(
        event: CounterContract.Event.OnDecrement,
        emitter: Emitter<CounterContract.State>
    ) {

        emitter.call(
            state.value.copy(
                count = state.value.count - 1
            )

        )

        postSideEffect(CounterContract.SideEffect.ShowToast("Decremented!"))

    }
}
