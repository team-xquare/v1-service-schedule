package app.xqaure.schedule.presentation.root.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class RootHandler {
    suspend fun getScheduleByGroupId(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun modifySchedule(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun deleteSchedule(request: ServerRequest): ServerResponse {
        TODO()
    }
}
