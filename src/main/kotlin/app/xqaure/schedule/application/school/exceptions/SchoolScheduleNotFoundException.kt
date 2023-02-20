package app.xqaure.schedule.application.school.exceptions

import app.xqaure.schedule.global.error.ErrorCode
import app.xqaure.schedule.global.error.exception.BusinessException

class SchoolScheduleNotFoundException : BusinessException(ErrorCode.SCHOOL_SCHEDULE_NOT_FOUND)
