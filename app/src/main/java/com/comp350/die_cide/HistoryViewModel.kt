/*
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.data.InteractionRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: InteractionRepository): ViewModel() {
    val allInteractions: LiveData<List<Interaction>> = repository.allInteractions.asLiveData()

    fun insert(interaction: Interaction) = viewModelScope.launch {
        repository.insert(interaction)
    }
}

class HistoryViewModelFactory(private val repository: InteractionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
