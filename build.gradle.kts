plugins {
    java
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.lesson"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
//    maven {
//        url = uri("https://company/com/maven2")
//    }
//    mavenLocal()
//    flatDir {
//        dirs("libs")
//    }


}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    // Lombok (annotation processing - getters/setters etc.)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")



    testImplementation("org.springframework.boot:spring-boot-starter-test")


    // Spring Boot DevTools (optional - for automatic restarts etc. during development)
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
