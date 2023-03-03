package app.xqaure.schedule.domain.school

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface SchoolScheduleRepository : ReactiveCrudRepository<SchoolSchedule, String>
