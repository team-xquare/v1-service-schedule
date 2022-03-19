import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementationDependencies(libs: Libs) {
    libs.getDependencies().forEach { (dependency, type) ->
        add(type.originalName, dependency)
    }
}
