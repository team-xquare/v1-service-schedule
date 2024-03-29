package app.xqaure.schedule.application.schedule

import app.xqaure.schedule.application.schedule.exceptions.ScheduleNotFoundException
import app.xqaure.schedule.domain.schedule.Schedule
import app.xqaure.schedule.domain.schedule.ScheduleRepository
import app.xqaure.schedule.presentation.dto.BasicResponse
import app.xqaure.schedule.presentation.dto.ResponseCreator
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ScheduleUsecase(
    private val scheduleRepository: ScheduleRepository,
    private val responseCreator: ResponseCreator,
) {

    companion object {
        const val ADD_SCHEDULE_CODE = "school.schedule.create"
        const val MODIFY_SCHEDULE_CODE = "school.schedule.modify"
        const val DELETE_SCHEDULE_CODE = "school.schedule.delete"
    }

    suspend fun createSchedule(name: String, date: LocalDate, userId: String): BasicResponse {
        val schedule = scheduleRepository.save(
            Schedule(
                name = name,
                date = date,
                userId = userId,
            )
        ).awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = ADD_SCHEDULE_CODE,
            propertyName = ADD_SCHEDULE_CODE,
            schedule?.id
        )
    }

    @Transactional
    suspend fun modifySchedule(uuid: String, name: String, date: LocalDate): BasicResponse {
        if (scheduleRepository.updateSchedule(uuid, name, date) < 1) {
            throw ScheduleNotFoundException()
        }
        return responseCreator.onSuccess(
            code = MODIFY_SCHEDULE_CODE,
            propertyName = MODIFY_SCHEDULE_CODE,
            uuid
        )
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    suspend fun deleteSchedule(uuid: String, userId: String): BasicResponse {
        val schedule = scheduleRepository.findScheduleByIdAndUserId(uuid, userId)
            .awaitSingleOrNull() ?: throw ScheduleNotFoundException()

        scheduleRepository.delete(schedule)
            .awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = DELETE_SCHEDULE_CODE,
            propertyName = DELETE_SCHEDULE_CODE,
            uuid
        )
    }
}
