package app.xqaure.schedule.domain.school

import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.time.LocalDate

interface SchoolScheduleRepository : ReactiveCrudRepository<SchoolSchedule, String> {

    @Modifying
    @Query("UPDATE tbl_school_schedule SET name = :name, date = :date WHERE id = :id")
    fun updateSchoolSchedule(id: String, name: String, date: LocalDate): Int

    fun findByDate(date: LocalDate): Mono<SchoolSchedule>
}
