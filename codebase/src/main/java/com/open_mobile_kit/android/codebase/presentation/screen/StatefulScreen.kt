package com.open_mobile_kit.android.codebase.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.open_mobile_kit.android.codebase.presentation.state_management.bloc.compose.bloc
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
inline fun <
    S : BaseScreenContract.BaseState,
    E : BaseScreenContract.BaseEvent,
    SE : BaseScreenContract.BaseSideEffect,
    reified B : BaseBloc<E, S, SE>,
    > StatefulScreen(
  bloc: B?,
  noinline onSideEffect: (SE) -> Unit,
  crossinline content: @Composable (S, (E) -> Unit) -> Unit,
) {
  val screenBloc = bloc ?: bloc<B>()
  LaunchedEffect(key1 = true) {
    screenBloc.sideEffect.onEach { onSideEffect(it) }.launchIn(this)
  }
  BaseScreen(bloc = screenBloc) { state -> content(state, screenBloc::add) }
}
