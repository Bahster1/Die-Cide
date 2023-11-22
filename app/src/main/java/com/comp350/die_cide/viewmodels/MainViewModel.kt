package com.comp350.die_cide.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.data.InteractionRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: InteractionRepository): ViewModel() {
    fun insert(interaction: Interaction) = viewModelScope.launch {
        repository.insert(interaction)
    }
}

class MainViewModelFactory(private val repository: InteractionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
