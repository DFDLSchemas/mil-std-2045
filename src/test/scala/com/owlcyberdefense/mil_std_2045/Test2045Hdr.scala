package com.owlcyberdefense.mil_std_2045
import org.junit.Test
import org.apache.daffodil.tdml.DFDLTestSuite
import org.apache.daffodil.tdml.Runner
import org.apache.daffodil.util.Misc

object Test2045Hdr {
  val testDir = "com/owlcyberdefense/mil-std-2045/"
  lazy val runner = Runner(testDir, "milstd2045.tdml")
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

  @Test def test_presence1() {
    runner.runOneTest("presence1")
  }

  @Test def test_presence2() {
    runner.runOneTest("presence2")
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

  @Test def test_2045D1_operator_ind_on_unparsed() {
    runner.runOneTest("test2045D1_operator_ind_on_unparsed")
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

  @Test def test_2045_C_operator_machine_ind_on_unparsed() {
    runner.runOneTest("test_2045_C_operator_machine_ind_on_unparsed")
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

  @Test def test_2045_D1_all_fields() {
    runner.runOneTest("test_2045_D1_all_fields")
  }

  @Test def test_2045_D1_all_fields_x2p() {
    runner.runOneTest("test_2045_D1_all_fields_x2p")
  }

  // Gets SuspensionDeadlockException on Daffodil 3.3.0
  // This is just XML for two messages in a row. One message parses/unparses round trip
  // in test_2045_D1_all_fields. But two (of the same) message in a row parses properly
  // per test_2045_D1_all_fields_x2p, but the resulting XML document does not unparse. 
  // @Test
  def test_2045_D1_all_fields_x2u() {
    runner.runOneTest("test_2045_D1_all_fields_x2u")
  }

}
