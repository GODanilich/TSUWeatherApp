package com.example.myfrst

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                onValueChange = { searchQuery = it },
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Поиск города",
                                color = WhiteText40,
                                fontSize = 16.sp,
                                fontFamily = InterFontFamily
                            )
                        }
                        innerTextField() // Здесь отображается вводимый текст
                    }
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.cross_icon),
                contentDescription = "Clear",
                tint = WhiteText40,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { searchQuery = "" }
            )
        }
    }

    // Автоматическое открытие клавиатуры
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}