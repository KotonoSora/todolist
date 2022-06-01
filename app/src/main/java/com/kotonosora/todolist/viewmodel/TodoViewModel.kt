package com.kotonosora.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotonosora.todolist.data.Todo

class TodoViewModel : ViewModel() {
    private var _todos: MutableLiveData<List<Todo>> = MutableLiveData(listOf())
    val todos: LiveData<List<Todo>> = _todos

    init {
        resetTodos()
    }

    fun resetTodos() {
        _todos.value = listOf()
    }

    fun initData() {
        val initTodos = mutableListOf<Todo>()
        for (i in 1..5) {
            val item = Todo(i.toLong(), "Todo $i")
            initTodos.add(item)
        }
        _todos.value = initTodos
    }

    fun setTodos(newTodos: List<Todo>) {
        _todos.value = newTodos
    }

    fun addTodo(todoName: String) {
        var newId: Long = todos.value?.size?.toLong() ?: 0
        newId++
        val currentTodos: MutableList<Todo> = todos.value?.toMutableList() ?: mutableListOf()
        currentTodos.add(Todo(newId, todoName))
        Log.v("new size", currentTodos.size.toString())
        _todos.value = currentTodos
    }
}