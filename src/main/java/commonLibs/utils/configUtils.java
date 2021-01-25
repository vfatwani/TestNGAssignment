package commonLibs.utils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class configUtils {

    public static Properties readProperty(String fileName) throws  Exception{
        fileName=fileName.trim();
        InputStream fileInput = new FileInputStream(fileName);
        Properties property = new Properties();
        property.load(fileInput);
        return property;
    }

}
