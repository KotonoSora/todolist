package com.kotonosora.todolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotonosora.todolist.adapter.TodoAdapter
import com.kotonosora.todolist.database.TodoListApplication
import com.kotonosora.todolist.database.TodoModel
import com.kotonosora.todolist.databinding.ActivityMainBinding
import com.kotonosora.todolist.viewmodel.TodoViewModel
import com.kotonosora.todolist.viewmodel.TodoViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            (application as TodoListApplication).database.todoDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        val adapter = TodoAdapter(::deleteTodo, ::editTodo)

        viewModel.todos.observe(this) { todos ->
            todos.let {
                adapter.submitList(it)
            }
        }

        binding.todosList.adapter = adapter
        binding.todosList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.addNewItem.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteTodo(todo: TodoModel) {
        viewModel.deleteTodo(todo)
    }

    private fun editTodo(todo: TodoModel) {
        val intent = Intent(binding.root.context, FormActivity::class.java)
        intent.putExtra("id", todo.id.toString())
        binding.root.context.startActivity(intent)
    }
}