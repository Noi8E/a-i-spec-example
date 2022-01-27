package com.demowebshop;


import lombok.WishListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.Boolean.FALSE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static specification.Specs.cartRequest;
import static specification.Specs.responseSpec;

public class WishListTests extends TestBase {

    @Test
    @DisplayName("Нельзя добавить товар в Вишлист без имени реципиента")
    void canAddItemToWishList() {
        step("Отправить запрос на добавление товара в вишлист без имени реципиента", () -> {
            WishListResponse wishListResponse =

                    given()
                            .spec(cartRequest)
                            .when()
                            .post("/addproducttocart/details/2/2")
                            .then()
                            .spec(responseSpec)
                            .log().body()
                            .extract().as(WishListResponse.class);
            assertThat(wishListResponse.getSuccess(), is(FALSE));
            assertThat(wishListResponse.getMessage().get(1), is("Enter valid recipient email"));
            assertThat(wishListResponse.getMessage().get(0), is("Enter valid recipient name"));
        });
    }

    @Test
    @DisplayName("Товар можно добавить в ВишЛист если все параметры указаны верно")
    void canAddGiftCardToWishList() {
        step("Отправить валидный запрос", () ->
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body(wishListBody)
                        .log().all()
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/2/2")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
        );
    }
}
