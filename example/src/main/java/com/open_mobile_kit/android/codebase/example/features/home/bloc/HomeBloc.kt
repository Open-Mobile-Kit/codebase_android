package com.open_mobile_kit.android.codebase.example.features.home.bloc

import android.util.Log
import com.open_mobile_kit.android.codebase.example.features.counter.CounterRoute
import com.open_mobile_kit.android.codebase.presentation.navigation.NavigationCommand
import com.open_mobile_kit.android.codebase.presentation.navigation.NavigationManager
import com.open_mobile_kit.android.codebase.presentation.screen.BaseBloc

class HomeBloc(
    private val navigationManager: NavigationManager
) : BaseBloc<HomeContract.Event, HomeContract.State, HomeContract.SideEffect>(
    HomeContract.State()
) {
    init {
        on(
            eventType = HomeContract.Event.OnCounterClick::class.java,
            handler = { _, _ ->
                Log.d("HomeBloc", ": Navigate")
                navigationManager.push(destination = CounterRoute.route)
            })
    }
}
