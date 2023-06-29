plugins {
    id("java")
}

group = "com.epam"
version = "unspecified"

dependencies {
    implementation(project(":api"))
}

tasks.test {
    useJUnitPlatform()
    systemProperty("username", System.getProperty("username"))
    systemProperty("password", System.getProperty("password"))
    systemProperty ("selenide.browserSize", "1920x1080")
    systemProperty ("selenide.headless", System.getProperty("selenide.headless", "false"))
    systemProperty ("zephyrApiToken", System.getProperty("zephyrApiToken"))
}