package com.example.myfrst.data

// Модель города
data class City(
    val name: String
)

// Состояние экрана поиска
sealed class SearchState {
    object Idle : SearchState() // Начальное состояние
    object Loading : SearchState() // Загрузка
    data class Success(val cities: List<City>) : SearchState() // Успешный результат
    data class Error(val message: String) : SearchState() // Ошибка
}