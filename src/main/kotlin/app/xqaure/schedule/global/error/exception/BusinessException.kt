package app.xqaure.schedule.global.error.exception

import app.xqaure.schedule.global.error.ErrorCode

abstract class BusinessException(
    override val errorCode: ErrorCode
) : RuntimeException(errorCode.code), ExceptionProperty {
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
