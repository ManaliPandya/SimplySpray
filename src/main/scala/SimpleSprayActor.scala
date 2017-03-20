import akka.actor.Actor
import spray.routing.HttpService

class SimpleSprayActor extends Actor with HttpRoutes{

  def actorRefFactory = context
  def receive = runRoute(route)

}

trait HttpRoutes extends HttpService{
  val route = {
    path("status"){
      get{
        complete("Hello")
      }
    }
  }
}