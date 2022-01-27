package com.demowebshop;


import lombok.WishListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static specification.Specs.request;
import static specification.Specs.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.Boolean.FALSE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WishListTests extends TestBase {

    @Test
    @DisplayName("Can add item to Wishlist")
    void canAddItemToWishList() {
        step("try add Virtual Gift Card wo names", () -> {
            WishListResponse wishListResponse =

                    given()
                            .spec(request)
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
    @DisplayName("Can add Virtual Gift Card to wishist")
    void canAddGiftCardToWishList() {
        step("try add with required fields", () ->
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("giftcard_2.RecipientName=Test&giftcard_2.RecipientEmail=test@test.ru" +
                                "&giftcard_2.SenderName=Test2&giftcard_2.SenderEmail=test2@test.ru&" +
                                "giftcard_2.Message=TestMessage&addtocart_2.EnteredQuantity=1")
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
