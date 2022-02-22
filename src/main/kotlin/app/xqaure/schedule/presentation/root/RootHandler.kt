package app.xqaure.schedule.presentation.root

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import java.util.UUID

@RestController
class RootHandler {

    @GetMapping("/{groupId}")
    suspend fun getScheduleByGroupId(@PathVariable groupId: UUID): ServerResponse {
        TODO()
    }

    @PutMapping("/{scheduleId}")
    suspend fun modifySchedule(@PathVariable scheduleId: UUID): ServerResponse {
        TODO()
    }

    @DeleteMapping("/{scheduleId}")
    suspend fun deleteSchedule(@PathVariable scheduleId: UUID): ServerResponse {
        TODO()
    }
}
