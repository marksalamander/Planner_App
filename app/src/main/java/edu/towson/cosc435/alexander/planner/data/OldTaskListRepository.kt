package edu.towson.cosc435.alexander.planner.data

import edu.towson.cosc435.alexander.planner.data.model.OldTask

interface OldTaskListRepository {

    fun getTasks(): List<OldTask>

    fun deleteTask(song: OldTask)

    fun addTask(song: OldTask)


    //TODO: Possible future functions

    //fun changeDate()

    //fun changeTime()
}