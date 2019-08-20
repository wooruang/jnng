#!/usr/bin/env bash

NNG_DIR="nng"
BUILD_DIR="build"
INSTALL_DIR="install"

echo "Make dirs for build & install ..."
mkdir -p $NNG_DIR/$BUILD_DIR
mkdir -p $NNG_DIR/$INSTALL_DIR

cd $NNG_DIR/$BUILD_DIR

echo "CMake build ..."
cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=../$INSTALL_DIR ..

echo "Build & Install ..."
make -j4 && make install
