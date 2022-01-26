package app.xqaure.schedule.presentation.school

import app.xqaure.schedule.global.log.logger
import app.xqaure.schedule.presentation.filter.logFilter
import app.xqaure.schedule.presentation.school.handler.SchoolHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class SchoolRouter(
    private val schoolHandler: SchoolHandler
) {

    val logger = logger()

    @Bean
    fun schoolRouter() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "/school".nest {
                GET("", schoolHandler::getSchedule)
                POST("", schoolHandler::addSchedule)
                PUT("/{scheduleId}", schoolHandler::modifySchedule)
                DELETE("/{scheduleId}", schoolHandler::deleteSchedule)
            }

            filter { request, handler -> logFilter(request, handler, logger) }
        }
    }
}
