package tests.okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.UserDTOLombok;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.io.IOException;

public class RegTestOkHTTP {
    RandomUtils randomUtils = new RandomUtils();
    String email = randomUtils.generateEmail(7);
    UserDTOLombok user = UserDTOLombok.builder()
            .username(email)
            .password("Cristiano7777$!")
            .build();

    public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";


    @Test
    public void regPositive() {
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        //body for request - gson for google - we transfer it to JSON
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        //object of request
        Response response;
        //variable response for the response we will receive
        try {
            response = okHttpClient.newCall(request).execute();// to use okhttp client to make a call+ make request and get a response
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
    public void regNegativeError400() {
        UserDTOLombok userNegative1 = UserDTOLombok.builder()
                .username(email)
                .password("")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(userNegative1), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
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
            //String responseJson;
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
            Assert.assertEquals(response.code(), 400, "response not 400");

        }
    }

}
