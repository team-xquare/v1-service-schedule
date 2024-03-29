package app.xqaure.schedule.application.school

import app.xqaure.schedule.application.schedule.exceptions.ScheduleNotFoundException
import app.xqaure.schedule.application.school.exceptions.SchoolScheduleNotFoundException
import app.xqaure.schedule.domain.schedule.ScheduleRepository
import app.xqaure.schedule.domain.school.SchoolSchedule
import app.xqaure.schedule.domain.school.SchoolScheduleRepository
import app.xqaure.schedule.presentation.dto.BasicResponse
import app.xqaure.schedule.presentation.dto.ResponseCreator
import app.xqaure.schedule.presentation.schedule.dto.QueryIsHomecomingDayResponse
import app.xqaure.schedule.presentation.schedule.dto.QueryScheduleListResponse
import app.xqaure.schedule.presentation.schedule.dto.ScheduleElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class SchoolScheduleUsecase(
    private val schoolScheduleRepository: SchoolScheduleRepository,
    private val scheduleRepository: ScheduleRepository,
    private val responseCreator: ResponseCreator,
) {

    companion object {
        const val ADD_SCHEDULE_CODE = "school.schedule.create"
        const val MODIFY_SCHEDULE_CODE = "school.schedule.modify"
        const val DELETE_SCHEDULE_CODE = "school.schedule.delete"
    }

    suspend fun createSchoolSchedule(name: String, date: LocalDate): BasicResponse {

        val schoolSchedule = schoolScheduleRepository.save(
            SchoolSchedule(
                name = name,
                date = date,
            )
        ).awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = ADD_SCHEDULE_CODE,
            propertyName = ADD_SCHEDULE_CODE,
            schoolSchedule?.id
        )
    }

    @Transactional
    suspend fun modifySchoolSchedule(id: String, name: String, date: LocalDate): BasicResponse {
        if (schoolScheduleRepository.updateSchoolSchedule(id, name, date) < 0) {
            throw SchoolScheduleNotFoundException()
        }

        return responseCreator.onSuccess(
            code = MODIFY_SCHEDULE_CODE,
            propertyName = MODIFY_SCHEDULE_CODE,
            id
        )
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    suspend fun deleteSchoolSchedule(id: String): BasicResponse {
        val schoolSchedule =
            schoolScheduleRepository.findById(id)
                .awaitSingleOrNull() ?: throw ScheduleNotFoundException()

        schoolScheduleRepository.delete(schoolSchedule)
            .awaitSingleOrNull()

        return responseCreator.onSuccess(
            code = DELETE_SCHEDULE_CODE,
            propertyName = DELETE_SCHEDULE_CODE,
            id
        )
    }

    suspend fun querySchoolSchedule(month: Int, userId: String): QueryScheduleListResponse {

        val students = mutableListOf<ScheduleElement>()

        val schoolScheduleList = withContext(Dispatchers.IO) {
            schoolScheduleRepository.findAll().filter { it.date.month.value == month }.map {
                ScheduleElement(
                    id = it.id,
                    name = it.name,
                    date = it.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                )
            }.collectList().awaitFirstOrNull()
                ?: throw SchoolScheduleNotFoundException()
        }

        val scheduleList = withContext(Dispatchers.IO) {
            scheduleRepository.findAllByUserId(userId).filter { it.date.month.value == month }.map {
                ScheduleElement(
                    id = it.id,
                    name = it.name,
                    date = it.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                )
            }.collectList().awaitFirstOrNull()
                ?: throw SchoolScheduleNotFoundException()
        }

        students.addAll(schoolScheduleList)
        students.addAll(scheduleList)
        students.sortBy { it.date }

        return QueryScheduleListResponse(students)
    }

    suspend fun queryIsHomecomingDay(date: LocalDate): QueryIsHomecomingDayResponse {
        val schoolSchedule = schoolScheduleRepository.findAllByDate(date)
            .any {
                it.name == "의무귀가"
            }.awaitFirstOrNull()
            ?: false

        return QueryIsHomecomingDayResponse(schoolSchedule)
    }
}
