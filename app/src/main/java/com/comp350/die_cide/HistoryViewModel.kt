package com.comp350.die_cide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.data.InteractionDao
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(private val interactionDao: InteractionDao): ViewModel() {
    fun allInteractions(): Flow<List<Interaction>> = interactionDao.getInteractions()
}

class HistoryViewModelFactory(private val interactionDao: InteractionDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModelFactory::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModelFactory(interactionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
