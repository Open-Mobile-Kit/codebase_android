package com.open_mobile_kit.android.codebase.example.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.open_mobile_kit.android.codebase.example.features.home.bloc.HomeContract
import com.open_mobile_kit.android.codebase.presentation.screen.StatefulScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    StatefulScreen(
        bloc = viewModel.bloc,
        onSideEffect = {}
    ) { _, onEvent ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onEvent(HomeContract.Event.OnCounterClick) }) {
                Text(text = "Go to Counter")
            }
        }
    }
}
