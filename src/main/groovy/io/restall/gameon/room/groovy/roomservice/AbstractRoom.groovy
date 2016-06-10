package io.restall.gameon.room.groovy.roomservice

import com.google.inject.Inject
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import ratpack.websocket.WebSocket

abstract class AbstractRoom extends RoomState {

  private static jsonSlurper = new JsonSlurper()

  @Inject
  private RoomServiceConfig config

  public void personJoins(ws, content) {
    register(content.userId, ws)
    sendMessage("player", content.userId, [type       : 'location',
                                           name       : config.roomName,
                                           fullName   : config.fullRoomName,
                                           description: config.roomDescription
    ], listeners[content.userId])
  }

  public void personLeaves(content) {
    println content
  }

  public receiveMessage(content) {
    sendMessageToUser(content.userId, "You hear you message echo into the distance `$content.content`")
  }

  public void sendMessageToUser(userId, message) {
    sendMessage("player", userId,
                [type    : 'event',
                 content : [(userId): message],
                 bookmark: bookmark++],
                listeners[userId])
  }

  public void readMessage(ws, String raw) {
    def message = split(raw)
    switch (message[0]) {
      case "roomHello": personJoins(ws, message[2]); break;
      case "room": receiveMessage(message[2]); break;
      case "roomGoodbye": personLeaves(message[2]); break;
    }
    println raw
  }

  private static List<String> split(String raw) {
    def parts = raw.split(",")
    def json = jsonSlurper.parseText(parts[2..parts.length - 1].join(","))
    [parts[0], parts[1], json]
  }


  protected void sendMessage(type, userId, content, WebSocket... ws) {
    def message = "$type,$userId,${JsonOutput.toJson(content)}"
    ws*.send(message)
  }

}
