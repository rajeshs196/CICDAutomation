package rajeshsalimath.data;

//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
//	public void getJsonDataToMap() throws IOException 
//	{
//		//json to string
//		String jsonContent = FileUtils.readFileToString(
//				new File(System.getProperty("user.dir")+"\\src\\test\\java\\rajeshsalimath\\data\\PurchaseOrderData.json"), StandardCharsets.UTF_8);
//		
//		//String to List of Hashmap - using Jackson Binding (add dependency in pom.xml)
//		ObjectMapper objectMap = new ObjectMapper();
//		 List<HashMap<String, String>> data = objectMap.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
//		});
//	}
}


//Jackson-databind is a popular Java library providing high-performance data-binding functionality, 
//primarily for converting Java objects (POJOs) to and from JSON format, 
//and vice-versa, using the ObjectMapper class for seamless serialization and deserialization