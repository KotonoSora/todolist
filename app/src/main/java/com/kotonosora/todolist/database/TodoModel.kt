package com.kotonosora.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    val createdTime: String = System.currentTimeMillis().toString(),

    @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP")
    val lastModifiedTime: String = System.currentTimeMillis().toString()
)