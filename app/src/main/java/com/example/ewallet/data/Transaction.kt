package com.example.ewallet.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey (autoGenerate = true) val tranId: Int = 0,
    var date: Int,
    var month: Int,
    var year: Int,
    var hour: Int,
    var minute: Int,
    var outcome: Double,
    var rec_cardId: String,
    var sen_cardId: String,
    var description: String
)