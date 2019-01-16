package com.boyasafe.commons.utils;

import java.io.StringWriter;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonConvert {
	 static String jsonStr;  
	     public static String returnJson(Object object) throws Exception{  
	         ObjectMapper objectMapper = new ObjectMapper();  
	         StringWriter stringWriter = new StringWriter();  
	         objectMapper.writeValue(stringWriter, object);  
	         jsonStr = stringWriter.toString();  
	         return jsonStr;  
	     }  
	     
	     public static String returnDataGridJson(Object object,String size) throws Exception{  
	         ObjectMapper objectMapper = new ObjectMapper();  
	         StringWriter stringWriter = new StringWriter();  
	         objectMapper.writeValue(stringWriter, object);
	         String midjson = stringWriter.toString();
	         StringBuffer sb = new StringBuffer("{\"total\":");
	         sb.append(size);
	         sb.append(",\"rows\":");
	         sb.append(midjson);
	         sb.append("}");
	         jsonStr = sb.toString();
	         return jsonStr;  
	     }

}
