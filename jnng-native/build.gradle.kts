
group = "com.wooruang"

val nativeBuildDir = "cmake-build-release"
//val dynamicLibFile = file("$nativeBuildDir/libjnng.dylib")
//val destLibsDir = file("$buildDir/classes/java/main/libs")

val nngBuildScript = "build-nng.sh"
val javaFilesForJni =
        fileTree("$rootDir/src/main/java/com/wooruang/jnng/jni")
                .filter { it.isFile() && it.extension == "java" }.toList()

task<Exec>("build-native-nng") {
    workingDir = file("$projectDir")
    commandLine = listOf("bash", nngBuildScript)
}

task<Exec>("update-native") {
    dependsOn("build-native-nng")
    workingDir = file("$projectDir")
    commandLine = listOf("javac", "-h", "src/main/c") + javaFilesForJni
}

task<Exec>("build-native-mkdir") {
    dependsOn("update-native")
    workingDir = file("$projectDir")
    commandLine = listOf("mkdir", "-p", nativeBuildDir)
}

task<Exec>("build-native-cmake") {
    dependsOn("build-native-mkdir")
    workingDir = file("$projectDir/$nativeBuildDir")
    commandLine = listOf("cmake", "..")
}

task<Exec>("build-native-make") {
    dependsOn("build-native-cmake")
    workingDir = file("$projectDir/$nativeBuildDir")
    commandLine = listOf("make", "-j4")
}

task("build") {
    dependsOn("build-native-make")
}