# Virtual Reality Exhibition Manager (VREM)
The Virtual Reality Exhibition Manager (VREM) is a tool that allows for configuration, storage of and access to VR exhibition definitions. It has been
built as part of the [Open Cultural Data Hackathon 2018](http://make.opendata.ch/wiki/event:2018-10), held in Zurich, Switzerland.

## Building VREM
VREM can be built using [Gradle](http://gradle.org/). Building and running it is as easy as
```
 git clone https://github.com/ppanopticon/virtual-exhibition-manager.git vrem
 cd vrem
 ./gradlew clean deploy
 cd build/libs
 java -jar vrem.jar <command>
 ```

## Prerequisites
### System dependencies
* git
* JDK 8 or higher
* You will require [MongoDB](https://docs.mongodb.com/manual/installation/) as storage engine.

## Starting a server

Before starting, you must adapt the configurations in your config.json file (see example file). Then you can start the VREM server 
by typing into your console:

```
 java -jar virtual-exhibition-manager-1.0-SNAPSHOT.jar server -c /path/to/your/config.json
```