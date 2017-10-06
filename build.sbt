
name := "poker-player-scala-akka"

version := "0.1"

scalaVersion := "2.12.3"

enablePlugins(UniversalPlugin)
enablePlugins(JavaAppPackaging)

val akkaHttpVersion = "10.0.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.3" % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % Test
)