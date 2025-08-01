package com.open_mobile_kit.android.codebase.presentation.screen

import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

interface BaseScreenContract {
    abstract class BaseEvent : Event()

    abstract class BaseState : State()

    abstract class BaseSideEffect : SideEffect()
}
