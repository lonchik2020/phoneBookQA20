package api;

import com.jayway.restassured.response.Response;
import dto.AuthResponseDTO;
import dto.UserDTOLombok;

import static com.jayway.restassured.RestAssured.given;

public class UserApi extends BaseApi {

    Response responseLogin = null;//to put here the response from the request

    private Response loginRequest(UserDTOLombok user){//kherkin syntexis - private - so it will not be possible to call it
        responseLogin = given().body(user)
                .when()
                .post(baseUrl + "/v1/user/login/usernamepassword")
                .thenReturn();
        return responseLogin;

    }

    public void setResponseLoginNull(){//to reset response before sending a new request
        responseLogin = null;
    }

    public int getStatusCodeResponseLogin(UserDTOLombok user){
        if(responseLogin == null){
           responseLogin = loginRequest(user);
        }
        return responseLogin.getStatusCode();
    }

    public String getTokenFromLoginResponse(UserDTOLombok user){
        if(responseLogin == null){
            responseLogin = loginRequest(user);
        }
        return responseLogin.getBody().as(AuthResponseDTO.class).getToken();
    }
}
