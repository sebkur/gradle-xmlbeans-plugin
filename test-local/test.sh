#!/bin/bash

set -e

pushd .
cd test1
pwd
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/ ./gradlew clean test createRuntime
./scripts/Test
popd

pushd .
cd test2
pwd
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/ ./gradlew clean test createRuntime
./scripts/Test
popd

pushd .
cd test2
pwd
JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/ ./gradlew clean test createRuntime
./scripts/Test
popd
