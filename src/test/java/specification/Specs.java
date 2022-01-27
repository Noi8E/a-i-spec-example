package specification;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;

public class Specs {

    public static RequestSpecification cartRequest = with()
            .baseUri("http://demowebshop.tricentis.com")
            .filter(customLogFilter().withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .contentType(ContentType.JSON)
            .log().all();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static RequestSpecification cartRequestWithCookie = with()
            .baseUri("http://demowebshop.tricentis.com")
            .filter(customLogFilter().withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .contentType(ContentType.JSON)
            .cookie("a578b8a0-72b1-4a15-91e8-65255bc54583")
            .log().all();

}
