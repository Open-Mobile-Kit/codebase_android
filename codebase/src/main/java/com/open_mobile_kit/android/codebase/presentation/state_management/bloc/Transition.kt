package com.open_mobile_kit.android.codebase.presentation.state_management.bloc

import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

data class Transition<E : Event, S : State>(
    val currentState: S,
    val event: E,
    val nextState: S
)
