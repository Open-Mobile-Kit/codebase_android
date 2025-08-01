package com.open_mobile_kit.android.codebase.presentation.state_management.bloc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

typealias EventTransformer<E> = (Flow<E>) -> Flow<E>

object EventTransformers {
    fun <T> debounce(durationMillis: Long): EventTransformer<T> {
        return { events -> events.debounce(durationMillis) }
    }

    fun <T> distinctUntilChanged(): EventTransformer<T> {
        return { events -> events.distinctUntilChanged() }
    }
}
