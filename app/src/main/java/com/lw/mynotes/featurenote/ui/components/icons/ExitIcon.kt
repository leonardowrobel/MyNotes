package com.lw.mynotes.featurenote.ui.components.icons

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExitIcon(
    onClick: () -> Unit
){
    IconButton(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 10.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Profile",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}