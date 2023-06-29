buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("io.franzbecker:gradle-lombok:4.0.0")
    }
}
plugins {
    java
    id("io.qameta.allure") version "2.11.2"
}

apply(plugin = "java")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

ext {
    set("junit-jupiter.version", "5.9.2")
}


subprojects {

    apply {
        plugin("java")
        plugin("io.franzbecker.gradle-lombok")
        plugin("io.qameta.allure")
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation("com.google.guava:guava:29.0-jre")
        implementation("com.codeborne:selenide:5.16.2")

        implementation("org.projectlombok:lombok:1.18.26")

        implementation("org.slf4j:slf4j-api:1.7.36")
        implementation("ch.qos.logback:logback-classic:1.2.3")
        implementation("ch.qos.logback:logback-core:1.2.3")

        implementation("com.slack.api:slack-api-client:1.29.2")

        testImplementation("org.assertj:assertj-core:3.11.1")

        testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
        testImplementation("org.junit.vintage:junit-vintage-engine:5.9.3")
        implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")

        testImplementation("org.junit.platform:junit-platform-launcher:1.9.2")
        testImplementation ("org.junit.platform:junit-platform-console-standalone:1.9.2")

        testImplementation("org.aspectj:aspectjweaver:1.9.5")
        testImplementation("io.qameta.allure:allure-junit5:2.13.5")
        testImplementation("io.qameta.allure:allure-commandline:2.22.4")
        testImplementation("io.qameta.allure:allure-assertj:2.13.5")
        testImplementation("io.qameta.allure:allure-rest-assured:2.13.5")
        testImplementation("io.qameta.allure:allure-java-commons:2.13.5")

    }
}

tasks {
    test {
        useJUnitPlatform()

        systemProperty("wdm.chromeDriverVersion", "112")
        if (project.hasProperty("browser")) {
            systemProperty("browser", project.property("browser") ?: "chrome")
        }
        systemProperty("selenide.headless", "false")
        if (project.hasProperty("grid")) {
            systemProperty("browser.remote", "true")
            systemProperty("selenide.remote", "http://${project.property("grid")}:4444/wd/hub")
        }
    }
}
