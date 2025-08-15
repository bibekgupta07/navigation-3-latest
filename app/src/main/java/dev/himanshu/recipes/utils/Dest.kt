package dev.himanshu.recipes.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.himanshu.recipes.presentation.Screen1
import dev.himanshu.recipes.presentation.Screen2
import dev.himanshu.recipes.presentation.Screen3
import dev.himanshu.recipes.presentation.Screen4
import dev.himanshu.recipes.presentation.Screen5

interface AppNavigatorInterface {
    fun navigateToScreen1()
    fun navigateToScreen2(message: String)
    fun navigateToScreen3(userId: Int, userName: String)
    fun navigateToScreen4(itemId: String)
    fun navigateToScreen5()
    fun goBack()
    fun resetToScreen1()
}

class AppCoordinator(private val navigator: Navigator) : AppNavigatorInterface {
    override fun navigateToScreen1() {
        navigator.navigateTo(Dest.Screen1)
    }

    override fun navigateToScreen2(message: String) {
        navigator.navigateTo(Dest.Screen2(message))
    }

    override fun navigateToScreen3(userId: Int, userName: String) {
        navigator.navigateTo(Dest.Screen3(userId, userName))
    }

    override fun navigateToScreen4(itemId: String) {
        navigator.navigateTo(Dest.Screen4(itemId))
    }

    override fun navigateToScreen5() {
        navigator.navigateTo(Dest.Screen5)
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun goBack() {
        navigator.goBack()
    }

    override fun resetToScreen1() {
        navigator.resetTo(Dest.Screen1)
    }
}


sealed interface Dest {
    data object Screen1 : Dest
    data class Screen2(val message: String) : Dest
    data class Screen3(val userId: Int, val userName: String) : Dest
    data class Screen4(val itemId: String) : Dest
    data object Screen5 : Dest
}

class Navigator {
    private val _backStack = mutableStateListOf<Any>(Dest.Screen1)
    val backStack: List<Any> = _backStack

    fun navigateTo(destination: Dest) {
        _backStack.add(destination)

    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun goBack() {
        if (_backStack.size > 1) _backStack.removeLast()
    }

    fun resetTo(destination: Dest) {
        _backStack.clear()
        _backStack.add(destination)
    }
}


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun AppNavigator(modifier: Modifier = Modifier) {
    val navigator = remember { Navigator() }
    val coordinator = remember { AppCoordinator(navigator) }

    NavDisplay(
        backStack = navigator.backStack,
        onBack = { coordinator.goBack() },
        entryProvider = entryProvider {
            entry<Dest.Screen1> { key ->
                Screen1(
                    modifier = Modifier.fillMaxSize(),
//                    onNavigateTo = navigator::navigateTo,
                    navigateToScreen2 = coordinator::navigateToScreen2,
                    navigateToScreen5 = coordinator::navigateToScreen5
                )
            }
            entry<Dest.Screen2> { key ->
                Screen2(
                    modifier = Modifier.fillMaxSize(),
                    message = key.message,
//                    onNavigateTo = navigator::navigateTo,
                    navigateToScreen3 = coordinator::navigateToScreen3,
                   navigateToScreen5 = coordinator::navigateToScreen5
                )
            }
            entry<Dest.Screen3> { key ->
                Screen3(
                    modifier = Modifier.fillMaxSize(),
                    userId = key.userId,
                    userName = key.userName,
//                    onNavigateTo = navigator::navigateTo,
                    navigateToScreen4 = coordinator::navigateToScreen4
                )
            }
            entry<Dest.Screen4> { key ->
                Screen4(
                    modifier = Modifier.fillMaxSize(),
                    itemId = key.itemId,
//                    onNavigateTo = navigator::navigateTo,
                    navigateToScreen5 = coordinator::navigateToScreen5
                )
            }
            entry<Dest.Screen5> { key ->
                Screen5(
                    modifier = Modifier.fillMaxSize(),
//                    onNavigateTo = navigator::navigateTo,
                    navigateToScreen1 = coordinator::navigateToScreen1
                )
            }
        }
    )
}