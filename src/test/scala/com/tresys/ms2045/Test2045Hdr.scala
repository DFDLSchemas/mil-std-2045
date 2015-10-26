package com.tresys.ms2045
import org.junit.Test
import edu.illinois.ncsa.daffodil.tdml.DFDLTestSuite
import edu.illinois.ncsa.daffodil.util.Misc

class Test2045Hdr {

  val testDir = ""
  val aa = testDir + "milstd2045.tdml"
  val validateTDML = true
  val validateDFDLSchema = true
  lazy val runner = new DFDLTestSuite(Misc.getRequiredResource(aa), validateTDML, validateDFDLSchema)
  runner.setCheckAllTopLevel(true)

  @Test def test_presenceByChoice1() {
    runner.runOneTest("presenceByChoice1")
  }

  @Test def test_presenceByChoice2() {
    runner.runOneTest("presenceByChoice2")
  }

  @Test def test_2045msghdr1() {
    runner.runOneTest("test2045msghdr1")
  }

  @Test def test_2045msghdr2() {
    runner.runOneTest("test2045msghdr2")
  }

}
