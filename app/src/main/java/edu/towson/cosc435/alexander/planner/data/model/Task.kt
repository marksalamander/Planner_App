package edu.towson.cosc435.alexander.planner.data.model

import android.widget.AdapterView.OnItemSelectedListener

data class Task (
    val id: String,
    val title: String,
    val description: String,
    val taskDate: String,
    val taskTime: String,
    val isSelected: Boolean
    ){
}