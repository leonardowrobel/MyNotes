package com.lw.mynotes.featurenote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lw.mynotes.featurenote.domain.model.Note

@Composable
fun NoteCard(
    note: Note,
    onClickEdit: (() -> Unit),
    onClickExclude: (() -> Unit)
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .padding(10.dp, 8.dp)
    ) {
        Row {
            Text(
                text = note.title,
                modifier = Modifier.weight(10f),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = onClickEdit
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = onClickExclude
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
        Text(
            modifier = Modifier.offset(y = 5.dp), text = note.content)
    }
}