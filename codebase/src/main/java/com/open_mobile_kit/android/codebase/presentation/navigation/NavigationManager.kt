package com.open_mobile_kit.android.codebase.presentation.navigation

import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(

) : AppNavigation {
    private val observers: MutableSet<@JvmSuppressWildcards NavigationObserver> = mutableSetOf()
    private val guards: MutableSet<@JvmSuppressWildcards NavigationGuard> = mutableSetOf()
    private val _commands = MutableSharedFlow<NavigationCommand>()
    val commands = _commands.asSharedFlow()

    fun addObserver(observer: NavigationObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: NavigationObserver) {
        observers.remove(observer)
    }

    fun addGuard(guard: NavigationGuard) {
        guards.add(guard)
    }

    fun removeGuard(guard: NavigationGuard) {
        guards.remove(guard)
    }


    suspend fun navigate(command: NavigationCommand) {
        _commands.emit(command)
        observers.forEach { it.onNavigate(command) }
    }


    suspend fun validate(guards: List<NavigationGuard>): Boolean {
        return guards.all { it.canNavigate() == null } && this.guards.all { it.canNavigate() == null }
    }

    override suspend fun go(
        destination: String,
        args: Bundle?,
        guards: List<NavigationGuard>
    ) {
        if (validate(guards)) {
            navigate(NavigationCommand.Go(destination, args))
        }
    }

    override suspend fun push(
        destination: String,
        args: Bundle?,
        guards: List<NavigationGuard>
    ) {
        if (validate(guards)) {
            navigate(NavigationCommand.Push(destination, args))
        }
    }

    override suspend fun replace(
        destination: String,
        args: Bundle?,
        guards: List<NavigationGuard>
    ) {
        if (validate(guards)) {
            navigate(NavigationCommand.Replace(destination, args))
        }
    }

    override suspend fun pop() {
        navigate(NavigationCommand.Pop)
    }
}
