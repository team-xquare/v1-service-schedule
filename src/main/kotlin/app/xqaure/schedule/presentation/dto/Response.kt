package app.xqaure.schedule.presentation.dto

import app.xqaure.schedule.global.error.ErrorResponse
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
class ResponseCreator(
    private val messageSource: MessageSource
) {
    fun onSuccess(
        code: String,
        propertyName: String,
        vararg args: Any?
    ): BasicResponse {
        return BasicResponse(
            code = code,
            message = messageSource.getMessage(propertyName, args, LocaleContextHolder.getLocale())
        )
    }

    fun onError(
        code: Int,
        propertyName: String,
        vararg args: Any?
    ): ErrorResponse {
        return ErrorResponse(
            code = code,
            message = listOf(messageSource.getMessage(propertyName, args, LocaleContextHolder.getLocale()))
        )
    }
}

data class BasicResponse(
    val code: String,
    val message: String
)
