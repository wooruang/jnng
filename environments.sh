#!/usr/bin/env bash

# Install JDK. (version 11.0.2)
JDK_VER="jdk-11.0.2"
JDK_BASE="openjdk-11.0.2_linux-x64_bin"
JDK_TAR=$JDK_BASE".tar.gz"
JDK_MD5=$JDK_TAR".sha256"
JDK_BASE_URL="https://download.java.net/java/GA/jdk11/9/GPL/"
JDK_URL=$JDK_BASE_URL$JDK_TAR
JDK_MD5_URL=$JDK_BASE_URL$JDK_MD5
PATH="${PATH}:/$JDK_VER/bin"

curl -O "$JDK_URL" && curl -O "$JDK_MD5_URL"

echo "`cat $JDK_MD5` $JDK_TAR" > $JDK_MD5 && sha256sum $JDK_TAR | sha256sum --check $JDK_MD5 &&  tar xzf $JDK_TAR && java -version

