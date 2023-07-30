package com.example;

import java.io.IOException;
import java.io.InputStream;

import org.apache.xmlbeans.XmlException;

import com.example.config.parser.Class;
import com.example.config.parser.Classes;
import com.example.config.parser.ConfigDocument;
import com.example.config.parser.ConfigDocument.Config;
import com.example.config.parser.Ignore;
import com.example.config.parser.Map;

public class Test
{

	public static void main(String[] args) throws XmlException, IOException
	{
		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("pois.xml");
		ConfigDocument document = ConfigDocument.Factory.parse(input);

		Config config = document.getConfig();
		Map[] maps = config.getMapArray();
		Ignore[] ignores = config.getIgnoreArray();

		for (Map map : maps) {
			System.out.println("map " + map.getKey());
			Classes[] listOfClasses = map.getClassesArray();
			for (Classes classes : listOfClasses) {
				System.out.println("  " + classes.getValues());
			}
			Class[] listOfClass = map.getClass1Array();
			for (Class clazz : listOfClass) {
				System.out.println("  " + clazz.getValue());
			}
		}

		for (Ignore ignore : ignores) {
			String key = ignore.getKey();
			String value = ignore.getValue();
			String pattern = ignore.getPattern();
			if (value != null) {
				System.out.println("ignore key: " + key);
			} else if (pattern != null) {
				System.out.println("ignore pattern: " + pattern);
			}
		}
	}

}
