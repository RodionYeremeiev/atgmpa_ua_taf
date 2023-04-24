plugins {
    id("java")
}

group = "com.epam"
version = "unspecified"

tasks.test {
    useJUnitPlatform()
    systemProperty("username", System.getProperty("username"))
    systemProperty("password", System.getProperty("password"))
}