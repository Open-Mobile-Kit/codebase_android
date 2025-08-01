package com.open_mobile_kit.android.codebase.application

import com.open_mobile_kit.android.codebase.data.exception.ExceptionBase
import com.open_mobile_kit.android.codebase.domain.usecase.IUseCase

/**
 * A handler for the output of a [IUseCase].
 *
 * This class is passed to the [IUseCase.execute] method to allow the use case
 * to set its success result or error. This avoids returning values directly,
 * which simplifies the [IService] implementation.
 *
 * @param R The type of the successful result.
 * @param E The type of the error, which must extend [ExceptionBase].
 */
class CommandOutput<R, E : ExceptionBase> {
    private var _result: R? = null
    val result: R? get() = _result

    private var _error: E? = null
    val error: E? get() = _error

    val isSuccess: Boolean get() = _result != null && _error == null

    /**
     * Sets the successful result of the command.
     */
    fun setSuccess(result: R) {
        _result = result
        _error = null
    }

    /**
     * Sets the error that occurred during command execution.
     */
    fun setError(error: E) {
        _error = error
        _result = null
    }
}
