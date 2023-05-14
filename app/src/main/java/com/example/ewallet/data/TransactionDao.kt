package com.example.ewallet.data

import androidx.room.*

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE rec_cardId = :cardNumber")
    suspend fun getTransactionsForCard(cardNumber: String): List<Transaction>

    @Query("SELECT * FROM transactions")
    suspend fun get_all(): List<Transaction>

}