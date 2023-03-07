package app.xqaure.schedule.domain.schedule

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

interface ScheduleRepository : ReactiveCrudRepository<Schedule, String> {

    fun findScheduleByIdAndUserId(id: String, userId: String): Mono<Schedule>

    fun findAllByUserId(userId: String): Flux<Schedule>

    @Query("UPDATE tbl_schedule SET name = :name, date = :date WHERE id = :id")
    suspend fun updateSchedule(id: String, name: String, date: LocalDate)
}
