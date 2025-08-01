package com.open_mobile_kit.android.codebase.example.features.home.bloc

import com.open_mobile_kit.android.codebase.presentation.screen.BaseScreenContract

interface HomeContract : BaseScreenContract {
    sealed class Event : BaseScreenContract.BaseEvent() {
        object OnCounterClick : Event()
    }

    class State : BaseScreenContract.BaseState()

    sealed class SideEffect : BaseScreenContract.BaseSideEffect()
}
