package com.example.inventory

import androidx.lifecycle.*
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import androidx.lifecycle.LiveData

class InventoryViewModel(private val itemDao:ItemDao) :ViewModel(){

    val allItems: LiveData<List<Item>> =itemDao.getItems().asLiveData()

    private fun insertItem(item:Item){
        viewModelScope.launch {
            itemDao.insert(item) }
    }

    private fun getNewItemEntry(itemName:String, itemPrice:String, itemCount:String):Item{
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt())
    }
//you will add a function to verify if the text in the TextFields are not empty,
// You will use this function to verify user input before adding or updating the entity in the database.
    fun addNewItem(itemName:String, itemPrice:String, itemCount:String){
        val newItem = getNewItemEntry(itemName,itemPrice,itemCount)
        insertItem(newItem)
    }

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String):Boolean{
        if(itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()){
            return false
        }
        return true
    }

}

class InventoryViewModelFactory(private val itemDao:ItemDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InventoryViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}