package io.restall.gameon.room.groovy.roomservice

import com.google.inject.Scopes
import ratpack.guice.ConfigurableModule

class RoomServiceModule extends ConfigurableModule<RoomServiceConfig> {

  @Override
  protected void configure() {
    bind(Broadcaster).in(Scopes.SINGLETON)
    bind(GameOnHttpService).in(Scopes.SINGLETON)
    bind(SecurityService).in(Scopes.SINGLETON)
    bind(Room)
  }
}
