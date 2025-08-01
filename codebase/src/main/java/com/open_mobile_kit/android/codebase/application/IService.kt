package com.open_mobile_kit.android.codebase.application

import com.open_mobile_kit.android.codebase.data.exception.UnknownErrorException
import com.open_mobile_kit.android.codebase.domain.usecase.IUseCase
import com.open_mobile_kit.android.codebase.data.exception.ExceptionBase
import kotlin.reflect.KClass

/**
 * The command bus for a specific feature or domain.
 *
 * It maintains a registry of commands and their corresponding use case handlers.
 * The service itself does not contain business logic; it delegates to the appropriate [IUseCase].
 * This decouples the command issuer (e.g., a ViewModel) from the command handler (the Use Case).
 */
abstract class IService {
    protected val useCaseRegistry = mutableMapOf<KClass<out ICommand>, IUseCase<out ICommand, Any>>()

    /**
     * Registers a [IUseCase] as the handler for a specific [ICommand].
     *
     * @param C The type of the command.
     * @param R The return type of the use case.
     * @param useCase The use case instance that will handle the command.
     */
    protected inline fun <reified C : ICommand, R> on(useCase: IUseCase<C, R>) {
        useCaseRegistry[C::class] = useCase as IUseCase<out ICommand, Any>
    }

    /**
     * Submits a command to the service for execution.
     *
     * The service looks up the registered [IUseCase] for the command, executes it,
     * and returns the result wrapped in a [CommandResult].
     *
     * @param command The command to execute.
     * @return A [CommandResult] containing either the success result or an error.
     */
    suspend fun <R> submitCommand(command: ICommand): CommandResult<R, ExceptionBase> {
        val useCase = useCaseRegistry[command::class] as? IUseCase<ICommand, R>
            ?: throw IllegalStateException("No use case registered for command ${command::class.simpleName}")

        val output = CommandOutput<R, ExceptionBase>()
        useCase.execute(command, output)

        return if (output.isSuccess) {
            CommandResult.Success(output.result as R)
        } else {
            CommandResult.Error(output.error ?: UnknownErrorException())
        }
    }
}
