package io.restall.gameon.room.groovy.roomservice

import com.google.inject.Inject
import groovy.json.JsonSlurper

class Room {

  @Inject
  private Broadcaster broadcaster

  @Inject
  private RoomServiceConfig config

  def static jsonSlurper = new JsonSlurper()

  public static doors = ["n": "A Large doorway to the south",
                         "s": "A winding path leading off to the north",
                         "e": "An overgrown road, covered in brambles",
                         "w": "A shiny metal door, with a bright red handle",
                         "u": "A tunnel, leading down into the earth",
                         "d": "A spiral set of stairs, leading upward into the ceiling"]

  public void readMessage(String raw) {
    def message = split(raw)
    switch (message[0]) {
      case "roomHello": hello(message[2]); break;

    }

  }

  public void hello(def details) {
    broadcaster.sendMessage("player", details.userId, [type       : 'location',
                                           name       : config.roomName,
                                           fullName   : config.fullRoomName,
                                           description: config.roomDescription
    ])
  }



  private static List<String> split(String raw) {
    def parts = raw.split(",")
    def json = jsonSlurper.parseText(parts[2..parts.length - 1].join(","))
    [parts[0], parts[1], json]
  }

}
