package com.example.myfrst

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SevenDaysScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {

        TopSection(navController)

        WeeklyForecast()
    }
}


@Composable
fun TopSection(navController: NavController) {
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(160.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Панель с кнопками
        Row(
            modifier = Modifier
                .width(380.dp)
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Погода в Томске" с переходом на WeatherScreen
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

            // Кнопка настроек
            Button(
                onClick = { navController.navigate("settings") },
                modifier = Modifier
                    .size(32.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                //border = BorderStroke(1.6.dp, WhiteText40),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_icon),
                    contentDescription = "Settings",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Заголовок "Прогноз на неделю"
        Text(
            text = "Прогноз на неделю",
            color = WhiteText,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = InterFontFamily,
            lineHeight = 58.sp,
            letterSpacing = (-0.02).em,
            modifier = Modifier
                .width(380.dp)
                .height(116.dp)
        )
    }
}

// Секция прогноза на 7 дней
@Composable
fun WeeklyForecast() {
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(539.dp),
        verticalArrangement = Arrangement.spacedBy(56.dp)
    ) {
        // Прогноз по дням
        DayForecast(
            day = "Сегодня",
            weatherIcon = R.drawable.cloudy_weather_icon,
            minTemp = "-14℃",
            maxTemp = "-6℃"
        )
        DayForecast(
            day = "Пн",
            weatherIcon = R.drawable.rainy_weather_icon,
            minTemp = "-13℃",
            maxTemp = "-6℃"
        )
        DayForecast(
            day = "Вт",
            weatherIcon = R.drawable.sunny_weather_icon,
            minTemp = "-9℃",
            maxTemp = "-3℃"
        )
        DayForecast(
            day = "Ср",
            weatherIcon = R.drawable.sunny_weather_icon,
            minTemp = "-6℃",
            maxTemp = "-1℃"
        )
        DayForecast(
            day = "Чт",
            weatherIcon = R.drawable.snowy_weather_icon,
            minTemp = "-10℃",
            maxTemp = "-7℃"
        )
        DayForecast(
            day = "Пт",
            weatherIcon = R.drawable.snowy_weather_icon,
            minTemp = "-12℃",
            maxTemp = "-8℃"
        )
        DayForecast(
            day = "Сб",
            weatherIcon = R.drawable.cloudy_weather_icon,
            minTemp = "-11℃",
            maxTemp = "-7℃"
        )
    }
}

// Компонент для отображения прогноза на один день
@Composable
fun DayForecast(day: String, weatherIcon: Int, minTemp: String, maxTemp: String) {
    Row(
        modifier = Modifier
            .width(380.dp)
            .height(29.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // День недели и иконка погоды
        Row(
            modifier = Modifier
                .width(166.dp)
                .height(29.dp),
            horizontalArrangement = Arrangement.spacedBy(44.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = day,
                color = WhiteText,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFontFamily,
                lineHeight = 29.sp,
                letterSpacing = (-0.02).em,
                modifier = Modifier.width(98.dp)
            )
            Icon(
                painter = painterResource(id = weatherIcon),
                contentDescription = "Weather",
                tint = WhiteText,
                modifier = Modifier.size(24.dp)
            )
        }

        // Температура минимальная и максимальная
        Row(
            modifier = Modifier
                .width(172.dp)
                .height(29.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Минимальная температура
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down_icon),
                    contentDescription = "Min Temp",
                    tint = WhiteText40,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = minTemp,
                    color = WhiteText40,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily,
                    lineHeight = 29.sp,
                    letterSpacing = (-0.02).em
                )
            }

            // Максимальная температура
            Text(
                text = maxTemp,
                color = WhiteText,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFontFamily,
                lineHeight = 29.sp,
                letterSpacing = (-0.02).em
            )
        }
    }
}