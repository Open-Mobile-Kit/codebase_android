package com.open_mobile_kit.android.codebase.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.open_mobile_kit.android.codebase.example.features.counter.CounterBloc
import com.open_mobile_kit.android.codebase.example.features.counter.CounterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val counterBloc = CounterBloc()
            CounterScreen(bloc = counterBloc)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CounterScreen(bloc = CounterBloc())
}
