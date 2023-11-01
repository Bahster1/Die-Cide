/*
    * Copyright 2023 Bradley Walsh
 */
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

    @Query("DELETE FROM interaction")
    fun deleteAll()

    @Query("SELECT * FROM interaction ORDER BY id ASC")
    fun getInteractions(): Flow<List<Interaction>>
}
