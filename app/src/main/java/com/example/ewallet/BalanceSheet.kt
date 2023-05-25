package com.example.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ewallet.data.Card
import com.example.ewallet.data.CardDao
import com.example.ewallet.data.Transaction
import com.example.ewallet.data.TransactionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun BalanceSheetScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    cardDao: CardDao,
    trnDao: TransactionDao,
    scope: CoroutineScope
) {

    var totalIncome: Double = 0.0
    var totalOutcome: Double = 0.0
    var profitMargin: Double = 0.0
    val months = mapOf(
        1 to "January",
        2 to "February",
        3 to "March",
        4 to "April",
        5 to "May",
        6 to "June",
        7 to "July",
        8 to "August",
        9 to "September",
        10 to "October",
        11 to "November",
        12 to "December"
    )

    val calendar = Calendar.getInstance()


    var cards = listOf<Card>()
    var transactions = listOf<Transaction>()

    fun calculate() {
        scope.launch {
            if (CurrentUser.instance != null) {
                cards = cardDao.getCardsForUser(CurrentUser.instance!!.userId)
                transactions = trnDao.get_all()

                for (card in cards) {
                    for (tran in transactions) {
                        if (tran.sen_cardId == card.cardNumber) {
                            totalOutcome += tran.outcome
                        } else if (tran.rec_cardId == card.cardNumber) {
                            totalIncome += tran.outcome
                        }
                    }
                }
                profitMargin = ((totalIncome - totalOutcome) / totalIncome) * 100
            }
        }
    }
    calculate()


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
                        text = "$profitMargin%",
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
                    months[calendar.get(Calendar.MONTH)]?.let {
                        Text(
                            text = it,
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
}

