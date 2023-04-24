plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

tasks.test {
    useJUnitPlatform()
    useTestNG()
}