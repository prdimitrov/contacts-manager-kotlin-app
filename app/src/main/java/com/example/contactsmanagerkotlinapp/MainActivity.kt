package com.example.contactsmanagerkotlinapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanagerkotlinapp.databinding.ActivityMainBinding
import com.example.contactsmanagerkotlinapp.repository.ContactRepository
import com.example.contactsmanagerkotlinapp.room.Contact
import com.example.contactsmanagerkotlinapp.room.ContactDAO
import com.example.contactsmanagerkotlinapp.room.ContactDatabase
import com.example.contactsmanagerkotlinapp.view.MyRecyclerViewAdapter
import com.example.contactsmanagerkotlinapp.viewmodel.ContactViewModel
import com.example.contactsmanagerkotlinapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ROOM DB Initialization
        val dao: ContactDAO = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        // ViewModel
        viewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        binding.contactViewModel = viewModel

        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        displayUsersList()
    }

    private fun displayUsersList() {
        viewModel.contacts.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, { selectedContact: Contact -> listItemClicked(selectedContact)})
        })
    }

    private fun listItemClicked(selectedContact: Contact) {
        Toast.makeText(this, "Selected name is ${selectedContact.contactName}", Toast.LENGTH_SHORT).show()

        viewModel.initUpdateAndDelete(selectedContact)
    }
}