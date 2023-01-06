package com.example.inventory

import androidx.lifecycle.*
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

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

    fun isEntryValid(name: String, price: String, quantity: String): Boolean {
        if (name.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
            return false
        }
        return true
    }

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }

    fun isStockAvailable(item: Item): Boolean {
        return item.quantityInStock > 0
    }

    fun sellItem(item: Item) {
        if (isStockAvailable(item)) {
            val updatedItem = item.copy(quantityInStock = item.quantityInStock - 1)
            updateItem(updatedItem)
        }
    }
}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InventoryViewModel(itemDao) as T
    }
}
