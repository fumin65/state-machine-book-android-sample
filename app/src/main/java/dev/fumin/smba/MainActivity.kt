package dev.fumin.smba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.fumin.smba.ui.login.LoginPage
import dev.fumin.smba.ui.theme.StatemachinebookandroidsampleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StatemachinebookandroidsampleTheme {
                Routes()
            }
        }
    }
}

@Composable
fun Routes(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginPage {
                navController.navigate("home") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            }
        }
        composable("home") {
            Text(text = "Home")
        }
    }
}
