package com.example.myfrst

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val BlackBackground = Color(0xFF000000)
val WhiteText = Color(0xFFFFFFFF)
val WhiteText70 = Color(0xB3FFFFFF) // Белый текст с 70% прозрачностью
val WhiteText40 = Color(0x66FFFFFF) // Белый текст с 40% прозрачностью


val DefaultFontFamily = FontFamily.Default


val InterFontFamily = FontFamily(
    Font(
        resId = R.font.inter_variable,
        weight = FontWeight.Normal
    )
)