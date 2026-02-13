package com.example.shop.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Search
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import com.example.shop.domain.model.Product
import com.example.shop.ui.components.ErrorState
import com.example.shop.ui.components.ShimmerProductCard
import com.example.shop.ui.theme.PlayfairDisplay
import com.example.shop.ui.theme.Poppins
import com.example.shop.ui.theme.Primary
import com.example.shop.util.toPriceString
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onNavigateToShop: () -> Unit,
    onNavigateToProduct: (Int) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToDiscover: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
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
            .verticalScroll(rememberScrollState()),
    ) {
        // ───── Top Bar ─────
        HomeTopBar(
            cartItemCount = uiState.cartItemCount,
            onSearchClick = onNavigateToSearch,
            onCartClick = onNavigateToCart,
            onGridClick = onNavigateToDiscover,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ───── Sale Banner ─────
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(600)) + slideInVertically(
                initialOffsetY = { it / 3 },
                animationSpec = tween(600),
            ),
        ) {
            SaleBannerCard(
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        // ───── Best Sellers ─────
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(500, delayMillis = 300)) + slideInVertically(
                initialOffsetY = { it / 2 },
                animationSpec = tween(500, delayMillis = 300),
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Best Sellers",
                    fontFamily = PlayfairDisplay,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Fuel your workout routine with extra legroom\nand fewer distractions in our latest shorts.",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ───── Products Grid ─────
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(500, delayMillis = 500)),
        ) {
            if (uiState.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    ShimmerProductCard(modifier = Modifier.weight(1f))
                    ShimmerProductCard(modifier = Modifier.weight(1f))
                }
            } else if (uiState.error != null && uiState.featuredProducts.isEmpty()) {
                ErrorState(
                    message = uiState.error ?: "Error",
                    onRetry = { viewModel.retry() },
                )
            } else {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    uiState.featuredProducts.chunked(2).forEach { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            rowProducts.forEach { product ->
                                HomeProductCard(
                                    product = product,
                                    modifier = Modifier.weight(1f),
                                    onClick = { onNavigateToProduct(product.id) },
                                )
                            }
                            if (rowProducts.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ───── Top Bar ─────
@Composable
private fun HomeTopBar(
    cartItemCount: Int,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onGridClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "V",
            fontFamily = PlayfairDisplay,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Black,
        )

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
                        imageVector = Icons.Outlined.Lock,
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
            IconButton(onClick = { onGridClick() }) {
                Icon(
                    imageVector = Icons.Outlined.GridView,
                    contentDescription = "Discover",
                    tint = Color.Black,
                    modifier = Modifier.size(22.dp),
                )
            }
        }
    }
}

// ───── Sale Banner ─────
@Composable
private fun SaleBannerCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(380.dp)
            .clip(RoundedCornerShape(20.dp))
            .clipToBounds(),
    ) {
        // بک‌گراند نارنجی گرادیان
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Primary,
                            Primary.copy(alpha = 0.85f),
                        )
                    )
                )
        )

        // Page curl - گرادیان سفید سمت راست
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.1f),
                            Color.White.copy(alpha = 0.3f),
                            Color.White.copy(alpha = 0.6f),
                        )
                    )
                )
        )

        // محتوای بنر
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // نقطه‌چین بالا
            DottedLine()

            // متن‌ها
            Column {
                Text(
                    text = "BIG\nSEASON\nSALE",
                    fontFamily = PlayfairDisplay,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 40.sp,
                    color = Color.White,
                    lineHeight = 48.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "SAVE UP TO",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.85f),
                    letterSpacing = 3.sp,
                )

                Text(
                    text = "75%",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    color = Color.White,
                )
            }

            // نقطه‌چین پایین
            DottedLine()
        }
    }
}

// ───── نقطه‌چین (کوچک‌تر، داخل بنر) ─────
@Composable
private fun DottedLine() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(12) {
            Box(
                modifier = Modifier
                    .size(width = 12.dp, height = 1.5.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(1.dp),
                    )
            )
        }
    }
}

// ───── Product Card ─────
@Composable
private fun HomeProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5)),
        ) {
            Image(
                painter = painterResource(id = com.example.shop.util.ImageResource.getProductImage(product.imageUrl)),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            if (product.hasDiscount) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Primary)
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = "-${product.discountPercent}%",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.name,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = product.discountedPrice.toPriceString(),
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Color.DarkGray,
            )
            if (product.hasDiscount) {
                Text(
                    text = product.price.toPriceString(),
                    fontFamily = Poppins,
                    fontSize = 11.sp,
                    color = Color.LightGray,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
        }
    }
}