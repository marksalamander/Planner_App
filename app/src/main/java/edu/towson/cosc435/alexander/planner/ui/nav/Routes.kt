package edu.towson.cosc435.alexander.planner.ui.nav

sealed class Routes(val route: String) {
    object Calendar : Routes("calendar")
    object TaskList : Routes("taskList")
    object TaskWizard : Routes("taskWizard")
    object SelectedDatePage : Routes("selectedDate")
}