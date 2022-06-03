package com.kotonosora.todolist.database

import androidx.multidex.MultiDexApplication

class TodoListApplication : MultiDexApplication() {
    val database: TodoListDatabase by lazy { TodoListDatabase.getDatabase(this) }
}