package com.open_mobile_kit.android.codebase.example.features.counter

import com.open_mobile_kit.android.codebase.presentation.screen.BaseScreenContract

interface CounterContract : BaseScreenContract {
    sealed class Event : BaseScreenContract.BaseEvent() {
        object OnIncrement : Event()
        object OnDecrement : Event()
    }

    data class State(
        val count: Int = 0
    ) : BaseScreenContract.BaseState()

    sealed class SideEffect : BaseScreenContract.BaseSideEffect() {
        data class ShowToast(val message: String) : SideEffect()
    }
}
