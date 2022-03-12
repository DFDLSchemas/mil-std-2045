mil-std-2045
============

MIL-STD-2045 binary message header format. Uses least-significant-bit-first bit order.

This directory tree contains the DFDL Schema for MIL STD 2045.

   CONNECTIONLESS DATA TRANSFER APPLICATION LAYER STANDARD, 
   MIL-STD-2045-47001D w/CHANGE 1, 23 June 2008 

This document and other versions of it, is available publicly from US Dept. of Defense at a variety of public internet sites. 

The src/main/resources/xsd directory contains the schema

The src/test directories are TDML (test data markup language) unit tests for
the Apache Daffodil DFDL implementation.

The src/test/resources/milstd2045.tdml file contains unit tests with test data.

Note that this DFDL schema requires the dfdl:bitOrder format property. 
As such it works only with Daffodil and not IBM DFDL. (as of this writing - 2022-03-12)


