/*
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Interaction::class), version = 1, exportSchema = false)
abstract class InteractionRoomDatabase : RoomDatabase() {
    abstract fun interactionDao(): InteractionDao

    companion object
    {
        @Volatile
        private var INSTANCE: InteractionRoomDatabase? = null

        fun getDatabase(context: Context): InteractionRoomDatabase
        {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InteractionRoomDatabase::class.java,
                    "interaction_database"
                )
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
