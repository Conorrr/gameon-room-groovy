#Game On! Client written in Groovy with the Ratpack Framework

This webapp has been hacked together in a couple of hours but I hope to expand on it and fix it up; currently it is a pretty bad example of a Ratpack application.

#Installation prerequisites

When deployed using an instant runtime, Gameon-room-java requires the following:

##Registering a room
* When the application first runs it will attempt to create a room using the details in the `application.yaml` config

*N.B. Rooms don't get automatically deleted, and you will not be able to create a new room with the same name if one already exists.*

##Deleting a room
* Make a GET request to `http://localhost:5050/delete/:roomId` - expect this to change it's just a convenience

*N.B. Currently you will need to restart the application in order to get it to register the room*

##Prerequistes
* Gradle 2.5+
* A game-on account

##Running the application locally
0. I recommend that people use [ngrok2](https://ngrok.com/) to safely and easily make your local application publically available
  * Download and install ngrok 
  * Run ngrok http 5050
  * Note the public hostname 
0. Configure your application in `src/ratpack/application.yaml`, make sure the hostname is correct
0. run `gradle run`

*N.B. When you restart ngrok you will recieve a new hostname. If you want to be able to specify the hostname you will need an ngrok premium subscription*

###Running with IntelliJ
0. Follow the previous points except 3
0. Import the project into IntelliJ
0. Open `src/ratpack/ratpack.groovy` right click the file and select `Run 'ratpack'`

## Access room on Game On!

Once the room is set up and it has registered with Game On!, it will be accessible on [Game On!](https://game-on.org/). It may take a moment for the room to appear.

1. Log in to [Game On!](https://game-on.org/) using the authentication method you used to create your user ID and shared secret for the registered room.
2. Use the Game On! command `/listmyrooms` from The First Room, to see your list of rooms. Once your room is registered, it will appear in that list.
3. To get to your room, navigate through the network or go directly there by using the `/teleport` command from The First Room.
4. Look at gradle console to see "A new connection has been made to the room"

## Application Structure
The application is written in [Groovy](http://www.groovy-lang.org/) and uses the [Ratpack](https://ratpack.io/) framework.
* `src/ratpack/ratpack.groovy` - A groovy script that uses the Ratpack DSL and contains the Web application logic
* `src/groovy/io.restall.gameon.room.groovy.roomservice.RoomServiceConfig` - Holds configuration for the application, the config is loaded from `src/ratpack/application.yaml` at start
* `src/groovy/io.restall.gameon.room.groovy.roomservice.GameOnService` - contains the logic for communicating with the Game-On REST endpoint.
* `src/groovy/io.restall.gameon.room.groovy.roomservice.AbstractRoom` - contains much of the basic logic for communicating over WebSockets
* `src/groovy/io.restall.gameon.room.groovy.roomservice.RoomState` - should contain any state that the room may hold
* `src/groovy/io.restall.gameon.room.groovy.roomservice.RoomState` - currently only contains the basic door information.  At some point logic to handle messages will be put in here.

## TODOs
* Add `ack` response
* Tidy up messy code
* Add more helper methods
* Make it easier to extend abstract room
* Improve static compilation
* lots of other stuff
* Come up with a better way to manage rooms

