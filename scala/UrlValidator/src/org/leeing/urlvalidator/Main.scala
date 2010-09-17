package org.leeing.urlvalidator

import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import scala.actors.Actor
import scala.actors.Actor._
import scala.io.Source

case class Result(val path:String,val url:String,val status:String)
case class PathUrl(val path:String,val url:String)

class CrawlActor(val validActor:Actor) extends Actor{
  def act = loop {
    react {
      case file:String => {
          println("file received : "+file)
          crawl(file)
        }
    }
  }

  def crawl(file:String)={
    val lines = Source.fromFile(file).mkString
    var urlpattern = "http://.*".r

    if(file.endsWith("java")){
      urlpattern =""""http://.*""".r
    }

    for(url <- urlpattern findAllIn lines){
      println("<------------- get url: "+url)
      validActor ! PathUrl(file,url.replaceAll("\"", ""))
      println("-------------> send url: "+url)
    }
  }
}

class ValidateActor extends Actor{

  private var invalid = 0;
  private var valid = 0;

  def act = loop {
    react {
      case PathUrl(path,url)=>{
          println("----------------validating url :"+url)
          println("the urls to be processd: "+ mailboxSize)
          actor{
            if(validate(path,url)) valid +=1 else invalid +=1
          }
        }
    }
  }

  def validate(path:String,url:String):Boolean={
    try {
      val urladdr = new URL(url)
      val conn = urladdr.openConnection.asInstanceOf[HttpURLConnection]
      val state = conn.getResponseCode

      println("valid: "+valid+" ,invalid: "+invalid)

      if(state == 200){
        println (Result(path,url,"valid"))
        return true;
      } else{
        Console.err.println("\n"+Result(path,url,"error code is: "+state))
        return false;
      }
    }catch{
      case ex:Exception => {
          Console.err.println("\nError with url :"+url+"\nCaused by "+
                              ex.getClass.getName+","+ex.getMessage)

          return false;
        }
    }
  }
}

object Main {

  def main(args: Array[String]): Unit = {

    val dir = new File("data/");
    println(dir.getAbsoluteFile)
    val validateActor = new ValidateActor()
    validateActor.start
    
    val crawlActor = new CrawlActor(validateActor)
    crawlActor.start

    listfiles(dir,crawlActor)
    println("all files are listed")
    
  }

  def listfiles(file:File,crawlActor:Actor):Unit ={
    if(file.isDirectory){
      val files:Array[File] = file.listFiles
      for (subfile<-files){
        listfiles(subfile,crawlActor)
      }
    }else if (file.getName.endsWith(".txt") || file.getName.endsWith(".java")){
      println(file.getAbsolutePath)
      crawlActor ! file.getAbsolutePath 
    }
  }
}
