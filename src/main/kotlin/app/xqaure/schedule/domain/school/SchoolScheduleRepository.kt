package app.xqaure.schedule.domain.school

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.time.LocalDate

interface SchoolScheduleRepository : ReactiveCrudRepository<SchoolSchedule, String> {

    @Query("UPDATE tbl_school_schedule SET name = :name, date = :date WHERE id = :id")
    fun updateSchoolSchedule(id: String, name: String, date: LocalDate)
}
