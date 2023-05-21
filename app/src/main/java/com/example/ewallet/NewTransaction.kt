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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ewallet.data.Card
import com.example.ewallet.data.CardDao
import com.example.ewallet.data.Transaction
import com.example.ewallet.data.TransactionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun NewTransactionScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    cardDao: CardDao,
    trnDao: TransactionDao,
    scope: CoroutineScope
) {
    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )

    val focusManager = LocalFocusManager.current

    var rec_card by remember { mutableStateOf("") }
    var sen_card by remember { mutableStateOf("") }
    var sending_amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
            TitleBanner(sectionId = R.string.newTrn, navController = navController)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column (
                horizontalAlignment = Alignment.Start,
                modifier = modifier.background(Color.White)
            ) {
                Text(
                    text = "Number of Receiving Account:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = rec_card,
                    placeholder = { Text("000000000000") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)}),
                    singleLine = true,
                    onValueChange = {rec_card = it},
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
            Spacer(modifier = modifier
                .height(10.dp)
                .background(Color.White))
            Column (
                horizontalAlignment = Alignment.Start,
                modifier = modifier.background(Color.White)
            ) {
                Text(
                    text = "Number of Sending Account:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = sen_card,
                    placeholder = { Text("000000000000") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)}),
                    singleLine = true,
                    onValueChange = {sen_card = it},
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
            Spacer(modifier = modifier
                .height(10.dp)
                .background(Color.White))
            Column (
                horizontalAlignment = Alignment.Start,
                modifier = modifier.background(Color.White)
            ) {
                Text(
                    text = "Amount:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = sending_amount,
                    placeholder = { Text("0.0 BAM") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)}),
                    singleLine = true,
                    onValueChange = {sending_amount = it},
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
            Spacer(modifier = modifier
                .height(10.dp)
                .background(Color.White))
            Column (
                horizontalAlignment = Alignment.Start,
                modifier = modifier.background(Color.White)
            ) {
                Text(
                    text = "Description:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                TextField(
                    value = description,
                    placeholder = { Text("Short Description") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.clearFocus()}),
                    singleLine = true,
                    onValueChange = {description = it},
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

            Spacer(modifier = modifier
                .height(10.dp)
                .background(Color.White))
            Button(
                onClick = {
                    scope.launch {
                        val sender: Card? = cardDao.getCardByNumber(sen_card)
                        val receiver: Card? = cardDao.getCardByNumber(rec_card)


                        if(sender != null && receiver != null && sending_amount.toDouble() > 0 && CurrentUser.instance != null) {
                            val calendar = Calendar.getInstance()

                            var newTran = Transaction(
                                date = calendar.get(Calendar.DATE),
                                month = calendar.get(Calendar.MONTH),
                                year = calendar.get(Calendar.YEAR),
                                hour = calendar.get(Calendar.HOUR),
                                minute = calendar.get(Calendar.MINUTE),
                                description = description,
                                outcome = sending_amount.toDouble(),
                                rec_cardId = rec_card,
                                sen_cardId = sen_card
                            )

                            trnDao.insert(newTran)

                            sender.res -= sending_amount.toDouble()
                            receiver.res += sending_amount.toDouble()

                            cardDao.update(sender)
                            cardDao.update(receiver)
                        }
                    }
                    navController.navigate("TransactionHistory")
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD0000))
            ) {
                Text("Make Transaction", fontSize = 20.sp, fontFamily = ubuntuFont, color = Color.White)
            }
        }
    }
}