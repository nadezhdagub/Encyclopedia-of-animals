#!/bin/bash

docker swarm init
docker stack deploy -c docker/docker-compose.yml app
echo "Wait 50 seconds for containers to start up"
sleep 50
echo "Docker stack services"
docker service ls
echo "Docker stack processes"
docker stack ps app 
