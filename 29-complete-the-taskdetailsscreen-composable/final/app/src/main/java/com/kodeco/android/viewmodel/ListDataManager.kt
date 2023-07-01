package com.kodeco.android.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.kodeco.android.data.TaskList

class ListDataManager(private val application: Application):
AndroidViewModel(application){

    fun saveList(list: TaskList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(application)
            .edit()
        sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
        sharedPrefs.apply()
    }

    fun readLists(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(application)
        val contents = sharedPrefs.all
        val taskLists = ArrayList<TaskList>()
        for (taskList in contents) {
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, taskItems)
            taskLists.add(list)
        }
        return taskLists
    }
}