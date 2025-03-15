package com.example.myfrst.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfrst.data.SearchState
import com.example.myfrst.domain.SearchCitiesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val searchCitiesUseCase: SearchCitiesUseCase) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchState: StateFlow<SearchState> = _searchState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private var searchJob: Job? = null

    // Обработка изменения текста в поле поиска
    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        searchCities(newQuery)
    }

    // Очистка поля поиска
    fun clearQuery() {
        _query.value = ""
        _searchState.value = SearchState.Idle
    }

    // Поиск городов
    private fun searchCities(query: String) {
        searchJob?.cancel() // Отмена предыдущего поиска
        if (query.isBlank()) {
            _searchState.value = SearchState.Idle
            return
        }

        searchJob = viewModelScope.launch {
            _searchState.value = SearchState.Loading
            val result = searchCitiesUseCase(query)
            _searchState.value = result.fold(
                onSuccess = { cities ->
                    SearchState.Success(cities)
                },
                onFailure = { error ->
                    SearchState.Error("Ошибка поиска: ${error.message}")
                }
            )
        }
    }
}