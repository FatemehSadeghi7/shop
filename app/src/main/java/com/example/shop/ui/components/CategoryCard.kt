package com.example.shop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.domain.model.Category
import com.example.shop.ui.theme.PlayfairDisplay
import com.example.shop.ui.theme.Poppins
import com.example.shop.util.ImageResource

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick(category.id) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(category.backgroundColor)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(24.dp),
            ) {
                Text(
                    text = category.type,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    text = category.name,
                    fontFamily = PlayfairDisplay,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                )
            }

            Image(
                painter = painterResource(id = ImageResource.getCategoryImage(category.imageUrl)),
                contentDescription = category.name,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxHeight()
                    .fillMaxWidth(0.45f),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
            )
        }
    }
}