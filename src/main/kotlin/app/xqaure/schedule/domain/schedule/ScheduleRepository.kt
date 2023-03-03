package app.xqaure.schedule.domain.schedule

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ScheduleRepository : ReactiveCrudRepository<Schedule, String> {
    fun findAllByUserId(userId: String): Flux<Schedule>
}
