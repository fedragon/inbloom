name := "inbloom"

version := "1.0.0"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
 
libraryDependencies ++= Seq (
	"org.scalaz" %% "scalaz-core" % "7.0.5",
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "junit" % "junit" % "4.10" % "test"
)
