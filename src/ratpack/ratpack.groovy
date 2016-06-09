import io.restall.gameon.room.groovy.roomservice.Broadcaster
import io.restall.gameon.room.groovy.roomservice.Room
import io.restall.gameon.room.groovy.roomservice.RoomServiceConfig
import io.restall.gameon.room.groovy.roomservice.RoomServiceModule
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