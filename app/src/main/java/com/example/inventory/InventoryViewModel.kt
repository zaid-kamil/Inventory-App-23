package com.example.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insertItem(item)
        }
    }

    private fun getNewItemEntry(name: String, price: String, count: String): Item {
        return Item(
            itemName = name,
            itemPrice = price.toDouble(),
            quantityInStock = count.toInt()
        )
    }

    //  to be used from the fragment
    fun addNewItem(name: String, price: String, count: String) {
        insertItem(getNewItemEntry(name, price, count))
    }
}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InventoryViewModel(itemDao) as T
    }
}
