package app.xqaure.schedule.presentation.school

import app.xqaure.schedule.application.school.SchoolUsecase
import app.xqaure.schedule.presentation.school.dto.AddScheduleRequest
import app.xqaure.schedule.presentation.school.dto.ModifyScheduleRequest
import app.xqaure.schedule.presentation.school.dto.QueryScheduleListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/school")
@RestController
class SchoolScheduleHandler(
    private val schoolUsecase: SchoolUsecase
) {

    @GetMapping
    suspend fun getSchedule(@RequestParam month: Int): QueryScheduleListResponse {
        return schoolUsecase.querySchoolSchedule(month)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun addSchedule(@RequestBody @Valid request: AddScheduleRequest) =
        schoolUsecase.createSchoolSchedule(name = request.name!!, date = request.date!!)

    @PutMapping("/{schoolScheduleId}")
    suspend fun modifySchedule(
        @PathVariable schoolScheduleId: UUID,
        @RequestBody @Valid request: ModifyScheduleRequest
    ) = schoolUsecase.modifySchoolSchedule(uuid = schoolScheduleId, name = request.name!!, date = request.date!!)

    @DeleteMapping("/{schoolScheduleId}")
    suspend fun deleteSchedule(@PathVariable schoolScheduleId: UUID) =
        schoolUsecase.deleteSchoolSchedule(schoolScheduleId)
}
