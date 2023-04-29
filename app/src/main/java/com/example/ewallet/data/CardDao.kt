package com.example.ewallet.data

import androidx.room.*

@Dao
interface CardDao {
    @Insert
    suspend fun insert(card: Card)

    @Update
    suspend fun update(card: Card)

    @Delete
    fun delete(card: Card)

    @Query("SELECT * FROM cards WHERE userId = :userId")
    suspend fun getCardsForUser(userId: Int): List<Card>
}