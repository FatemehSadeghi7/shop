package com.example.shop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.domain.model.Banner
import com.example.shop.ui.theme.PlayfairDisplay
import com.example.shop.ui.theme.Poppins
import com.example.shop.ui.theme.Primary
import com.example.shop.ui.theme.PrimaryDark
import com.example.shop.ui.theme.PrimaryLight

@Composable
fun SaleBanner(
    banner: Banner,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(banner.backgroundColor),
                            PrimaryLight.copy(alpha = 0.8f),
                        )
                    )
                )
                .clip(MaterialTheme.shapes.large),
        ) {
            // محتوای بنر
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(24.dp),
            ) {
                // عنوان: BIG SEASON SALE
                Text(
                    text = banner.title,
                    fontFamily = PlayfairDisplay,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                    color = Color.White,
                    lineHeight = 34.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // زیرعنوان: SAVE UP TO
                Text(
                    text = banner.subtitle,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    letterSpacing = 2.sp,
                )

                // درصد تخفیف: 75%
                if (banner.discountPercent > 0) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = "${banner.discountPercent}",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 48.sp,
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "%",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                    }
                }
            }

            // Page curl effect (افکت تا خوردن صفحه)
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(80.dp)
                    .height(200.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.15f),
                                Color.White.copy(alpha = 0.3f),
                            )
                        )
                    )
            )
        }
    }
}
