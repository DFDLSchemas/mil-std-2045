DFDL Schema for MIL-STD-2045-47001 C/D1
============

MIL-STD-2045-47001 binary message header format.
This name is usually abbreviated to just MIL-STD-2045.

This directory tree contains the DFDL Schema for:

    CONNECTIONLESS DATA TRANSFER APPLICATION LAYER STANDARD, 
    MIL-STD-2045-47001D w/CHANGE 1, 23 June 2008 

This document and other versions of it, is available publicly from US Dept. of Defense at a variety of public internet sites. 

This format uses the DFDL `dfdl:bitOrder="leastSignificantBitFirst"` property and is 
probably the best publicly available DFDL schema that illustrates use of this bit order. 

The `src/main/resources/xsd` directory contains the schema

The `src/test` directories are TDML (test data markup language) unit tests for
the Apache Daffodil DFDL implementation.

The `src/test/resources/milstd2045.tdml` file contains unit tests with test data.

Note that this DFDL schema requires the `dfdl:bitOrder` format property. 
As such it works only with Daffodil and not IBM DFDL. (as of this writing - 2025-10-21)

# Usage

This DFDL schema is a _component_ schema intended for use by reference from an _assembly_ schema
that assembles it together with the data payload schema to form a complete schema.  

To use this schema you must define this namespace prefix:

    xmlns:ms2045="urn:milstd2045DFDL"

Then you import the main schema:

    <import namespace="urn:milstd2045DFDL"
          schemaLocation="/com/owlcyberdefense/mil-std-2045/xsd/milstd2045_application_header.dfdl.xsd"/>

This defines the complex type for the MIL-STD-2045 header, which you would use by defining a 
local element to contain the header information (called `header` below) followed by a payload 
element (named `payload` below) which contains the data described by the header. In general there 
are several kinds of payloads described by MIL-STD-2045 headers (see the enumeration definition 
for `umfEnum`), and in the example below you can see a choice of two of these. 

    <element name="message">
      <complexType>
        <sequence>
          <element name="header" 
                   type="ms2045:milstd2045_application_header_type" />
          <xs:element name="payload">
            <xs:complexType>
              <!-- Below assumes only 1 message_handling_group/item. The header 
                   can support more than one, but many use cases require only one. --> 
              <xs:choice dfdl:choiceDispatchKey='{
                ../header/contents/message_handling_group/item[1]/umf/value
                }'>
                <xs:element dfdl:choiceBranchKey="OK_VMF"
                            name="message" type="tns:vmf_message"/>
                <xs:element dfdl:choiceBranchKey="OK_Binary_File"
                            name="binary_file" type="tns:binary_file_type"/>
              </xs:choice>
            </xs:complexType>
          </xs:element>
        </sequence>
      </complexType>
    </element>

A further requirement for use of this DFDL schema is to provide for the `message_size` field. 
This field is deep within the header, to be computed when unparsing in terms of the
length of the payload element. The trick by which this component schema allows the assembly 
schema to define one of its types is this: One must define a separate 
file with known name and location that is imported by this header schema. 
The header schema imports the `message_size_type` definition via this import statement:

      <import namespace="urn:milstd2045DFDLMessageSize"
          schemaLocation="/com/owlcyberdefense/mil-std-2045/xsd/milstd2045_message_size.dfdl.xsd"/>

The file at that schema location must be defined by your assembly schema. 
By the usual conventions for DFDL schema
project layouts, that file would be
located within your schema underneath the `src/main/resources/` directory. 

The contents of that file must
define a complex type named `message_size_type` in the target namespace 
`urn:milstd2045DFDLMessageSize`.
The file will typically look like this:

```xml
<?xml version='1.0' encoding='UTF-8'?>

<schema
  targetNamespace="urn:milstd2045DFDLMessageSize"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  xmlns="http://www.w3.org/2001/XMLSchema"
  xmlns:dfdl="http://www.ogf.org/dfdl/dfdl-1.0/"
  xmlns:ms2045="urn:milstd2045DFDL"
  elementFormDefault="unqualified">

  <import namespace="urn:milstd2045DFDL"
    schemaLocation="/com/owlcyberdefense/mil-std-2045/xsd/milstd2045_application_header.dfdl.xsd"/>

  <annotation>
    <appinfo source="http://www.ogf.org/dfdl/">
      <dfdl:format ref="ms2045:msCommon"/>
    </appinfo>

    </appinfo>
  </annotation>

  <complexType name="message_size_type">
    <sequence>
      <!--
      When unparsing, we recompute the message_size (of the payload) using the
      `dfdl:outputValueCalc` property
      
      This inner field must be named `value` and must be a 20 bit `tIntField` integer.
      
      This value field ends up appearing 6 levels deep inside the MIL-STD-2045 header;
      hence, the long relative path to get back to the `payload` field which 
      is a sibling to the `header` field. 
      -->
      <element name="value" type="ms2045:tIntField" dfdl:length="20"
       dfdl:outputValueCalc="{
           dfdl:valueLength(../../../../../../payload, 'bytes')
         }"
       />
    </sequence>
  </complexType>
</schema>
```

Note that this MIL-STD-2045 schema contains a definition of this `message_size_type` under the 
`src/test/resources/` directory. It is a stub used for isolated testing, but can be copied into 
your assembly schema and the stub-schema replaced by a _real_ definition, which is available -- 
though commented out -- in that file.  

# Release Notes

## Version 1.3.3

Updated to use Daffodil 4.0.0

Updated to latest DFDL schema style standard.
The exception is namespace URIs which are kept the same because this schema is in extensive use 
and changing the URIs would be a breaking incompatible change. Those URIs are: 

- `xmlns:ms2045="urn:milstd2045DFDL"`
- `xmlns:msms="urn:milstd2045DFDLMessageSize"`

Cleaned up import/include structure. Added usage instructions to README.md. 
