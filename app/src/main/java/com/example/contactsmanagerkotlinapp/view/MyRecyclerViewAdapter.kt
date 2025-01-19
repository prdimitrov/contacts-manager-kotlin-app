package com.example.contactsmanagerkotlinapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanagerkotlinapp.R
import com.example.contactsmanagerkotlinapp.databinding.CardItemBinding
import com.example.contactsmanagerkotlinapp.room.Contact

class MyRecyclerViewAdapter(
    private val contactsList:List<Contact>,
    private val clickListener: (Contact) -> Unit): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {



        class MyViewHolder(val binding: CardItemBinding)
            : RecyclerView.ViewHolder(binding.root) {
                fun bind(contact: Contact, clickListener: (Contact) -> Unit) {


                    binding.nameTextView.text = contact.contactName
                    binding.emailTextView.text = contact.contactEmail

                    binding.listItemLayout.setOnClickListener {
                        clickListener(contact)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil
            .inflate(layoutInflater, R.layout.card_item, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactsList[position], clickListener)
    }

}