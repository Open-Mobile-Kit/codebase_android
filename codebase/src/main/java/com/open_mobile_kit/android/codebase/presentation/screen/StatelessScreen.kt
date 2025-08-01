package com.open_mobile_kit.android.codebase.presentation.screen

import androidx.compose.runtime.Composable

@Composable
fun <S : BaseScreenContract.BaseState, E : BaseScreenContract.BaseEvent> StatelessScreen(
  state: S,
  onEvent: (E) -> Unit,
  content: @Composable (S, (E) -> Unit) -> Unit,
) {
  content(state, onEvent)
}
