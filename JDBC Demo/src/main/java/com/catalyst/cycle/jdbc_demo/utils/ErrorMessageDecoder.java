package com.catalyst.cycle.jdbc_demo.utils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;


/**
 * To be used to change error codes to readable messages in the presentation layer. 
 * Uses the errorCodes.properties file located in src/main/resources.
 * @author egover
 *
 */
public class ErrorMessageDecoder {

    
  //TODO do not use this staticly. Load properties once.
    public static String decodeMessages(List<String> errorCodes) throws IOException{
    
      //Read in the errorCodes.properties file.
      Properties properties = PropertiesLoaderUtils.loadAllProperties("errorCodes.properties");
      StringBuilder message = new StringBuilder();
      
      for(String code : errorCodes){
          String errorMessage = properties.getProperty(code);
          if(errorMessage != null){
              message.append(errorMessage);
              message.append("<br />");
          }
      }
      
      
        return message.toString();
    }
  
}
