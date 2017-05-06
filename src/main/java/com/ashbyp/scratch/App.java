package com.ashbyp.scratch;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

public class App {
	private static Logger log = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		InputStream is = App.class.getResourceAsStream( "/META-INF/scratch.properties" );
    	try {
    		props.load(is);
		} catch (IOException e) {
		}
    	for (Entry<Object, Object> e : props.entrySet()) {
    		log.info(e);
    	}
    	Reader r = new InputStreamReader(is);
    	int c;
    	while (( c = r.read()) != -1) {
    		System.out.print(c);
    	}
    	System.out.println();
	}
}
