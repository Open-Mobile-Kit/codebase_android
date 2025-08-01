package com.open_mobile_kit.android.codebase.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(
    private val observers: Set<@JvmSuppressWildcards NavigationObserver>
) {
    private val _commands = MutableSharedFlow<NavigationCommand>()
    val commands = _commands.asSharedFlow()

    suspend fun navigate(command: NavigationCommand) {
        _commands.emit(command)
        observers.forEach { it.onNavigate(command) }
    }
}
