package com.kotonosora.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotonosora.todolist.database.TodoModel
import com.kotonosora.todolist.databinding.TodoViewItemBinding

class TodoAdapter(
    private val deleteTodo: (todo: TodoModel) -> Unit,
    private val editTodo: (todo: TodoModel) -> Unit,
    private val detailTodo: (todo: TodoModel) -> Unit
) :
    ListAdapter<TodoModel, TodoAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private var binding: TodoViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val btnDelete = binding.btnDelete
        val btnEdit = binding.btnEdit
        val btnDetail = binding.btnDetail

        fun bind(todo: TodoModel) {
            binding.todo = todo
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TodoModel>() {
        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(TodoViewItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
        holder.btnDelete.setOnClickListener { deleteTodo(todo) }
        holder.btnEdit.setOnClickListener { editTodo(todo) }
        holder.btnDetail.setOnClickListener { detailTodo(todo) }
    }
}