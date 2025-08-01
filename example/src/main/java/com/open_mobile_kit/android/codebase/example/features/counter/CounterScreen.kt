package com.open_mobile_kit.android.codebase.example.features.counter

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.open_mobile_kit.android.codebase.example.features.counter.bloc.CounterBloc
import com.open_mobile_kit.android.codebase.example.features.counter.bloc.CounterContract
import com.open_mobile_kit.android.codebase.example.features.home.HomeViewModel
import com.open_mobile_kit.android.codebase.presentation.screen.StatefulScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen( viewModel: CounterViewModel = hiltViewModel()) {
    val context = LocalContext.current
    StatefulScreen(
        bloc = viewModel.bloc,
        onSideEffect = { sideEffect ->
            // Handle side effects here, e.g., show a toast

            when (sideEffect) {
                is CounterContract.SideEffect.ShowToast -> {

                    // Show the toast

                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    ) { state, onEvent ->

        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(text = "Counter") },
                    navigationIcon = {
                        Button(onClick = { onEvent(CounterContract.Event.OnBack) }) {
                            Text(text = "Back")
                        }

                    }
                )
            }
        ) {
            paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Count: ${state.count}")
                Row {
                    Button(onClick = { onEvent(CounterContract.Event.OnIncrement) }) {
                        Text(text = "Increment")
                    }
                    Button(onClick = { onEvent(CounterContract.Event.OnDecrement) }) {
                        Text(text = "Decrement")
                    }
                }
            }
        }
    }
}
