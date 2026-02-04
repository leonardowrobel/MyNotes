package com.lw.mynotes.featurenote.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Fab(
    modifier: Modifier? = Modifier,
    onClick: () -> Unit,
    icon: ImageVector = Icons.Filled.Add
){
    FloatingActionButton(
        modifier = modifier!!,
        onClick = onClick,
        shape = CircleShape
    ) {
        Icon(icon, "Add note")
    }
}