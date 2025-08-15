package dev.himanshu.recipes

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.himanshu.recipes.ui.theme.RecipesTheme
import dev.himanshu.recipes.utils.AppNavigatorNew

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets.systemBars
                ) { innerPadding ->
                    AppNavigatorNew(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
