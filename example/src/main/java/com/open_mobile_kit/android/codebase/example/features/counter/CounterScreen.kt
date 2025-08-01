package com.open_mobile_kit.android.codebase.example.features.counter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.open_mobile_kit.android.codebase.presentation.screen.StatefulScreen

@Composable
fun CounterScreen(bloc: CounterBloc) {
    val context = LocalContext.current
    StatefulScreen(
        bloc = bloc,
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
        Column(
            modifier = Modifier.fillMaxSize(),
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
