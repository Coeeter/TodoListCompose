package com.example.todolistcompose.presentation.screens.addtodosscreen.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.example.todolistcompose.R
import java.util.*

@Composable
fun DatePickerTextField(
    onDateChangeListener: (Long) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var value by remember {
        onDateChangeListener(calendar.timeInMillis)
        mutableStateOf(getDateString(year, month, day))
    }
    val datePicker = remember {
        DatePickerDialog(
            context,
            R.style.DialogTheme,
            { _, year, month, day ->
                val date = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month - 1)
                    set(Calendar.DAY_OF_MONTH, day)
                }
                onDateChangeListener(date.timeInMillis)
                value = getDateString(year, month, day)
            },
            year,
            month,
            day,
        )
    }
    ReadOnlyTextField(
        value = value,
        label = label,
        modifier = modifier,
        onClick = {
            focusManager.clearFocus()
            datePicker.show()
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.CalendarToday,
                contentDescription = "Pick a date"
            )
        },
    )
}

@Composable
fun ReadOnlyTextField(
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    trailingIcon: @Composable () -> Unit,
    label: @Composable () -> Unit
) {
    Box {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = modifier,
            label = label,
            trailingIcon = trailingIcon
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}

fun getDateString(year: Int, month: Int, day: Int) =
    "${if (day < 10) "0$day" else day.toString()}/" +
            "${if (month < 10) "0$month" else month.toString()}/" +
            year.toString()