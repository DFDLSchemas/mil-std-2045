val root = (project in file("."))
  .settings(
    name := "dfdl-mil-std-2045",
    organization := "com.owlcyberdefense",
    version := "1.3.3",
    daffodilVersion := "4.1.0",
    Seq(packageBin, packageSrc, packageDoc).map { task =>
      Compile / task / mappings += (baseDirectory.value / "COPYRIGHT.txt") -> "COPYRIGHT.txt"
    }
  )
  .daffodilProject(crossDaffodilVersions = Seq("3.6.0", "3.7.0", "3.8.0", "3.9.0", "3.10.0", "3.11.0", "4.0.0"))
