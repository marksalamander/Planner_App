package edu.towson.cosc435.alexander.planner.data.impl

import edu.towson.cosc435.alexander.planner.data.OldTaskListRepository
import edu.towson.cosc435.alexander.planner.data.model.OldTask

class OldTaskRepository : OldTaskListRepository {

    private var _tasks = listOf<OldTask>()

    init {
        _tasks = (0..20).map { i ->
            OldTask(i.toString(), "Task $i", "Summary $i", "Date", "Time", true)
        }
    }

    override fun getTasks(): List<OldTask> {
        return _tasks
    }
    override fun deleteTask(task: OldTask) {
        _tasks = _tasks.filter { t -> t.id != task.id}
    }
    override fun addTask(task: OldTask) {
        _tasks = listOf(task) + _tasks
    }
    suspend fun toggleSelected(task: OldTask) {
        _tasks = _tasks.map { i ->
            if(i.id == task.id) {
                i.copy(isSelected = !i.isSelected)
            } else {
                i
            }
        }
    }

    //TODO: Future interface additions

}