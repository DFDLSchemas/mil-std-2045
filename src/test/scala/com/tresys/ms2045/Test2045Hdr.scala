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

class Test2045Hdr {

  val testDir = ""
  val aa = testDir + "milstd2045.tdml"
  val validateTDML = true
  //
  // TODO: fix validation - for some reason, if we run our tests
  // with validateDFDLSchema, it is unable to resolve the 
  // msi:tStringNNN symbols. The xerces xml validator resolving
  // of schema locations is not working identically to the way 
  // our own DFDL include/import schema location logic is working
  //
  val vaidateDFDLSchema = false
  lazy val runner = new DFDLTestSuite(Misc.getRequiredResource(aa), validateTDML, vaidateDFDLSchema)
  def dbg = {
    Debugger.withTracing(false)
    // LoggingDefaults.setLoggingLevel(LogLevel.Debug)
  }

  @Test def test_2045msghdr1() {
    runner.runOneTest("test2045msghdr1")
  }

  @Test def test_2045msghdr2() {
    dbg
    runner.runOneTest("test2045msghdr2")
  }

}