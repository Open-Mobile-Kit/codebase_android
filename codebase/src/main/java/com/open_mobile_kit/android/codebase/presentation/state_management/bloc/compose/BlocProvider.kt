package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import kotlin.reflect.KClass
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State

@Composable
fun <E : Event, S : State, SE : SideEffect> BlocProvider(
    bloc: Bloc<E, S, SE>,
    content: @Composable () -> Unit
) {
    val parentMap = LocalBlocMap.current
    val newMap = parentMap.toMutableMap()
    newMap[bloc::class] = bloc
    CompositionLocalProvider(LocalBlocMap provides newMap) {
        content()
    }
}
