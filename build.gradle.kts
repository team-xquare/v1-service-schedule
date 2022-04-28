import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.apache.tools.ant.taskdefs.condition.Os
import java.net.URI

// For formatting
val ktlint: Configuration by configurations.creating

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Management.SPRING_CLOUD_VERSION}")
    }
}

group = "app.xqaure"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementationDependencies(Libs.SpringBoot)
    implementationDependencies(Libs.SpringCloud)
    implementationDependencies(Libs.Reactor)
    implementationDependencies(Libs.Database)
    implementationDependencies(Libs.Jackson)
    implementationDependencies(Libs.Kotlin)

    // Linting
    ktlint(Dependencies.KTLINT) {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

tasks.getByName<Jar>("jar") {
    enabled = false
}

// Checking lint
val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

// Formatting all source files
val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
    jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}

val installGitHook by tasks.creating(Copy::class) {

    description = "Install git hook to root project."

    var suffix = "macos"
    if (Os.isFamily(Os.FAMILY_WINDOWS)) {
        suffix = "windows"
    }

    val sourceDir = File(rootProject.rootDir, "pre-build/scripts/pre-push-$suffix")
    val targetDir = File(rootProject.rootDir, ".git/hooks")

    from(sourceDir)
    into(targetDir)
    rename("pre-push-$suffix", "pre-push")

    fileMode = 0b111101101
}

project.tasks.getByName("build").dependsOn(":installGitHook")