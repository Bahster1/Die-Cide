/*
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import android.app.Application
import com.comp350.die_cide.data.InteractionRepository
import com.comp350.die_cide.data.InteractionRoomDatabase

class DieCideApplication : Application() {
    val database by lazy { InteractionRoomDatabase.getDatabase(this) }
    val repository by lazy { InteractionRepository(database.interactionDao()) }
}
