package app.xqaure.schedule.domain.schedule

import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotNull

@Table(value = "tbl_schedule")
class Schedule(
    date: LocalDate,
    name: String,

    @field: NotNull
    @field: Length(min = 36, max = 36)
    val userId: String,

    ) : Persistable<UUID> {
    @Id
    private var id: UUID = UUID.randomUUID()

    var date: LocalDate = date

    var name: String = name

    override fun getId(): UUID = id
    override fun isNew(): Boolean = true

    fun getScheduleId(): UUID {
        return this.id
    }
}
