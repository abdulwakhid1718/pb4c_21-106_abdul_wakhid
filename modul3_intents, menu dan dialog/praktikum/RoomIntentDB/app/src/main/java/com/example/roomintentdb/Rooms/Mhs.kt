package com.example.roomintentdb.Rooms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mhs(
    @PrimaryKey
    val nim: String,
    val nama: String,
    val prodi: String,
    val alamat: String
)
