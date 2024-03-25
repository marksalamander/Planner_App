package edu.towson.cosc435.alexander.planner.data

import edu.towson.cosc435.alexander.planner.data.model.Task

interface TaskListRepository {

    fun getTasks(): List<Task>

    fun deleteTask(song: Task)

    fun addTask(song: Task)


    //TODO: Possible future functions

    //fun changeDate()

    //fun changeTime()
}