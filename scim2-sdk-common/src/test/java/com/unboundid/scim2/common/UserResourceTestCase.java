/*
 * Copyright 2015 UnboundID Corp.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.unboundid.scim2.common;

import com.unboundid.scim2.common.types.EnterpriseUserExtension;
import com.unboundid.scim2.common.types.UserResource;
import com.unboundid.scim2.common.utils.SchemaUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Some basic tests for serializing and de-serializing of the core user
 * resource.
 */
public class UserResourceTestCase
{
  /**
   * Test de-serializing the full core user representation copied from
   * draft-ietf-scim-core-schema-20.
   *
   * @throws Exception if an error occurs.
   */
  @Test
  public void testSpecFullRepresentation() throws Exception
  {
    String fullRepresentation = "{  \n" +
        "  \"schemas\":[  \n" +
        "    \"urn:ietf:params:scim:schemas:core:2.0:User\",\n" +
        "    \"urn:ietf:params:scim:schemas:extension:enterprise:2.0:User\"\n" +
        "  ],\n" +
        "  \"id\":\"2819c223-7f76-453a-919d-413861904646\",\n" +
        "  \"externalId\":\"701984\",\n" +
        "  \"userName\":\"bjensen@example.com\",\n" +
        "  \"name\":{  \n" +
        "    \"formatted\":\"Ms. Barbara J Jensen III\",\n" +
        "    \"familyName\":\"Jensen\",\n" +
        "    \"givenName\":\"Barbara\",\n" +
        "    \"middleName\":\"Jane\",\n" +
        "    \"honorificPrefix\":\"Ms.\",\n" +
        "    \"honorificSuffix\":\"III\"\n" +
        "  },\n" +
        "  \"displayName\":\"Babs Jensen\",\n" +
        "  \"nickName\":\"Babs\",\n" +
        "  \"profileUrl\":\"https://login.example.com/bjensen\",\n" +
        "  \"emails\":[  \n" +
        "    {  \n" +
        "      \"value\":\"bjensen@example.com\",\n" +
        "      \"type\":\"work\",\n" +
        "      \"primary\":true\n" +
        "    },\n" +
        "    {  \n" +
        "      \"value\":\"babs@jensen.org\",\n" +
        "      \"type\":\"home\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"addresses\":[  \n" +
        "    {  \n" +
        "      \"streetAddress\":\"100 Universal City Plaza\",\n" +
        "      \"locality\":\"Hollywood\",\n" +
        "      \"region\":\"CA\",\n" +
        "      \"postalCode\":\"91608\",\n" +
        "      \"country\":\"USA\",\n" +
        "      \"formatted\":\"100 Universal City Plaza\\nHollywood, " +
        "CA 91608 USA\",\n" +
        "      \"type\":\"work\",\n" +
        "      \"primary\":true\n" +
        "    },\n" +
        "    {  \n" +
        "      \"streetAddress\":\"456 Hollywood Blvd\",\n" +
        "      \"locality\":\"Hollywood\",\n" +
        "      \"region\":\"CA\",\n" +
        "      \"postalCode\":\"91608\",\n" +
        "      \"country\":\"USA\",\n" +
        "      \"formatted\":\"456 Hollywood Blvd\\nHollywood, " +
        "CA 91608 USA\",\n" +
        "      \"type\":\"home\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"phoneNumbers\":[  \n" +
        "    {  \n" +
        "      \"value\":\"555-555-5555\",\n" +
        "      \"type\":\"work\"\n" +
        "    },\n" +
        "    {  \n" +
        "      \"value\":\"555-555-4444\",\n" +
        "      \"type\":\"mobile\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"ims\":[  \n" +
        "    {  \n" +
        "      \"value\":\"someaimhandle\",\n" +
        "      \"type\":\"aim\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"photos\":[  \n" +
        "    {  \n" +
        "      \"value\":\"https://photos.example.com/profilephoto/" +
        "72930000000Ccne/F\",\n" +
        "      \"type\":\"photo\"\n" +
        "    },\n" +
        "    {  \n" +
        "      \"value\":\"https://photos.example.com/profilephoto/" +
        "72930000000Ccne/T\",\n" +
        "      \"type\":\"thumbnail\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"userType\":\"Employee\",\n" +
        "  \"title\":\"Tour Guide\",\n" +
        "  \"preferredLanguage\":\"en-US\",\n" +
        "  \"locale\":\"en-US\",\n" +
        "  \"timezone\":\"America/Los_Angeles\",\n" +
        "  \"active\":true,\n" +
        "  \"password\":\"t1meMa$heen\",\n" +
        "  \"groups\":[  \n" +
        "    {  \n" +
        "      \"value\":\"e9e30dba-f08f-4109-8486-d5c6a331660a\",\n" +
        "      \"$ref\":\"../Groups/e9e30dba-f08f-4109-8486-d5c6a331660a\",\n" +
        "      \"display\":\"Tour Guides\"\n" +
        "    },\n" +
        "    {  \n" +
        "      \"value\":\"fc348aa8-3835-40eb-a20b-c726e15c55b5\",\n" +
        "      \"$ref\":\"../Groups/fc348aa8-3835-40eb-a20b-c726e15c55b5\",\n" +
        "      \"display\":\"Employees\"\n" +
        "    },\n" +
        "    {  \n" +
        "      \"value\":\"71ddacd2-a8e7-49b8-a5db-ae50d0a5bfd7\",\n" +
        "      \"$ref\":\"../Groups/71ddacd2-a8e7-49b8-a5db-ae50d0a5bfd7\",\n" +
        "      \"display\":\"US Employees\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"x509Certificates\":[  \n" +
        "    {  \n" +
        "      \"value\":\"MIIDQzCCAqygAwIBAgICEAAwDQYJKoZIhvcNAQEFBQAwTj" +
        "ELMAkGA1UEBhMCVVMx \n" +
        "        EzARBgNVBAgMCkNhbGlmb3JuaWExFDASBgNVBAoMC2V4YW1wbGUuY29t" +
        "MRQwEgYD \n" +
        "        VQQDDAtleGFtcGxlLmNvbTAeFw0xMTEwMjIwNjI0MzFaFw0xMjEwMDQw" +
        "NjI0MzFa \n" +
        "        MH8xCzAJBgNVBAYTAlVTMRMwEQYDVQQIDApDYWxpZm9ybmlhMRQwEgYD" +
        "VQQKDAtl \n" +
        "        eGFtcGxlLmNvbTEhMB8GA1UEAwwYTXMuIEJhcmJhcmEgSiBKZW5zZW4g" +
        "SUlJMSIw \n" +
        "        IAYJKoZIhvcNAQkBFhNiamVuc2VuQGV4YW1wbGUuY29tMIIBIjANBgkq" +
        "hkiG9w0B \n" +
        "        AQEFAAOCAQ8AMIIBCgKCAQEA7Kr+Dcds/JQ5GwejJFcBIP682X3xpjis" +
        "56AK02bc \n" +
        "        1FLgzdLI8auoR+cC9/Vrh5t66HkQIOdA4unHh0AaZ4xL5PhVbXIPMB5v" +
        "APKpzz5i \n" +
        "        PSi8xO8SL7I7SDhcBVJhqVqr3HgllEG6UClDdHO7nkLuwXq8HcISKkbT" +
        "5WFTVfFZ \n" +
        "        zidPl8HZ7DhXkZIRtJwBweq4bvm3hM1Os7UQH05ZS6cVDgweKNwdLLrT" +
        "51ikSQG3 \n" +
        "        DYrl+ft781UQRIqxgwqCfXEuDiinPh0kkvIi5jivVu1Z9QiwlYEdRbLJ" +
        "4zJQBmDr \n" +
        "        SGTMYn4lRc2HgHO4DqB/bnMVorHB0CC6AV1QoFK4GPe1LwIDAQABo3sw" +
        "eTAJBgNV \n" +
        "        HRMEAjAAMCwGCWCGSAGG+EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZ" +
        "XJ0aWZp \n" +
        "        Y2F0ZTAdBgNVHQ4EFgQU8pD0U0vsZIsaA16lL8En8bx0F/gwHwYDVR0jB" +
        "BgwFoAU \n" +
        "        dGeKitcaF7gnzsNwDx708kqaVt0wDQYJKoZIhvcNAQEFBQADgYEAA81Ss" +
        "FnOdYJt \n" +
        "        Ng5Tcq+/ByEDrBgnusx0jloUhByPMEVkoMZ3J7j1ZgI8rAbOkNngX8+pK" +
        "fTiDz1R \n" +
        "        C4+dx8oU6Za+4NJXUjlL5CvV6BEYb1+QAEJwitTVvxB/A67g42/vzgAto" +
        "RUeDov1 \n" +
        "        +GFiBZ+GNF/cAYKcMtGcrs2i97ZkJMo=\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"urn:ietf:params:scim:schemas:extension:enterprise:2.0:User\":{\n" +
        "    \"employeeNumber\":\"701984\",\n" +
        "    \"costCenter\":\"4130\",\n" +
        "    \"organization\":\"Universal Studios\",\n" +
        "    \"division\":\"Theme Park\",\n" +
        "    \"department\":\"Tour Operations\",\n" +
        "    \"manager\":{  \n" +
        "      \"value\":\"26118915-6090-4610-87e4-49d8ca9f808d\",\n" +
        "      \"$ref\":\"../Users/26118915-6090-4610-87e4-49d8ca9f808d\",\n" +
        "      \"displayName\":\"John Smith\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"meta\":{  \n" +
        "    \"resourceType\":\"User\",\n" +
        "    \"created\":\"2010-01-23T04:56:22Z\",\n" +
        "    \"lastModified\":\"2011-05-13T04:42:34Z\",\n" +
        "    \"version\":\"W\\/\\\"3694e05e9dff591\\\"\",\n" +
        "    \"location\":\"https://example.com/v2/Users/" +
        "2819c223-7f76-453a-919d-413861904646\"\n" +
        "  }\n" +
        "}";

    UserResource userResource =
        SchemaUtils.createSCIMCompatibleMapper().readValue(
            fullRepresentation, UserResource.class);

    EnterpriseUserExtension enterpriseUserExtension =
        userResource.getExtensionValue(
            Path.extension(EnterpriseUserExtension.class),
            EnterpriseUserExtension.class);

    assertNotNull(enterpriseUserExtension);

    String serializedString =
        SchemaUtils.createSCIMCompatibleMapper().writeValueAsString(
            userResource);

    assertEquals(
        SchemaUtils.createSCIMCompatibleMapper().readValue(
            serializedString, UserResource.class),
        userResource);
  }
}