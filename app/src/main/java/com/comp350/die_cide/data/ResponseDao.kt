package com.comp350.die_cide.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ResponseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(response: Response)

    @Update
    suspend fun update(response: Response)

    @Delete
    suspend fun delete(response: Response)

    @Query("SELECT * FROM response WHERE id = :id")
    fun getResponse(id: Int): Flow<Response>

    @Query("SELECT * FROM response ORDER BY question ASC")
    fun getResponses(): Flow<List<Response>>
}
