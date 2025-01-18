package com.example.contactsmanagerkotlinapp.repository

import com.example.contactsmanagerkotlinapp.room.Contact
import com.example.contactsmanagerkotlinapp.room.ContactDAO

// Acts as a bridge between the ViewModel and Data Source
class ContactRepository(private val contactDAO: ContactDAO) {
    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contact): Long {
        return contactDAO.insertContact(contact)
    }

    suspend fun delete(contact: Contact): Long {
        return contactDAO.deleteContact(contact)
    }

    suspend fun deleteAll() {
        return contactDAO.deleteAllContacts()
    }

    suspend fun update(contact: Contact): Long {
        return contactDAO.updateContact(contact)
    }
}