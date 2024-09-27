package com.pks.shoppingapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF252525), // Keep primary color
    secondary = Color(0xFF757575), // Adjusted secondary to a more neutral tone
    tertiary = Color(0xFFD32F2F),  // Dark red for tertiary
    background = Color(0xFF121212), // Standard dark background
    surface = Color(0xFF313131), // Slightly lighter surface
    onPrimary = Color.White,      // Text on primary
    onSecondary = Color.White,    // Text on secondary
    onTertiary = Color.White,     // Text on tertiary
    onBackground = Color(0xFFE0E0E0), // Lighter text for contrast on dark background
    onSurface = Color(0xFFE0E0E0)   // Lighter text for contrast on dark surface
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF68B8B),   // Keep primary color
    secondary = Color(0xFFD1C4E9), // Adjusted secondary for a softer look
    tertiary = Color(0xFFFFAB91),   // Light coral for tertiary
    background = Color(0xFFFFFFFF), // Bright background
    surface = Color(0xFFF5F5F5),   // Light surface color for contrast
    onPrimary = Color.Black,        // Text on primary
    onSecondary = Color.Black,      // Text on secondary
    onTertiary = Color.Black,       // Text on tertiary
    onBackground = Color(0xFF0E0E0E), // Dark text for contrast on light background
    onSurface = Color(0xFF1C1B1F)   // Dark text for contrast on light surface
)

@Composable
fun ShoppingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}