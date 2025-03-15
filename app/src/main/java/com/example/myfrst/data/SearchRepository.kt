package com.example.myfrst.data

interface SearchRepository {
    suspend fun searchCities(query: String): List<City>
}

class FakeSearchRepository : SearchRepository {
    // Заглушечные данные
    private val cities = listOf(
        City("Томск"),
        City("Москва"),
        City("Санкт-Петербург"),
        City("Новосибирск"),
        City("Шымкент"),
        City("Астана"),
        City("Алма-Ата"),
        City("Кемерово"),
        City("Юрга"),
        City("Екатеринбург")
    )

    override suspend fun searchCities(query: String): List<City> {
        // Имитация задержки сети
        kotlinx.coroutines.delay(500)
        return if (query.isNotBlank()) {
            cities.filter { it.name.contains(query, ignoreCase = true) }
        } else {
            emptyList()
        }
    }
}