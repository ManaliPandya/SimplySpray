import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object ScalatraBootstrap extends App {

  implicit val system = ActorSystem("MyActorSystem")
  val service = system.actorOf(Props[SimpleSprayActor],"simple-actor")

  IO(Http) ! Http.Bind(service,"0.0.0.0",9000)

}
