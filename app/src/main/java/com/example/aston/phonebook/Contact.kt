package com.example.aston.phonebook

import java.io.Serializable

data class Contact(
    val id: Int,
    val name: String,
    val surname: String,
    val number: String,
    val isSelected: Boolean = false
) : Serializable