/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leeing.scala


import se.scalablesolutions.akka.actor.Actor

class MyActor extends Actor {
  def receive = {
    case "test" => println("received test")
    case _ =>      println("received unknown message")
  }
}
