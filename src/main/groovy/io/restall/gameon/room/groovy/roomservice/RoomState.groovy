package io.restall.gameon.room.groovy.roomservice

import ratpack.func.Action

import java.util.concurrent.CopyOnWriteArrayList

abstract class RoomState {

  protected final List<Action<String>> listeners = new CopyOnWriteArrayList<>()



}
