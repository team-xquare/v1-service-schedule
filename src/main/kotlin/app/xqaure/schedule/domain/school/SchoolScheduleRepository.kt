package app.xqaure.schedule.domain.school

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import java.time.LocalDate
import java.util.UUID

interface SchoolScheduleRepository : ReactiveCrudRepository<SchoolSchedule, UUID> {
}
