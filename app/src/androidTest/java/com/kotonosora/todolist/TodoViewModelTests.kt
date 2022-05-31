package com.kotonosora.todolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kotonosora.todolist.data.Todo
import com.kotonosora.todolist.viewmodel.TodoViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoViewModelTests {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun quantity_init_todos() {
        val viewModel = TodoViewModel()
        viewModel.initData()
        viewModel.todos.observeForever() {}
        assertEquals("100", viewModel.todos.value?.size.toString())
    }

    @Test
    fun quantity_reset_todos() {
        val viewModel = TodoViewModel()
        viewModel.resetTodos()
        viewModel.todos.observeForever() {}
        assertEquals("0", viewModel.todos.value?.size.toString())
    }

    @Test
    fun quantity_set_todos() {
        val viewModel = TodoViewModel()
        val newTodos = mutableListOf<Todo>()
        for (i in 1..10) {
            newTodos.add(Todo(i.toLong(), "Test Todo $i"))
        }
        viewModel.setTodos(newTodos)
        assertEquals("10", viewModel.todos.value?.size.toString())
    }
}