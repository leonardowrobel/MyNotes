package com.lw.mynotes.featurenote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lw.mynotes.featurenote.domain.model.Note

@Composable
fun NoteCard(
    note: Note,
    onClickEdit: (() -> Unit)
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 2.dp,
                    spread = 2.dp,
                    color = Color(0x10000000),
                    offset = DpOffset(x = 2.dp, 2.dp)
                )
            )
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(12.dp, 10.dp)
    ) {
        Row {
            Text(
                text = note.title,
                modifier = Modifier.weight(10f),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface)
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = onClickEdit
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Text(
            modifier = Modifier.offset(y = 5.dp), text = note.content)
    }
}