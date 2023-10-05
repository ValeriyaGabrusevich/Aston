package com.example.aston

import android.app.Application
import com.example.aston.data.PeopleRepository
import com.example.aston.data.PersonService

class App: Application() {
    private val personService = PersonService()
    val peopleRepository = PeopleRepository(personService)
}