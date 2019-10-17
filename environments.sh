#!/usr/bin/env bash

# Install JDK. (version 11.0.2)
JDK_VER="jdk-11.0.2"
JDK_BASE="openjdk-11.0.2_linux-x64_bin"
JDK_TAR=$JDK_BASE".tar.gz"
JDK_MD5=$JDK_TAR".sha256"
JDK_BASE_URL="https://download.java.net/java/GA/jdk11/9/GPL/"
JDK_URL=$JDK_BASE_URL$JDK_TAR
JDK_MD5_URL=$JDK_BASE_URL$JDK_MD5

# Exist Check!
if [[ -f "$JDK_TAR" ]]; then
  echo "Already exist a $JDK_TAR"
else
  echo "Could not find a $JDK_TAR"
  echo "Download a $JDK_TAR!"
  curl -O "$JDK_URL"
fi

if [[ -f "$JDK_MD5" ]]; then
  echo "Already exist a $JDK_MD5"
else
  echo "Could not find a $JDK_MD5"
  echo "Download a $JDK_MD5!"
  curl -O "$JDK_MD5_URL"
fi

# Check OS.
echo $OSTYPE
if [[ "$OSTYPE" == "linux-gnu" ]]; then
  # Linux
  echo "Linux"
  cat $JDK_MD5 | awk -v file=$JDK_TAR '{print $1, "", file }' | shasum -a 256 -c
elif [[ "$OSTYPE" == "darwin"* ]]; then
  # Mac OSX
  echo "MacOSX"
  cat $JDK_MD5 | awk -v file=$JDK_TAR '{print $1, "", file }' | shasum -a 256 -c
#elif [[ "$OSTYPE" == "cygwin" ]]; then
#        # POSIX compatibility layer and Linux environment emulation for Windows
#elif [[ "$OSTYPE" == "msys" ]]; then
#        # Lightweight shell and GNU utilities compiled for Windows (part of MinGW)
#elif [[ "$OSTYPE" == "win32" ]]; then
#        # I'm not sure this can happen.
#elif [[ "$OSTYPE" == "freebsd"* ]]; then
#        # ...
else
  # Unknown.
  echo "Unknown OS."
  exit 1
fi

if [[ $? -ne 0 ]]
then
  echo "Fail to check a shasum value"
  exit 1
fi

# Extract a tar file.
if [[ -d "$JDK_VER" ]]
then
  echo "Already extract a tar file. ($JDK_VER)"
else
  tar xzf $JDK_TAR && java -version
fi


$PWD/$JDK_VER/bin/java --version

if [[ $? -eq 0 ]]
then
  export PATH=$PATH:$PWD/$JDK_VER/bin
  export JAVA_HOME=$PWD/$JDK_VER
  echo "Success."
else
  echo "Fail."
fi


