package com.example;

import java.io.IOException;
import java.io.InputStream;

import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;

import com.example.config.parser.ConfigDocument;
import com.example.config.parser.ConfigDocument.Config;
import com.example.config.parser.Ignore;
import com.example.config.parser.Map;

public class UnitTest
{

	@Test
	public void test() throws XmlException, IOException
	{
		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("pois.xml");
		ConfigDocument document = ConfigDocument.Factory.parse(input);

		Config config = document.getConfig();
		Map[] maps = config.getMapArray();
		Ignore[] ignores = config.getIgnoreArray();

		Assert.assertEquals(4, maps.length);
		Assert.assertEquals(1, ignores.length);

		int c = 0;
		Assert.assertEquals("place", maps[c++].getKey());
		Assert.assertEquals("railway", maps[c++].getKey());
		Assert.assertEquals("leisure", maps[c++].getKey());
		Assert.assertEquals("natural", maps[c++].getKey());

		c = 0;
		Assert.assertEquals("[0-9]+[a-zA-Z]?", ignores[c++].getPattern());
	}

}
