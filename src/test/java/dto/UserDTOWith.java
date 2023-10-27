package dto;

public class UserDTOWith {

    String email;
    String password;

    //with empty default constructor

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //=============================================


//    public void setName(String email) {setter
//        this.email = email;
//    }


    public UserDTOWith withEmail(String email){
        this.email = email;
        return this;
    }

    public UserDTOWith withPassword(String password){
        this.password = password;
        return this;
    }


}
