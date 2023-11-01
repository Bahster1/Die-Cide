package com.comp350.die_cide.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class InteractionRepository(private val interactionDao: InteractionDao) {
    val allInteractions: Flow<List<Interaction>> = interactionDao.getInteractions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(interaction: Interaction) {
        interactionDao.insert((interaction))
    }
}
