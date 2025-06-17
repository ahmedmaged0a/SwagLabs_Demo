package com.swaglabs.utils;

import org.testng.asserts.SoftAssert;

public class CustomSoftAssertions extends SoftAssert {


    public static  CustomSoftAssertions softAssertions = new CustomSoftAssertions();

    public static void customAssertAll()
    {
        try {
            softAssertions.assertAll("Custom Soft Assertions");
        }catch (Exception e) {
            System.err.println("Custom Soft Assertions failed: " + e.getMessage());
        }
    }
}
