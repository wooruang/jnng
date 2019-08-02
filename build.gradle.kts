plugins {
    java
    `java-library`
    `ivy-publish`
}

group = "com.wooruang"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<IvyPublication>("ivy") {
            from(components["java"])
        }
    }
    repositories {
        ivy {
            url = uri("${System.getProperty("user.home")}/.ivy2/local")
            layout("ivy")
        }
    }
}

val nativeBuildDir = "cmake-build-release"

task<Exec>("update-native") {
    workingDir = file("$rootDir")
    commandLine = listOf("javac", "-h", "$rootDir/src/main/c", "$rootDir/src/main/java/com/wooruang/jnng/**/*.java")
}

task<Exec>("build-native-mkdir") {
    workingDir = file("$projectDir")
    commandLine = listOf("mkdir", nativeBuildDir)
}

task<Exec>("build-native-cmake") {
//    dependsOn(":build-native-mkdir")
    workingDir = file("$projectDir/$nativeBuildDir")
    commandLine = listOf("cmake", "..")
}

task<Exec>("build-native-make") {
    dependsOn(":build-native-cmake")
    workingDir = file("$projectDir/$nativeBuildDir")
    commandLine = listOf("make", "-j4")
}

task("go-server") {
    dependsOn(":build-native-make")
    dependsOn(":jnng-server:run")
}

task("go-client") {
    dependsOn(":build-native-make")
    dependsOn(":jnng-client:run")
}
