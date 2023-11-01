/*
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interaction")
data class Interaction (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "question")
    val question: String,

    @ColumnInfo(name = "number")
    val number: Int,

    @ColumnInfo(name = "answer")
    val answer: String?
)
