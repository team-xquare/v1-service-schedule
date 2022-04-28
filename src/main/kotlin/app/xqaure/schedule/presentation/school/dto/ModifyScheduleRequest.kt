package app.xqaure.schedule.presentation.school.dto

import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ModifyScheduleRequest(

    @field:NotEmpty(message = "{name.empty}")
    val name: String?,

    @field:NotNull(message = "{date.null}")
    val date: LocalDate?
)
