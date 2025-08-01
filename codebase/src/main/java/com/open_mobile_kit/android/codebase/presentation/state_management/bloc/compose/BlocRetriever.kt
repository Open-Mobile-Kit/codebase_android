package com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Bloc
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.Event
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.SideEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.State
import kotlin.reflect.KClass

val LocalBlocMap = compositionLocalOf<Map<KClass<*>, Bloc<*, *, *>>> { emptyMap() }

@Composable
inline fun <reified T : Bloc<out Event, out State, out SideEffect>> bloc(): T {
    val blocMap = LocalBlocMap.current
    return blocMap[T::class] as? T
        ?: error("Bloc of type ${T::class.simpleName} not found. Make sure it is provided.")
}
