package com.open_mobile_kit.android.codebase.presentation.navigation

import android.os.Bundle

sealed class NavigationCommand {
    object Pop : NavigationCommand()

    class Push(
        val destination: String,
        val args: Bundle? = null
    ) : NavigationCommand()

    class Go(
        val destination: String,
        val args: Bundle? = null
    ) : NavigationCommand()

    class Replace(
        val destination: String,
        val args: Bundle? = null
    ) : NavigationCommand()
}
