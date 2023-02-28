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

## XSAT2 Schema Quality Report

The schema has been scanned with the XSAT2 Schema Quality Tool.
The report is below:

    ***************************************
    * XSAT2 Schema Quality Checker Report  *
    * File(s) processed: 9 
    ****************************************
    In File: /milstd2045.common.dfdl.xsd

     Test 'Base64Binary or hexBinary' Line 76, Col 6, Section: 3.19, Risk Level: High
     Description: Remove the element or attribute with the binary data.
     Non-Remediation Reason:
        This hexBinary type is used only for crypographic-related fields which are
        documented in the mil-std-2045 spec. as just byte strings. The fields are:
        * authentication_data,
        * keying_material_id,
        * cryptographic_initialization,
        * key_token,
        * message_security_padding

    ****************************************
    *          End XSAT2 Report            *
    ****************************************




