package com.owlcyberdefense.mil_std_2045

import org.apache.daffodil.util.Misc
import org.junit.{Ignore, Test}

import java.io.File

class TestWithXSAT2 {

  @Ignore("Use to run XSAT2 report manually.")
  @Test
  def test_xsat2_schema(): Unit = {
    val schemaURI = Misc.getRequiredResource("com/owlcyberdefense/mil-std-2045/xsd/milstd2045_application_header.dfdl.xsd")
    val schemaFile = new File(schemaURI)
    val schemaFilePath = schemaFile.toString
    // For XSAT2, Uncomment the line below, and the corresponding line in the built.sbt.
    // com.owlcyberdefense.xsat2.XSAT2SchemaQualityChecker.XSAT2SchemaQualityChecker.doXSAT2(schemaFilePath)
  }

}