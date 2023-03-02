package app.xqaure.schedule.presentation.schedule

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ScheduleRouter {

    @Bean
    fun schoolScheduleRouter(scheduleHandler: ScheduleHandler) = coRouter {
        "/schedules".nest {
            contentType(MediaType.APPLICATION_JSON)
            GET("/school", scheduleHandler::getSchoolSchedule)
            POST("/school", scheduleHandler::addSchoolSchedule)
            PUT("/school/{school-schedule-uuid}", scheduleHandler::modifySchoolSchedule)
            DELETE("/school/{school-schedule-uuid}", scheduleHandler::deleteSchoolSchedule)
            POST("/mine", scheduleHandler::addSchedule)
            PUT("/mine/{schedule-uuid}", scheduleHandler::modifySchedule)
            DELETE("/mine/{schedule-uuid}", scheduleHandler::deleteSchedule)
        }
    }
}