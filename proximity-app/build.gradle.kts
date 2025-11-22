plugins {
    kotlin("jvm") version "2.2.21"
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
    application
}

repositories { mavenCentral() }

dependencies { testImplementation(kotlin("test")) }

application { mainClass = "org.proximity.AppKt" }
