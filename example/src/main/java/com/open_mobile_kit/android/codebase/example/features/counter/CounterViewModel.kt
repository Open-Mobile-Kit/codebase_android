package com.open_mobile_kit.android.codebase.example.features.counter

import androidx.lifecycle.ViewModel
import com.open_mobile_kit.android.codebase.example.features.counter.bloc.CounterBloc
import com.open_mobile_kit.android.codebase.example.features.home.bloc.HomeBloc
import com.open_mobile_kit.android.codebase.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    navigationManager: NavigationManager
) : ViewModel() {
    val bloc = CounterBloc(navigationManager)
}
