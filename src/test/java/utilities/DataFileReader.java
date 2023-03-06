package utilities;

import java.io.*;
import java.util.Properties;

public class DataFileReader {

    public static Properties properties;
    public static final String propertyFilePath= System.getProperty("user.dir") + "/src/test/resources/com/data.properties";

    public DataFileReader(){
        BufferedReader reader;
                try {
                    //InputStream input = null;
                    //load in the properties file from src/test/resources

                    //input = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFilePath);
                    //input = DataFileReader.class.getResourceAsStream("com/dataProd.properties");
                    InputStream fis = DataFileReader.class.getResourceAsStream("/com/data.properties");
                    properties = new Properties();
                    // load a properties file
                    try {
                        properties.load(fis);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
//                    reader = new BufferedReader(new FileReader(propertyFilePath));
//                    properties = new Properties();
//                    try {
//                        properties.load(reader);
//                        reader.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }catch (Exception e){
                //}catch (FileNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException("data.properties not found at " + propertyFilePath);
                }
    }

    public static String getData(String strData){
        String dataValue = properties.getProperty(strData);
        //System.out.println(dataValue);
        if(dataValue!= null) return dataValue;
        else throw new RuntimeException("dataValue not specified in the data.properties file.");
    }



}
