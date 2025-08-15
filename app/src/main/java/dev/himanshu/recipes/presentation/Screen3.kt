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
fun Screen3(
    modifier: Modifier = Modifier,
    userId: Int,
    userName: String,
//    onNavigateTo: (Dest) -> Unit,
    navigateToScreen4: (String) -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 3 - User: $userId, Name: $userName")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
//            onNavigateTo(Dest.Screen4("item_456"))
            navigateToScreen4("item_456")
        }) {
            Text("Go to Screen 4")
        }
    }
}