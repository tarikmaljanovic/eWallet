package com.example.ewallet.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "transactions",
    primaryKeys = ["userId", "cardId"],
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"]),
        ForeignKey(entity = Card::class,
            parentColumns = ["cardId"],
            childColumns = ["cardId"])
    ]
)
data class Transaction(
    val dateAndTime: Int,
    val outcome: Int,
    val cardName: String,
    val cardId: Int,
    val userId: Int,
    val description: String
)