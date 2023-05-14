package com.example.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ewallet.data.Card
import com.example.ewallet.data.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Composable
fun BalanceSheetScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    var totalIncome: Int = 0
    var totalOutcome: Int = 0
    var profitMargin: Double = 0.0
    var maxIncomeCard: String = "Card Name"
    var maxOutcomeCard: String = "Card Name"
    var month: String = "Month"

    val focusManager = LocalFocusManager.current
    val myScope = CoroutineScope(Dispatchers.IO)

    val context = LocalContext.current
    val db = MyDatabase.getInstance(context)


    Column(
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
            TitleBanner(sectionId = R.string.balSheet, navController = navController)
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
                    text = "Total Monthly income:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(55.dp)
                        .width(300.dp)
                        .background(Color(0xFFD9D9D9)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = totalIncome.toString(),
                        fontSize = 15.sp,
                        fontFamily = ubuntuFont,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(15.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier.background(Color.White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Total Monthly outcome:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(55.dp)
                        .width(300.dp)
                        .background(Color(0xFFD9D9D9)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = totalOutcome.toString(),
                        fontSize = 15.sp,
                        fontFamily = ubuntuFont,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(15.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier.background(Color.White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Profit margin:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(55.dp)
                        .width(300.dp)
                        .background(Color(0xFFD9D9D9)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = profitMargin.toString(),
                        fontSize = 15.sp,
                        fontFamily = ubuntuFont,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(15.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier.background(Color.White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Card with most income:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(55.dp)
                        .width(300.dp)
                        .background(Color(0xFFD9D9D9)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = maxIncomeCard,
                        fontSize = 15.sp,
                        fontFamily = ubuntuFont,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(15.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier.background(Color.White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Card with most outcome:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(55.dp)
                        .width(300.dp)
                        .background(Color(0xFFD9D9D9)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = maxOutcomeCard.toString(),
                        fontSize = 15.sp,
                        fontFamily = ubuntuFont,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(15.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier.background(Color.White),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Month:",
                    fontSize = 15.sp,
                    fontFamily = ubuntuFont,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(5.dp),
                )
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(55.dp)
                        .width(300.dp)
                        .background(Color(0xFFD9D9D9)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = month,
                        fontSize = 15.sp,
                        fontFamily = ubuntuFont,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(15.dp),
                    )
                }
            }
        }
    }
}

