package com.example.ewallet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.ewallet.data.MyDatabase

@Composable
fun MyCardsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val db = MyDatabase.getInstance(context)
    val userDao = db.cardDao()
}

@Composable
fun CardTemplate() {

}