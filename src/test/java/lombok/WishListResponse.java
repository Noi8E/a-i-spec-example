package lombok;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class WishListResponse {
    private Boolean success;
    @Singular
    private List<String> message;

}
