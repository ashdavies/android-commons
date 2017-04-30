#!/bin/sh

./gradlew adapters:bintrayUpload -PbintrayUser=$1 -PbintrayKey=$2 -PdryRun=$3
./gradlew android:bintrayUpload -PbintrayUser=$1 -PbintrayKey=$2 -PdryRun=$3
./gradlew java:bintrayUpload -PbintrayUser=$1 -PbintrayKey=$2 -PdryRun=$3
./gradlew rx:bintrayUpload -PbintrayUser=$1 -PbintrayKey=$2 -PdryRun=$3
