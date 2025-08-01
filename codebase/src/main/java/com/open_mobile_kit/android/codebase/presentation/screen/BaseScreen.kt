package com.open_mobile_kit.android.codebase.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose.BlocProvider

@Composable
fun <E : Event, S : State, SE : SideEffect, B : BaseBloc<E, S, SE>> BaseScreen(
    bloc: B,
    content: @Composable (S) -> Unit
) {
    BlocProvider(bloc = bloc) {
        val state = bloc.state.collectAsState()
        content(state.value)
    }
}
