package com.kotonosora.todolist.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoModelDao {
    @Query("SELECT * FROM todos ORDER BY created_at DESC")
    fun getAll(): Flow<List<TodoModel>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getById(id: Int): Flow<TodoModel>

    @Query("SELECT * FROM todos WHERE title LIKE :title ORDER BY created_at DESC")
    fun getByTitle(title: String): Flow<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: TodoModel)

    @Update
    suspend fun update(todo: TodoModel)

    @Delete
    suspend fun delete(todo: TodoModel)
}