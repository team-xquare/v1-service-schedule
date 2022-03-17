package app.xqaure.schedule.application.school

import app.xqaure.schedule.domain.school.SchoolSchedule
import app.xqaure.schedule.domain.school.SchoolScheduleRepository
import app.xqaure.schedule.presentation.dto.BasicResponse
import app.xqaure.schedule.presentation.dto.ResponseCreator
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SchoolUsecase(
    private val schoolScheduleRepository: SchoolScheduleRepository,
    private val responseCreator: ResponseCreator
) {

    companion object {
        const val ADD_SCHEDULE_CODE = "school.schedule.create"
    }

    suspend fun createSchedule(name: String, date: LocalDate): BasicResponse {

        val schoolSchedule = schoolScheduleRepository.save(
            SchoolSchedule(
                name = name,
                date = date
            )
        ).awaitSingleOrNull()

        return responseCreator.fromMessageSource(
            code = ADD_SCHEDULE_CODE,
            propertyName = ADD_SCHEDULE_CODE,
            schoolSchedule?.id
        )
    }
}
