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
    compile(rootProject)
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

val nativeBuildDir = "cmake-build-release"

application {
    applicationDefaultJvmArgs = listOf("-Djava.library.path=$rootDir/$nativeBuildDir")
    mainClassName = "com.wooruang.jnngserver.JNNGServer"
}

tasks.named<JavaExec>("run") {
    if (rootProject.hasProperty("url")) {
        val url: String by project
        if (url.isNotEmpty()) {
            args = listOf(url)
        }
    }
}
