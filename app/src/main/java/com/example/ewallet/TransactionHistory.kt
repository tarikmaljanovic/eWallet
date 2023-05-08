package com.example.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ewallet.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
fun TransactionHistoryScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )

    val myScope = CoroutineScope(Dispatchers.IO)

    val context = LocalContext.current
    val db = MyDatabase.getInstance(context)
    val trnsDao = db.transactionDao()
    val cardDao = db.cardDao()

    var cards = listOf<Card>()

    fun getCards() {
        myScope.launch {
            if(CurrentUser.instance != null) {
                cards = cardDao.getCardsForUser(CurrentUser.instance!!.userId)
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
            TitleBanner(sectionId = R.string.trnHistory, navController = navController)
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
            LazyColumn{
                items(cards) {
                    item -> TranTemplate(card = item, cardDao = cardDao, trnsDao = trnsDao)
                }
            }
        }
    }
}

@Composable
fun TranTemplate(modifier: Modifier = Modifier, card: Card, cardDao: CardDao, trnsDao: TransactionDao) {
    val ubuntuFont = FontFamily(
        Font(R.font.ubuntu_font)
    )
    val myScope = CoroutineScope(Dispatchers.IO)
    var transactions = listOf<Transaction>()

    fun getTrns() {
        myScope.launch {
            transactions = trnsDao.getTransactionsForCard(card.cardId)
        }
    }

    getTrns()

    if(transactions.isNotEmpty()) {
        LazyColumn {
            items(transactions) { item ->
                Card(
                    modifier
                        .width(350.dp)
                        .height(150.dp),
                    shape = RoundedCornerShape(10),
                    backgroundColor = Color(0xFFFB6767)
                ) {
                    Column() {
                       Text(text = "${item.date}.${item.month}.${item.year} ${item.hour}:${item.minute}", fontFamily = ubuntuFont, fontSize = 20.sp, color = Color.White, modifier = modifier.padding(start = 10.dp, top = 5.dp))
                        if(item.sen_cardId == card.cardNumber) {
                            Text(text = "-${item.outcome}", fontFamily = ubuntuFont, fontSize = 20.sp, color = Color.White, modifier = modifier.padding(start = 10.dp, top = 5.dp))
                        } else {
                            Text(text = "+${item.outcome}", fontFamily = ubuntuFont, fontSize = 20.sp, color = Color.White, modifier = modifier.padding(start = 10.dp, top = 5.dp))
                        }
                        Text(text = card.cardName, fontFamily = ubuntuFont, fontSize = 20.sp, color = Color.White, modifier = modifier.padding(start = 10.dp, top = 5.dp))
                        Text(text = card.cardNumber, fontFamily = ubuntuFont, fontSize = 10.sp, color = Color.White, modifier = modifier.padding(start = 10.dp, top = 5.dp))
                        Text(text = item.description, fontFamily = ubuntuFont, fontSize = 20.sp, color = Color.White, modifier = modifier.padding(start = 10.dp, bottom = 5.dp))
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

}
