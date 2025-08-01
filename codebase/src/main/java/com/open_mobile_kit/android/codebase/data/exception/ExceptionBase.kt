package com.open_mobile_kit.android.codebase.data.exception

/**
 * Base class for all custom exceptions in the application.
 *
 * Using a custom base exception allows for more structured error handling
 * and prevents catching generic [Exception] types, which can hide bugs.
 *
 * @param message A descriptive message for the exception.
 * @param cause The original cause of the exception, if any.
 */
abstract class ExceptionBase(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause)
