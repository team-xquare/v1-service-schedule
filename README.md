### Project Structure
```.
├── Dockerfile
├── LICENSE
├── README.md
├── build.gradle.kts
├── buildSrc
│   ├── build.gradle.kts
│   └── src
│       └── main
│           └── kotlin
│               ├── Dependencies.kt
│               ├── DependencyHandlerExtension.kt
│               └── Management.kt
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── pre-build
│   └── scripts
│       ├── pre-push-macos
│       └── pre-push-windows
├── settings.gradle.kts
└── src
    ├── main
    │   ├── kotlin
    │   │   └── app
    │   │       └── xqaure
    │   │           └── schedule
    │   │               ├── ScheduleApplication.kt
    │   │               ├── application
    │   │               │   ├── group
    │   │               │   │   └── GroupUsecase.kt
    │   │               │   └── school
    │   │               │       ├── SchoolUsecase.kt
    │   │               │       └── exceptions
    │   │               │           └── ScheduleNotFoundException.kt
    │   │               ├── domain
    │   │               │   ├── group
    │   │               │   │   ├── Group.kt
    │   │               │   │   └── GroupMember.kt
    │   │               │   ├── schedule
    │   │               │   │   └── Schedule.kt
    │   │               │   └── school
    │   │               │       ├── SchoolSchedule.kt
    │   │               │       └── SchoolScheduleRepository.kt
    │   │               ├── global
    │   │               │   ├── error
    │   │               │   │   ├── ErrorCode.kt
    │   │               │   │   ├── ErrorResponse.kt
    │   │               │   │   ├── GlobalExceptionHandler.kt
    │   │               │   │   └── exception
    │   │               │   │       └── BusinessException.kt
    │   │               │   ├── jackson
    │   │               │   │   └── JacksonConfig.kt
    │   │               │   ├── log
    │   │               │   │   └── Logger.kt
    │   │               │   └── message
    │   │               │       └── MessageConfig.kt
    │   │               ├── infrastructure
    │   │               │   └── database
    │   │               │       ├── MySQLConfig.kt
    │   │               │       └── converter
    │   │               │           └── UUIDConverter.kt
    │   │               └── presentation
    │   │                   ├── dto
    │   │                   │   └── Response.kt
    │   │                   ├── group
    │   │                   │   ├── GroupHandler.kt
    │   │                   │   └── dto
    │   │                   │       └── CreateGroupRequest.kt
    │   │                   ├── root
    │   │                   │   └── RootHandler.kt
    │   │                   └── school
    │   │                       ├── SchoolHandler.kt
    │   │                       └── dto
    │   │                           ├── AddScheduleRequest.kt
    │   │                           └── ModifyScheduleRequest.kt
    │   └── resources
    │       ├── application-local.yml
    │       ├── application-prod.yml
    │       ├── application.yml
    │       ├── i18n
    │       │   └── messages.properties
    │       ├── log
    │       │   └── logback.xml
    │       └── schema.sql
    └── test
        └── kotlin
            └── app
                └── xqaure
                    └── schedule
                        └── ScheduleApplicationTests.kt

46 directories, 47 files
```
