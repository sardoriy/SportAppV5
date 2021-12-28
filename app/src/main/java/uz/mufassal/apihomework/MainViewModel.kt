package uz.mufassal.apihomework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.mufassal.apihomework.network.Event
import uz.mufassal.apihomework.network.Resource
import uz.mufassal.apihomework.network.data.FruitResponse
import uz.mufassal.apihomework.network.data.WordsResponse
import uz.mufassal.apihomework.repository.Repository

class MainViewModel(val repository: Repository) : ViewModel() {
    var wordsResponse = MutableLiveData<Event<Resource<WordsResponse>>>()
    var fruitsResponse = MutableLiveData<Event<Resource<FruitResponse>>>()

    fun getWords(words: String) {
        viewModelScope.launch {
            repository.getWords(words).onEach {
                wordsResponse.value = Event(it)
            }.launchIn(viewModelScope)
        }
    }

    fun getFruit(words: String) {
        viewModelScope.launch {
            repository.getFruits(words).onEach {
                fruitsResponse.value = Event(it)
            }.launchIn(viewModelScope)
        }
    }
}