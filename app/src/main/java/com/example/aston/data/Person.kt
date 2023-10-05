package com.example.aston.data

import java.io.Serializable

data class Person(
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val photo: String
) : Serializable