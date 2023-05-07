package com.example.ewallet.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey val cardId: Int,
    var cardName: String,
    var cardNumber: String,
    var expDate: Int,
    var expMonth: Int,
    var expYear: Int,
    var res: Int,
    val userId: Int
)