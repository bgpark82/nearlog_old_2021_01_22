#!/bin/bash

echo "build user docker image..."
cd ~/nearlog/user
./gradlew buildDocker

echo "build place docker image..."
cd ~/nearlog/place
./gradlew buildDocker

echo "docker-compose up..."
cd ~/nearlog/nearlog
docker-compose up




