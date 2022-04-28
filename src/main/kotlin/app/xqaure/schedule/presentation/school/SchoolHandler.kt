package app.xqaure.schedule.presentation.school

import app.xqaure.schedule.application.school.SchoolUsecase
import app.xqaure.schedule.presentation.school.dto.AddScheduleRequest
import app.xqaure.schedule.presentation.school.dto.ModifyScheduleRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/school")
class SchoolHandler(
    private val schoolUsecase: SchoolUsecase
) {

    @GetMapping
    suspend fun getSchedule(request: ServerRequest): ServerResponse {
        TODO()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun addSchedule(@RequestBody @Valid request: AddScheduleRequest) =
        schoolUsecase.createSchedule(name = request.name!!, date = request.date!!)

    @PutMapping("/{scheduleId}")
    suspend fun modifySchedule(
        @PathVariable scheduleId: UUID,
        @RequestBody @Valid request: ModifyScheduleRequest
    ) = schoolUsecase.modifySchedule(uuid = scheduleId, name = request.name!!, date = request.date!!)

    @DeleteMapping("/{scheduleId}")
    suspend fun deleteSchedule(@PathVariable scheduleId: UUID) =
        schoolUsecase.deleteSchedule(scheduleId)
}
