package data;

import dto.UserDTOLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
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

    @DataProvider
    public Iterator<Object[]>loginCSV(){
        List<Object[]> list = new ArrayList<>();
        String path = "src/test/resources/datalogin.csv";
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))){
            String line = reader.readLine();
            while( line !=null){
                String[]split = line.split(",");
                list.add(new Object[]{
                        UserDTOLombok.builder()
                                .email(split[0])
                                .password(split[1])
                                .build()
                });
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return list.iterator();
    }

}
