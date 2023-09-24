package com.example.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel()
{
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    private fun insertItem(item: Item)
    {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String) : Item
    {
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    private fun getUpdatedItemEntry(itemId: Int, itemName: String, itemPrice: String, itemCount: String): Item
    {
        return Item(id = itemId, itemName = itemName, itemPrice = itemPrice.toDouble(), quantityInStock = itemCount.toInt())
    }

    private fun updateItem(item: Item)
    {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    fun sellItem(item: Item)
    {
        if (item.quantityInStock > 0)
        {
            val newItem = item.copy(quantityInStock = item.quantityInStock - 1)
            updateItem(newItem)
        }
    }

    fun updateItem(itemId: Int, itemName: String, itemPrice: String, itemCount: String)
    {
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemPrice, itemCount)
        updateItem(updatedItem)
    }

    fun retrieveItem(id: Int): LiveData<Item>
    {
        return itemDao.getItem(id).asLiveData()
    }

    fun addNewItem(itemName: String, itemPrice: String, itemCount: String)
    {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        insertItem(newItem)
    }

    fun deleteItem(item: Item)
    {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String) : Boolean
    {
        return itemName.isNotBlank() && itemPrice.isNotBlank() && itemCount.isNotBlank()
    }

    fun isStockAvailable(item: Item): Boolean
    {
        return item.quantityInStock > 0
    }
}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>) : T
    {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java))
        {
            @Suppress("UNCHECKED CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
