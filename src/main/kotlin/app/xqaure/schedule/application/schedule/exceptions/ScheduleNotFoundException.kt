package app.xqaure.schedule.application.schedule.exceptions

import app.xqaure.schedule.global.error.ErrorCode
import app.xqaure.schedule.global.error.exception.BusinessException

class ScheduleNotFoundException : BusinessException(ErrorCode.SCHEDULE_NOT_FOUND)
