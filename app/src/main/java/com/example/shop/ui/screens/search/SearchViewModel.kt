package com.example.shop.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.usecase.product.SearchProductsUseCase
import com.example.shop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }

        searchJob?.cancel()
        if (query.length >= 2) {
            searchJob = viewModelScope.launch {
                delay(400) // debounce 400ms
                search(query)
            }
        } else {
            _uiState.update { it.copy(results = emptyList(), hasSearched = false) }
        }
    }

    fun onSearch() {
        val query = _uiState.value.query
        if (query.isNotBlank()) {
            searchJob?.cancel()
            viewModelScope.launch { search(query) }
        }
    }

    private fun search(query: String) {
        searchProductsUseCase(query).onEach { result ->
            when (result) {
                is Resource.Loading -> _uiState.update { it.copy(isSearching = true) }
                is Resource.Success -> _uiState.update {
                    it.copy(
                        isSearching = false,
                        results = result.data ?: emptyList(),
                        hasSearched = true,
                    )
                }
                is Resource.Error -> _uiState.update {
                    it.copy(isSearching = false, results = emptyList(), hasSearched = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clearSearch() {
        _uiState.update { SearchUiState() }
    }
}
