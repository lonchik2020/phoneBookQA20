package tests.okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.UserDTOLombok;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHTTP {
    UserDTOLombok user = UserDTOLombok.builder()
            .username("krasleo@gmail.com")
            .password("Cristiano7777$!")
            .build();

    public static final MediaType JSON = MediaType.get("application/json");
    //means that requests response body will be in json format
    Gson gson = new Gson();
    //example of gson - library from google that allows us serialization/deserialization our json in java object dto
    //that we created
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @Test
    public void loginPositive() {
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        //body for request
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();
        //object of request
        Response response;
        //variable response for the response we will receive
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response == null) {
            Assert.fail("got null response");
        } else if (response.isSuccessful()) {//return ok 200 and token
            String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {//body as a string
                throw new RuntimeException(e);
            }
            AuthResponseDTO authResponseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);//to return string to object
            System.out.println(authResponseDTO.getToken());
            System.out.println(response.code());
            System.out.println(response.message());
            Assert.assertEquals(response.code(), 200, "response not 200");
        } else {
            System.out.println(response.code() + "error");
            Assert.fail("response not successful");
        }
    }


    @Test
    public void loginNegative() {
        UserDTOLombok userNegative = UserDTOLombok.builder()
                .username("krasleo@gmail.com")
                .password("Cristiano777777")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(userNegative), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response == null) {
            Assert.fail("got null response");
        } else if (response.isSuccessful()) {//return ok 200 and token
            Assert.fail("got response with status code" + response.code());
            String responseJson;
        } else {
            String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {//body as a string
                throw new RuntimeException(e);
            }

            ErrorDTO errorDTO = gson.fromJson(responseJson, ErrorDTO.class);
            System.out.println(response.code());
            System.out.println(response.message());
            System.out.println("string error" + errorDTO.getError());
            System.out.println("int status" + errorDTO.getStatus());
            Assert.assertEquals(response.code(), 401, "response not 401");

        }
    }
}
