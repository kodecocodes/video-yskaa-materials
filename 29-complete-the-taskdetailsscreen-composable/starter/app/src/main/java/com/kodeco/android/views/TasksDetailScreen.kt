package com.kodeco.android.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kodeco.android.R

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
