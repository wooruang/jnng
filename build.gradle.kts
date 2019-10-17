plugins {
    `java-library`
    `ivy-publish`
    `maven-publish`
    signing
}

group = "com.wooruang"
version = "0.1-SNAPSHOT"

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
    val dynamicLibFileForUbuntu = file(project.extra["dynamicLibFileForUbuntu"].toString())
    val dynamicLibFileForMacOS = file(project.extra["dynamicLibFileForMacOS"].toString())
    val destLibsDir = file("$buildDir/classes/java/main/libs")
//    println("libCopy $dynamicLibFile")
//    System.out.flush()
    from(dynamicLibFileForUbuntu, dynamicLibFileForMacOS)
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
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("jnng")
                description.set("This project is a wrapper of nng(nanomsg-next-generation) by using JNI for JVM")
                url.set("https://github.com/wooruang")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("wooruang")
                        name.set("HanSaem")
                        email.set("wooruang@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/wooruang/jnng.git")
                    developerConnection.set("scm:git:git@github.com:wooruang/jnng.git")
                    url.set("https://github.com/wooruang/jnng")
                }
            }
        }
    }
    repositories {
        ivy {
            url = uri("${System.getProperty("user.home")}/.ivy2/local")
            layout("ivy")
        }
        mavenCentral()
        maven {
            credentials {

                username = project.properties["nexusUsername"].toString()
                password = project.properties["nexusPassword"].toString()
            }
            name = "ossrh-snapshot"
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
        maven {
            credentials {
                username = project.properties["nexusUsername"].toString()
                password = project.properties["nexusPassword"].toString()
            }
            name = "ossrh-staging-for-release"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}

task("go-server") {
    dependsOn(":build-native-make")
    dependsOn(":jnng-server:run")
}

task("go-client") {
    dependsOn(":build-native-make")
    dependsOn(":jnng-client:run")
}
