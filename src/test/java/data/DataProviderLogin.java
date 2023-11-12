package data;

import dto.UserDTOLombok;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderLogin {
    //data for login tests
    @DataProvider
    public Iterator<Object[]> positiveDataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
            UserDTOLombok.builder()
                    .email("krasleo@gmail.com")
                    .password("Cristiano7777$!")
                    .build()
        });
        list.add(new Object[]{
            UserDTOLombok.builder()
                    .email("krasleo@gmail.com")
                    .password("Cristiano7777$!")
                    .build()
        });
        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> negativePasswordDataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDTOLombok.builder()
                        .email("krasleo@gmail.com")
                        .password("Cristiano7777hh")
                        .build()
        });
        list.add(new Object[]{
                UserDTOLombok.builder()
                        .email("krasleo@gmail.com")
                        .password("Cristiano777787")
                        .build()
        });
        return list.iterator();

    }

}
