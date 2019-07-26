plugins {
    java
    application
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

val nativeBuildDir = "cmake-build-release"

application {
    applicationDefaultJvmArgs = listOf("-Djava.library.path=$projectDir/$nativeBuildDir")
    mainClassName = "com.wooruang.jnng.JNNG"
}

task<Exec>("build-native-cmake") {
    workingDir = file("$projectDir/$nativeBuildDir")
    commandLine = listOf("cmake", "..")
}

task<Exec>("build-native-make") {
    workingDir = file("$projectDir/$nativeBuildDir")
    commandLine = listOf("make", "-j4")
}

task("go") {
    dependsOn(":build-native-cmake")
    dependsOn(":build-native-make")
    dependsOn(":run")
}

