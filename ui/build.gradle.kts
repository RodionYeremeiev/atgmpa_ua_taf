plugins {
    id("java")
    id("se.thinkcode.cucumber-runner") version "0.0.11"
}

group = "com.epam"
version = "unspecified"

dependencies {
    implementation("io.cucumber:cucumber-java:6.10.4")
    testImplementation("io.cucumber:cucumber-junit:7.11.2")
//    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.11.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api:latest.release")
}

cucumber {
    threads = "4"
    glue = "classpath:com.epam.stepdefinitions"
    plugin = arrayOf("pretty")
    dryRun = "false"

    featurePath = "src/test/java/com/epam/features"
    main = "io.cucumber.core.cli.Main"
}

tasks.test {
    useJUnitPlatform()
    systemProperty("username", System.getProperty("username"))
    systemProperty("password", System.getProperty("password"))
    systemProperty("selenide.browserSize", "1920x1080")
    systemProperty("selenide.headless", System.getProperty("selenide.headless", "false"))
}