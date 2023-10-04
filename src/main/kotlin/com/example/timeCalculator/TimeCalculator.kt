package com.example.timeCalculator

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun timeTaken(time1: String, time: String): Float {
    val startTime = LocalTime.parse(time1, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()
    val endTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()
    return ((endTime - startTime) / 3600f)
}
fun time(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalDateTime.now().format(formatter)
}
