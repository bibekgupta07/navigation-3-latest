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
fun Screen5(
    modifier: Modifier = Modifier,
//    onNavigateTo: (Dest) -> Unit
    navigateToScreen1: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 5 (Final Screen)")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
//            onNavigateTo(Dest.Screen1)
            navigateToScreen1()
        }) {
            Text("Reset to Screen 1")
        }
    }
}