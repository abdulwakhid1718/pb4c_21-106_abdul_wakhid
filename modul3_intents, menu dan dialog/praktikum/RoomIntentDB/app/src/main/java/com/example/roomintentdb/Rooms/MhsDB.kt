package com.example.roomintentdb.Rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Mhs::class],
    version = 1
)

abstract class MhsDB : RoomDatabase() {
    abstract fun dataDao() : MhsDao

    companion object {

        @Volatile private var instance : MhsDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MhsDB::class.java,
            "mhs12345.db"
        ).build()

    }
}