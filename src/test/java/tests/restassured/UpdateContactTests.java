package tests.restassured;

import dto.NewContactDTO;
import org.testng.annotations.Test;

public class UpdateContactTests extends BaseRA{
    @Test
    public void testUpdateContact() {
        NewContactDTO contact = createNewContact();
        String id = contactsService.getIdResponseAddNewContact(contact, token);
        contact.setId(id);
        contact.setPhone(randomUtils.generateStringDigits(12));
        System.out.println(contactsService.getMessageSuccessUpdateContact(contact, token)); // Contact was updated
        softAssert.assertTrue(contactsService.getMessageSuccessUpdateContact(contact, token).equals("Contact was updated"));
        softAssert.assertEquals(contactsService.getStatusCodeResponseUpdateContact(contact, token), 200);
        softAssert.assertAll();
    }
}
