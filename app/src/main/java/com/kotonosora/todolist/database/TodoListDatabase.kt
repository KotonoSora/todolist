package com.kotonosora.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoListDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoModelDao

    companion object {
        @Volatile
        private var INSTANCE: TodoListDatabase? = null

        fun getDatabase(context: Context): TodoListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TodoListDatabase::class.java,
                    "todolist_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}