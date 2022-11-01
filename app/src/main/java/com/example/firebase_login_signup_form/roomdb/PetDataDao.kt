package com.example.firebase_login_signup_form.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

@Dao
interface PetDataDao {
//interfaces only contain method declaration
  //  @Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(petsHelper: PetsHelper)

    @Query("SELECT * FROM PetNames")
     suspend fun getAllNames():List<PetsHelper>


    @Query("SELECT * FROM PetNames")
    suspend fun getBirdsNames():List<PetsHelper>
}

