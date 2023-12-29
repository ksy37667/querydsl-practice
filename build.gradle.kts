import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
	kotlin("plugin.jpa") version "1.9.21"
	kotlin("plugin.allopen") version "1.8.21"
	kotlin("kapt") version "1.9.21"
	idea
}

group = "com.querydsl"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	//swagger
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// mysql connector
	runtimeOnly("com.mysql:mysql-connector-j")

	// querydsl
	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt ("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt ("jakarta.annotation:jakarta.annotation-api")
	kapt ("jakarta.persistence:jakarta.persistence-api")
}

val generated = "src/main/generated"


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

idea {
	module {
		val kaptMain = file("build/generated/sources/kapt/main")
		sourceDirs.add(kaptMain)
		generatedSourceDirs.add(kaptMain)
	}
}
