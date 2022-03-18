package app.xqaure.schedule.global.error

import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleException(e: WebExchangeBindException): ErrorResponse =
        ErrorResponse(
            code = 400,
            message = e
                .bindingResult
                .allErrors
                .mapNotNull(DefaultMessageSourceResolvable::getDefaultMessage)
        )
}
