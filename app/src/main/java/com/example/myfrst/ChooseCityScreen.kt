package com.example.myfrst


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ChooseCityScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        ChooseCityTopSection(navController)
        CitiesSection()
    }
}


@Composable
fun ChooseCityTopSection(navController: NavController) {
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(154.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Кнопка перехода на WeatherScreen
        Button(
            onClick = { navController.navigate("weather") },
            modifier = Modifier
                .wrapContentWidth()
                .height(32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_icon),
                    contentDescription = "back arrow",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Погода в Томске",
                    color = WhiteText40,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily,
                    lineHeight = 16.sp
                )
            }
        }

        // Заголовок "Города"
        Text(
            text = "Города",
            color = WhiteText,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = InterFontFamily,
            lineHeight = 58.sp,
            letterSpacing = (-0.02).em,
            modifier = Modifier
                .width(380.dp)
                .height(58.dp)
        )

        // Поле поиска
        Row(
            modifier = Modifier
                .width(380.dp)
                .height(40.dp)
                .background(Color(0xFF1E1E1E), RoundedCornerShape(9999.dp))
                .border(1.dp, Color(0xFF444444), RoundedCornerShape(9999.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .clickable { navController.navigate("search") },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Поиск города",
                color = WhiteText40,
                fontSize = 16.sp,
                fontFamily = InterFontFamily,
                lineHeight = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "search",
                tint = WhiteText40,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// Секция со списком городов
@Composable
fun CitiesSection() {
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(420.dp),
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        // Город Томск
        CityItem(cityName = "Томск", time = "13:31", condition = "Облачно", temp = "-11℃", minMax = "Макс.: -6℃, мин.: -13℃")
        // Город Кемерово
        CityItem(cityName = "Кемерово", time = "13:31", condition = "Облачно", temp = "-10℃", minMax = "Макс.: -6℃, мин.: -13℃")
        // Город Новосибирск
        CityItem(cityName = "Новосибирск", time = "13:31", condition = "Облачно", temp = "-13℃", minMax = "Макс.: -6℃, мин.: -13℃")
    }
}

// Компонент для отображения информации о городе
@Composable
fun CityItem(cityName: String, time: String, condition: String, temp: String, minMax: String) {
    Row(
        modifier = Modifier
            .width(380.dp)
            .height(108.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        // Левая часть: название города, время, состояние
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .height(108.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(51.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = cityName,
                    color = WhiteText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily,
                    lineHeight = 29.sp,
                    letterSpacing = (-0.02).em
                )
                Text(
                    text = time,
                    color = WhiteText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = InterFontFamily,
                    lineHeight = 22.sp
                )
            }
            Text(
                text = condition,
                color = WhiteText,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFontFamily,
                lineHeight = 22.sp
            )
        }

        // Правая часть: температура и мин/макс
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .height(108.dp),

            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = temp,
                color = WhiteText,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = InterFontFamily,
                lineHeight = 64.sp,
                textAlign = TextAlign.Right,
                letterSpacing = (-0.03).em,
                modifier = Modifier.wrapContentWidth()
            )
            Text(
                text = minMax,
                color = WhiteText,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFontFamily,
                lineHeight = 22.sp,
                modifier = Modifier.width(190.dp)
            )
        }
    }
}