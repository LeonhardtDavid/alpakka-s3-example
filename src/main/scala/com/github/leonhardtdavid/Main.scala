package com.github.leonhardtdavid

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.s3.S3Settings
import akka.stream.alpakka.s3.scaladsl.S3Client
import akka.stream.scaladsl.Sink
import com.typesafe.config.ConfigFactory

import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.concurrent.duration.Duration

object Main {

  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem()
    implicit val executor: ExecutionContextExecutor = system.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val config = ConfigFactory.load()

    val client = new S3Client(S3Settings(config))

    val future = client.listBucket("bucket-name", None) // instead of None you can use Some("prefix") to access a particular directory
      .map(content => content.key)
      .runWith(Sink.foreach(println))

    future.onComplete { _ =>
      system.terminate()
      Await.result(system.whenTerminated, Duration.Inf)
    }

  }

}
