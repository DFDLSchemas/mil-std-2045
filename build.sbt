name := "dfdl-mil-std-2045"
 
organization := "com.tresys"
 
version := "0.0.2"

scalaVersion := "2.11.12"
 
crossPaths := false
 
testOptions in ThisBuild += Tests.Argument(TestFrameworks.JUnit, "-v")
 
libraryDependencies in ThisBuild := Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.apache.daffodil" %% "daffodil-tdml" % "2.2.0" % "test"
)

