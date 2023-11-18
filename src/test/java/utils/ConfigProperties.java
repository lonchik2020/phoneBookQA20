package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigProperties {//all the methods will be static
    static Properties properties = new Properties();

    public static String getProperty(String param){
        //will return value of email and will receive param of email ( for example)
        if(properties.toString().equals("{}")){//SINGLETON - PATTERN
            initProperty();
        }
        return  properties.getProperty(param);
    }

    private static void initProperty(){
        String target = System.getProperty("target", "prod");
        //instead prod--> %s + to write the name of the variable
        String path  = String.format("src/test/resources/%s.properties", target);
        try(FileReader fileReader = new FileReader(path)){
            properties.load(fileReader);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
