package io.restall.gameon.room.groovy.roomservice

import ratpack.websocket.WebSocket

import java.util.concurrent.ConcurrentHashMap

abstract class RoomState {

  // userid, ws
  protected final Map<String, WebSocket> listeners = new ConcurrentHashMap<>()

  protected int bookmark = 0

  public register(String userId, WebSocket ws) {
    listeners[userId] = ws
  }

  public close(content) {
    // get userid and remove listeners
  }

}
