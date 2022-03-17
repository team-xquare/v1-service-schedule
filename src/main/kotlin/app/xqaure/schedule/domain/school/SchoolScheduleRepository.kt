package app.xqaure.schedule.domain.school

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface SchoolScheduleRepository : ReactiveCrudRepository<SchoolSchedule, UUID>