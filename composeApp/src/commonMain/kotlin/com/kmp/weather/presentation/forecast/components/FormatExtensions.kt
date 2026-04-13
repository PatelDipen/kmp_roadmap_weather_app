package com.kmp.weather.presentation.forecast.components

import kotlin.math.round

internal fun Double.toOneDecimalString(): String {
    val rounded = round(this * 10) / 10
    return rounded.toString()
}

