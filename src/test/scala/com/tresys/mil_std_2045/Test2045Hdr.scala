package com.tresys.mil_std_2045
import org.junit.Test
import org.apache.daffodil.tdml.DFDLTestSuite
import org.apache.daffodil.util.Misc

object Test2045Hdr {
  val testDir = "com/tresys/mil-std-2045/"
  val aa = testDir + "milstd2045.tdml"
  val validateTDML = true
  val validateDFDLSchema = true
  lazy val runner = new DFDLTestSuite(Misc.getRequiredResource(aa), validateTDML, validateDFDLSchema)
}

class Test2045Hdr {
  import Test2045Hdr._

  @Test def test_stringMaxLength() {
    runner.runOneTest("stringMaxLength")
  }

  @Test def test_stringMaxLengthUnparse() {
    runner.runOneTest("stringMaxLengthUnparse")
  }

  // Fixes are needed to Daffodil, which enable the fix to mil-std-2045 schema
  // in order for this test to work.
  //
  // Also affects VMF - see JIRA https://jira.di2e.net/browse/DFDL-18 for VMF
  // github https://github.com/DFDLSchemas/mil-std-2045/issues/4 for mil-std-2045
  // Apache Daffodil JIRA DAFFODIL-1493 and DAFFODIL-1477
  // @Test def test_stringWSPBug() { runner.runOneTest("stringWSPBug") }

  @Test def test_presenceByChoice1() {
    runner.runOneTest("presenceByChoice1")
  }

  @Test def test_presenceByChoice2() {
    runner.runOneTest("presenceByChoice2")
  }

  @Test def test_recurrenceField1() {
    runner.runOneTest("recurrenceField1")
  }

  @Test def test_recurrenceField2() {
    runner.runOneTest("recurrenceField2")
  }

  @Test def test_2045msghdr1() {
    runner.runOneTest("test2045msghdr1")
  }

  @Test def test_2045msghdr2() {
    runner.runOneTest("test2045msghdr2")
  }

  @Test def test_2045msghdr3() {
    runner.runOneTest("test2045msghdr3")
  }

  @Test def test_2045_C_msghdr1() {
    runner.runOneTest("test_2045_C_msghdr1")
  }

  @Test def test_2045_C_msghdr2() {
    runner.runOneTest("test_2045_C_msghdr2")
  }
}
