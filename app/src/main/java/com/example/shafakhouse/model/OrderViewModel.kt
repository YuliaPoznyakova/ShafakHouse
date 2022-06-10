package com.example.shafakhouse.model

import androidx.lifecycle.*
import com.example.shafakhouse.network.DishApi
import kotlinx.coroutines.launch
import java.text.NumberFormat


enum class DishApiStatus { LOADING, ERROR, DONE }

class OrderViewModel: ViewModel() {

    private val _status = MutableLiveData<DishApiStatus>()
    val status: LiveData<DishApiStatus> = _status

    private val _dishObjects = MutableLiveData<List<Dish>>()
    val dishObjects: LiveData<List<Dish>> = _dishObjects

    private val _currentDish = MutableLiveData<Dish>()
    val currentDish: LiveData<Dish> = _currentDish


    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        getDish()
    }

    private fun getDish() {
        viewModelScope.launch {
            _status.value = DishApiStatus.LOADING
            try {
                _dishObjects.value = DishApi.retrofitService.getPhotos()
                _status.value = DishApiStatus.DONE
            } catch (e: Exception) {
                _status.value = DishApiStatus.DONE
                _dishObjects.value = listOf()
            }
        }
    }
}

