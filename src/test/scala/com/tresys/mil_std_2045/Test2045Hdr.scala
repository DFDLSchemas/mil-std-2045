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

  @Test def test_stringWSPBug() {
    runner.runOneTest("stringWSPBug")
  }

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

  @Test def test_2045msghdr1_unparsed() {
    runner.runOneTest("test2045msghdr1_unparsed")
  }

  @Test def test_2045msghdr1_variant_unparsed() {
    runner.runOneTest("test2045msghdr1_variant_unparsed")
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

  @Test def test_2045_C_msghdr1_unparsed() {
    runner.runOneTest("test_2045_C_msghdr1_unparsed")
  }

  @Test def test_2045_C_msghdr1_variant_unparsed() {
    runner.runOneTest("test_2045_C_msghdr1_variant_unparsed")
  }

  @Test def test_2045_C_msghdr2() {
    runner.runOneTest("test_2045_C_msghdr2")
  }

  @Test def test_2045_D1_minimum_size_header() {
    runner.runOneTest("test_2045_D1_minimum_size_header")
  }

  @Test def test_2045_C_minimum_size_header() {
    runner.runOneTest("test_2045_C_minimum_size_header")
  }

  @Test def test_2045_D1_control_release_marking1() {
    runner.runOneTest("test_2045_D1_control_release_marking1")
  }

  @Test def test_2045_D1_control_release_marking2() {
    runner.runOneTest("test_2045_D1_control_release_marking2")
  }

  @Test def test_2045_C_control_release_marking1() {
    runner.runOneTest("test_2045_C_control_release_marking1")
  }

  @Test def test_2045_C_minimum_size_acknoledgement() {
    runner.runOneTest("test_2045_C_minimum_size_acknoledgement")
  }
}
