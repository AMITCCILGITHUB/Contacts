package org.apandey.props;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {
	private static Properties prop;
	static {
		prop = new Properties();
		File fl = new File("resources/app.properties");
		try {
			prop.load(new FileInputStream(fl));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getStringValue(String attr) {
		return prop.getProperty(attr);
	}

	public static boolean getBooleanValue(String attr) {
		return Boolean.parseBoolean(prop.getProperty(attr));
	}

	public static int getIntegerValue(String attr) {
		return Integer.parseInt(prop.getProperty(attr));
	}

	public static double getDoubleValue(String attr) {
		return Double.parseDouble(prop.getProperty(attr));
	}
}
