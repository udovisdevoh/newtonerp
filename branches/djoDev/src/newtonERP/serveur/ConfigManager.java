package newtonERP.serveur;

import java.io.File;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * manage de config of the software from an XML file
 * 
 * @author Guillaume Lacasse JoCloutier
 */
public class ConfigManager
{
	private static File file = null;
	private static Document document = null;
	private static final String configFile = "config.xml";

	private static Hashtable<String, String> propertyValue = new Hashtable<String, String>();

	private static Document getDocument() throws Exception
	{
		if (document == null)
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(getFile());
			document.getDocumentElement().normalize();
		}
		return document;
	}

	private static File getFile()
	{
		if (file == null)
			file = new File(configFile);
		return file;
	}

	/**
	 * @param propertyName the property name
	 * @param attributName the attribut name
	 * @param defaultValue a default value in case the property is'nt present in
	 *            the config file
	 * @return String property value
	 */
	public static String loadStringProperty(String propertyName,
			String attributName, String defaultValue)
	{
		if (propertyName.contains("#")
				|| (attributName != null && attributName.contains("#")))
			throw new RuntimeException(
					"name of config property or config attribut cannot containe '#' char");
		if (propertyValue.containsKey(propertyName + "#" + attributName))
			return propertyValue.get(propertyName + "#" + attributName);

		try
		{
			String val;
			NodeList nodeList = getDocument()
					.getElementsByTagName(propertyName);
			Node node = nodeList.item(0);
			if (attributName != null)
			{
				NamedNodeMap attributeList = node.getAttributes();
				val = attributeList.getNamedItem(attributName).getFirstChild()
						.getNodeValue();
			}
			else
			{
				val = node.getFirstChild().getNodeValue();
			}

			propertyValue.put(propertyName + "#" + attributName, val);
			return val;
		} catch (Exception e)
		{
			if (defaultValue == null)
				throw new RuntimeException(
						"the property "
								+ propertyName
								+ " is needed in the config file, there is no default value possible");

			propertyValue.put(propertyName + "#" + attributName, defaultValue);
			return defaultValue;
		}
	}

	/**
	 * @param propertyName the propetry name
	 * @param defaultValue a default value in case the property is'nt present in
	 *            the config file
	 * @return String property value
	 */
	public static String loadStringProperty(String propertyName,
			String defaultValue)
	{
		return loadStringProperty(propertyName, null, defaultValue);
	}

	/**
	 * @param propertyName the propetry name
	 * @param attributName the attribut name
	 * @return String property value
	 */
	public static String loadStringAttrProperty(String propertyName,
			String attributName)
	{
		return loadStringProperty(propertyName, attributName, null);
	}

	/**
	 * @param propertyName the propetry name
	 * @return String property value
	 */
	public static String loadStringProperty(String propertyName)
	{
		return loadStringProperty(propertyName, null);
	}

	/**
	 * @param propertyName the propetry name
	 * @param attributName the attribut name
	 * @return int property value
	 * @param defaultValue a default value in case the property is'nt present in
	 *            the config file
	 */
	public static int loadIntProperty(String propertyName, String attributName,
			Integer defaultValue)
	{
		return Integer.parseInt(loadStringProperty(propertyName, attributName,
				defaultValue + ""));
	}

	/**
	 * @param propertyName the propetry name
	 * @return int property value
	 * @param defaultValue a default value in case the property is'nt present in
	 *            the config file
	 */
	public static int loadIntProperty(String propertyName, Integer defaultValue)

	{
		return loadIntProperty(propertyName, null, defaultValue);
	}

	/**
	 * @param propertyName the propetry name
	 * @param attributName the attribut name
	 * @return int property value
	 */
	public static int loadIntAttrProperty(String propertyName,
			String attributName)
	{
		return loadIntProperty(propertyName, attributName, null);
	}

	/**
	 * @param propertyName the propetry name
	 * @return int property value
	 */
	public static int loadIntProperty(String propertyName)
	{
		return loadIntProperty(propertyName, null);
	}

	/**
	 * @param propertyName the propetry name
	 * @param attributName the attribut name
	 * @param defaultValue a default value in case the property is'nt present in
	 *            the config file
	 * @return boolean property value
	 */
	public static boolean loadBoolProperty(String propertyName,
			String attributName, Boolean defaultValue)
	{
		return Boolean.parseBoolean(loadStringProperty(propertyName,
				attributName, defaultValue + ""));
	}

	/**
	 * @param propertyName the propetry name
	 * @param defaultValue a default value in case the property is'nt present in
	 *            the config file
	 * @return boolean property value
	 */
	public static boolean loadBoolProperty(String propertyName,
			Boolean defaultValue)
	{
		return loadBoolProperty(propertyName, null, defaultValue);
	}

	/**
	 * @param propertyName the propetry name
	 * @param attributName the attribut name
	 * @return boolean property value
	 */
	public static boolean loadBoolAttrProperty(String propertyName,
			String attributName)
	{
		return loadBoolProperty(propertyName, attributName, null);
	}

	/**
	 * @param propertyName the propetry name
	 * @return boolean property value
	 */
	public static boolean loadBoolProperty(String propertyName)

	{
		return loadBoolProperty(propertyName, null);
	}
}