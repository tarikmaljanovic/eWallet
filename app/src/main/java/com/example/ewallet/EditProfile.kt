package com.example.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userDao: UserDao,
    scope: CoroutineScope
) {

    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )

    val focusManager = LocalFocusManager.current

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(Color(0xFFCD0000)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleBanner(sectionId = R.string.editProf, navController = navController)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = modifier.background(Color.White),
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
            Column(
                modifier = modifier.background(Color.White),
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
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)}),
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
            Column(
                modifier = modifier.background(Color.White),
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
                    placeholder = { Text("**********") },
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
            Button(
                onClick = {
                    scope.launch {
                        if (CurrentUser.instance != null) {
                            CurrentUser.instance!!.email = email
                            CurrentUser.instance!!.fullName = fullName
                            CurrentUser.instance!!.password = password


                            userDao.update(CurrentUser.instance!!)
                        }
                    }
                            navController.navigate("MainMenu")


                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000))
            ) {
                Text("Save Changes", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
            }
        }
    }
}