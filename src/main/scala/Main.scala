//import akka.actor._



/*
object ActorTest extends App {

  val person = new Person("Ehsan")
  val system = ActorSystem("FirstTest")
  val firstActor = system.actorOf(Props[new FirstActor(person)], name = "firstActor")

}


class Person(var name: String) {

  def setName(changedName: String): Unit = {
    name = changedName
  }
}

class FirstActor(person: Person) extends Actor {

  def receive = {
    case "changeName" =>
      while (true) {
        person.setName("Ali")
      }
    case _ => println("Invalid Message")
  }
}

class SecondActor(person: Person) extends Actor {

  def receive = {
    case "changeName" =>
      while (true) {
        person.setName("Mohsen")
      }
    case _ => println("Invalid Message")
  }
}

class ThirdActor(person: Person) extends Actor {

  def receive = {
    case "printIt" => println(person.name)
    case _ => println("Invalid Message")
  }
}*/









// example for system.stop(actor) and constructor methods

/*package actortests

import akka.actor._


class TestActor extends Actor {

  override def receive = {
    case _ => println("a message was received")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("preRestar was called")
    super.preRestart(reason, message)
  }

  override def preStart(): Unit = {
    println("preStart was called")
    super.preStart()
  }

  override def postStop(): Unit = {
    println("postStop was called")
    super.postStop()
  }

  override def postRestart(reason: Throwable): Unit = {
    println("postRestart was called")
    super.postRestart(reason)
  }
}



object SystemStopExample extends App {

  val actorSystem = ActorSystem("SystemStopExample")
  val actor = actorSystem.actorOf(Props[TestActor], name = "actor")
  actor ! "hello"

  actorSystem.stop(actor)

  actor ! "hello"
  actor ! "hello"
}*/



// Future Test

/*
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.duration.Duration


object FutureTest {

  def main(args: Array[String]): Unit = {

    implicit val prefix: String = "The number is:"
//    implicit val newPrefix: String = "aaa"

    pritnWithPrefix(10)
  }

  def pritnWithPrefix(number: Int)(implicit prefix: String): Unit = {

    println(f"${prefix}%20s${number}%20s")
  }

  def testIt = {


  }
}


import scala.concurrent.ExecutionContext.Implicits.global

val ft = Future {

  Thread.sleep(1000)
  "Ehsan"
}
*/


// calculate sum of array with number of futures


import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.duration.Duration
import scala.io.StdIn.readLine
import scala.util.{Failure, Success}
import scala.collection.mutable.ListBuffer



object Result {

  var value: Int = 0
  def show  = println(value)
}

object SumOfArray {

  def main(args: Array[String]): Unit = {

    var numberOfThreads: Int = readLine().toInt
    var numberOfElements: Int = readLine().toInt
    var buffer = new Array[Int](numberOfElements)


    for (i <- 0 to numberOfElements - 1) {

      buffer(i) = readLine().toInt
    }

    //    buffer.map({i => println(i)})

    println(f"The result is: ${sumIt(buffer, numberOfThreads)}%30d")
  }

  def sumIt(numbers: Array[Int], numberOfThreads: Int): Int = {

    var result: Int = 0


    var futures = numbers.grouped(numbers.size / numberOfThreads).map { ls =>
      Future {
        var resultValue = 0
        ls.map(i => resultValue += i)
//        println(resultValue)
        resultValue
//        Await.result(ls, Duration.Inf)
      }
    }

    val rsl = Future.sequence(futures).map { ls =>


      ls.map(resultOfThread => result += resultOfThread)
      result
    }.onComplete({
      case Success(value) => value
      case  Failure(exception) => println("Bugggg")
    })

//    Await.result(futures, Duration.Inf)
    Thread.sleep(1000)
    result

    //    var sumOfElements: Int = 0
    /*val coefficient = (numbers.size / numberOfThreads)
//    val futuressss: Array[Future[Int]] = {

      var result = new Array[Future[Int]](numberOfThreads)

      for (i <- 0 to (numberOfThreads - 1)) {

        result(i) = Future {

          var resultValue: Int = 0
          for (j <- (i * coefficient) to ((i + 1) * coefficient - 1)) {
            resultValue += numbers(j)
          }
          resultValue
        }
      }
      result
    }
    val resultFuture = Future.sequence(futures.toSeq)

    var sum:Int  = 0

    resultFuture.onComplete({

      case Success(value) =>
        value.map(i =>  sum = sum + i)
      case Failure(exception) => println("Buggggggg")
    })

    Thread.sleep(1000)
    sum
  }*/

  }

}