package com.example.aston

class PeopleRepository(service: PersonService) {
    private val people = mutableListOf<Person>()

    init {
        people.addAll(service.getPeople())
    }

    fun update(newPeople: List<Person>) {
        people.clear()
        people.addAll(newPeople)
    }

    fun getPeople(): List<Person> = people
}