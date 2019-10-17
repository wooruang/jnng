#!/usr/bin/env bash

NNG_PATH="$PWD/nng"
NNG_BUILD_DIR="nng-build"
BUILD_DIR="build"
INSTALL_DIR="install"

echo "Make dirs for build & install ..."
mkdir -p $NNG_BUILD_DIR/$BUILD_DIR
mkdir -p $NNG_BUILD_DIR/$INSTALL_DIR

cd $NNG_BUILD_DIR/$BUILD_DIR

echo "CMake build ..."
echo $OSTYPE
if [[ "$OSTYPE" == "linux-gnu" ]]; then
  # Linux
  echo "Linux"
  cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_C_FLAGS=-fpic -DCMAKE_INSTALL_PREFIX=../$INSTALL_DIR $NNG_PATH
elif [[ "$OSTYPE" == "darwin"* ]]; then
  # Mac OSX
  echo "MacOSX"
  cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=../$INSTALL_DIR $NNG_PATH
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

echo "Build & Install ..."
make -j4 && make install

