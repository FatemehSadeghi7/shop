package com.example.shop.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import com.example.shop.ui.theme.PlayfairDisplay
import com.example.shop.ui.theme.Primary

@Composable
fun MainScreen(
    onNavigateToHome: () -> Unit,
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onDiscoverClick: () -> Unit = {},

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bitmap),
            contentDescription = "Fashion model",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .offset(x = (-16).dp)
                .fillMaxWidth(0.21f)
                .background(Color.White),

            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "VERTLUNE",
                fontFamily = PlayfairDisplay,
                fontWeight = FontWeight.W500,
                fontSize = 64.sp,
                color = Color.Black,
                letterSpacing = 4.sp,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(
                            constraints.copy(
                                minWidth = 0,
                                maxWidth = Int.MAX_VALUE,
                            )
                        )
                        layout(placeable.height, placeable.width) {
                            placeable.place(
                                x = -(placeable.width / 2 - placeable.height / 2),
                                y = placeable.width / 2 - placeable.height / 2,
                            )
                        }
                    }
                    .graphicsLayer { rotationZ = -90f },
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 68.dp)
                .width(1.5.dp)
                .height(380.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            //Color.White,
                            Color.White.copy(alpha = 0.9f),

                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.9f),

                          //  Color.White,
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "V",
                fontFamily = PlayfairDisplay,
                fontWeight = FontWeight.Bold,
                fontSize = 52.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 24.dp, start = 39.dp)
            )

            Spacer(modifier = Modifier.width(120.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp),
                    )
                }
                IconButton(onClick = onCartClick) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "Cart",
                        tint = Color.Black,
                        modifier = Modifier.size(27.dp),
                    )
                }
                IconButton(onClick = onDiscoverClick) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        repeat(2) {
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                repeat(2) {
                                    Box(
                                        modifier = Modifier
                                            .size(7.dp)
                                            .clip(CircleShape)
                                            .background(Color.Black)
                                    )
                                }
                            }
                        }
                    }
                }

            }


        }

        val dir = LocalLayoutDirection.current
        val shift = if (dir == LayoutDirection.Rtl) 8.dp else (-8).dp
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 48.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(Primary)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { onNavigateToHome() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Go to Home",
                tint = Color.White,
                modifier = Modifier
                    .align(if (dir == LayoutDirection.Rtl) Alignment.CenterEnd else Alignment.CenterStart)
                    .padding(horizontal = 0.dp)
                    .size(32.dp)
                    .graphicsLayer(scaleX = 2.40f)
            )
        }
    }
}
