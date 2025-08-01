package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose.BlocBuilder
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose.BlocListener
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

@Composable
fun <E : Event, S : State, SE : SideEffect> BlocConsumer(
    bloc: Bloc<E, S, SE>,
    buildWhen: (S, S) -> Boolean = { _, _ -> true },
    listenWhen: (S, S) -> Boolean = { _, _ -> true },
    listener: (SE) -> Unit,
    builder: @Composable (S) -> Unit
) {
    BlocListener(
        bloc = bloc,
        listener = listener,
        listenWhen = listenWhen
    )
    BlocBuilder(
        bloc = bloc,
        content = builder,
        buildWhen = buildWhen
    )
}
