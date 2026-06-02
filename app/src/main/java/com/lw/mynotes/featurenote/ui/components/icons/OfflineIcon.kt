package com.lw.mynotes.featurenote.ui.components.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lw.mynotes.R
import com.lw.mynotes.ui.theme.PunchRed

@Composable
fun OfflineIcon(small: Boolean = false) {
    val modifier = if (small) Modifier.zIndex(1f).offset(x = 30.dp, y = 10.dp).size(25.dp) else Modifier.size(25.dp)
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.fillMaxSize(0.65f),
            painter = painterResource(id = R.drawable.ic_offline),
            contentDescription = "Offline",
            tint = PunchRed
        )
    }
}