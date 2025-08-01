package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

@Composable
fun MultiBlocProvider(
    vararg providers: Bloc<out Event, out State, out SideEffect>,
    content: @Composable () -> Unit
) {
    if (providers.isEmpty()) {
        content()
        return
    }
    val provider = providers.first()
    val rest = providers.drop(1).toTypedArray()
    BlocProvider(bloc = provider) {
        MultiBlocProvider(providers = rest, content = content)
    }
}
