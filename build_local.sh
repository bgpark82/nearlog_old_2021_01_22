#!/bin/bash

echo "build user docker image..."
cd ~/Desktop/nearlog/nearlog/user
#cd ~/nearlog/user
./gradlew buildDocker

echo "build place docker image..."
cd ~/Desktop/nearlog/nearlog/place
#cd ~/nearlog/user
./gradlew buildDocker

echo "docker-compose up..."
cd ~/Desktop/nearlog/nearlog
docker-compose up




