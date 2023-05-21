package com.example.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.ewallet.data.Card
import com.example.ewallet.data.CardDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun TitleBanner(modifier: Modifier = Modifier, sectionId: Int, navController: NavHostController) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
            modifier
                .height(40.dp)
                .width(40.dp)
                .padding(5.dp)
                .clickable(onClick = { navController.navigate("MainMenu") }),
            tint = Color(0xFFFFFFFF),
        )
        Text(
            text = stringResource(id = sectionId),
            fontFamily = ubuntuFont,
            fontSize = 15.sp,
            color = Color.White,
            modifier = modifier.padding(10.dp)
        )
    }
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = CurrentUser.instance?.fullName ?: "<Full Name>",
            fontSize = 30.sp,
            fontFamily = ubuntuFont,
            color = Color.White,
            modifier = modifier.padding(10.dp)
        )
    }
}


@Composable
fun MyCardsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    cardDao: CardDao,
    scope: CoroutineScope
) {
    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )

    var userCards = listOf<Card>()

    fun getCards() {
        scope.launch {
            if(CurrentUser.instance != null) {
                userCards = cardDao.getCardsForUser(CurrentUser.instance!!.userId)
            }
        }
    }

    getCards()

    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = Color(0xFFCD0000)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleBanner(sectionId = R.string.myCards, navController = navController)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                items(userCards) { item ->
                    CardTemplate(card = item, cardDao = cardDao, navController = navController, scope = scope)
                }
            }
        }
        FloatingActionButton(
            onClick = { /* Handle button click */ },
            backgroundColor = Color.Red,
            modifier = Modifier
                .offset(x = 300.dp, y = -30.dp)
        ) {
            IconButton(
                onClick = {
                    navController.navigate("NewCard")
                },
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                )
            }
        }
    }
}


@Composable
fun CardTemplate(modifier: Modifier = Modifier, card: Card, cardDao: CardDao, navController: NavHostController, scope: CoroutineScope) {
    var openDialog by remember { mutableStateOf(false) }
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val calendar = Calendar.getInstance()


    fun onDismiss() {openDialog = true}

    if (openDialog) {
       Dialog(onDismissRequest = { onDismiss() }) {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center,
               modifier = modifier.clip(RoundedCornerShape(10.dp)).background(Color.White).verticalScroll(rememberScrollState())
               ) {
               Column (
                   horizontalAlignment = Alignment.Start,
                   modifier = modifier.background(Color.White).padding(10.dp)
               ) {
                   Text(
                       text = "Card Number:",
                       fontSize = 15.sp,
                       fontFamily = ubuntuFont,
                       textAlign = TextAlign.Center,
                       modifier = modifier.padding(5.dp),
                   )
                   TextField(
                       value = cardNumber,
                       placeholder = { Text("000000000000") },
                       keyboardOptions = KeyboardOptions.Default.copy(
                           keyboardType = KeyboardType.Text,
                           imeAction = ImeAction.Next
                       ),
                       keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                           FocusDirection.Down)}),
                       singleLine = true,
                       onValueChange = {cardNumber = it},
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
                   Spacer(modifier = modifier.height(5.dp).background(Color.White))
               }
               Column (
                   horizontalAlignment = Alignment.Start,
                   modifier = modifier.background(Color.White).padding(10.dp)
               ) {
                   Text(
                       text = "Card Name:",
                       fontSize = 15.sp,
                       fontFamily = ubuntuFont,
                       textAlign = TextAlign.Center,
                       modifier = modifier.padding(5.dp),
                   )
                   TextField(
                       value = cardName,
                       placeholder = { Text("Name") },
                       keyboardOptions = KeyboardOptions.Default.copy(
                           keyboardType = KeyboardType.Text,
                           imeAction = ImeAction.Next
                       ),
                       keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                           FocusDirection.Down)}),
                       singleLine = true,
                       onValueChange = {cardName = it},
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
                   Spacer(modifier = modifier.height(5.dp).background(Color.White))
               }
               Button(
                   onClick = {
                       scope.launch {
                           card.cardName = cardName
                           card.cardNumber = cardNumber
                           cardDao.update(card)
                           openDialog = false
                       }
                   },
                   shape = RoundedCornerShape(50),
                   colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000)),
                   modifier = modifier
                       .width(300.dp)
               ) {
                   Text("Save Chaneges", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
               }
               Spacer(modifier = modifier.height(5.dp).background(Color.White))
               Button(
                   onClick = {
                        scope.launch {
                            cardDao.delete(card)
                            openDialog = false
                        }
                       navController.navigate("MyCards")
                   },
                   shape = RoundedCornerShape(50),
                   colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000)),
                   modifier = modifier
                       .width(300.dp)
               ) {
                   Text("Delete Card", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
               }
               Spacer(modifier = modifier.height(5.dp).background(Color.White))
               Button(
                   onClick = {
                       openDialog = false
                  },
                   shape = RoundedCornerShape(50),
                   colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000)),
                   modifier = modifier
                       .width(300.dp)
               ) {
                   Text("Dismiss", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
               }
           }
       }
    }

    Card(
        modifier
            .width(350.dp)
            .height(180.dp)
            .clickable {
                openDialog = true
            },
        shape = RoundedCornerShape(10),
        backgroundColor = Color(0xFFFB6767)
        ) {
        Column() {
            Text(text = card.cardName, fontSize = 30.sp, fontFamily = ubuntuFont, color = Color.White, modifier = modifier.padding(start = 10.dp, top = 5.dp))
            Text(text = card.cardNumber, fontSize = 15.sp, fontFamily = ubuntuFont, color = Color.White, modifier = modifier.padding(start = 10.dp, bottom = 5.dp))
            Spacer(modifier = modifier.height(20.dp))
            Text(text = "${card.res} BAM", fontSize = 25.sp, fontFamily = ubuntuFont, color = Color.White, modifier = modifier.padding(start = 10.dp, bottom = 5.dp))
            Text(text = "Expires: ${card.expDate}.${card.expMonth}.${card.expYear}", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White, modifier = modifier.padding(start = 10.dp, bottom = 5.dp))
            if(card.expYear == calendar.get(Calendar.YEAR)) {
                Text(text = "Your card is expiring soon!",fontSize = 15.sp, fontFamily = ubuntuFont, color = Color.Yellow, modifier = modifier.padding(start = 10.dp, bottom = 5.dp) )
            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}