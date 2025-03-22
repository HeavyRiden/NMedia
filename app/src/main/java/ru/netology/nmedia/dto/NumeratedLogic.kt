package ru.netology.nmedia.dto

import java.util.Locale
import kotlin.math.floor

fun numLogic(number: Long): String {
    return when {
        (number % 1_000L == 0L) -> (number / 1000).toString() + "K"
        number in 1_000..9_999 -> String.format(Locale.US,"%.1f", floor(number / 100.0) / 10) + "K"
        number in 10_000..999_999 -> (number / 1000).toString() + "K"
        number >= 1_000_000 -> String.format(Locale.US,"%.1f", floor(number / 100_000.0) / 10) + "M"
        else -> number.toString()
    }
}