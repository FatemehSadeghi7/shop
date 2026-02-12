package com.example.shop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.ui.theme.PlayfairDisplay
import com.example.shop.ui.theme.Primary

@Composable
fun ShopTopBar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    showLogo: Boolean = true,
    showSearch: Boolean = true,
    showCart: Boolean = true,
    showMenu: Boolean = true,
    cartItemCount: Int = 0,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Left: Back button or Logo
        if (showBackButton) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        } else if (showLogo) {
            Text(
                text = "V",
                fontFamily = PlayfairDisplay,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    ),
            )
        }

        // Right: Actions
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showSearch) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            if (showCart) {
                IconButton(onClick = onCartClick) {
                    BadgedBox(
                        badge = {
                            if (cartItemCount > 0) {
                                Badge(
                                    containerColor = Primary,
                                    contentColor = Color.White,
                                ) {
                                    Text(
                                        text = cartItemCount.toString(),
                                        fontSize = 10.sp,
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "Cart",
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }

            if (showMenu) {
                IconButton(onClick = onMenuClick) {
                    // Grid dots icon (مطابق تصویر اپ)
                    Box(modifier = Modifier.size(20.dp)) {
                        // 4 dots grid
                        val dotSize = 4.dp
                        val spacing = 6.dp
                        for (row in 0..1) {
                            for (col in 0..1) {
                                Box(
                                    modifier = Modifier
                                        .size(dotSize)
                                        .align(Alignment.TopStart)
                                        .padding(
                                            start = (col * (dotSize.value + spacing.value)).dp,
                                            top = (row * (dotSize.value + spacing.value)).dp,
                                        )
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.onBackground)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
