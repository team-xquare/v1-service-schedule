package app.xqaure.schedule.presentation.school

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import java.util.UUID

@RestController
@RequestMapping("/school")
class SchoolHandler {

    @GetMapping
    suspend fun getSchedule(request: ServerRequest): ServerResponse {
        TODO()
    }

    @PostMapping
    suspend fun addSchedule(request: ServerRequest): ServerResponse {
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
