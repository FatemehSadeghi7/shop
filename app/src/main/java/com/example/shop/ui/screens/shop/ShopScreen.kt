package com.example.shop.ui.screens.shop


import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.domain.model.SortType
import com.example.shop.ui.components.EmptyState
import com.example.shop.ui.components.ErrorState
import com.example.shop.ui.components.ProductCard
import com.example.shop.ui.components.ShimmerProductGrid
import com.example.shop.ui.components.ShopTopBar
import com.example.shop.ui.theme.FilterButtonShape
import com.example.shop.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    onNavigateToProduct: (Int) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToDiscover: () -> Unit = {},
    viewModel: ShopViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ,
    ) {
        ShopTopBar(
            showBackButton = true,
            showLogo = false,
            cartItemCount = uiState.cartItemCount,
            onBackClick = onNavigateBack,
            onSearchClick = onNavigateToSearch,
            onCartClick = onNavigateToCart,
            onMenuClick = onNavigateToDiscover,
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Shop",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${uiState.products.size} products",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(FilterButtonShape)
                    .background(
                        if (uiState.sortType != SortType.NEWEST) Color.Black
                        else MaterialTheme.colorScheme.surface
                    )
                    .clickable { viewModel.toggleSortSheet() }
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Sort,
                    contentDescription = "Sort",
                    modifier = Modifier.height(18.dp),
                    tint = if (uiState.sortType != SortType.NEWEST) Color.White
                    else MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sort",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = if (uiState.sortType != SortType.NEWEST) Color.White
                    else MaterialTheme.colorScheme.onSurface,
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(FilterButtonShape)
                    .background(
                        if (uiState.filterOptions.isActive) Color.Black
                        else MaterialTheme.colorScheme.surface
                    )
                    .clickable { viewModel.toggleFilterSheet() }
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = "Filter",
                    modifier = Modifier.height(18.dp),
                    tint = if (uiState.filterOptions.isActive) Color.White
                    else MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = if (uiState.filterOptions.isActive) Color.White
                    else MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> {
                ShimmerProductGrid()
            }
            uiState.error != null && uiState.products.isEmpty() -> {
                ErrorState(
                    message = uiState.error ?: "Error",
                    onRetry = { viewModel.loadProducts() },
                )
            }
            uiState.products.isEmpty() -> {
                EmptyState(
                    title = "No products found",
                    subtitle = "Try changing filters",
                )
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    items(
                        items = uiState.products,
                        key = { it.id },
                    ) { product ->
                        ProductCard(
                            product = product,
                            onProductClick = onNavigateToProduct,
                            onFavoriteClick = { viewModel.toggleFavorite(it) },
                        )
                    }
                }
            }
        }
    }

    if (uiState.showSortSheet) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.toggleSortSheet() },
            sheetState = rememberModalBottomSheetState(),
        ) {
            SortSheetContent(
                selectedSort = uiState.sortType,
                onSortSelected = { viewModel.onSortChanged(it) },
            )
        }
    }

    if (uiState.showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.toggleFilterSheet() },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        ) {
            FilterSheetContent(
                filterOptions = uiState.filterOptions,
                onApply = { viewModel.onFilterChanged(it) },
                onReset = { viewModel.onFilterChanged(com.example.shop.domain.model.FilterOptions()) },
            )
        }
    }
}

@Composable
private fun SortSheetContent(
    selectedSort: SortType,
    onSortSelected: (SortType) -> Unit,
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Sort by",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        SortType.entries.forEach { sort ->
            val isSelected = sort == selectedSort
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .background(if (isSelected) Primary.copy(alpha = 0.1f) else Color.Transparent)
                    .clickable { onSortSelected(sort) }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = sort.displayName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    color = if (isSelected) Primary else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun FilterSheetContent(
    filterOptions: com.example.shop.domain.model.FilterOptions,
    onApply: (com.example.shop.domain.model.FilterOptions) -> Unit,
    onReset: () -> Unit,
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Filter",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Reset",
                style = MaterialTheme.typography.labelLarge,
                color = Primary,
                modifier = Modifier.clickable { onReset() },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        FilterChipRow(
            label = "On Sale",
            isSelected = filterOptions.onlyOnSale,
            onClick = { onApply(filterOptions.copy(onlyOnSale = !filterOptions.onlyOnSale)) },
        )
        Spacer(modifier = Modifier.height(8.dp))

        FilterChipRow(
            label = "In Stock Only",
            isSelected = filterOptions.onlyInStock,
            onClick = { onApply(filterOptions.copy(onlyInStock = !filterOptions.onlyInStock)) },
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun FilterChipRow(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(if (isSelected) Primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) Primary else MaterialTheme.colorScheme.onSurface,
        )
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .background(if (isSelected) Primary else Color.Transparent)
                .then(
                    if (!isSelected) Modifier.background(Color.Transparent)
                    else Modifier
                ),
        )
    }
}