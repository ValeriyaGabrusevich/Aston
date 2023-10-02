package com.example.aston.phonebook

class ContactRepository (private val service: ContactsService){
    fun getContacts(): List<Contact> = service.getContacts()
}
