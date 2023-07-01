package com.kodeco.android.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kodeco.android.data.TaskList

@Composable
fun TaskListContent(
    modifier: Modifier,
    tasks: List<TaskList>,
    onClick: (String) -> Unit
) {
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