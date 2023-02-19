#!/bin/bash

# Check if the container is already running
if docker ps --filter "name=local-mongo" --format '{{.Names}}' | grep -q "local-mongo"; then
  echo "Stopping existing container..."
  # Stop the container
  docker stop local-mongo
fi

# Check if the container exists
if docker ps --all --filter "name=local-mongo" --format '{{.Names}}' | grep -q "local-mongo"; then
  echo "Removing existing container..."
  # Remove the container
  docker rm local-mongo
fi

docker pull mongo
docker run -d --name local-mongo -p 27017:27017 mongo
