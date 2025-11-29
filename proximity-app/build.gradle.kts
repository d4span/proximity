plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
    id("org.springframework.boot") version "4.0.0"
    id("com.github.ben-manes.versions") version "0.48.0"
    application
}

repositories { mavenCentral() }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter:4.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.0")
    testImplementation(kotlin("test"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.addAll(listOf("-Xjsr305=strict"))
        jvmTarget.set(
            org.jetbrains.kotlin.gradle.dsl.JvmTarget
                .fromTarget("21"),
        )
    }
}

tasks.test {
    useJUnitPlatform()
}

application { mainClass = "org.proximity.ApplicationKt" }
