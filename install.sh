#!/bin/bash

if [ -x "$(command -v docker)" ]; 
then echo "Docker is installed. Proceeding with the next steps..." 
else 
echo "Install docker before executing this script" 
fi

if [ -x "$(command -v docker-compose)" ]; 
then echo "Docker Compose is installed. Proceeding with the next steps..." 
else 
echo "Install Docker Compose before executing this script" 
fi

# URL for Docker Desktop on windows: https://hub.docker.com/editions/community/docker-ce-desktop-mac/

#check that the Docker compose file exists in the current directory
FILE="./docker-compose.yml"
if test -f "$FILE"; 
then
    echo "$FILE exists."
else
	echo "$FILE does not exist."
	exit
fi

CLIENT_DIR=$(ls -d *Client)
echo $CLIENT_DIR
echo $CLIENT_DIR
echo $CLIENT_DIR

echo  "Hello"


length=${#CLIENT_DIR}

echo $length

server_name_length=$length-6

echo ${#server_name_length}

SERVER_DIR="${CLIENT_DIR:0:server_name_length}"

echo $CLIENT_DIR
echo $SERVER_DIR

rm -f ./.env

echo "CLIENT_DIR=${CLIENT_DIR}" >> ./.env

echo "SERVER_DIR=${SERVER_DIR}" >> ./.env

echo "WAR_FILE=${SERVER_DIR}-0.0.1-SNAPSHOT.war" >> ./.env

# Now run docker-compose.yml
# Check whether command exists and if so run it

program="docker-compose"
cmd="docker-compose up"

if ! command -v ${program} &> /dev/null;
then
    echo "$program command is not available on your path environment variable. Please ensure you have docker-compose installed and it's added to your environment path variable."
    exit
fi

$cmd