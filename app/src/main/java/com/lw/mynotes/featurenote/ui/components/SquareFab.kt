package com.lw.mynotes.featurenote.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SquareFab(
    modifier: Modifier? = Modifier,
    onClick: () -> Unit,
    icon: ImageVector = Icons.Filled.Add,
    enable: Boolean = true,
    contentDescription: String = ""
){
    FloatingActionButton(
        modifier = modifier!!,
        onClick = onClick,
        containerColor = if(enable) FloatingActionButtonDefaults.containerColor else Color.Gray,
        contentColor = if(enable) contentColorFor(containerColor) else Color.DarkGray
    ) {
        Icon(icon, contentDescription)
    }
}