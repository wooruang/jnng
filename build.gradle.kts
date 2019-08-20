plugins {
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
project.extra["dynamicLibFile"] = ""

val libCopy by tasks.registering(Copy::class) {
    dependsOn(":jnng-native:build")
    val dynamicLibFile = file(project.extra["dynamicLibFile"].toString())
    val destLibsDir = file("$buildDir/classes/java/main/libs")
//    println("libCopy $dynamicLibFile")
//    System.out.flush()
    from(dynamicLibFile)
    into(destLibsDir)
}

tasks.compileJava {
    dependsOn(libCopy)
}

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
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


//val nativeBuildDir = "cmake-build-release"
//project(":jnng-native") {
//
//    val nngBuildScript = "$rootDir/build-nng.sh"
//    val javaFilesForJni =
//            fileTree("$rootDir/src/main/java/com/wooruang/jnng/jni")
//                    .filter { it.isFile() && it.extension == "java" }.toList()
//
//    task<Exec>("build-native-nng") {
//        workingDir = file("$rootDir")
//        commandLine = listOf("bash", nngBuildScript)
//    }
//
//    task<Exec>("update-native") {
//        dependsOn(":build-native-nng")
//        workingDir = file("$rootDir")
//        commandLine = listOf("javac", "-h", "$rootDir/src/main/c") + javaFilesForJni
//    }
//
//
//    task<Exec>("build-native-mkdir") {
//        dependsOn(":update-native")
//        workingDir = file("$rootDir")
//        commandLine = listOf("mkdir", "-p", nativeBuildDir)
//    }
//
//    task<Exec>("build-native-cmake") {
//        dependsOn(":build-native-mkdir")
//        workingDir = file("$rootDir/$nativeBuildDir")
//        commandLine = listOf("cmake", "..")
//    }
//
//    task<Exec>("build-native-make") {
//        dependsOn(":build-native-cmake")
//        workingDir = file("$rootDir/$nativeBuildDir")
//        commandLine = listOf("make", "-j4")
//    }
//}



task("go-server") {
    dependsOn(":build-native-make")
    dependsOn(":jnng-server:run")
}

task("go-client") {
    dependsOn(":build-native-make")
    dependsOn(":jnng-client:run")
}
