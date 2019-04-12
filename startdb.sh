#!/usr/bin/env bash
# Starts a mongo db docker container if not previously started.

# List containers with vrem-container anywhere
containers=$(docker ps -al | grep "vrem-container")

# if this variable is empty, no such container exists and it has do be created
if [ -z "$containers" ]; then
	echo "No vrem-container found. Starting new one"
	docker run --name vrem-container -d -p 27017:27017 mongo
	echo "Freshly started new container."
else
        if [[ $containers == *Exited* ]]; then
		echo "Existing container was stopped. Restarting now"
		docker start "vrem-container"
	else
		echo "vrem-container already running."
	fi
fi
