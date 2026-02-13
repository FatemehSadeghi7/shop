package com.example.shop.ui.screens.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.ui.components.CategoryCard
import com.example.shop.ui.components.ErrorState
import com.example.shop.ui.components.ShimmerCategoryCard
import com.example.shop.ui.theme.PlayfairDisplay
import com.example.shop.ui.theme.Poppins
import com.example.shop.ui.theme.Primary
import kotlinx.coroutines.delay

@Composable
fun DiscoverScreen(
    onNavigateToShop: (Int) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ,
    ) {
        DiscoverTopBar(
            cartItemCount = uiState.cartItemCount,
            onBackClick = onNavigateBack,
            onSearchClick = onNavigateToSearch,
            onCartClick = onNavigateToCart,
        )

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Discover",
                fontFamily = PlayfairDisplay,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Discover your products",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Gray,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    repeat(3) { ShimmerCategoryCard() }
                }
            }
            uiState.error != null -> {
                ErrorState(
                    message = uiState.error ?: "Error",
                    onRetry = { viewModel.retry() },
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFE0E0E0),
                                    shape = RoundedCornerShape(20.dp),
                                )
                                .clickable { },
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Add",
                                modifier = Modifier.size(28.dp),
                                tint = Color.Black,
                            )
                        }
                    }

                    itemsIndexed(
                        items = uiState.categories,
                        key = { _, cat -> cat.id },
                    ) { _, category ->
                        CategoryCard(
                            category = category,
                            onClick = { onNavigateToShop(it) },
                        )
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
        }
    }
}

@Composable
private fun DiscoverTopBar(
    cartItemCount: Int,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(24.dp),
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp),
                )
            }
            Box {
                IconButton(onClick = onCartClick) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "Cart",
                        tint = Color.Black,
                        modifier = Modifier.size(22.dp),
                    )
                }
                if (cartItemCount > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Primary)
                    )
                }
            }
            IconButton(onClick = {}) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(2) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(2) {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp)
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
}