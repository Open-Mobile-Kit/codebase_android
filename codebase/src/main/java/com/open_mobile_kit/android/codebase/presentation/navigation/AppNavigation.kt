package com.open_mobile_kit.android.codebase.presentation.navigation

import android.os.Bundle

interface AppNavigation {
    suspend fun go(
        destination: String,
        args: Bundle? = null,
        guards: List<NavigationGuard> = emptyList()
    )

    suspend fun push(
        destination: String,
        args: Bundle? = null,
        guards: List<NavigationGuard> = emptyList()
    )

    suspend fun replace(
        destination: String,
        args: Bundle? = null,
        guards: List<NavigationGuard> = emptyList()
    )

    suspend fun pop()
}
