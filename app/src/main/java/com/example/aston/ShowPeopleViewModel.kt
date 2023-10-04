package com.example.aston

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras

class ShowPeopleViewModel(repository: PeopleRepository):ViewModel(), PersonActionListener {

    private val _peopleLiveData = MutableLiveData<List<Person>>()
    val peopleLiveData: LiveData<List<Person>> = _peopleLiveData

    private val _sideEffectLiveData = SingleLiveData<Pair<Person, Int>>()
    val sideEffectLiveData: LiveData<Pair<Person, Int>> = _sideEffectLiveData


    init {
        _peopleLiveData.value = repository.getPeople()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                extras.createSavedStateHandle()

                return ShowPeopleViewModel(
                    (application as App).peopleRepository
                ) as T
            }
        }
    }

    override fun onClickPerson(person: Person, position: Int) {
      _sideEffectLiveData.value = person to position
    }
}
