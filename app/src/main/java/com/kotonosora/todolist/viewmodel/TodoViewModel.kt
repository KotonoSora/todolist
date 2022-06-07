package com.kotonosora.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kotonosora.todolist.database.TodoModel
import com.kotonosora.todolist.database.TodoModelDao
import kotlinx.coroutines.launch

class TodoViewModel(private val todoDao: TodoModelDao) : ViewModel() {
    /**
     * Query
     */
    val todos: LiveData<List<TodoModel>> = todoDao.getAll().asLiveData()
    fun getTodoById(todoId: Int) = todoDao.getById(todoId).asLiveData()
    fun searchByTitle(todoTitle: String) = todoDao.getByTitle(todoTitle).asLiveData()

    /**
     * Insert
     */
    private fun insertTodo(todo: TodoModel) {
        viewModelScope.launch {
            todoDao.insert(todo)
        }
    }

    private fun getNewTodoEntry(todoTitle: String, todoDescription: String?) = TodoModel(
        title = todoTitle,
        description = todoDescription ?: ""
    )


    fun addNew(t: String, d: String? = null) {
        val newTodo = getNewTodoEntry(t, d)
        insertTodo(newTodo)
    }

    /**
     * Update
     */
    private fun updateTodo(todo: TodoModel) {
        viewModelScope.launch {
            todoDao.update(todo)
        }
    }

    private fun getUpdatedTodoEntry(
        todoId: Int,
        todoTitle: String,
        todoDescription: String? = null
    ) =
        TodoModel(
            id = todoId,
            title = todoTitle,
            description = todoDescription
        )

    fun updateDetailTodo(
        todoId: Int,
        todoTitle: String,
        todoDescription: String? = null
    ) {
        val todo = getUpdatedTodoEntry(todoId, todoTitle, todoDescription)
        updateTodo(todo)
    }

    /**
     * Delete
     */
    fun deleteTodo(todo: TodoModel) {
        viewModelScope.launch {
            todoDao.delete(todo)
        }
    }
}