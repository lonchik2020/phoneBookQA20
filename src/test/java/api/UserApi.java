package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AuthResponseDTO;
import dto.UserDTOLombok;

import static com.jayway.restassured.RestAssured.given;

public class UserApi extends BaseApi {

    Response responseLogin = null;//to put here the response from the request
    Response responseRegistration = null;

    //============================================== responseLogin

    private Response loginRequest(UserDTOLombok user){//kherkin syntexis - private - so it will not be possible to call it
        System.out.println("-------------------------------------- loginRequest method run");
        responseLogin = given()
                //.contentType(ContentType.JSON)
                .body(user)
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

    //===============================================================================================

    //============================================responseRegistration

    private Response getRegistrationResponse(UserDTOLombok user) {
        responseRegistration = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(baseUrl + "/v1/user/registration/usernamepassword")
                .thenReturn();
        return responseRegistration;
    }

    public void responseRegistrationSetNull() {
        responseRegistration = null;
    }

    public int getStatusCodeResponseRegistration(UserDTOLombok user) {
        if(responseRegistration == null) {
            responseRegistration = getRegistrationResponse(user);
        }
        return responseRegistration.getStatusCode();
    }

    public String getTokenRegistrationResponse(UserDTOLombok user) {
        if(responseRegistration == null) {
            responseRegistration = getRegistrationResponse(user);
        }
        return responseRegistration.then().extract().path("token");//only when there is only 1 param
    }

}
