package app.xqaure.schedule.presentation.schedule.dto

import java.util.UUID

data class ScheduleElement(
    val id: UUID,
    val name: String,
    val date: String,
)
