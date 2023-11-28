package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.MessageResponseDTO;
import dto.NewContactDTO;

import static com.jayway.restassured.RestAssured.given;

public class ContactsService extends BaseApi{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;

    //================================================ responseAddNewContact

    private Response getResponseAddNewContact(NewContactDTO newContactDTO, String token){
        responseAddNewContact = given()
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

    public String getIdResponseAddNewContact(NewContactDTO contactDTO, String token) {
        return getMessagePositiveResponseAddNewContact(contactDTO, token).split(":")[1].trim();
    }

    //============================================================================================

    //=========================================================== responseDeleteOneContact

    private Response getResponseDeleteOneContact(String token, String id){
        responseDeleteOneContact = given()
                //.contentType(ContentType.JSON) ( no need content according to swagger)
                .header("Authorization", token)
//                //.body(newContactDto) (no need body according to swagger)
                .when()
                  .delete(baseUrl + "/v1/contacts/" + id);
        return responseDeleteOneContact;
    }

    public void setNullResponseDeleteOneContact(){
        responseDeleteOneContact = null;
    }

    public int getStatusCodeResponseDeleteOneContact(String token, String id){
        if(responseDeleteOneContact == null){
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);
        }
        return responseDeleteOneContact.getStatusCode();
    }

    public String getMessageDeleteOneContact(String token, String id){
        if(responseDeleteOneContact == null){
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);
        }
        return responseDeleteOneContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    //===================================================================================================

    //==================================================== responseDeleteAllContacts

    private Response getResponseDeleteAllContacts(String token) {
        responseDeleteAllContacts = given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/clear");
        return responseDeleteAllContacts;
    }

    public void setResponseDeleteAllContactsNull() {
        responseDeleteAllContacts = null;
    }

    public int getStatusCodeResponseDeleteAllContacts(String token) {
        if(responseDeleteAllContacts == null) {
            responseDeleteAllContacts = getResponseDeleteAllContacts(token);
        }
        return responseDeleteAllContacts.getStatusCode();
    }

    public String getMessageResponseDeleteAllContactsPositive(String token) {
        if(responseDeleteAllContacts == null) {
            responseDeleteAllContacts = getResponseDeleteAllContacts(token);
        }
        return responseDeleteAllContacts.getBody().as(MessageResponseDTO.class).getMessage();
    }
}
