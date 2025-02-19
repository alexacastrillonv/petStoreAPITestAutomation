

plugins {
    id ("java")
    id("net.serenity-bdd.serenity-gradle-plugin") version "4.2.16"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("io.rest-assured:rest-assured:5.5.0")
    implementation("io.rest-assured:json-path:5.5.0")
    implementation("io.rest-assured:json-schema-validator:5.5.0")
    compileOnly ("org.projectlombok:lombok:1.18.30" )
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    implementation ("org.slf4j:slf4j-api:2.0.16")
    testImplementation ("io.cucumber:cucumber-java:7.14.0")
    testImplementation ("io.cucumber:cucumber-junit:7.14.0")
    implementation ("net.serenity-bdd:serenity-core:4.2.16")
    implementation ("net.serenity-bdd:serenity-junit5:4.2.16")
    implementation ("net.serenity-bdd:serenity-rest-assured:4.2.16")
    implementation ("net.serenity-bdd:serenity-cucumber:4.2.16")



}

tasks.test {
    useJUnitPlatform()
}

