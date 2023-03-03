package app.xqaure.schedule.presentation.schedule

import app.xqaure.schedule.application.schedule.ScheduleUsecase
import app.xqaure.schedule.application.school.SchoolScheduleUsecase
import app.xqaure.schedule.global.exception.UnAuthorizedException
import app.xqaure.schedule.presentation.schedule.dto.AddScheduleRequest
import app.xqaure.schedule.presentation.schedule.dto.ModifyScheduleRequest
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI
import java.util.*

@Component
class ScheduleHandler(
    private val schoolScheduleUsecase: SchoolScheduleUsecase,
    private val scheduleUsecase: ScheduleUsecase,
) {

    suspend fun getSchoolSchedule(serverRequest: ServerRequest): ServerResponse {
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()
        val month = serverRequest.queryParam("month").orElse("")
        val response = schoolScheduleUsecase.querySchoolSchedule(month = month.toInt(), userId = userId)

        return ServerResponse.ok().bodyValueAndAwait(response)
    }

    suspend fun addSchoolSchedule(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.getAddScheduleRequestBody()

        schoolScheduleUsecase.createSchoolSchedule(name = request.name!!, date = request.date!!)

        return ServerResponse.created(URI("schedules/school")).buildAndAwait()
    }

    suspend fun modifySchoolSchedule(
        serverRequest: ServerRequest
    ): ServerResponse {
        val schoolScheduleId = serverRequest.pathVariable("school-schedule-uuid")
        val request = serverRequest.getModifyScheduleRequestBody()
        schoolScheduleUsecase.modifySchoolSchedule(
            uuid = schoolScheduleId,
            name = request.name,
            date = request.date
        )

        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun deleteSchoolSchedule(serverRequest: ServerRequest): ServerResponse {
        val schoolScheduleId = serverRequest.pathVariable("school-schedule-uuid")
        schoolScheduleUsecase.deleteSchoolSchedule(schoolScheduleId)

        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun addSchedule(
        serverRequest: ServerRequest
    ): ServerResponse {
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()
        val request = serverRequest.getAddScheduleRequestBody()

        scheduleUsecase.createSchedule(name = request.name!!, date = request.date!!, userId = userId)

        return ServerResponse.created(URI("/schedules/mine")).buildAndAwait()
    }

    suspend fun modifySchedule(
        serverRequest: ServerRequest,
    ): ServerResponse {
        val scheduleId = serverRequest.pathVariable("schedule-uuid")
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()
        val request = serverRequest.getModifyScheduleRequestBody()

        scheduleUsecase.modifySchedule(
            uuid = scheduleId,
            name = request.name,
            date = request.date,
            userId = userId
        )

        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun deleteSchedule(serverRequest: ServerRequest): ServerResponse {
        val scheduleId = serverRequest.pathVariable("schedule-uuid")
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()

        scheduleUsecase.deleteSchedule(uuid = scheduleId, userId = userId)

        return ServerResponse.noContent().buildAndAwait()
    }

    private suspend fun ServerRequest.getAddScheduleRequestBody() =
        this.bodyToMono<AddScheduleRequest>().awaitSingle()

    private suspend fun ServerRequest.getModifyScheduleRequestBody() =
        this.bodyToMono<ModifyScheduleRequest>().awaitSingle()
}
