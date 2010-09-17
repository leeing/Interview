package org.leeing.scala

import org.leeing.scalajava.Person 
import se.scalablesolutions.akka.actor.Actor._
import scala.io.Source

object TestScala {
  def main(args:Array[String]):Unit={
    println("hello scala java joint compilation!")
    val p = new Person("leeing")
    println(p.toString)

    val actor = actorOf[MyActor]
    actor.start
    actor !"leeing"

    val content = Source.fromFile("/home/leeing/oow").mkString
    println(content)

    actor ! content
    
  }
}
