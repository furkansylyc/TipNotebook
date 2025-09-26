package com.tiprecorder.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.tiprecorder.R
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerButton(
    selectedDate: Date,
    onDateSelected: (Date) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("tr", "TR"))
    
    // Calendar instance'ı oluştur
    val calendar = Calendar.getInstance().apply {
        time = selectedDate
    }

    OutlinedTextField(
        value = dateFormat.format(selectedDate),
        onValueChange = {},
        readOnly = true,
        label = { Text(stringResource(R.string.date)) },
        trailingIcon = {
            IconButton(
                onClick = {
                    val datePickerDialog = android.app.DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val newCalendar = Calendar.getInstance().apply {
                                set(year, month, dayOfMonth)
                            }
                            onDateSelected(newCalendar.time)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Tarih Seç"
                )
            }
        },
        modifier = modifier
    )
}
