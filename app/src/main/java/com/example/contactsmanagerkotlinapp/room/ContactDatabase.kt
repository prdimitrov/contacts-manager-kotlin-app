package com.example.contactsmanagerkotlinapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    //Singleton Design Pattern
    abstract val contactDAO: ContactDAO

    @Volatile
    private var INSTANCE: ContactDatabase? = null

    fun getInstance(context: Context): ContactDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ContactDatabase::class.java,
                "contacts_db"
            ).build()

            INSTANCE = instance
            return instance
        }
    }
}