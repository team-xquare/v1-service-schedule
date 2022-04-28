package app.xqaure.schedule.global.error

import app.xqaure.schedule.global.error.exception.BusinessException
import app.xqaure.schedule.presentation.dto.ResponseCreator
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class GlobalExceptionHandler(
    private val responseCreator: ResponseCreator
) {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleException(e: WebExchangeBindException): ErrorResponse =
        ErrorResponse(
            code = 400,
            message = e
                .bindingResult
                .allErrors
                .mapNotNull(DefaultMessageSourceResolvable::getDefaultMessage)
        )

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode

        return ResponseEntity
            .status(errorCode.status)
            .body(responseCreator.onError(errorCode.status, errorCode.code))
    }
}
