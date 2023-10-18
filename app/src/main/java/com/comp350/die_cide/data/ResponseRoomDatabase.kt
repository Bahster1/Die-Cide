package com.comp350.die_cide.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Response::class], version = 1, exportSchema = false)
abstract class ResponseRoomDatabase : RoomDatabase() {
    abstract fun responseDao(): ResponseDao

    companion object
    {
        @Volatile
        private var INSTANCE: ResponseRoomDatabase? = null

        fun getDatabase(context: Context): ResponseRoomDatabase
        {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResponseRoomDatabase::class.java,
                    "response_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
