package com.example.roomintentdb.Rooms

import androidx.room.*

@Dao
interface MhsDao {
    @Insert
    suspend fun addData(mhs: Mhs)

    @Update
    suspend fun updateData(mhs: Mhs)

    @Delete
    suspend fun deleteData(mhs: Mhs)

    @Query("SELECT * FROM mhs")
    suspend fun getData(): List<Mhs>

    @Query("SELECT * FROM mhs WHERE nim=:mhs_nim")
    suspend fun getOneData(mhs_nim: String): List<Mhs>
}