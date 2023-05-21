package com.example.ewallet.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardDao {
    @Insert
    fun insert(card: Card)

    @Update
    fun update(card: Card)

    @Delete
    fun delete(card: Card)

    @Query("SELECT * FROM cards WHERE userId = :userId")
    fun getCardsForUser(userId: Int): List<Card>

    @Query("SELECT * FROM cards WHERE cardNumber = :cardNumber")
    fun getCardByNumber(cardNumber: String): Card?

    @Query("SELECT * FROM cards")
    fun get_all(): LiveData<List<Card>>
}