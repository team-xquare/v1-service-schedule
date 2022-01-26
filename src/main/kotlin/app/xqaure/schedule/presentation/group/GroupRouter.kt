package app.xqaure.schedule.presentation.group

import app.xqaure.schedule.global.log.logger
import app.xqaure.schedule.presentation.filter.logFilter
import app.xqaure.schedule.presentation.group.handler.GroupHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class GroupRouter(
    private val groupHandler: GroupHandler
) {

    val logger = logger()

    @Bean
    fun groupRouter() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "/group".nest {
                POST("", groupHandler::createGroup)
                POST("/groupId", groupHandler::addMemberToGroup)
                DELETE("/{scheduleId}/member/{userId}", groupHandler::deleteMemberInGroup)
                PATCH("/{scheduleId}/member/{userId}", groupHandler::modifyMemberPermission)
            }

            filter { request, handler -> logFilter(request, handler, logger) }
        }
    }
}
