package io.restall.gameon.room.groovy.roomservice

import com.google.inject.Inject
import groovy.json.JsonSlurper

class Room extends AbstractRoom{

  public static doors = ["n": "A Large doorway to the south",
                         "s": "A winding path leading off to the north",
                         "e": "An overgrown road, covered in brambles",
                         "w": "A shiny metal door, with a bright red handle",
                         "u": "A tunnel, leading down into the earth",
                         "d": "A spiral set of stairs, leading upward into the ceiling"]

}
