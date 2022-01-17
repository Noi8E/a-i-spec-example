package com.demowebshop;

import lombok.CartResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demowebshop.Specs.request;
import static com.demowebshop.Specs.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CartTests extends TestBase {

    @Test
    @DisplayName("Можем добавить товар в корзину без куки в запросе")
    void canAddItemToCartWOCookie() {

        step("Added item to cart WO cookie", () ->
                given()
                        .spec(request)
                        .when()
                        .post("/addproducttocart/details/14/1")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .body("updatetopcartsectionhtml", is("(1)"))
        );
    }

    @Test
    @DisplayName("Тест с использзованием lombok")
    void cartTestWithLombok() {
        step("can added item with lombok", () -> {
            CartResponse cartResponse =
                    given()
                            .spec(request)
                            .when()
                            .post("/addproducttocart/details/14/1")
                            .then()
                            .spec(responseSpec)
                            .log().body()
                            .extract().as(CartResponse.class);
            assertThat(cartResponse.getSuccess(), is(TRUE));

        });
    }

    @Test
    @DisplayName("Можем добавить товар в корзину с сохранением сессии")
    void canAddItemToCartWithCookie() {
        step("Added item to cart With cookie", () -> {
            given()
                    .spec(request)
                    .cookie(HARD_COOKIE)
                    .when()
                    .post("/addproducttocart/details/14/1")
                    .then()
                    .spec(responseSpec)
                    .log().body()
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                    .body("updatetopcartsectionhtml", is("(1)"));

            step("open logo for create browser-session", () ->
                    open(baseUrl + "/Themes/DefaultClean/Content/images/logo.png")

            );


            step("Inject cookie", () ->
                    getWebDriver().manage().addCookie(new Cookie("Nop.customer", HARD_COOKIE))
            );


        });

        step("Some UI Action", () -> {
        });
    }
}
