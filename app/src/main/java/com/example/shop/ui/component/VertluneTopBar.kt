package com.example.shop.ui.component

import com.example.shop.ui.theme.Dimens
import com.example.shop.ui.theme.VertluneColors


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shop.R


@Composable
fun VertluneTopBar(
    showBackArrow: Boolean = false,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = Dimens.PaddingMd, vertical = Dimens.PaddingSm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackArrow) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(Dimens.IconButtonSize)
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = VertluneColors.Black,
                    modifier = Modifier.size(Dimens.IconMd)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(Dimens.IconButtonSize))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingXs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier.size(Dimens.IconButtonSize)
            ) {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = VertluneColors.Black,
                    modifier = Modifier.size(Dimens.IconMd)
                )
            }

            IconButton(
                onClick = onCartClick,
                modifier = Modifier.size(Dimens.IconButtonSize)
            ) {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Cart",
                        tint = VertluneColors.Black,
                        modifier = Modifier.size(Dimens.IconMd)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp)
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(VertluneColors.Orange)
                    )
                }
            }

            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(Dimens.IconButtonSize)
            ) {
                Canvas(modifier = Modifier.size(Dimens.IconSm)) {
                    val dotRadius = 3.dp.toPx()
                    val gap = size.width * 0.35f
                    val cx = size.width / 2
                    val cy = size.height / 2
                    drawCircle(Color.Black, dotRadius, Offset(cx - gap, cy - gap))
                    drawCircle(Color.Black, dotRadius, Offset(cx + gap, cy - gap))
                    drawCircle(Color.Black, dotRadius, Offset(cx - gap, cy + gap))
                    drawCircle(Color.Black, dotRadius, Offset(cx + gap, cy + gap))
                }
            }
        }
    }
}