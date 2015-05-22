package com.tresys.ms2045
import junit.framework.Assert._
import org.junit.Test
import scala.xml._
import edu.illinois.ncsa.daffodil.tdml.DFDLTestSuite
import edu.illinois.ncsa.daffodil.debugger.Debugger
import edu.illinois.ncsa.daffodil.util.Misc
import edu.illinois.ncsa.daffodil.debugger.InteractiveDebugger
import edu.illinois.ncsa.daffodil.debugger.TraceDebuggerRunner
import edu.illinois.ncsa.daffodil.util.LoggingDefaults
import edu.illinois.ncsa.daffodil.util.LogLevel
import edu.illinois.ncsa.daffodil.dsom.ExpressionCompiler
import edu.illinois.ncsa.daffodil.util.TestUtils

class Test2045Hdr {

  val testDir = ""
  val aa = testDir + "milstd2045.tdml"
  val validateTDML = true
  val validateDFDLSchema = true
  lazy val runner = new DFDLTestSuite(Misc.getRequiredResource(aa), validateTDML, validateDFDLSchema)
  runner.setCheckAllTopLevel(true)
  def dbg = {
    Debugger.setDebugger(new InteractiveDebugger(new TraceDebuggerRunner, ExpressionCompiler))
    Debugger.withTracing(false)
    // LoggingDefaults.setLoggingLevel(LogLevel.Debug)
  }

  @Test def test_presenceByChoice1() {
    runner.runOneTest("presenceByChoice1")
  }

  @Test def test_presenceByChoice2() {
    runner.runOneTest("presenceByChoice2")
  }

  @Test def test_2045msghdr1() {
    TestUtils.trace
    runner.runOneTest("test2045msghdr1")
  }

  @Test def test_2045msghdr2() {
    runner.runOneTest("test2045msghdr2")
  }

}