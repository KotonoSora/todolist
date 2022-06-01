package com.kotonosora.todolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotonosora.todolist.adapter.TodoAdapter
import com.kotonosora.todolist.databinding.ActivityMainBinding
import com.kotonosora.todolist.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.todos.value?.isEmpty() == true) {
            viewModel.initData()
        }
        binding.viewModel = viewModel
        val adapter = TodoAdapter()
        adapter.submitList(viewModel.todos.value)
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
}