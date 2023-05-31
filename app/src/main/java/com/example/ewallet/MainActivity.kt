package com.example.ewallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ewallet.data.MyDatabase
import com.example.ewallet.ui.theme.EWalletTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = MyDatabase.getInstance(this)
            EWalletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(db = db)
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, db: MyDatabase) {
    val viewModelStore = ViewModelStore()

    fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }

    val myScope = CoroutineScope(Dispatchers.Default)
    val userDao = db.userDao()
    val cardDao = db.cardDao()
    val trnDao = db.transactionDao()
    


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable("MainMenu") { MainMenuScreen(navController = navController) }
        composable("Login") { LoginScreen(navController = navController, userDao = userDao, scope = myScope) }
        composable("TransactionHistory") { TransactionHistoryScreen(navController = navController, cardDao = cardDao, trnsDao = trnDao, scope = myScope)}
        composable("Register") { RegisterScreen(navController = navController, userDao = userDao, scope = myScope) }
        composable("BalanceSheet") { BalanceSheetScreen(navController = navController, cardDao = cardDao, trnDao = trnDao, scope = myScope ) }
        composable("MyCards") { MyCardsScreen(navController = navController, cardDao = cardDao, scope = myScope) }
        composable("NewCard") { NewCardScreen(navController = navController, cardDao = cardDao, scope = myScope) }
        composable("NewTransaction") { NewTransactionScreen(navController = navController, trnDao = trnDao, cardDao = cardDao, scope = myScope) }
        composable("EditProfile") { EditProfileScreen(navController = navController, userDao = userDao, scope = myScope)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EWalletTheme {
        val db = MyDatabase.getInstance(LocalContext.current)
        MainScreen(db = db)
    }
}