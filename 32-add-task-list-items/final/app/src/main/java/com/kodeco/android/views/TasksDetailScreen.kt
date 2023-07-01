package com.kodeco.android.views

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
fun TaskDetailScreenContent(
    modifier: Modifier,
    taskTodos: List<String>
) {
    if (taskTodos.isEmpty()) {
        EmptyView(message = stringResource(id = R.string.text_no_todos))
    } else {
        LazyColumn(
            modifier = modifier,
            content = {
                items(taskTodos) {
                    ListItemView(
                        value = it,
                        onClick = {

                        }
                    )
                }
            }
        )
    }
}

@Composable
fun TaskDetailScreen(
    taskName: String?,
    onBackPressed: () -> Unit
) {
    val viewModel: ListDataManager = viewModel()
    var taskTodos by remember {
        mutableStateOf(viewModel.readLists().firstOrNull() { it.name == taskName}
            ?.tasks ?: emptyList())
    }
    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = taskName ?: stringResource(id = R.string.label_task_list),
                showBackButton = true,
                onBackPressed = onBackPressed
            )
        },
        content = {
            TaskDetailScreenContent(
                modifier = Modifier.padding(it),
                taskTodos = taskTodos
            )
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = stringResource(id = R.string.task_to_add),
                inputHint = stringResource(id = R.string.task_hint),
                onFabClick = { todoName ->
                    viewModel.saveList(TaskList(
                        taskName ?: "",
                        taskTodos + listOf(todoName)
                    ))
                    taskTodos = viewModel.readLists().firstOrNull {
                        it.name == taskName
                    }?.tasks ?: emptyList()
                }
            )
        }
    )
}










