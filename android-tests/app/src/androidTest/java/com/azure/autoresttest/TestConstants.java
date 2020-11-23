package com.azure.autoresttest;

public class TestConstants {
    public static String IpLeading = "100.64.";
    public static String IpTrailing = "202.121";

    public static String hostIp() {
        return IpLeading + IpTrailing;
    }

    public static String testServerRootUrl() {
        return String.format("http://%s:3000", hostIp());
    }
}
