package com.example.contactsmanagerkotlinapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact): Int

    @Delete
    suspend fun deleteContact(contact: Contact): Int

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContacts()

    @Query("SELECT * FROM contacts_table")
    fun getAllContactsInDB(): LiveData<List<Contact>>
}