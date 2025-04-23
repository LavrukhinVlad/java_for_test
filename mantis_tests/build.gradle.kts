plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:5.12.1")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.29.0")
    testImplementation("com.squareup.okhttp3:okhttp:4.11.0")
    testImplementation("com.squareup.okhttp3:okhttp-urlconnection:4.11.0")
    testImplementation ("org.eclipse.angus:angus-mail:2.0.2")
    testImplementation ("com.fasterxml.jackson.core:jackson-databind:2.17.1")
}

tasks.test {
    useJUnitPlatform()
}