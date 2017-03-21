import spray.json._
import DefaultJsonProtocol._
import scala.concurrent.ExecutionContext

object DataRepository {

  val mp = scala.collection.mutable.Map[String,Tuple3[Int,String,Long]]("Manali"->Tuple3("Address123",1234567891,"otherdetails1"))

  case class Input(name: String)
  object Input extends DefaultJsonProtocol {
    implicit val InputFormat = jsonFormat1(Input.apply)

  }

  case class Person(name: String, details: Tuple3[Int, String, Long])
  object Person extends DefaultJsonProtocol {
    implicit val PersonFormat = jsonFormat2(Person.apply)
  }

  def findData(I: Input) = {
    val details = mp(I.name)
    Person(I.name, details)
  }

  def addData(person: Person): Unit = {
    mp(person.name) = person.details
    ()
  }
}