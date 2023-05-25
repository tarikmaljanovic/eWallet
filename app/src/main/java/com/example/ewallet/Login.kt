package com.example.ewallet


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ewallet.data.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userDao: UserDao,
    scope: CoroutineScope
) {
    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )

    var wrongInfo by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
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
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if(wrongInfo) {
                Text(text = "There is no user with such credentials!", fontSize = 15.sp, fontFamily = ubuntuFont, color = Color(0xFFCD0000))
            }
            Spacer(modifier = modifier.height(35.dp))
            Column (
                horizontalAlignment = Alignment.Start,
                modifier = modifier.background(Color.White)
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
                        .height(55.dp)
                        .width(300.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent, // removes underline when focused
                        unfocusedIndicatorColor = Color.Transparent, // removes underline when unfocused
                        disabledIndicatorColor = Color.Transparent, // removes underline when disabled
                        backgroundColor = Color(0xFFD9D9D9)
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                horizontalAlignment = Alignment.Start,
                modifier = modifier.background(Color.White)
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
                        .height(55.dp)
                        .width(300.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent, // removes underline when focused
                        unfocusedIndicatorColor = Color.Transparent, // removes underline when unfocused
                        disabledIndicatorColor = Color.Transparent, // removes underline when disabled
                        backgroundColor = Color(0xFFD9D9D9)
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = modifier.background(Color.White)
            ) {
                Button(
                    onClick = {
                            scope.launch {
                                CurrentUser.instance = userDao.getUserByCredentials(email = email, password = password) ?: null
                            }
                            if(CurrentUser.instance == null) {
                                wrongInfo = true
                            }
                            if(CurrentUser.instance != null) {
                                wrongInfo = false
                                navController.navigate("MainMenu")
                            }

                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000))
                ) {
                    Text("Log in", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
                }
                Spacer(modifier = modifier.width(10.dp))
                Button(
                    onClick = {
                        navController.navigate("Register")
                    },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Red),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFCD0000), backgroundColor = Color.White),

                ) {
                    Text("Register", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color(0xFFCD0000))
                }
            }
        }
    }
}
