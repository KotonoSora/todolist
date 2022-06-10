package com.kotonosora.todolist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kotonosora.todolist.database.TodoListApplication
import com.kotonosora.todolist.databinding.ActivityFormBinding
import com.kotonosora.todolist.viewmodel.TodoViewModel
import com.kotonosora.todolist.viewmodel.TodoViewModelFactory


class FormActivity : AppCompatActivity() {
    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            (application as TodoListApplication).database.todoDao()
        )
    }

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTodo: String? = intent.getStringExtra("id")
        Log.v("form_activity", idTodo.toString())

        if (idTodo != null && idTodo.isNotEmpty()) {
            viewModel.getTodoById(idTodo.toInt()).observe(this) { todo ->
                todo.let {
                    binding.titleEditText.setText(todo.title)
                    binding.descEditText.setText(todo.description)
                }
            }
        }

        binding.btnEdit.setOnClickListener {
            if (idTodo != null && idTodo.isNotEmpty()) {
                editTodo(idTodo)
            } else {
                addNewTodo()
            }
        }

        binding.titleEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }

        binding.descEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun addNewTodo() {
        val titleTodo = binding.titleEditText.text.toString()
        val descTodo = binding.descEditText.text.toString()
        viewModel.addNew(titleTodo, descTodo)
        this.finish()
    }

    private fun editTodo(idTodo: String) {
        val titleTodo = binding.titleEditText.text.toString()
        val descTodo = binding.descEditText.text.toString()
        viewModel.updateDetailTodo(idTodo.toInt(), titleTodo, descTodo)
        this.finish()
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }


}