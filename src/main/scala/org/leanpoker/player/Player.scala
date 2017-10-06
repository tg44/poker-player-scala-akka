package org.leanpoker.player

import spray.json.JsValue

case class Player() {

  def betRequest(request: JsValue) = 0

  def showdown(game: JsValue) = {

  }

}

object Player {
  val version = "Default Scala folding player"


}