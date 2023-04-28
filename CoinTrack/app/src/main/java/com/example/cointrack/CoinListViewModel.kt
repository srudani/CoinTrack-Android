package com.example.cointrack

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*

const val SAVED_DATA = "SAVED_DATA"
class CoinListViewModel constructor(private val mainRepository: CoinListRepository, private  val handle: SavedStateHandle) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<CoinListResponse>()
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies() {
        if (getSavedResponse() != null) {
            Log.e("Getting saved Data", "getAllMovies")
            movieList.value = handle[SAVED_DATA]
        } else {
            Log.e("Getting New Data", "getAllMovies")
            viewModelScope.launch {
                val response = mainRepository.getCoinList()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        movieList.value = response.body()
                        loading.value = false
                        handle[SAVED_DATA] = movieList.value
                    } else onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun getSavedResponse(): CoinListResponse? {
        return handle[SAVED_DATA]
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}
