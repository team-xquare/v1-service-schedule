package app.xqaure.schedule.presentation.filter

import org.slf4j.Logger
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBodyOrNull

/**
 * Logging requests.
 *
 * Returns return value of handler.
 *
 * Usage
 * ```
 * @Bean
 * fun rootRouter() = coRouter {
 *      accept(APPLICATION_JSON).nest {
 *          GET("", handler::someFunction)
 *          filter { request, handler -> logFilter(request, handler, logger) }
 *      }
 * }
```
 *
 * @see org.springframework.web.reactive.function.server.CoRouterFunctionDsl.filter
 * @return [org.springframework.web.reactive.function.server.ServerRequest]
 */
suspend inline fun logFilter(
    request: ServerRequest,
    crossinline handler: suspend (ServerRequest) -> ServerResponse,
    logger: Logger
): ServerResponse {

    val requestBody = request.awaitBodyOrNull<String>()
    logger.info("Path: ${request.path()},  Body: $requestBody")

    val newRequest = ServerRequest.from(request).body(requestBody ?: "").build()
    return handler(newRequest)
}
