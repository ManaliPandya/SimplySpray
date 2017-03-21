import akka.actor.Actor
import spray.http.MediaTypes
import spray.httpx.SprayJsonSupport._
import spray.json.JsObject
import spray.routing.Directives._
import spray.routing.HttpService
import DataRepository._
import Person._
import Input._


class SimpleSprayActor extends Actor with HttpRoutes {
  def actorRefFactory = context
  implicit val system = context.system
  import spray.routing.RejectionHandler.Default

  def receive = runRoute(route)

}

trait HttpRoutes extends  HttpService   {
//  import MyJsonProtocol._

  val route = {
    path("status"){
      get{
        complete("Ok")
      }
    }~
    path("greetMe"){
      get{
        parameters("myname" ? "there",'id.as[Int] ? 0){(myname,id)=>
        complete( "Hi "+ myname  +", how are you? Thanks for providing your id: "+id)
        }
      }
    }~
    path("findData"){
      post{
          detach() {
            respondWithMediaType(MediaTypes.`application/json`)
            entity(as[Input]) {x=>
              complete(findData(x))
          }
        }
      }
    }~
    path("addData"){
      post{
        detach(){
          entity(as[Person]){p=>
            addData(p)
            complete("Inserted data.")
//            modify our post call to read data
//            complete(Person(p.name + "pandya", p.details))
          }
        }
      }
    }
  }
}