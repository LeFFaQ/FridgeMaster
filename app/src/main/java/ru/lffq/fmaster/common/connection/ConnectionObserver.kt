package ru.lffq.fmaster.common.connection

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost, Unknown
    }
}