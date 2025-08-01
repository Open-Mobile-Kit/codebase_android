package com.open_mobile_kit.android.codebase.presentation.navigation

interface NavigationGuard {
    suspend fun canNavigate(): NavigationCommand?
}
