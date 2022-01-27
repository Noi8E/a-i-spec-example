package com.demowebshop;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public static final String baseUrl = "http://demowebshop.tricentis.com";
    public static final String wishListBody =
            "giftcard_2.RecipientName=Test&giftcard_2.RecipientEmail=test@test.ru" +
                    "&giftcard_2.SenderName=Test2&giftcard_2.SenderEmail=test2@test.ru&" +
                    "giftcard_2.Message=TestMessage&addtocart_2.EnteredQuantity=1";

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "600x600";
        Configuration.timeout = 30000;
    }
}
