name := "dfdl-mil-std-2045"

organization := "com.owlcyberdefense"

version := "1.0.9"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.daffodil" %% "daffodil-tdml-processor" % "3.4.0" % "test",
  "junit" % "junit" % "4.13.2" % "test",
  "com.github.sbt" % "junit-interface" % "0.13.2" % "test",
  "org.apache.logging.log4j" % "log4j-core" % "2.18.0" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

crossPaths := false
