import io.restall.gameon.room.groovy.roomservice.GameOnHttpService
import io.restall.gameon.room.groovy.roomservice.Room
import io.restall.gameon.room.groovy.roomservice.RoomServiceConfig
import io.restall.gameon.room.groovy.roomservice.RoomServiceModule
import ratpack.websocket.WebSocketClose
import ratpack.websocket.WebSocketHandler
import ratpack.websocket.WebSocketMessage

import static ratpack.groovy.Groovy.ratpack

ratpack {
  serverConfig {
    yaml("application.yaml")
    require("/roomService", RoomServiceConfig)
  }
  bindings {
    module RoomServiceModule
  }
  handlers {
    get("delete/:room") { GameOnHttpService service ->
      service.deleteRoom(pathTokens.room)
      render("deleting $pathTokens.room")
    }
    get("room") { RoomServiceConfig roomServiceConfig, Room room ->
      context.websocket(
          [
              onOpen   : {/*do nothing*/ },
              onClose  : { WebSocketClose close -> room.close(close)/* do nothing yet*/ },
              onMessage: { WebSocketMessage msg -> room.readMessage(msg.connection, msg.text) }
          ] as WebSocketHandler);
    }
  }
}