package com.example.shafakhouse.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shafakhouse.network.DishApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

enum class DishApiStatus { LOADING, ERROR, DONE }

class OrderViewModel: ViewModel() {

    private val _status = MutableLiveData<DishApiStatus>()
    val status: LiveData<DishApiStatus> = _status

    private val _dishObjects = MutableLiveData<List<Dish>>()
    val dishObjects: LiveData<List<Dish>> = _dishObjects

    private val _currentDish = MutableLiveData<Dish>()
    val currentDish: LiveData<Dish> = _currentDish

    private val _checked = MutableLiveData<Boolean>()
    val checked : LiveData<Boolean> = _checked

    private val _typeDish = MutableLiveData<MutableList<String>>()
    val typeDish: LiveData<MutableList<String>> = _typeDish

    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())

    val calendar = Calendar.getInstance()

    val dateOptions = getPickupOptions()

    init {
        getDish()
        resetOrder()
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

    fun setTypeDish(typeDish: String) {
        _typeDish.value?.add(typeDish)
        _typeDish.value = _typeDish.value
    }

    fun removeTypeDish(typeDish: String) {
        _typeDish.value?.remove(typeDish)
        _typeDish.value = _typeDish.value
    }

    fun setQuantity() {
            _quantity.value = _typeDish.value!!.size
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun updateCurrentDish(dish: Dish) {
        _currentDish.value = dish
    }

    fun resetOrder() {
        _typeDish.value = mutableListOf()
        _quantity.value = 0
        _date.value = dateOptions[0]
    }
}

