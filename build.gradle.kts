buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("io.franzbecker:gradle-lombok:4.0.0")
        classpath("io.qameta.allure:allure-gradle:2.8.1")
    }
}
plugins {
    java
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

        testImplementation("org.assertj:assertj-core:3.11.1")
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.junit.vintage:junit-vintage-engine")
        testImplementation("org.junit.jupiter:junit-jupiter-api:latest.release")

//        testImplementation("org.testng:testng:7.7.1")

        testImplementation("io.rest-assured:rest-assured:5.3.0")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:latest.release")
    }
}

tasks {
    test {
        useJUnitPlatform()

        systemProperty("wdm.chromeDriverVersion", "86")
        if (project.hasProperty("browser")) {
            systemProperty("browser", project.property("browser") ?: "chrome")
        }
        systemProperty("selenide.headless", "false")
        if (project.hasProperty("grid")) {
            systemProperty("browser.remote", "true")
            systemProperty("selenide.remote", "http://${project.property("grid")}:4444/wd/hub")
        }

        systemProperty("username", System.getProperty("username"))
        systemProperty("password", System.getProperty("password"))
    }
}
