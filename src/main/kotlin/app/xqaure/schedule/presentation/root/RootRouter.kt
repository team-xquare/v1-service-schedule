package app.xqaure.schedule.presentation.root

import app.xqaure.schedule.global.log.logger
import app.xqaure.schedule.presentation.filter.logFilter
import app.xqaure.schedule.presentation.root.handler.RootHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RootRouter(
    private val rootHandler: RootHandler,
) {

    val logger = logger()

    @Bean
    fun rootRouter() = coRouter {
        accept(APPLICATION_JSON).nest {
            POST("/{groupId}", rootHandler::getScheduleByGroupId)
            PUT("/{scheduleId}", rootHandler::modifySchedule)
            DELETE("/{scheduleId}", rootHandler::deleteSchedule)

            filter { request, handler -> logFilter(request, handler, logger) }
        }
    }
}
