package com.kodeco.android.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kodeco.android.R
import com.kodeco.android.data.TaskList
import com.kodeco.android.viewmodel.ListDataManager

@Composable
fun TaskListScreen() {
    val taskListViewModel: ListDataManager = viewModel()
    val viewModelTasks = taskListViewModel.readLists().toList()
    var tasks by remember {
        mutableStateOf(viewModelTasks)
    }
    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = stringResource(id = R.string.label_list_maker),
                showBackButton = false,
                onBackPressed = {}
            )
        },
        content = {
            TaskListContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                tasks = tasks,
                onClick = { taskName ->
                    // TODO navigate to the task details screen
                }
            )
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = stringResource(id = R.string.name_of_list),
                inputHint =  stringResource(id = R.string.task_hint),
                onFabClick = {
                    tasks = (tasks + TaskList(it))
                    taskListViewModel.saveList(TaskList(it))
                }
            )
        }
    )
}

@Composable
fun TaskListContent(
    modifier: Modifier,
    tasks: List<TaskList>,
    onClick: (String) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyView(message = stringResource(id = R.string.text_no_tasks))
    } else {
        LazyColumn(
            modifier = modifier,
            content = {
                items(tasks) {
                    ListItemView(
                        value = it.name,
                        onClick = onClick
                    )
                }
            }
        )
    }
}