package app.xqaure.schedule.global.error

data class ErrorResponse (
    val code: Int,
    val message: List<String>
)