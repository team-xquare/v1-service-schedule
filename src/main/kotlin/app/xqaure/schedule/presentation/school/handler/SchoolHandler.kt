package app.xqaure.schedule.presentation.school.handler

import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Controller
class SchoolHandler {
    suspend fun getSchedule(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun addSchedule(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun modifySchedule(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun deleteSchedule(request: ServerRequest): ServerResponse {
        TODO()
    }
}
