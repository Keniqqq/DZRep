plugins {
    java
}

group = "ru.netology"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.thoughtworks.xstream:xstream:1.4.20")
    implementation("org.codehaus.jettison:jettison:1.5.4")
    implementation("com.opencsv:opencsv:5.7.1")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.google.code.gson:gson:2.8.9")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}