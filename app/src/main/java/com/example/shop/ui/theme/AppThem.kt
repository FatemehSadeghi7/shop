package com.example.shop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private  val Dark= darkColorScheme()
private  val Light= lightColorScheme()
@Composable
fun AppThem(
    dark: Boolean=false,
    content: @Composable ()->Unit
){
    MaterialTheme (colorScheme = if (dark) Dark else Light,
        content=content
    )
}