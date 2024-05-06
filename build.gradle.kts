plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("se.michaelthelin.spotify:spotify-web-api-java:8.4.0")
}

tasks.test {
    useJUnitPlatform()
}