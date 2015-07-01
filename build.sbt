name := "bracelet_udp_scala"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.11"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.12"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "2.2.7"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.35"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"