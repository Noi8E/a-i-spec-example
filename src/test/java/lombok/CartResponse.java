package lombok;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartResponse {

    private Boolean success;
    private String message;
    private String updatetopcartsectionhtml;
    private String updateflyoutcartsectionhtml;


}
