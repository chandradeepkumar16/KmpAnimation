package com.chandradeepdev.dhyaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource

@Composable
fun TitleImage() {
    Image(
        painter = painterResource("images/newch.jpg"),
        contentDescription = "This is a chakri img", // Provide a suitable content description here
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape),
        contentScale = ContentScale.FillBounds
    )
}
