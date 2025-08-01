package com.open_mobile_kit.android.codebase.presentation.state_management.bloc

import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

interface Emitter<S : State> {
    fun call(state: S)
}
