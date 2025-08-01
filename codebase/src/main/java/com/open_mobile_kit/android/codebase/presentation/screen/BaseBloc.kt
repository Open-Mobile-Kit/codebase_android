package com.open_mobile_kit.android.codebase.presentation.screen

import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

abstract class BaseBloc<E : Event, S : State, SE : SideEffect>(initialState: S) :
    Bloc<E, S, SE>(initialState)
