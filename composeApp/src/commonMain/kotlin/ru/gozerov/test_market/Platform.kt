package ru.gozerov.test_market

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform