mil-std-2045
============

MIL-STD-2045 binary message header format. Uses least-significant-bit-first bit order.

This directory tree contains the DFDL Schema for MIL STD 2045.

   CONNECTIONLESS DATA TRANSFER APPLICATION LAYER STANDARD, 
   MIL-STD-2045-47001D w/CHANGE 1, 23 June 2008 

This document is available publicly from US Dept. of Defense at 

   http://assistdocs.com/

The src/main/resources/xsd directory contains the schema

The src/test directories are TDML (test data markup language) unit tests for
the Daffodil DFDL implementation - as of 2014-07-25) 

The src/test/resources/milstd2045.tdml file contains unit tests with test data.

Note that this DFDL schema requires the proposed new dfdl:bitOrder format property.

The directory is setup as an Eclipse IDE project, and contains the .project and
other files needed to run the unit tests in conjunction with the Daffodil DFDL
implementation under the Eclipse IDE. 