package dto;




public class AllContactsDTO {
    NewContactDTO[] contacts;//variable name must be the same as in api request response body

    public AllContactsDTO() {
    }

    public AllContactsDTO(NewContactDTO[] contacts) {
        this.contacts = contacts;
    }

    public NewContactDTO[] getContacts() {
        return contacts;
    }

    public void setContacts(NewContactDTO[] contacts) {
        this.contacts = contacts;
    }
}


