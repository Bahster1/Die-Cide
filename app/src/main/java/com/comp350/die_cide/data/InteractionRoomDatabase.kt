package com.comp350.die_cide.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Interaction::class], version = 1, exportSchema = false)
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
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
