import io.restall.gameon.room.groovy.roomservice.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static ratpack.groovy.Groovy.ratpack

ratpack {
  final Logger logger = LoggerFactory.getLogger("ratpack")
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
    get("room") { Broadcaster broadcaster, RoomServiceConfig roomServiceConfig, Room room ->
      context.websocket { ws ->
        broadcaster.register {
          ws.send(it)
        }
      }.connect {
        it.onClose {
          it.openResult.close()
        }
        it.onMessage {
          room.readMessage(it.text)

//          switch (it.text) {
//            case "roomHello":
//              sessions.add(session);
//              addNewPlayer(session, contents[2]);
//              break;
//            case "room":
//              processCommand(session, contents[2]);
//              break;
//            case "roomGoodbye":
//              removePlayer(session, contents[2]);
//              break;
//          }

          logger.info(it.text)
          it.connection.send(it.text)
        }
      }
    }
  }
}