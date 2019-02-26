lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.tresys",
      version      := "0.0.2",
      scalaVersion := "2.12.6",
      crossPaths := false,
      testOptions += Tests.Argument(TestFrameworks.JUnit, "-v"),
    )),
    name := "dfdl-mil-std-2045",
    libraryDependencies := Seq(
      "org.apache.daffodil" %% "daffodil-tdml-processor" % "2.3.0" % "test",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test",
    )
  )
