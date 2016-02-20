package in.co.thingsdata.lms.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
	
	private final static Map<String,String> props = new HashMap<>();
	private final static PropertiesReader _INSTANCE = new PropertiesReader();
	
	private static void loadProperties(Map<String, String> props, String confPath) {
		
		Properties prop;
		
		InputStream input;
		
		try {
			
			prop = new Properties();
			
			input = new FileInputStream(confPath + "config.properties");
			//input = PropertiesReader.class.getClassLoader().getResourceAsStream(Business.getInstance().getConfigPath()+"chart.properties");
			prop.load(input);
			
			populateMap(prop, props);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void populateMap(Properties prop, Map<String, String> props2) {

		Iterator<?> itr = prop.keySet().iterator();
		
		String key;
		
		while (itr.hasNext()) {
			
			key = String.valueOf(itr.next());
			
			props.put(key, prop.getProperty(key));
			
		}
	}
	
	public String getProperty (String key) {
		
		if (props.containsKey(key)) {
			
			return props.get(key);
			
		}
		
		return null;
		
	}
	
	public void init(String confPath) {
		loadProperties(props, confPath);
	}

	public static PropertiesReader getInstance() {
		
		return _INSTANCE;
		
	}

}
