package com.example.firebase_login_signup_form.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PetNames")
data class PetsHelper(

    @PrimaryKey
    @ColumnInfo(name = "Name")
    val petsName: String,

    @ColumnInfo(name = "Image")
    val petsImage : Int
)
