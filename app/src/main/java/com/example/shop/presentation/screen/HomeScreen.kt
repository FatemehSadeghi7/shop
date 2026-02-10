package com.example.shop.presentation.screen


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R

val OrangeAccent = Color(0xFFE8511A)
val OrangeLight = Color(0xFFF47B52)
val OrangeSoft = Color(0xFFFBB8A0)
val PinkSoft = Color(0xFFFDE8E0)

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // ---- Top Bar ----
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo V
            Text(
                text = "V",
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            // Icons
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onSearchClick, modifier = Modifier.size(36.dp)) {
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.size(22.dp)
                    )
                }
                // Cart with orange dot badge
                IconButton(onClick = onCartClick, modifier = Modifier.size(36.dp)) {
                    Box {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                            contentDescription = "Cart",
                            tint = Color.Black,
                            modifier = Modifier.size(22.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = 2.dp, y = (-2).dp)
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(OrangeAccent)
                        )
                    }
                }
                // 4-dot menu icon
                IconButton(onClick = onMenuClick, modifier = Modifier.size(36.dp)) {
                    // Custom 4-dot grid
                    Canvas(modifier = Modifier.size(18.dp)) {
                        val dotRadius = 3.dp.toPx()
                        val gap = size.width * 0.35f
                        val cx = size.width / 2
                        val cy = size.height / 2

                        // Top-left
                        drawCircle(Color.Black, dotRadius, Offset(cx - gap, cy - gap))
                        // Top-right
                        drawCircle(Color.Black, dotRadius, Offset(cx + gap, cy - gap))
                        // Bottom-left
                        drawCircle(Color.Black, dotRadius, Offset(cx - gap, cy + gap))
                        // Bottom-right
                        drawCircle(Color.Black, dotRadius, Offset(cx + gap, cy + gap))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ---- Season Sale Banner ----
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(420.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            // Background gradient
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Main orange gradient background
                val mainBrush = Brush.linearGradient(
                    colors = listOf(OrangeAccent, OrangeLight),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height)
                )
                drawRect(brush = mainBrush)

                // Curved overlay on the right side (page curl effect)
                val curvePath = Path().apply {
                    moveTo(size.width * 0.55f, 0f)
                    cubicTo(
                        size.width * 0.65f, size.height * 0.2f,
                        size.width * 0.50f, size.height * 0.5f,
                        size.width * 0.70f, size.height * 0.7f
                    )
                    cubicTo(
                        size.width * 0.85f, size.height * 0.85f,
                        size.width * 0.95f, size.height * 0.6f,
                        size.width, size.height * 0.3f
                    )
                    lineTo(size.width, 0f)
                    close()
                }
                drawPath(
                    path = curvePath,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PinkSoft.copy(alpha = 0.7f),
                            Color.White.copy(alpha = 0.5f)
                        ),
                        start = Offset(size.width * 0.5f, 0f),
                        end = Offset(size.width, size.height * 0.5f)
                    )
                )

                // Second curve layer for depth
                val curvePath2 = Path().apply {
                    moveTo(size.width * 0.60f, 0f)
                    cubicTo(
                        size.width * 0.70f, size.height * 0.15f,
                        size.width * 0.55f, size.height * 0.45f,
                        size.width * 0.75f, size.height * 0.65f
                    )
                    cubicTo(
                        size.width * 0.90f, size.height * 0.80f,
                        size.width, size.height * 0.55f,
                        size.width, size.height * 0.25f
                    )
                    lineTo(size.width, 0f)
                    close()
                }
                drawPath(
                    path = curvePath2,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.3f),
                            Color.White.copy(alpha = 0.15f)
                        ),
                        start = Offset(size.width * 0.6f, 0f),
                        end = Offset(size.width, size.height * 0.4f)
                    )
                )

                // Dashed line top
                val dashEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
                drawLine(
                    color = Color.White.copy(alpha = 0.5f),
                    start = Offset(size.width * 0.15f, size.height * 0.08f),
                    end = Offset(size.width * 0.55f, size.height * 0.08f),
                    strokeWidth = 2.dp.toPx(),
                    pathEffect = dashEffect,
                    cap = StrokeCap.Round
                )

                // Dashed line bottom
                drawLine(
                    color = Color.White.copy(alpha = 0.5f),
                    start = Offset(size.width * 0.15f, size.height * 0.92f),
                    end = Offset(size.width * 0.55f, size.height * 0.92f),
                    strokeWidth = 2.dp.toPx(),
                    pathEffect = dashEffect,
                    cap = StrokeCap.Round
                )
            }

            // Text content
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 28.dp, bottom = 20.dp)
            ) {
                // BIG SEASON SALE
                Text(
                    text = "BIG",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 52.sp
                )
                Text(
                    text = "SEASON",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 52.sp
                )
                Text(
                    text = "SALE",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 52.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // SAVE UP TO
                Text(
                    text = "SAVE UP TO",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp
                )

                // 75%
                Text(
                    text = "75%",
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 68.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // ---- Best Sellers Section ----
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Best Sellers",
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Fuel your workout routine with extra legroom\nand fewer distractions in our latest shorts.",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ---- Product Card Placeholder ----
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(280.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            // اینجا بعداً محصولات قرار میگیرن
            // مثلا: Image(...) یا LazyRow با کارت‌های محصول
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

