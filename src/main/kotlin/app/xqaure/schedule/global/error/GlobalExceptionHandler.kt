package app.xqaure.schedule.global.error

import app.xqaure.schedule.global.error.exception.BusinessException
import app.xqaure.schedule.global.error.exception.ExceptionProperty
import app.xqaure.schedule.global.exception.BadRequestException
import app.xqaure.schedule.global.exception.InternalServerException
import app.xqaure.schedule.global.exception.RequestHandlerNotFoundException
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebInputException
import reactor.core.publisher.Mono

@Order(-2)
@Component
class GlobalExceptionHandler(
    errorAttributes: ErrorAttributes,
    webProperties: WebProperties,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(
    errorAttributes,
    webProperties.resources,
    applicationContext
) {

    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> =
        RouterFunctions.route(RequestPredicates.all(), this::handleError)

    private fun handleError(request: ServerRequest): Mono<ServerResponse> =
        when (val throwable = super.getError(request)) {
            is BusinessException -> throwable.toErrorResponse()
            is ServerWebInputException, is NoSuchElementException -> BadRequestException().toErrorResponse()
            is ResponseStatusException -> RequestHandlerNotFoundException().toErrorResponse()
            else -> InternalServerException().toErrorResponse()
        }

    private fun ExceptionProperty.toErrorResponse() =
        ServerResponse.status(this.errorCode.status)
            .bodyValue(
                ErrorResponse(
                    errorMessage = this.errorCode.code,
                    responseStatus = this.errorCode.status
                )
            )
}
