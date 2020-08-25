package com.sort.feriaapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val first_name: String,
    val last_name: String,
    val career: String,
    val year: Int,
    val email: String,
    val password: String
)