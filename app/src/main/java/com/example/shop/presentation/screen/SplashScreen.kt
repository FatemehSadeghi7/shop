package com.example.shop.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onDone:()->Unit){
    LaunchedEffect(Unit) {
        delay(950L)
        onDone()
    }
    Box(modifier = Modifier.fillMaxSize().background(Color.Red),
        contentAlignment = Alignment.Center){
        Text("V", color = Color.White,
            modifier = Modifier.align (Alignment.Center).offset(y=18.dp),
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                fontSize = 96.sp,
                letterSpacing = 0.sp
            ))
    }


}