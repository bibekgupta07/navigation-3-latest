package dev.himanshu.recipes.utils

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
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable

// 1) nav keys — @Serializable + NavKey
@Serializable
data object KScreen1 : NavKey

@Serializable
data class KScreen2(val message: String) : NavKey

@Serializable
data class KScreen3(val userId: Int, val userName: String) : NavKey

@Serializable
data class KScreen4(val itemId: String) : NavKey

@Serializable
data object KScreen5 : NavKey


@Composable
fun AppNavigatorNew(modifier: Modifier = Modifier) {
    // 2) create the saveable back stack — pass initial key as positional parameter
    val backStack = rememberNavBackStack(KScreen1)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        // onBack should remove last entry if there is more than one entry
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = entryProvider {
            // IMPORTANT: the lambda param for entry<K>() is the actual key instance
            entry<KScreen1> {
                Screen1(
                    modifier = Modifier.fillMaxSize(),
                    navigateToScreen2 = { msg -> backStack.add(KScreen2(msg)) },
                    navigateToScreen5 = { backStack.add(KScreen5) }
                )
            }

            // note: lambda param 'key' is the typed key (KScreen2)
            entry<KScreen2> { key ->
                Screen2(
                    modifier = Modifier.fillMaxSize(),
                    message = key.message,
                    navigateToScreen3 = { id, name -> backStack.add(KScreen3(id, name)) },
                    navigateToScreen5 = { backStack.add(KScreen5) }
                )
            }

            entry<KScreen3> { key ->
                Screen3(
                    modifier = Modifier.fillMaxSize(),
                    userId = key.userId,
                    userName = key.userName,
                    navigateToScreen4 = { itemId -> backStack.add(KScreen4(itemId)) }
                )
            }

            entry<KScreen4> { key ->
                Screen4(
                    modifier = Modifier.fillMaxSize(),
                    itemId = key.itemId,
                    navigateToScreen5 = { backStack.add(KScreen5) }
                )
            }

            entry<KScreen5> {
                Screen5(
                    modifier = Modifier.fillMaxSize(),
                    navigateToScreen1 = {
                        // replace the stack with a single KScreen1 root:
                        backStack.clear()
                        backStack.add(KScreen1)
                    }
                )
            }
        }
    )
}




@Composable
fun Screen1(
    modifier: Modifier = Modifier,
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
        Button(onClick = { navigateToScreen2("Hello from Screen 1") }) {
            Text("Go to Screen 2")
        }
        Button(onClick = { navigateToScreen5() }) {
            Text("Go directly to Screen 5")
        }
    }
}

@Composable
fun Screen2(
    modifier: Modifier = Modifier,
    message: String,
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
        Button(onClick = { navigateToScreen3(123, "Himanshu") }) {
            Text("Go to Screen 3")
        }
        Button(onClick = { navigateToScreen5() }) {
            Text("Go to Screen 5")
        }
    }
}

@Composable
fun Screen3(
    modifier: Modifier = Modifier,
    userId: Int,
    userName: String,
    navigateToScreen4: (String) -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 3 - User: $userId, Name: $userName")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navigateToScreen4("item_456") }) {
            Text("Go to Screen 4")
        }
    }
}

@Composable
fun Screen4(
    modifier: Modifier = Modifier,
    itemId: String,
    navigateToScreen5: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 4 - Item ID: $itemId")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navigateToScreen5() }) {
            Text("Go to Screen 5")
        }
    }
}

@Composable
fun Screen5(
    modifier: Modifier = Modifier,
    navigateToScreen1: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 5 (Final Screen)")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navigateToScreen1() }) {
            Text("Reset to Screen 1")
        }
    }
}