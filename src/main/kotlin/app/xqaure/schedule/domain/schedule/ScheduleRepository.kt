package app.xqaure.schedule.domain.schedule

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ScheduleRepository : ReactiveCrudRepository<Schedule, String> {

    fun findScheduleByIdAndUserId(id: String, userId: String): Mono<Schedule>

    fun findAllByUserId(userId: String): Flux<Schedule>
}
