name := "dfdl-mil-std-2045"
 
organization := "com.tresys"
 
version := "0.0.2"
 
scalaVersion := "2.11.8"
 
crossPaths := false
 
testOptions in ThisBuild += Tests.Argument(TestFrameworks.JUnit, "-v")
 
resolvers in ThisBuild ++= Seq(
  "NCSA Sonatype Releases" at "https://opensource.ncsa.illinois.edu/nexus/content/repositories/releases",
  "NCSA Sonatype Snapshots" at "https://opensource.ncsa.illinois.edu/nexus/content/repositories/snapshots"
)
 
libraryDependencies in ThisBuild := Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test",
  "edu.illinois.ncsa" %% "daffodil-tdml" % "2.0.0-SNAPSHOT" % "test"
)

// this is so that the test jar containing the message_size definition is on
// the classpath and is available when compiling the schema
fullClasspath in Test <+= (packageBin in Test).map { dir => Attributed.blank(dir) }
