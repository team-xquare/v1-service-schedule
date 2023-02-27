package app.xqaure.schedule.presentation.schedule

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ScheduleRouter {

    @Bean
    fun userBaseRouter(scheduleHandler: ScheduleHandler) = coRouter {
        "/points".nest {
            contentType(MediaType.APPLICATION_JSON)
            POST("/schedule/mine", scheduleHandler::addSchedule)
            PUT("/schedule/mine/{schedule-id}", scheduleHandler::modifySchedule)
            DELETE("/schedule/mine/{schedule-id}", scheduleHandler::deleteSchedule)
        }
    }
}