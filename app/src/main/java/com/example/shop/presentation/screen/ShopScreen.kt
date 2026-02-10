package com.example.shop.presentation.screen


import ShopViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shop.ui.component.VertluneTopBar
import com.example.shop.ui.theme.Dimens
import com.example.shop.ui.theme.VertluneColors


@Composable
fun ShopScreen(
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    viewModel: ShopViewModel = viewModel(factory = ShopViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VertluneColors.White)
    ) {
        VertluneTopBar(
            showBackArrow = true,
            onBackClick = onBackClick,
            onSearchClick = onSearchClick,
            onCartClick = onCartClick,
            onMenuClick = onMenuClick
        )

        // Title section
        Column(modifier = Modifier.padding(horizontal = Dimens.PaddingLg)) {
            Text(
                text = "Shop",
                color = VertluneColors.Black,
                fontSize = Dimens.FontHeading,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingXs))
            Text(
                text = "Hollywood Hairstyles Do Not",
                color = VertluneColors.Gray,
                fontSize = Dimens.FontMd
            )
        }

        Spacer(modifier = Modifier.height(Dimens.PaddingLg))

        // Sort & Filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.PaddingLg),
            horizontalArrangement = Arrangement.Center
        ) {
            // Sort - outlined
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.RadiusPill))
                    .border(
                        1.dp,
                        VertluneColors.Border,
                        RoundedCornerShape(Dimens.RadiusPill)
                    )
                    .clickable { /* sort bottom sheet */ }
                    .padding(horizontal = 28.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = "Sort",
                    tint = VertluneColors.Black,
                    modifier = Modifier.size(Dimens.IconSm)
                )
                Spacer(modifier = Modifier.width(Dimens.PaddingSm))
                Text(
                    text = "Sort",
                    color = VertluneColors.Black,
                    fontSize = Dimens.FontLg,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.width(Dimens.PaddingSm))}}}