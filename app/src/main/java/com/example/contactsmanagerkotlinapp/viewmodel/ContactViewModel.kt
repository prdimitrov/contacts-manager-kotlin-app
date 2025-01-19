package com.example.contactsmanagerkotlinapp.viewmodel

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanagerkotlinapp.repository.ContactRepository
import com.example.contactsmanagerkotlinapp.room.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository)
    : ViewModel(), Observable {

        val contacts = repository.contacts
    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contact

    // Data Binding with Live Data
    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    private fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    private fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)
        resetButtonsAndFields()
    }

    private fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)
        resetButtonsAndFields()
    }

    private fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    private fun resetButtonsAndFields() {
        // Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            // Make an update
            contactToUpdateOrDelete.contactName = inputName.value!!
            contactToUpdateOrDelete.contactName = inputEmail.value!!
            update(contactToUpdateOrDelete)
        } else {
            // Insert new contact
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(Contact(0, name, email))

            // Reset the name and email
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllOrDelete(view: View) {
        if (isUpdateOrDelete) {
            delete(contactToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun initUpdateAndDelete(contact: Contact) {
        inputName.value = contact.contactName
        inputEmail.value = contact.contactEmail
        isUpdateOrDelete = true
        contactToUpdateOrDelete = contact
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}