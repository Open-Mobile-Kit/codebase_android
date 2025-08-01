package com.open_mobile_kit.android.codebase.presentation.state_management.bloc

import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Transition

interface BlocObserver {
    fun onEvent(event: Event) {}
    fun onTransition(transition: Transition<out Event, out State>) {}
    fun onError(error: Throwable) {}
}
