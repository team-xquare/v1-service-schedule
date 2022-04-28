package app.xqaure.schedule.application.school

import app.xqaure.schedule.application.school.exceptions.ScheduleNotFoundException
import app.xqaure.schedule.domain.school.SchoolSchedule
import app.xqaure.schedule.domain.school.SchoolScheduleRepository
import app.xqaure.schedule.presentation.dto.BasicResponse
import app.xqaure.schedule.presentation.dto.ResponseCreator
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.UUID

@Service
class SchoolUsecase(
    private val schoolScheduleRepository: SchoolScheduleRepository,
    private val responseCreator: ResponseCreator
) {

    companion object {
        const val ADD_SCHEDULE_CODE = "school.schedule.create"
        const val MODIFY_SCHEDULE_CODE = "school.schedule.modify"
        const val DELETE_SCHEDULE_CODE = "school.schedule.delete"
    }

    suspend fun createSchedule(name: String, date: LocalDate): BasicResponse {

        val schoolSchedule = schoolScheduleRepository.save(
            SchoolSchedule(
                name = name,
                date = date,
                isNew = true
            )
        ).awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = ADD_SCHEDULE_CODE,
            propertyName = ADD_SCHEDULE_CODE,
            schoolSchedule?.id
        )
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    suspend fun modifySchedule(uuid: UUID, name: String, date: LocalDate): BasicResponse {

        val schoolSchedule = schoolScheduleRepository.findById(uuid)
            .awaitSingleOrNull() ?: throw ScheduleNotFoundException()

        schoolSchedule.name = name

        return responseCreator.onSuccess(
            code = MODIFY_SCHEDULE_CODE,
            propertyName = MODIFY_SCHEDULE_CODE,
            schoolSchedule.id
        )
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    suspend fun deleteSchedule(uuid: UUID): BasicResponse {

        val schoolSchedule = schoolScheduleRepository.findById(uuid)
            .awaitSingleOrNull() ?: throw ScheduleNotFoundException()

        schoolScheduleRepository.delete(schoolSchedule)
            .awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = DELETE_SCHEDULE_CODE,
            propertyName = DELETE_SCHEDULE_CODE,
            schoolSchedule.id
        )
    }
}
