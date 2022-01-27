package com.demowebshop;

import lombok.CartResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static specification.Specs.*;

public class CartTests extends TestBase {

    @Test
    @DisplayName("Можем добавить товар в корзину без куки в запросе")
    void canAddItemToCartWOCookie() {

        step("Добавление товара без куки", () ->
                given()
                        .spec(cartRequest)
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
    @DisplayName("Добавление товара в корзину  без куки (lombok)")
    void cartTestWithLombok() {
        step("Отправка запроса с помощью lombok", () -> {
            CartResponse cartResponse =
                    given()
                            .spec(cartRequest)
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
                    .spec(cartRequestWithCookie)
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
                    getWebDriver().manage().addCookie(new Cookie("Nop.customer", "a578b8a0-72b1-4a15-91e8-65255bc54583"))
            );
        });
        step("Some UI Action", () -> {
        });
    }
}
