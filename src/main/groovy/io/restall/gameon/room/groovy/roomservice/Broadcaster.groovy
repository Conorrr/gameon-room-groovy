package io.restall.gameon.room.groovy.roomservice

import groovy.json.JsonOutput
import ratpack.func.Action

import java.util.concurrent.CopyOnWriteArrayList

class Broadcaster {

  private final List<Action<String>> listeners = new CopyOnWriteArrayList<>()

  public AutoCloseable register(Action<String> subscriber) {
    listeners << subscriber
    new AutoCloseable() {

      void close() {
        listeners.remove subscriber
      }
    }
  }

  public void sendMessage(type, userId, response) {
    broadcast("$type,$userId,${JsonOutput.toJson(response)}")
  }

  public void broadcast(String msg) {
    listeners*.execute(msg)
  }
}
