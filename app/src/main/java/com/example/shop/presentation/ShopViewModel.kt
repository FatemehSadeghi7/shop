
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shop.di.AppModule
import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.SortType
import com.example.shop.domain.usecase.GetCategoriesUseCase
import com.example.shop.domain.usecase.GetProductsUseCase
import com.example.shop.presentation.ShopUiEvent
import com.example.shop.presentation.ShopUiState

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShopViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
        loadCategories()
    }

    fun onEvent(event: ShopUiEvent) {
        when (event) {
            is ShopUiEvent.OnSortChanged -> {
                _uiState.update { it.copy(sortType = event.sortType) }
                loadProducts()
            }
            is ShopUiEvent.OnFilterChanged -> {
                _uiState.update { it.copy(filterOptions = event.filterOptions) }
                loadProducts()
            }
            is ShopUiEvent.OnClearFilter -> {
                _uiState.update {
                    it.copy(
                        filterOptions = FilterOptions(),
                        sortType = SortType.DEFAULT
                    )
                }
                loadProducts()
            }
            is ShopUiEvent.OnProductClick -> {
                // Handled by navigation in UI layer
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                getProductsUseCase(
                    sortType = _uiState.value.sortType,
                    filter = _uiState.value.filterOptions
                ).collect { products ->
                    _uiState.update {
                        it.copy(products = products, isLoading = false, error = null)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { categories ->
                _uiState.update { it.copy(categories = categories) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ShopViewModel(
                    getProductsUseCase = AppModule.getProductsUseCase,
                    getCategoriesUseCase = AppModule.getCategoriesUseCase
                ) as T
            }
        }
    }
}