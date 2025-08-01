package com.open_mobile_kit.android.codebase.presentation.state_management.bloc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

typealias EventHandler<E, S> = suspend (event: E, emitter: Emitter<S>) -> Unit

abstract class Bloc<E : Event, S : State, SE : SideEffect>(
    initialState: S,
    private val observer: BlocObserver? = null
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event = Channel<E>()
    private val event = _event.receiveAsFlow()

    private val _sideEffect: Channel<SE> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    private val eventHandlers = mutableMapOf<Class<out E>, Pair<EventHandler<E, S>, EventTransformer<E>?>>()

    private fun getEmitter(event: E) = object : Emitter<S> {
        override fun call(state: S) {
            if (_state.value == state) return
            val transition = Transition(
                currentState = _state.value,
                event = event,
                nextState = state
            )
            observer?.onTransition(transition)
            _state.value = state
        }
    }

    init {
        subscribeEvents()
    }

    fun add(event: E) {
        observer?.onEvent(event)
        viewModelScope.launch {
            _event.send(event)
        }
    }

    protected fun postSideEffect(effect: SE) {
        viewModelScope.launch {
            _sideEffect.send(effect)
        }
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : E> on(
        eventType: Class<T>,
        handler: EventHandler<T, S>,
        transformer: EventTransformer<T>? = null
    ) {
        eventHandlers[eventType] = Pair(handler as EventHandler<E, S>, transformer as EventTransformer<E>?)
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect { event ->
                val (handler, transformer) = eventHandlers[event.javaClass] ?: return@collect
                val eventFlow = flowOf(event)
                val transformedFlow = transformer?.invoke(eventFlow) ?: eventFlow
                transformedFlow.collect { transformedEvent ->
                    try {
                        handler(transformedEvent, getEmitter(transformedEvent))
                    } catch (e: Exception) {
                        observer?.onError(e)
                    }
                }
            }
        }
    }
}
