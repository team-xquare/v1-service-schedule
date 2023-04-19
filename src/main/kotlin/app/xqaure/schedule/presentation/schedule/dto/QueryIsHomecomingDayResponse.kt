package app.xqaure.schedule.presentation.schedule.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryIsHomecomingDayResponse(

    @JsonProperty("isHomecomingDay")
    val isHomecomingDay: Boolean
)
