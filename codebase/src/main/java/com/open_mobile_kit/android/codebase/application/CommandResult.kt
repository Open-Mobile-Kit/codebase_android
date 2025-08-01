package com.open_mobile_kit.android.codebase.application

import com.open_mobile_kit.android.codebase.data.exception.ExceptionBase

/**
 * A sealed class that represents the result of a command execution.
 * It can either be a [Success] or an [Error].
 *
 * @param R The type of the successful result.
 * @param E The type of the error, which must extend [ExceptionBase].
 */
sealed class CommandResult<out R, out E : ExceptionBase> {

    /**
     * Represents a successful command execution.
     * @property result The result of the command.
     */
    data class Success<out R>(val result: R) : CommandResult<R, Nothing>()

    /**
     * Represents a failed command execution.
     * @property error The error that occurred.
     */
    data class Error<out E : ExceptionBase>(val error: E) : CommandResult<Nothing, E>()

    /**
     * Returns `true` if the result is [Success], `false` otherwise.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Returns `true` if the result is [Error], `false` otherwise.
     */
    val isError: Boolean get() = this is Error

    /**
     * Returns the result if this is a [Success], or `null` if it is an [Error].
     */
    fun getOrNull(): R? = if (this is Success) result else null

    /**
     * Returns the error if this is an [Error], or `null` if it is a [Success].
     */
    fun errorOrNull(): E? = if (this is Error) error else null
}
