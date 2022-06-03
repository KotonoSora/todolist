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

        binding.addNewTodoButton.setOnClickListener { addNewTodo() }

        binding.nameTodoEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun addNewTodo() {
        val stringInTextField = binding.nameTodoEditText.text.toString()
        Log.v("aaaaaa string in field", stringInTextField)
//        this.finish()
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