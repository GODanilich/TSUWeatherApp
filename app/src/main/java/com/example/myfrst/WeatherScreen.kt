package com.example.myfrst

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource


// Главный экран
@Composable
fun WeatherScreen(navController: NavController) {
    // Основной контейнер
    Column(
        modifier = Modifier
            .fillMaxSize() // Растягивается на весь экран
            .background(BlackBackground)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp), // Отступы по краям
        verticalArrangement = Arrangement.SpaceBetween, // Распределяет элементы с равными промежутками
        horizontalAlignment = Alignment.CenterHorizontally // Центрирует элементы по горизонтали
    ) {
        TopBar(navController) //Верхняя панель с кнопкой навигации и кнопкой настроек
        WeatherDetails() // Секция с дополнительной информацией: влажность, давление, ветер, восход, закат
        WeatherInfo(navController) // Основная информация
        HourlyForecast() // Прогноз погоды по часам
    }
}

// Верхняя панель с кнопкой навигации и кнопкой настроек
@Composable
fun TopBar(navController: NavController) {
    // Горизонтальный контейнер для размещения кнопок
    Row(
        modifier = Modifier
            .width(380.dp)
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // расположение кнопок по краям
        verticalAlignment = Alignment.CenterVertically // Центрирование по вертикали
    ) {
        // Кнопка навигации
        Button(
            onClick = { navController.navigate("choose_city") },
            modifier = Modifier
                .wrapContentWidth() // Ширина подстраивается под содержимое текста
                .height(32.dp)
                .padding(horizontal = 8.dp), // Внутренние отступы по горизонтали для текста
            shape = RoundedCornerShape(8.dp), // Скругление углов
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Прозрачный фон
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Отступ между иконкой и текстом
            ) {

            Icon(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Location",
                tint = WhiteText40,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = "Томск",
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
}


// Секция с дополнительной информацией
@Composable
fun WeatherDetails() {
    // Вертикальный контейнер
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(200.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp) // Отступ между верхним и нижним рядами
    ) {
        // Первый ряд: влажность, давление, ветер
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(98.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Отступ между элементами
        ) {
            // Влажность
            Column(
                modifier = Modifier
                    .width(118.67.dp)
                    .fillMaxHeight()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally, // Центрирование по горизонтали
                verticalArrangement = Arrangement.Center // Центрирование по вертикали
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp) // Отступ между текстом
                ) {
                    Text(
                        text = "Влажность",
                        color = WhiteText70,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily,
                        lineHeight = 20.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.humidity_icon),
                        contentDescription = "humidity",
                        tint = WhiteText40,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "80%",
                        color = WhiteText70,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily,
                        lineHeight = 22.sp
                    )
                }
            }

            // Давление
            Column(
                modifier = Modifier
                    .width(118.67.dp)
                    .fillMaxHeight()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Давление",
                        color = WhiteText70,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily,
                        lineHeight = 20.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.pressure_icon),
                        contentDescription = "pressure",
                        tint = WhiteText40,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "772 мм рт. ст.",
                        color = WhiteText70,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily,
                        lineHeight = 22.sp
                    )
                }
            }

            // Ветер
            Column(
                modifier = Modifier
                    .width(118.67.dp)
                    .fillMaxHeight()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Ветер",
                        color = WhiteText70,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily,
                        lineHeight = 20.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.wind_icon),
                        contentDescription = "wind",
                        tint = WhiteText40,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "3 м/с",
                        color = WhiteText70,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Второй ряд: восход и закат
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Восход
            Column(
                modifier = Modifier
                    .width(190.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Восход",
                    color = WhiteText70,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily,
                    lineHeight = 20.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.sunrise_icon),
                    contentDescription = "sunrise",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "08:10",
                    color = WhiteText70,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily,
                    lineHeight = 22.sp
                )
            }

            // Закат
            Column(
                modifier = Modifier
                    .width(190.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Закат",
                    color = WhiteText70,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily,
                    lineHeight = 20.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.sunset_icon),
                    contentDescription = "sunset",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "18:11",
                    color = WhiteText70,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily,
                    lineHeight = 22.sp
                )
            }
        }
    }
}


// Основная информация о погоде
@Composable
fun WeatherInfo(navController: NavController) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(212.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.current_location_icon),
                    contentDescription = "current location",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Томск",
                    color = WhiteText70,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = InterFontFamily,
                    lineHeight = 22.sp
                )
            }
            Text(
                text = "-11℃",
                color = WhiteText,
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = InterFontFamily,
                lineHeight = 86.sp,
                letterSpacing = (-0.03).em
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cloudy_weather_icon),
                    contentDescription = "cloudy weather",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Облачно",
                    color = WhiteText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = InterFontFamily,
                    lineHeight = 24.sp
                )
            }
        }

        // Кнопка переходf на SevenDaysScreen
        Button(
            onClick = { navController.navigate("seven_days") },
            modifier = Modifier
                .wrapContentWidth()
                .height(40.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Text(
                    text = "Погода на 7 дней",
                    color = WhiteText40,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily,
                    lineHeight = 16.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.translate_icon),
                    contentDescription = "translation",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

// Прогноз погоды по часам
@Composable
fun HourlyForecast() {
    // Состояние для горизонтальной прокрутки
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .width(380.dp)
            .height(226.dp)
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        // Карточки прогноза на каждый час
        WeatherCard(
            time = "Сейчас",
            weatherIcon = R.drawable.cloudy_weather_icon,
            temperature = "-11℃"
        )
        WeatherCard(
            time = "13",
            weatherIcon = R.drawable.rainy_weather_icon,
            temperature = "-10℃"
        )
        WeatherCard(
            time = "14",
            weatherIcon = R.drawable.rainy_weather_icon,
            temperature = "-9℃"
        )
        WeatherCard(
            time = "15",
            weatherIcon = R.drawable.stormy_weather_icon,
            temperature = "-8℃"
        )
        WeatherCard(
            time = "16",
            weatherIcon = R.drawable.cloudy_weather_icon,
            temperature = "-7℃"
        )
        WeatherCard(
            time = "17",
            weatherIcon = R.drawable.cloudy_weather_icon,
            temperature = "-6℃"
        )
        WeatherCard(
            time = "18",
            weatherIcon = R.drawable.cloudy_weather_icon,
            temperature = "-11℃"
        )
        WeatherCard(
            time = "19",
            weatherIcon = R.drawable.cloudy_weather_icon,
            temperature = "-11℃"
        )
    }
}

// Компонент карточки прогноза
@Composable
fun WeatherCard(time: String, weatherIcon: Int, temperature: String) {
    Column(
        modifier = Modifier
            .width(52.dp)
            .height(78.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = time,
            color = WhiteText70,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = InterFontFamily,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(id = weatherIcon),
                contentDescription = "Weather",
                tint = WhiteText70,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = temperature,
                color = WhiteText70,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFontFamily,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


