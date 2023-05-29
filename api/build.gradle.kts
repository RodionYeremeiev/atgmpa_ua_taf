plugins {
    id("java")
}

group = "com.epam"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.rest-assured:rest-assured:5.3.0")
    implementation("io.rest-assured:rest-assured-common:5.3.0")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("token", System.getProperty("token"))
}