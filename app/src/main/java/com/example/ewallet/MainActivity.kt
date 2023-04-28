package com.example.ewallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ewallet.ui.theme.EWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EWalletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable("MainMenu") { MainMenuScreen(navController = navController) }
        composable("Login") { LoginScreen(navController = navController) }
        composable("Register") { RegisterScreen(navController = navController) }
        composable("BalanceSheet") { BalanceSheetScreen(navController = navController) }
        composable("MyCards") { MyCardsScreen(navController = navController) }
        composable("NewCard") { NewCardScreen(navController = navController) }
        composable("NewTransaction") { NewTransactionScreen(navController = navController) }
        composable("TransactionHistory") { TransactionHistoryScreen(navController = navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EWalletTheme {
        MainScreen()
    }
}