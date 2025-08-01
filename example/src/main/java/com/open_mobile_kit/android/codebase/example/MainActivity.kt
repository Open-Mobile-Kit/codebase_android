package com.open_mobile_kit.android.codebase.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.open_mobile_kit.android.codebase.example.di.SingletonModule
import com.open_mobile_kit.android.codebase.example.features.counter.CounterRoute
import com.open_mobile_kit.android.codebase.example.features.counter.CounterScreen
import com.open_mobile_kit.android.codebase.example.features.home.HomeRoute
import com.open_mobile_kit.android.codebase.example.features.home.HomeScreen
import com.open_mobile_kit.android.codebase.example.ui.theme.CodebaseTheme
import com.open_mobile_kit.android.codebase.presentation.navigation.AppNavHost
import com.open_mobile_kit.android.codebase.presentation.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(
                        navigationManager = navigationManager
                    )
                }
            }
        }
    }
}

@Composable
fun App(
    navigationManager: NavigationManager = SingletonModule().provideNavigationManager()
) {
    val navController = rememberNavController()

    AppNavHost(
        navController = navController,
        startDestination = HomeRoute.route,
        navigationManager = navigationManager,
    ) {
        composable(HomeRoute.route) {
            HomeScreen()
        }
        composable(CounterRoute.route) {
            CounterScreen()
        }
    }
}
