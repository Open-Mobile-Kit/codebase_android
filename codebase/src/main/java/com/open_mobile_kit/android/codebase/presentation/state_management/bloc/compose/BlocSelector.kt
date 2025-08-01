package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

@Composable
fun <E : Event, S : State, SE : SideEffect, T> BlocSelector(
    bloc: Bloc<E, S, SE>,
    selector: (S) -> T,
    content: @Composable (T) -> Unit
) {
    val state by bloc.state.collectAsState()
    val selectedState = selector(state)
    content(selectedState)
}
