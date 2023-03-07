package app.xqaure.schedule.global.exception

import app.xqaure.schedule.global.error.ErrorCode
import app.xqaure.schedule.global.error.exception.BusinessException

class BadRequestException : BusinessException(ErrorCode.BAD_REQUEST)