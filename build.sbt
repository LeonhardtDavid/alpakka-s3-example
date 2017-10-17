name := "alpakka-s3-tes"
organization := "com.github.leonhardtdavid"
version := "1.0-SNAPSHOT"

scalaVersion := "2.12.3"

lazy val root = project in file(".")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.2",
  "com.lightbend.akka" %% "akka-stream-alpakka-s3" % "0.13"
)

scalacOptions ++= Seq(
  "-feature"
)
