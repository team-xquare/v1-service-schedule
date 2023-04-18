package app.xqaure.schedule.presentation.schedule

import app.xqaure.schedule.application.schedule.ScheduleUsecase
import app.xqaure.schedule.application.school.SchoolScheduleUsecase
import app.xqaure.schedule.global.exception.UnAuthorizedException
import app.xqaure.schedule.presentation.schedule.dto.AddScheduleRequest
import app.xqaure.schedule.presentation.schedule.dto.ModifyScheduleRequest
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI
import java.time.LocalDate

@Component
class ScheduleHandler(
    private val schoolScheduleUsecase: SchoolScheduleUsecase,
    private val scheduleUsecase: ScheduleUsecase,
) {

    suspend fun getSchoolSchedule(serverRequest: ServerRequest): ServerResponse {
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()
        val month = serverRequest.queryParam("month").orElse(LocalDate.now().monthValue.toString())
        val response = schoolScheduleUsecase.querySchoolSchedule(month = month.toInt(), userId = userId)

        return ServerResponse.ok().bodyValueAndAwait(response)
    }

    suspend fun addSchoolSchedule(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.getAddScheduleRequestBody()

        val response = schoolScheduleUsecase.createSchoolSchedule(name = request.name!!, date = request.date!!)

        return ServerResponse.created(URI("schedules/school")).bodyValueAndAwait(response)
    }

    suspend fun modifySchoolSchedule(
        serverRequest: ServerRequest
    ): ServerResponse {
        val schoolScheduleId = serverRequest.pathVariable("school-schedule-uuid")
        val request = serverRequest.getModifyScheduleRequestBody()
        val response = schoolScheduleUsecase.modifySchoolSchedule(
            uuid = schoolScheduleId,
            name = request.name,
            date = request.date
        )

        return ServerResponse.status(HttpStatus.NO_CONTENT).bodyValueAndAwait(response)
    }

    suspend fun deleteSchoolSchedule(serverRequest: ServerRequest): ServerResponse {
        val schoolScheduleId = serverRequest.pathVariable("school-schedule-uuid")
        val response = schoolScheduleUsecase.deleteSchoolSchedule(schoolScheduleId)

        return ServerResponse.status(HttpStatus.NO_CONTENT).bodyValueAndAwait(response)
    }

    suspend fun addSchedule(
        serverRequest: ServerRequest
    ): ServerResponse {
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()
        val request = serverRequest.getAddScheduleRequestBody()

        val response = scheduleUsecase.createSchedule(name = request.name!!, date = request.date!!, userId = userId)

        return ServerResponse.created(URI("/schedules/mine")).bodyValueAndAwait(response)
    }

    suspend fun modifySchedule(
        serverRequest: ServerRequest,
    ): ServerResponse {
        val scheduleId = serverRequest.pathVariable("schedule-uuid")
        val request = serverRequest.getModifyScheduleRequestBody()

        val response = scheduleUsecase.modifySchedule(
            uuid = scheduleId,
            name = request.name,
            date = request.date,
        )

        return ServerResponse.status(HttpStatus.NO_CONTENT).bodyValueAndAwait(response)
    }

    suspend fun deleteSchedule(serverRequest: ServerRequest): ServerResponse {
        val scheduleId = serverRequest.pathVariable("schedule-uuid")
        val userId = serverRequest.headers().firstHeader("Request-User-Id")
            ?: throw UnAuthorizedException()

        val response = scheduleUsecase.deleteSchedule(uuid = scheduleId, userId = userId)

        return ServerResponse.status(HttpStatus.NO_CONTENT).bodyValueAndAwait(response)
    }

    private suspend fun ServerRequest.getAddScheduleRequestBody() =
        this.bodyToMono<AddScheduleRequest>().awaitSingle()

    private suspend fun ServerRequest.getModifyScheduleRequestBody() =
        this.bodyToMono<ModifyScheduleRequest>().awaitSingle()

    suspend fun getIsHomecomingDay(serverRequest: ServerRequest): ServerResponse {
        val dateParam = serverRequest.queryParam("date").orElse(LocalDate.now().toString())
        val date = LocalDate.parse(dateParam)

        val response = schoolScheduleUsecase.queryIsHomecomingDay(date)

        return ServerResponse.ok().bodyValueAndAwait(response)
    }
}
