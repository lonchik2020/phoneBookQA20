package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.MessageResponseDTO;
import dto.NewContactDTO;

public class ContactsService extends BaseApi{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;

    //================================================ responseAddNewContact

    private Response getResponseAddNewContact(NewContactDTO newContactDTO, String token){
        responseAddNewContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(newContactDTO)
                .when()
                .post(baseUrl + "/v1/contacts");
        return responseAddNewContact;
    }

    public void setResponseAddNewContactNull(){
        responseAddNewContact = null;
    }

    public int getStatusCodeResponseAddNewContact(NewContactDTO contactDTO, String token){
        if(responseAddNewContact == null){
            responseAddNewContact = getResponseAddNewContact(contactDTO,token);
        }
        return responseAddNewContact.getStatusCode();
    }

    public String getMessagePositiveResponseAddNewContact(NewContactDTO contactDTO, String token){
        if(responseAddNewContact == null){
            responseAddNewContact = getResponseAddNewContact(contactDTO,token);
        }
        return responseAddNewContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    //============================================================================================
}
