package com.example.bonly.domain

fun calculateDaysBetween(
    startTimestampMillis: Long,
    endTimestampMillis: Long
): Int {
    if (endTimestampMillis <= startTimestampMillis) return 0
    val diffMillis = endTimestampMillis - startTimestampMillis
    val millisInDay = 1000L * 60 * 60 * 24
    return (diffMillis / millisInDay).toInt()
}
