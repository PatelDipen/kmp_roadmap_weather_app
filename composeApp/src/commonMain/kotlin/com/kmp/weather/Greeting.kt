package com.kmp.weather

class Greeting {

    fun greet(): String {

        return "Hello, ${BuildKonfig.OPEN_WEATHER_API_KEY}"
    }
}