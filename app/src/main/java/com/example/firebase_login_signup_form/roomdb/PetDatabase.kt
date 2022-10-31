package com.example.firebase_login_signup_form.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

@Database(entities = [PetsHelper::class], version = 1, exportSchema = true)
abstract class PetDatabase : RoomDatabase() {

    abstract fun petDataDao(): PetDataDao

    companion object {
        @Volatile
        private var INSTANCE: PetDatabase? = null
        fun getDatabase(context: Context): PetDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        PetDatabase::class.java,
                        "petsDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}