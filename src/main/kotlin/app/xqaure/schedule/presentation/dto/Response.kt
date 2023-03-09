package app.xqaure.schedule.presentation.dto

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
}

data class BasicResponse(
    val code: String,
    val message: String
)
