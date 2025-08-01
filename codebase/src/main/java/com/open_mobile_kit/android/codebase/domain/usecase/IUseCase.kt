package com.open_mobile_kit.android.codebase.domain.usecase

import com.open_mobile_kit.android.codebase.application.ICommand
import com.open_mobile_kit.android.codebase.application.CommandOutput
import com.open_mobile_kit.android.codebase.data.exception.ExceptionBase

/**
 * Defines a single, specific business operation.
 *
 * Each use case is responsible for handling one specific command, orchestrating the flow of data
 * from repositories and other use cases to achieve a business goal.
 *
 * @param C The type of [ICommand] this use case handles.
 * @param R The type of the successful result.
 */
abstract class IUseCase<C : ICommand, R> {

    /**
     * Executes the business logic of the use case.
     *
     * @param command The command containing the input data for the use case.
     * @param output The [CommandOutput] handler to set the result or error.
     */
    abstract suspend fun execute(command: C, output: CommandOutput<R, ExceptionBase>)
}
