package com.example.myfrst


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Экран настроек
@Composable
fun SettingsScreen(navController: NavController) {
    // Получаем предыдущий экран из аргументов навигации
    val previousRoute = navController.previousBackStackEntry?.destination?.route ?: "weather"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.Start
    ) {

        TopSection(navController, previousRoute)

        SettingsSection()
    }
}


@Composable
fun TopSection(navController: NavController, previousRoute: String) {
    // Определение текст кнопки возврата в зависимости от предыдущего экрана
    val backButtonText = when (previousRoute) {
        "weather" -> "Погода в Томске"
        "seven_days" -> "Прогноз на 7 дней"
        else -> "Назад" // По умолчанию
    }
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(102.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Button(
            onClick = { navController.navigate(previousRoute) },
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
                    text = backButtonText,
                    color = WhiteText40,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily,
                    lineHeight = 16.sp
                )
            }
        }

        // Заголовок "Настройки"
        Text(
            text = "Настройки",
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
    }
}


@Composable
fun SettingsSection() {
    Column(
        modifier = Modifier
            .width(380.dp)
            .height(166.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        SwitchField(label = "Геолокация", initialChecked = true)

        SwitchField(label = "Уведомления", initialChecked = false)

        SelectField()
    }
}

// Компонент переключателя
@Composable
fun SwitchField(label: String, initialChecked: Boolean) {
    val checked = remember { mutableStateOf(initialChecked) }
    Row(
        modifier = Modifier
            .width(380.dp)
            .height(24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = WhiteText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = InterFontFamily,
            lineHeight = 22.sp,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            modifier = Modifier
                .width(40.dp)
                .height(24.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF1E1E1E),
                checkedTrackColor = Color(0xFFF5F5F5),
                uncheckedThumbColor = Color(0xFFB3B3B3),
                uncheckedTrackColor = Color(0xFF1E1E1E),
                uncheckedBorderColor = Color(0xFFB3B3B3)
            )
        )
    }
}

// Компонент поля выбора с выпадающим списком
@Composable
fun SelectField() {
    var expanded by remember { mutableStateOf(false) } // Состояние для открытия/закрытия списка
    var selectedOption by remember { mutableStateOf("Градусы Цельсия") } // Выбранная опция

    Column(
        modifier = Modifier
            .width(380.dp)
            .height(70.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Единицы измерения",
            color = WhiteText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = InterFontFamily,
            lineHeight = 22.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Box {
            Row(
                modifier = Modifier
                    .width(380.dp)
                    .height(40.dp)
                    .background(Color(0xFF1E1E1E), RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFF444444), RoundedCornerShape(8.dp))
                    .clickable { expanded = true }
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption,
                    color = WhiteText,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily,
                    lineHeight = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.combobox_arrow_icon),
                    contentDescription = "Dropdown Arrow",
                    tint = WhiteText40,
                    modifier = Modifier.size(16.dp)
                )
            }

            // Выпадающий список
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }, // Закрытие при клике вне списка
                modifier = Modifier
                    .width(380.dp)
                    .background(Color(0xFF1E1E1E), RoundedCornerShape(1.dp))
                    .border(1.dp, Color(0xFF444444), RoundedCornerShape(10.dp))
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Градусы Цельсия",
                            color = WhiteText,
                            fontSize = 16.sp,
                            fontFamily = InterFontFamily
                        )
                    },
                    onClick = {
                        selectedOption = "Градусы Цельсия"
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Градусы Фаренгейта",
                            color = WhiteText,
                            fontSize = 16.sp,
                            fontFamily = InterFontFamily
                        )
                    },
                    onClick = {
                        selectedOption = "Градусы Фаренгейта"
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }
}