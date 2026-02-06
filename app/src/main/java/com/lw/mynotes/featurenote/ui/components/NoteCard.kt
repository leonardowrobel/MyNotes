package com.lw.mynotes.featurenote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lw.mynotes.featurenote.domain.model.Note

@Composable
fun NoteCard(note: Note){
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(10.dp, 8.dp)
            .background(color = Color.Gray)
    ) {
        Text(note.title)
        Text(note.content)
    }
}