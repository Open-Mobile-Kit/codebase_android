package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

@Composable
fun <E : Event, S : State, SE : SideEffect> BlocBuilder(
    bloc: Bloc<E, S, SE>,
    buildWhen: (S, S) -> Boolean = { _, _ -> true },
    content: @Composable (S) -> Unit
) {
    val state by bloc.state.collectAsState()
    val buildWhenState by remember {
        derivedStateOf {
            buildWhen(state, state)
        }
    }
    if (buildWhenState) {
        content(state)
    }
}
