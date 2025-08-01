package com.open_mobile_kit.android.codebase.presentation.navigation

import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(

) {
    private val observers: MutableSet<@JvmSuppressWildcards NavigationObserver> = mutableSetOf()
    private val _commands = MutableSharedFlow<NavigationCommand>()
    val commands = _commands.asSharedFlow()

    suspend fun navigate(command: NavigationCommand) {
        _commands.emit(command)
        observers.forEach { it.onNavigate(command) }
    }

    suspend fun  pop() {
        navigate(NavigationCommand.Pop)
    }

    suspend fun push(destination: String, args: Bundle? = null) {
        navigate(NavigationCommand.Push(destination, args))
    }


    suspend fun go(destination: String, args: Bundle? = null) {
        navigate(NavigationCommand.Go(destination, args))
    }

    suspend fun replace(destination: String, args: Bundle? = null) {
        navigate(NavigationCommand.Replace(destination, args))
    }

    fun addObserver(observer: NavigationObserver) {
        observers.add(observer)
    }
}
