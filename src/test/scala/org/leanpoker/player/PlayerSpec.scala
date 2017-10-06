package org.leanpoker.player

import org.scalatest.{Matchers, WordSpecLike}
import spray.json.JsObject

class PlayerSpec extends WordSpecLike with Matchers {

  "Player" must {

    "answer 0 to betRequest" in {
      Player().betRequest(JsObject.empty) shouldBe 0
    }

  }
}
