package com.example.ewallet.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "transactions",
    primaryKeys = ["userId", "rec_cardId"],
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"]),
        ForeignKey(entity = Card::class,
            parentColumns = ["cardId"],
            childColumns = ["rec_cardId"]),
        ForeignKey(entity = Card::class,
            parentColumns = ["cardId"],
            childColumns = ["sen_cardId"]),
    ]
)
data class Transaction(
    val date: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int,
    val outcome: Double,
    val rec_cardId: String,
    val sen_cardId: String,
    val userId: Int,
    val description: String
)