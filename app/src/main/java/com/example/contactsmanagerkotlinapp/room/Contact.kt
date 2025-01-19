package com.example.contactsmanagerkotlinapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val contactId: Int,
    @ColumnInfo(name = "contact_name")
    var contactName: String,
    @ColumnInfo(name = "contact_email")
    var contactEmail: String
)
