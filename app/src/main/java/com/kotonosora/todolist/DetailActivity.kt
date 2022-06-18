package com.kotonosora.todolist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kotonosora.todolist.database.TodoListApplication
import com.kotonosora.todolist.databinding.ActivityDetailBinding
import com.kotonosora.todolist.viewmodel.TodoViewModel
import com.kotonosora.todolist.viewmodel.TodoViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            (application as TodoListApplication).database.todoDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTodo: String? = intent.getStringExtra("id")
        Log.v("detail_activity", idTodo.toString())

        if (idTodo != null && idTodo.isNotEmpty()) {
            viewModel.getTodoById(idTodo.toInt()).observe(this) { todo ->
                todo.let {
                    binding.txtTitle.text = todo.title
                    binding.txtDescription.text = todo.description
                }
            }
        }

    }
}