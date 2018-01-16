package de.georghenkel.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class PropertyReaderTest
{
	@Test
	// FIXME: This Testcase fails, because Properties cannot read in keys with spaces!
	public void shouldReadProperties()
	{
		Properties prop = new Properties();

		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("data.txt"))
		{
			prop.load(input);

			Assert.assertEquals("TestJob", prop.getProperty("Jobname"));
			Assert.assertEquals("10 Hz", prop.getProperty("Langsame Messrate"));
		}
		catch (IOException ex)
		{
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void shouldReadPropertiesWithSpace()
	{
		Properties prop = new Properties();

		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("data.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)))
		{
			String readLine = "";
			while ((readLine = reader.readLine()) != null)
			{
				String[] keyValue = readLine.split("=");
				prop.put(keyValue[0], keyValue[1]);
			}

			Assert.assertEquals("TestJob", prop.getProperty("Jobname"));
			Assert.assertEquals("10 Hz", prop.getProperty("Langsame Messrate"));
		}
		catch (IOException ex)
		{
			Assert.fail(ex.getMessage());
		}
	}
}
