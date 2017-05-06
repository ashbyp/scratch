package com.ashbyp.scratch;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	private static Logger log = Logger.getLogger(App.class.getName());
	
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testTestProperties() {
		Properties p = new Properties();
		InputStream is = getClass().getResourceAsStream("/test.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			fail();
		}
		assertTrue(Boolean.valueOf(p.getProperty("testing")));
	}

	public void testAppProperties() {
		Properties p = new Properties();
		InputStream is = getClass().getResourceAsStream("/META-INF/scratch.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			fail();
		}
		assertEquals(p.getProperty("developer.name"), "paul.ashby");
	}
	
	public void testAllProperties() {
		Properties p = new Properties();
		InputStream is = getClass().getResourceAsStream("/META-INF/scratch.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			fail();
		}
		for (Entry<Object, Object> e : p.entrySet()) {
			log.info(e);
		}
	}
}
