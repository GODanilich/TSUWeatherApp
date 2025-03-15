package com.example.myfrst.domain

import com.example.myfrst.data.City
import com.example.myfrst.data.SearchRepository

class SearchCitiesUseCase(private val repository: SearchRepository) {
    suspend operator fun invoke(query: String): Result<List<City>> {
        return try {
            val cities = repository.searchCities(query)
            Result.success(cities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}