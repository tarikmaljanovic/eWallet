package com.example.ewallet.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Insert
    fun insert(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE rec_cardId = :cardNumber")
    fun getTransactionsForCard(cardNumber: String): List<Transaction>

    @Query("SELECT * FROM transactions")
    fun get_all(): List<Transaction>

    @Query("SELECT * FROM transactions")
    fun get_all2(): LiveData<List<Transaction>>

}