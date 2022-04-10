package com.gb.stopwatch.domain.entity


interface TimestampProvider {
    fun getMilliseconds(): Long
}
