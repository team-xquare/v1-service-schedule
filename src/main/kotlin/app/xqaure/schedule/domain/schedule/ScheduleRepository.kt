package app.xqaure.schedule.domain.schedule

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface ScheduleRepository : ReactiveCrudRepository<Schedule, UUID>