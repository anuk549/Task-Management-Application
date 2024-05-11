package com.example.task.repository

import androidx.lifecycle.LiveData
import com.example.task.data.TaskDao
import com.example.task.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTasks(){
        taskDao.deleteAllTasks()
    }

    fun searchTasksByName(searchQuery: String): LiveData<List<Task>> {
        return taskDao.searchTasksByName(searchQuery)
    }


}