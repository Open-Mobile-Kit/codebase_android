package com.open_mobile_kit.android.codebase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit
) {
    LaunchedEffect(Unit) {
        navigationManager.commands.onEach { command ->
            when (command) {
                is NavigationCommand.Pop -> navController.popBackStack()
                is NavigationCommand.Push -> navController.navigate(command.destination)
                is NavigationCommand.Go -> navController.navigate(command.destination) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }

                is NavigationCommand.Replace -> navController.navigate(command.destination) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        builder = builder
    )
}