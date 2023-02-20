package app.xqaure.schedule.presentation.schedule

import app.xqaure.schedule.application.schedule.ScheduleUsecase
import app.xqaure.schedule.presentation.school.dto.AddScheduleRequest
import app.xqaure.schedule.presentation.school.dto.ModifyScheduleRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RequestMapping("/mine")
@RestController
class ScheduleHandler(
    private val scheduleUsecase: ScheduleUsecase,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun addSchedule(@RequestBody @Valid request: AddScheduleRequest) =
        scheduleUsecase.createSchedule(name = request.name!!, date = request.date!!)

    @PutMapping("/{schedule-id}")
    suspend fun modifySchedule(
        @PathVariable("schedule-id") scheduleId: UUID,
        @RequestBody @Valid request: ModifyScheduleRequest
    ) = scheduleUsecase.modifySchedule(uuid = scheduleId, name = request.name, date = request.date)

    @DeleteMapping("/{scheduleId}")
    suspend fun deleteSchedule(@PathVariable scheduleId: UUID) =
        scheduleUsecase.deleteSchedule(scheduleId)
}