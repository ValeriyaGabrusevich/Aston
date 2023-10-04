package com.example.aston

import android.app.Application

class App: Application() {
    private val personService = PersonService()
    val peopleRepository = PeopleRepository(personService)
}