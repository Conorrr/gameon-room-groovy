package io.restall.gameon.room.groovy.roomservice

abstract class AbstractRoom extends RoomState {

  public void personJoins(ws) {
    sendMessage(ws)
  }

  public void personLeaves(ws) {

  }

  public static void messageReceived() {

  }

  public static receiveMessage() {

  }

  protected void sendMessage(ws) {

  }


}
