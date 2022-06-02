package com.example.shafakhouse.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_DISH = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel: ViewModel() {

    private val _typeDish = MutableLiveData<MutableList<String>>()
    val typeDish: LiveData<MutableList<String>> = _typeDish

    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())

    val calendar = Calendar.getInstance()

    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    fun setTypeDish(typeDish: String) {
        _typeDish.value?.add(typeDish)
        _typeDish.value = _typeDish.value
        }


    fun setQuantity(amountDishes: Boolean) {
        if (amountDishes) {
            _quantity.value = _quantity.value?.plus(1)
       }
    }


    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create a list of dates starting with the current date and the following 3 dates
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder() {
        _typeDish.value = mutableListOf()
        _quantity.value = 0
        _date.value = dateOptions[0]
        _price.value = 0.0
    }


    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_DISH
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

}

