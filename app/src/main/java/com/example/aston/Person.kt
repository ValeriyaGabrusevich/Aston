package com.example.aston

import java.io.Serializable

data class Person(
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val photo: String
) : Serializable