package com.kmp.weather

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {

        return "Hello, ${platform.name}! ${BuildKonfig.OPEN_WEATHER_API_KEY}"
    }
}