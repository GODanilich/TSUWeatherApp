package com.example.myfrst

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myfrst.data.SearchState
import com.example.myfrst.vm.SearchViewModel
import com.example.myfrst.data.FakeSearchRepository
import com.example.myfrst.domain.SearchCitiesUseCase

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModelFactory())
) {
    val searchQuery by viewModel.query.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Поле поиска
        Row(
            modifier = Modifier
                .width(380.dp)
                .height(40.dp)
                .background(Color(0xFF1E1E1E), RoundedCornerShape(9999.dp))
                .border(1.dp, Color(0xFF444444), RoundedCornerShape(9999.dp))
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = searchQuery,
                onValueChange = { viewModel.onQueryChanged(it) },
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    color = WhiteText,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily
                ),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Поиск города",
                                color = WhiteText40,
                                fontSize = 16.sp,
                                fontFamily = InterFontFamily
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.cross_icon),
                contentDescription = "Clear",
                tint = WhiteText40,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { viewModel.clearQuery() }
            )
        }

        // Отображение состояния поиска
        when (searchState) {
            is SearchState.Loading -> {
                Text(
                    text = "Загрузка...",
                    color = WhiteText,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily
                )
            }
            is SearchState.Success -> {
                val cities = (searchState as SearchState.Success).cities
                if (cities.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .width(380.dp)
                            .height(144.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        cities.take(4).forEach { city ->
                            Row(
                                modifier = Modifier
                                    .width(380.dp)
                                    .height(24.dp)
                                    .clickable { navController.navigate("weather") },
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = highlightMatchingText(city.name, searchQuery),
                                    style = TextStyle(
                                        fontFamily = InterFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 20.sp,
                                        lineHeight = 24.sp
                                    )
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Города не найдены",
                        color = WhiteText40,
                        fontSize = 16.sp,
                        fontFamily = InterFontFamily
                    )
                }
            }
            is SearchState.Error -> {
                Text(
                    text = (searchState as SearchState.Error).message,
                    color = WhiteText40,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily
                )
            }
            is SearchState.Idle -> {
                // Ничего не отображаем
            }
        }
    }

    // Автоматический фокус
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

// Функция для подсветки всех совпадающих букв
private fun highlightMatchingText(fullText: String, query: String): AnnotatedString {
    return buildAnnotatedString {
        if (query.isBlank()) {
            append(fullText)
            addStyle(SpanStyle(color = WhiteText40), 0, fullText.length)
            return@buildAnnotatedString
        }

        val queryLower = query.lowercase()
        val fullTextLower = fullText.lowercase()
        var lastIndex = 0

        while (lastIndex < fullText.length) {
            val matchIndex = fullTextLower.indexOf(queryLower, lastIndex)
            if (matchIndex == -1) {
                // Остаток текста без совпадений
                append(fullText.substring(lastIndex))
                addStyle(SpanStyle(color = WhiteText40), lastIndex, fullText.length)
                break
            } else {
                // Текст до совпадения
                if (matchIndex > lastIndex) {
                    append(fullText.substring(lastIndex, matchIndex))
                    addStyle(SpanStyle(color = WhiteText40), lastIndex, matchIndex)
                }
                // Совпадающий текст
                append(fullText.substring(matchIndex, matchIndex + queryLower.length))
                addStyle(
                    SpanStyle(color = WhiteText70),
                    matchIndex,
                    matchIndex + queryLower.length
                )
                lastIndex = matchIndex + queryLower.length
            }
        }
    }
}

// Фабрика для ViewModel
class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            val repository = FakeSearchRepository()
            val useCase = SearchCitiesUseCase(repository)
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

