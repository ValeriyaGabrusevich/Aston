package com.example.aston

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aston.databinding.ViewHolderBinding

class PersonAdapter(private val personActionListener: PersonActionListener) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private val items = mutableListOf<Person>()
    class PersonViewHolder(val binding: ViewHolderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)

    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = items[position]
        val context = holder.itemView.context


        with(holder.binding) {
            root.setOnClickListener {
                personActionListener.onClickPerson(person, position)
            }


            textViewPersonName.text = person.name
            textViewPersonSurname.text = person.surname
            textViewPersonPhoneNumber.text = person.phoneNumber

            Glide.with(context).load(person.photo).circleCrop()
                .placeholder(R.drawable.baseline_person_24).into(imageViewPersonPhoto)
        }

    }
    fun setItems(newItems: List<Person>){
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilPeopleCallback(
                items,
                newItems
            )
        )
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

}
