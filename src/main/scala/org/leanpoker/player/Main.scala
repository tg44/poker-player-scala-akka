package org.leanpoker.player

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.{Directive1, Route}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json._

import scala.concurrent.{ExecutionContextExecutor, Future}

object Main extends App {
  implicit val system = ActorSystem("poker-player")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val player = Player()

  val bodyParserDirective: Directive1[Map[String, String]] = {
    entity(as[String]).map{
      str =>
        str.split("&").map{ p =>
          val s = p.split("=")
          require(s.size==2)
          (s.head, s(1))
        }.toMap
    }
  }

  val routes: Route = {
    post {
      bodyParserDirective { params =>
        params("action") match {
          case "bet_request" =>
            complete(player.betRequest(params("game_state").parseJson).toString)
          case "showdown" =>
            player.showdown(params("game_state").parseJson)
            complete(HttpResponse(OK))
          case _ =>
            complete(Player.version)
        }
      }
    }
  }



  val adminApiBindingFuture: Future[ServerBinding] = Http()
    .bindAndHandle(routes, "localhost", 8080)
    .map(binding => {
      println(s"Server started on localhost:8080")
      binding
    })

}
