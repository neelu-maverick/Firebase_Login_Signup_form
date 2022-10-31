package com.example.firebase_login_signup_form.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

@Dao
interface PetDataDao {

    @Query("SELECT * FROM PetNames")
     suspend fun getAllNames():List<PetsHelper>
}