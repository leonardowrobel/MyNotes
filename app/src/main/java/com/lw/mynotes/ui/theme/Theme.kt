package com.lw.mynotes.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.lw.mynotes.ui.theme.Platinum

private val DarkColorScheme = darkColorScheme(
    primary = NeonViolet,
    secondary = ShockingPink,
    tertiary = Pink80, // TODO: choose
    background = SpaceIndigo,
    surface = SpaceIndigo,
    onPrimary = SpaceIndigo,
    onSecondary = SpaceIndigo,
    onTertiary = SpaceIndigo,
    onBackground = Platinum,
    onSurface = Platinum,
)

private val LightColorScheme = lightColorScheme(
    primary = PunchRed,
    secondary = Cinnabar,
    tertiary = PrincetonOrange,
    background = Platinum,
    surface = Platinum,
    onPrimary = Platinum,
    onSecondary = SpaceIndigo,
    onTertiary = SpaceIndigo,
    onBackground = SpaceIndigo,
    onSurface = SpaceIndigo,
)

@Composable
fun MyNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}