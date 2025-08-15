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
fun Screen2(
    modifier: Modifier = Modifier,
    message: String,
//    onNavigateTo: (Dest) -> Unit
    navigateToScreen3: (Int, String) -> Unit,
    navigateToScreen5: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 2 - Message: $message")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
//            onNavigateTo(Dest.Screen3(123, "Himanshu"))
            navigateToScreen3(123, "Himanshu")
        }) {
            Text("Go to Screen 3")
        }
        Button(onClick = {
//            onNavigateTo(Dest.Screen5)
            navigateToScreen5()
        }) {
            Text("Go to Screen 5")
        }
    }
}