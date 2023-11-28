package tests.restassured;

import org.testng.annotations.Test;

public class DeleteContactTests extends BaseRA {

    @Test
    public void deleteContactTest(){
        contactsService.setResponseAddNewContactNull();
        String id  = contactsService.getIdResponseAddNewContact(createNewContact(), token);
        contactsService.setResponseAddNewContactNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteOneContact(token,id), 200);
        System.out.println(contactsService.getMessageDeleteOneContact(token, id));
        //a message from the console here after the test succeed-->
        softAssert.assertEquals(contactsService.getMessageDeleteOneContact(token, id),"Contact was deleted!");
        contactsService.setNullResponseDeleteOneContact();
        softAssert.assertAll();//always to put at the end of soft assert -
        //takes all the previous soft asserts results and if all of the passed we will get v

    }

    @Test
    public void deleteAllContactsWhenSMTHExist() {
        contactsService.getIdResponseAddNewContact(createNewContact(), token);
        contactsService.setResponseDeleteAllContactsNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);
        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token),
                "All contacts was deleted!");
        contactsService.setResponseDeleteAllContactsNull();
        softAssert.assertAll();
    }

    @Test
    public void deleteAllContactsWhenNoContacts() {
        contactsService.getStatusCodeResponseDeleteAllContacts(token);
        contactsService.setResponseDeleteAllContactsNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);
        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token),
                "All contacts was deleted!");
        softAssert.assertAll();
    }


}
