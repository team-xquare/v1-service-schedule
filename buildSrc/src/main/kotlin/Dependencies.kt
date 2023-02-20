enum class ImplementationType(val originalName: String) {
    IMPLEMENTATION("implementation"),
    TEST_IMPLEMENTATION("testImplementation"),
    KAPT("kapt")
}

interface Libs {
    fun getDependencies(): List<Pair<String, ImplementationType>>

    object SpringBoot : Libs {

        private const val STARTER_WEBFLUX = "org.springframework.boot:spring-boot-starter-webflux"
        private const val STARTER_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
        private const val STARTER_DATA_R2DBC = "org.springframework.boot:spring-boot-starter-data-r2dbc"
        private const val STARTER_ACTUATOR = "org.springframework.boot:spring-boot-starter-actuator"
        private const val STRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"
        private const val STARTER_TEST = "org.springframework.boot:spring-boot-starter-test"
        private const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"

        override fun getDependencies() = listOf(
            STARTER_WEBFLUX to ImplementationType.IMPLEMENTATION,
            STARTER_VALIDATION to ImplementationType.IMPLEMENTATION,
            STARTER_DATA_R2DBC to ImplementationType.IMPLEMENTATION,
            STARTER_ACTUATOR to ImplementationType.IMPLEMENTATION,
            STRING_SECURITY to ImplementationType.IMPLEMENTATION,
            STARTER_TEST to ImplementationType.TEST_IMPLEMENTATION,
            SPRING_SECURITY to ImplementationType.IMPLEMENTATION
        )
    }

    object SpringCloud : Libs {

        // private const val SPRING_CLOUD = "org.springframework.cloud:spring-cloud-starter-aws-messaging"
        private const val SPRING_CLOUD_CONFIG = "org.springframework.cloud:spring-cloud-config-client"

        override fun getDependencies() = listOf(
            // SPRING_CLOUD to ImplementationType.IMPLEMENTATION,
            SPRING_CLOUD_CONFIG to ImplementationType.IMPLEMENTATION
        )
    }

    object Reactor : Libs {

        private const val KOTLIN_EXTENSIONS = "io.projectreactor.kotlin:reactor-kotlin-extensions"
        private const val COROUTINES_REACTOR = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor"

        override fun getDependencies() = listOf(
            KOTLIN_EXTENSIONS to ImplementationType.IMPLEMENTATION,
            COROUTINES_REACTOR to ImplementationType.IMPLEMENTATION
        )
    }

    object Jackson : Libs {

        private const val MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin"
        private const val DATATYPE_JSR_310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"

        override fun getDependencies() = listOf(
            MODULE_KOTLIN to ImplementationType.IMPLEMENTATION,
            DATATYPE_JSR_310 to ImplementationType.IMPLEMENTATION
        )
    }

    object Database : Libs {

        private object Version {
            const val MYSQL_CONNECTOR = "0.8.2.RELEASE"
        }

        private const val MYSQL_CONNECTOR = "dev.miku:r2dbc-mysql:${Version.MYSQL_CONNECTOR}"
        private const val R2DBC = "org.springframework.boot:spring-boot-starter-data-r2dbc"

        override fun getDependencies() = listOf(
            MYSQL_CONNECTOR to ImplementationType.IMPLEMENTATION,
            R2DBC to ImplementationType.IMPLEMENTATION
        )
    }

    object Kotlin : Libs {

        private const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"

        override fun getDependencies() = listOf(
            KOTLIN_REFLECT to ImplementationType.IMPLEMENTATION,
        )
    }
}

object Dependencies {
    const val KTLINT = "com.pinterest:ktlint:0.45.0"
}
