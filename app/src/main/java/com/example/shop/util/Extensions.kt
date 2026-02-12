package com.example.shop.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.toPriceString(): String {
    val format = DecimalFormat("#,##0.##")
    return "$${format.format(this)} usd"
}

fun Double.toDiscountedPrice(discountPercent: Int): Double {
    return this * (1 - discountPercent / 100.0)
}
fun String.truncate(maxLength: Int): String {
    return if (this.length > maxLength) "${this.take(maxLength)}..." else this
}
fun <T> List<T>.toPairs(): List<Pair<T, T?>> {
    return this.chunked(2).map { chunk ->
        Pair(chunk[0], chunk.getOrNull(1))
    }
}

fun Int.toFormattedString(): String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}
