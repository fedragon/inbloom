name := "bloomfilter"

version := "1.0.0"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
 
libraryDependencies ++= Seq (
  "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test",
  "junit" % "junit" % "4.10" % "test"
)
