package app.xqaure.schedule.domain.schedule

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import java.util.UUID

interface ScheduleRepository : ReactiveCrudRepository<Schedule, UUID> {
    fun findAllByUserId(userId: String): Flux<Schedule>
}
