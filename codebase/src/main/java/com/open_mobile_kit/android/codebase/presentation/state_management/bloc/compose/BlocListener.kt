package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf

@Composable
fun <E : Event, S : State, SE : SideEffect> BlocListener(
    bloc: Bloc<E, S, SE>,
    listenWhen: (S, S) -> Boolean = { _, _ -> true },
    listener: (SE) -> Unit
) {
    val state by bloc.state.collectAsState()
    val listenWhenState by remember {
        derivedStateOf {
            listenWhen(state, state)
        }
    }
    if (listenWhenState) {
        LaunchedEffect(Unit) {
            launch {
                bloc.sideEffect.collectLatest {
                    listener(it)
                }
            }
        }
    }
}
