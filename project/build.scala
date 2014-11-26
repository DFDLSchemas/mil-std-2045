import sbt._
import Keys._
import scala.language.existentials
import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._
import com.typesafe.sbt.SbtLicenseReport.autoImportImpl._
import com.typesafe.sbt.license.LicenseCategory
import com.typesafe.sbt.license.LicenseInfo
import com.typesafe.sbt.license.DepModuleInfo
import com.typesafe.sbt.license.Html
import com.typesafe.sbt.pgp.PgpSettings._

object Build1 extends Build {

  var s = Defaults.defaultSettings

  // modify the managed source and resource directories so that any generated code can be more easily included in IDE's
  s ++= Seq(sourceManaged <<= baseDirectory(_ / "src_managed"))

  s ++= Seq(resourceManaged <<= baseDirectory(_ / "resource_managed"))

  // modify the managed libraries directory to be lib/jars/ instead of lib,
  // allowing us to manage sources/docs in lib/srcs and lib/docs without them
  // being included as a dependency
  s ++= Seq(unmanagedBase <<= baseDirectory(_ / "lib" / "jars"))

  // license report configuration
  val licenseSettings = Seq(
    licenseReportTitle := "Third_Party_Licenses",
    licenseConfigurations := Set("compile"),
    licenseSelection := Seq(LicenseCategory("NCSA"), LicenseCategory("ICU")) ++ LicenseCategory.all,
    licenseOverrides := {
      case DepModuleInfo("commons-io", "commons-io", _) => LicenseInfo(LicenseCategory.Apache, "The Apache Software License, Version 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html")
      case DepModuleInfo("net.sf.expectit", "expectit-core", _) => LicenseInfo(LicenseCategory.Apache, "The Apache Software License, Version 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html")
      case DepModuleInfo("xml-resolver", "xml-resolver", _) => LicenseInfo(LicenseCategory.Apache, "The Apache Software License, Version 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html")
      case DepModuleInfo("org.scala-tools.testing", "test-interface", _) => LicenseInfo(LicenseCategory.BSD, "BSD", "https://github.com/sbt/test-interface/blob/master/LICENSE")
      case DepModuleInfo("org.hamcrest", "hamcrest-core", _) => LicenseInfo(LicenseCategory.BSD, "BSD", "https://github.com/hamcrest/JavaHamcrest/blob/master/LICENSE.txt")
    },
    licenseFilter := {
      case LicenseCategory("NCSA", _) => false
      case _ => true
    },
    licenseReportMakeHeader := {
      case Html => Html.header1(licenseReportTitle.value.replace("_", " ")) + "<p>mil-std-2045 is licensed under the <a href='https://github.com/DFDLSchemas/mil-std-2045/blob/master/COPYRIGHT.txt'>Tresys Technology Open Source License</a>.</p><p>Below are the libraries that this software depends on and their licenses.<br></p>"
      case l => l.header1(licenseReportTitle.value.replace("_", " "))
    }
  )
  s ++= licenseSettings

  // native package configuration
  val packageSettings = Seq(
    packageName := "mil-std-2045-dfdl",
    mappings in Universal += dumpLicenseReport.value / (licenseReportTitle.value + ".html") -> "LICENSES.html",
    mappings in Universal += baseDirectory.value / "README.md" -> "README.md",
    mappings in Universal <+= (packageBin, name, organization, version) map {
      (bin, n, o, v) => bin -> "lib/%s.%s-%s.jar".format(o, n, v)
    }
  )
  s ++= packageSettings


  def exec(cmd: String): Seq[String] = {
    val r = java.lang.Runtime.getRuntime()
    val p = r.exec(cmd)
    p.waitFor()
    val ret = p.exitValue()
    if (ret != 0) {
      sys.error("Command failed: " + cmd)
    }
    val is = p.getInputStream
    val res = scala.io.Source.fromInputStream(is).getLines()
    res.toSeq
  }

  // get the version from the latest tag
  s ++= Seq(version := {
    val describe = exec("git describe --long HEAD")
    assert(describe.length == 1)

    val DescribeRegex = """^(.+)-(.+)-(.+)$""".r
    val res = describe(0) match {
      case DescribeRegex(taggedVersion, "0", hash) => {
        // we are on a tag, build a tag release
        val status = exec("git status --porcelain")
        if (status.length > 0) {
          taggedVersion + "-SNAPSHOT"
        } else {
          taggedVersion
        }
      }
      case DescribeRegex(version, _, hash) => {
        // not on a tag

        // get the current branch
        val branch = exec("git rev-parse --symbolic-full-name HEAD")
        assert(branch.length == 1)
        val VersionBranchRegex = """^refs/heads/(\d+\.\d+\.\d+)$""".r
        branch(0) match {
          case "HEAD" => {
            // not on the tip of a branch
            "0.0.0-SNAPSHOT"
          }
          case VersionBranchRegex(versionBranch) => {
            // we are developing on a version branch, create a snapshot
            versionBranch + "-SNAPSHOT"
          }
          case branch => {
            // not on a version branch (e.g. a review branch), try to figure
            // out the tracking branch
            val trackingBranch = exec("git for-each-ref --format=%(upstream:short) " + branch)
            assert(trackingBranch.length == 1)
            val TrackingBranchRegex = """^[^/]+/(\d+\.\d+\.\d+)$""".r
            trackingBranch(0) match {
              case TrackingBranchRegex(trackingVersion) => {
                trackingVersion + "-SNAPSHOT"
              }
              case _ => {
                // no idea what the version is, set it to a default
                "0.0.0-SNAPSHOT"
              }
            }
          }
        }
      }
    }
    res
  })

  def gitShortHash(): String = {
    val hash = exec("git rev-parse --short HEAD")
    assert(hash.length == 1)
    hash(0)
  }


  // update the manifest version to include the git hash
  lazy val manifestVersion = packageOptions in (Compile, packageBin) <++= version map { v => {
    val version = "%s-%s".format(v, gitShortHash)
    Seq(
      Package.ManifestAttributes(java.util.jar.Attributes.Name.IMPLEMENTATION_VERSION -> version),
      Package.ManifestAttributes(java.util.jar.Attributes.Name.SPECIFICATION_VERSION -> version)
    )
  }}
  s ++= manifestVersion

  // by default, sbt-pgp tries to read ~/.gnupg for keys. We need to force it
  // to be .sbt, which makes it easier to pass the keys around without having
  // to import into gnupg keyrings
  lazy val pgpSettings = Seq(
    pgpPublicRing := file(System.getProperty("user.home")) / ".sbt" / "gpg" / "daffodil" / "pubring.asc",
    pgpSecretRing := file(System.getProperty("user.home")) / ".sbt" / "gpg" / "daffodil" / "secring.asc"
  )
  s ++= pgpSettings

}
