package app.xqaure.schedule.presentation.schedule

import app.xqaure.schedule.application.schedule.ScheduleUsecase
import app.xqaure.schedule.global.exception.UnAuthorizedException
import app.xqaure.schedule.presentation.school.dto.AddScheduleRequest
import app.xqaure.schedule.presentation.school.dto.ModifyScheduleRequest
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI
import java.util.*
import javax.validation.Valid

@RequestMapping("/mine")
@RestController
class ScheduleHandler(
    private val scheduleUsecase: ScheduleUsecase,
) {

    @PostMapping
    suspend fun addSchedule(
        serverRequest: ServerRequest
    ): ServerResponse {
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()
        val request = serverRequest.getAddScheduleRequestBody()

        scheduleUsecase.createSchedule(name = request.name, date = request.date, userId = userId)

        return ServerResponse.created(URI("/schedules")).buildAndAwait()
    }

    @PutMapping("/{schedule-id}")
    suspend fun modifySchedule(
        serverRequest: ServerRequest,
    ): ServerResponse {
        val scheduleId = serverRequest.pathVariable("schedule-id")
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()

        val request = serverRequest.getModifyScheduleRequestBody()

        scheduleUsecase.modifySchedule(
            uuid = UUID.fromString(scheduleId),
            name = request.name,
            date = request.date,
            userId = userId
        )

        return ServerResponse.noContent().buildAndAwait()
    }

    @DeleteMapping("/{scheduleId}")
    suspend fun deleteSchedule(serverRequest: ServerRequest): ServerResponse {
        val scheduleId = serverRequest.pathVariable("schedule-id")
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()

        scheduleUsecase.deleteSchedule(uuid = UUID.fromString(scheduleId), userId = userId)

        return ServerResponse.noContent().buildAndAwait()
    }

    private suspend fun ServerRequest.getAddScheduleRequestBody() =
        this.bodyToMono<ModifyScheduleRequest>().awaitSingle()

    private suspend fun ServerRequest.getModifyScheduleRequestBody() =
        this.bodyToMono<ModifyScheduleRequest>().awaitSingle()
}
