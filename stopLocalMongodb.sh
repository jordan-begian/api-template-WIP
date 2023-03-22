#!/bin/bash

# Stops and removes the docker containers if they were started at any point in time - This is usually used when you're
# done working on development and want to shut down the mongo database

echo "stopping docker container..."
docker stop local-mongo

echo "removing docker container..."
docker rm local-mongo
