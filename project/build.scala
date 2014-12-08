import sbt._
import Keys._
import scala.language.existentials
import com.typesafe.sbt.pgp.PgpSettings._

object Build1 extends Build {

  var s = Defaults.defaultSettings

  // modify the managed libraries directory to be lib/jars/ instead of lib,
  // allowing us to manage sources/docs in lib/srcs and lib/docs without them
  // being included as a dependency
  s ++= Seq(unmanagedBase <<= baseDirectory(_ / "lib" / "jars"))

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
    pgpPublicRing := file(System.getProperty("user.home")) / ".sbt" / "gpg" / "mil-std-2045" / "pubring.asc",
    pgpSecretRing := file(System.getProperty("user.home")) / ".sbt" / "gpg" / "mil-std-2045" / "secring.asc"
  )
  s ++= pgpSettings

}
