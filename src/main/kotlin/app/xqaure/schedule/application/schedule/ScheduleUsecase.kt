package app.xqaure.schedule.application.schedule

import app.xqaure.schedule.domain.schedule.Schedule
import app.xqaure.schedule.domain.schedule.ScheduleRepository
import app.xqaure.schedule.presentation.dto.BasicResponse
import app.xqaure.schedule.presentation.dto.ResponseCreator
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingleOrNull
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
    suspend fun modifySchedule(uuid: String, name: String, date: LocalDate, userId: String): BasicResponse {
        scheduleRepository.findScheduleByIdAndUserId(uuid, userId)
            .flatMap {
                scheduleRepository.save(
                    Schedule(
                        name = name,
                        date = date,
                        userId = userId
                    )
                )
            }.awaitSingleOrNull()

        deleteSchedule(uuid, userId)

        return responseCreator.onSuccess(
            code = MODIFY_SCHEDULE_CODE,
            propertyName = MODIFY_SCHEDULE_CODE,
            uuid
        )
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    suspend fun deleteSchedule(uuid: String, userId: String): BasicResponse {
        scheduleRepository.findScheduleByIdAndUserId(uuid, userId)
            .flatMap {
                scheduleRepository.deleteById(uuid)
            }.awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = DELETE_SCHEDULE_CODE,
            propertyName = DELETE_SCHEDULE_CODE,
            uuid
        )
    }
}
