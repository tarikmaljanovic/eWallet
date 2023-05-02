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

    @Query("SELECT * FROM transactions WHERE userId = :userId")
    suspend fun getTransactionsForUser(userId: Int): List<Transaction>
}