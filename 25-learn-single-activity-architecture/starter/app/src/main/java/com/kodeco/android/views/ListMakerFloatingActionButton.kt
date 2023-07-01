package com.kodeco.android.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.kodeco.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMakerFloatingActionButton(
    title: String,
    inputHint: String,
    onFabClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }

    FloatingActionButton(
        onClick = {
            showDialog = true
        },
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.cd_add_icon)
            )
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = title)},
            text = {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it},
                    label = { Text(text = inputHint) },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onFabClick(taskName)
                        taskName = ""
                    },
                    content = {
                        Text(text = stringResource(id = R.string.label_create))
                    }
                )
            }

        )
    }
}