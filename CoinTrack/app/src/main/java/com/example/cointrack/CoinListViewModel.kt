package com.example.cointrack

import androidx.lifecycle.*
import kotlinx.coroutines.*

class CoinListViewModel constructor(private val mainRepository: CoinListRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<CoinListResponse>()
    val loading = MutableLiveData<Boolean>()

    var job: Job? = null

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getCoinList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    movieList.value = response.body()
                    loading.value = false
                } else onError("Error : ${response.message()} ")
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
