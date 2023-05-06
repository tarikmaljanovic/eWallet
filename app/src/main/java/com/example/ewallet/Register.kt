package com.example.ewallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ewallet.data.MyDatabase
import com.example.ewallet.data.User
import com.example.ewallet.ui.theme.EWalletTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val db = MyDatabase.getInstance(context)
    val userDao = db.userDao()

    val myScope = CoroutineScope(Dispatchers.IO)

    Column (
        modifier.fillMaxSize(),
    ) {
        Column (
            modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = Color(0xFFCD0000)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.welcoming), fontSize = 25.sp, color = Color.White, fontFamily = ubuntuFont)
        }
        Spacer(modifier = modifier.height(50.dp))
        Column (
            modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column (
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Full Name:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = fullName,
                    placeholder = { Text("Name Surname") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)}),
                    singleLine = true,
                    onValueChange = {fullName = it},
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(50.dp)
                        .width(300.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent, // removes underline when focused
                        unfocusedIndicatorColor = Color.Transparent, // removes underline when unfocused
                        disabledIndicatorColor = Color.Transparent // removes underline when disabled
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Email:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = email,
                    placeholder = { Text("example@email.com") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down)}),
                    singleLine = true,
                    onValueChange = {email = it},
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(50.dp)
                        .width(300.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent, // removes underline when focused
                        unfocusedIndicatorColor = Color.Transparent, // removes underline when unfocused
                        disabledIndicatorColor = Color.Transparent // removes underline when disabled
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Password:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = password,
                    placeholder = { Text("Password") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.clearFocus()}),
                    singleLine = true,
                    onValueChange = {password = it},
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(50.dp)
                        .width(300.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent, // removes underline when focused
                        unfocusedIndicatorColor = Color.Transparent, // removes underline when unfocused
                        disabledIndicatorColor = Color.Transparent // removes underline when disabled
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    myScope.launch {
                        if(fullName != null && email != null && password != null) {
                            userDao.insert(User(fullName = fullName, email = email, password = password))
                        }
                    }
                    navController.navigate("Login")
                },
                shape = CutCornerShape(10),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000))
            ) {
                Text("Register", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
            }
        }
    }
}