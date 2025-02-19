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
    implementation("io.rest-assured:rest-assured:5.5.0")
    implementation("io.rest-assured:json-path:5.5.0")
    implementation("io.rest-assured:json-schema-validator:5.5.0")
    compileOnly ("org.projectlombok:lombok:1.18.30" )
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    implementation ("org.slf4j:slf4j-api:2.0.16")
    testImplementation ("io.cucumber:cucumber-java:7.14.0")
    testImplementation ("io.cucumber:cucumber-junit:7.14.0")



}

tasks.test {
    useJUnitPlatform()
}
