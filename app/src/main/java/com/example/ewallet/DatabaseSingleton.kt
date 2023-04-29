package com.example.ewallet

import android.content.Context
import androidx.room.Room
import com.example.ewallet.data.MyDatabase

object DatabaseSingleton {
    private var instance: MyDatabase? = null

    fun getInstance(context: Context): MyDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "my_database"
            ).build().also { instance = it }
        }
    }
}