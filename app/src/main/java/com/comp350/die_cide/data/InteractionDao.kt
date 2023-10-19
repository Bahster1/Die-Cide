package com.comp350.die_cide.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InteractionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(interaction: Interaction)

    @Update
    suspend fun update(interaction: Interaction)

    @Delete
    suspend fun delete(interaction: Interaction)

    @Query("SELECT * FROM interaction WHERE id = :id")
    fun getInteraction(id: Int): Flow<Interaction>

    @Query("SELECT * FROM interaction ORDER BY question ASC")
    fun getInteractions(): Flow<List<Interaction>>
}
