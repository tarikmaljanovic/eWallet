package com.example.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val ubuntuFont = FontFamily(
    Font(R.font.ubuntu_font)
)

@Composable
fun MainMenuScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier
            .fillMaxSize()
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = Color(0xFFCD0000)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome: <FULL NAME>",
                fontSize = 25.sp,
                color = Color.White,
                fontFamily = ubuntuFont
            )
        }
        Column(
            modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MenuButton(optionName = "My Cards", destination = { navController.navigate("MyCards") })
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(optionName = "Transaction History", destination = { navController.navigate("TransactionHistory") })
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(optionName = "New Transaction", destination = { navController.navigate("NewTransaction") })
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(optionName = "Monthly Balance Sheet", destination = { navController.navigate("BalanceSheet") })
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(optionName = "Logout", destination = { navController.navigate("Login") })
        }
    }
}

@Composable
fun MenuButton(modifier: Modifier = Modifier, optionName: String, destination: () -> Unit) {
    Button(
        onClick = destination,
        shape = CutCornerShape(10),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000)),
        modifier = modifier
            .width(300.dp)
            .shadow(10.dp)
    ) {
        Text(optionName, fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
    }
}
