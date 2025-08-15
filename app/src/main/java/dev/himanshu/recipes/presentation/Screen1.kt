package dev.himanshu.recipes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.himanshu.recipes.utils.Dest

@Composable
fun Screen1(
    modifier: Modifier = Modifier,
//    onNavigateTo: (Dest) -> Unit,
    navigateToScreen2: (String) -> Unit,
    navigateToScreen5: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 1")
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
//                onNavigateTo(Dest.Screen2("Hello from Screen 1"))
                navigateToScreen2("Hello from Screen 1")

            }) {
            Text("Go to Screen 2")
        }
        Button(
            onClick = {
//                onNavigateTo(Dest.Screen5)
                navigateToScreen5()
            }) {
            Text("Go directly to Screen 5")
        }
    }
}