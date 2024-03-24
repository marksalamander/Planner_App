package edu.towson.cosc435.alexander.planner.data.impl

import edu.towson.cosc435.alexander.planner.data.TaskListRepository
import edu.towson.cosc435.alexander.planner.data.model.Task

class TaskRepository : TaskListRepository {

    private var _tasks = listOf<Task>()

    init {
        _tasks = (0..20).map { i ->
            Task(i.toString(), "Task $i", "Summary $i", "Date", "Time", true)
        }
    }

    override fun getTasks(): List<Task> {
        return _tasks
    }
    override fun deleteTask(task: Task) {
        _tasks = _tasks.filter { t -> t.id != task.id}
    }
    override fun addTask(task: Task) {
        _tasks = listOf(task) + _tasks
    }

    //TODO: Future interface additions

}