package com.kmp.weather.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale

@Composable
fun SplashScreen(onSplashComplete: () -> Unit = {}) {
    var isAnimating by remember { mutableStateOf(false) }

    // Start animation immediately
    LaunchedEffect(Unit) {
        isAnimating = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0f,
        animationSpec = tween(durationMillis = 2000),
        label = "text_alpha"
    )

    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0.8f,
        animationSpec = tween(durationMillis = 2000),
        label = "text_scale"
    )

    // Listen to animation state and call onSplashComplete when animation finishes
    LaunchedEffect(alpha) {
        if (isAnimating && alpha >= 0.99f) { // Check if animation is nearly complete
            onSplashComplete()
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .safeContentPadding(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "Weather App",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .alpha(alpha)
                .scale(scale)
        )
    }
}
