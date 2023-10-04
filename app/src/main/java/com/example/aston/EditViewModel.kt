package com.example.aston

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras

class EditViewModel(private val repository: PeopleRepository) : ViewModel() {

    private val _sideEffectLiveData = SingleLiveData<Unit>()
    val sideEffectLiveData: LiveData<Unit> = _sideEffectLiveData

    fun onSavePerson(id: Int,name:String,surname:String, phoneNumber:String, photo: String, position: Int) {
        val editPerson = Person(id, name,surname, phoneNumber, photo)
        val newPeople = mutableListOf<Person>()
        newPeople.addAll(repository.getPeople())
        newPeople.removeAt(position)
        newPeople.add(position, editPerson)
        repository.update(newPeople)
        _sideEffectLiveData.value = Unit
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                extras.createSavedStateHandle()

                return EditViewModel(
                    (application as App).peopleRepository
                ) as T
            }
        }
    }
}