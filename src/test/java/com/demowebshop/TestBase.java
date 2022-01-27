package com.demowebshop;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public static final String baseUrl = "http://demowebshop.tricentis.com";
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "600x600";
        Configuration.timeout = 30000;

    }

}
