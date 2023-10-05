package com.example.aston.show_fragment

import com.example.aston.data.Person

interface PersonActionListener {
    fun onClickPerson(person: Person, position: Int)
}