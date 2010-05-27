package newtonERP.serveur;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Sert à gérer la configuration dans un fichier XML
 * @author Guillaume Lacasse
 */
public class ConfigManager
{
    private static final String configFile = "config.xml";

    private static int port = -1;

    private static String styleFileScreen = null;

    private static String styleFilePrint = null;

    private static String displayName = null;

    private static String defaultUserName = null;

    private static String defaultPassWord = null;

    private static String defaultModuleName = null;

    private static String defaultActionName = null;

    private static String dbmsName = null;

    private static File file = null;

    private static Document document = null;

    private static Boolean isDisplayHeader = null;

    /**
     * @return le port (possiblement 80 ou autre) pour le portail web de
     *         l'application
     * @throws Exception si ça fail
     */
    public static int getPort() throws Exception
    {
	if (port == -1)
	    port = loadPort();

	return port;
    }

    /**
     * @return fichier css affiché à l'écran
     * @throws Exception si ça fail
     */
    public static String getStyleFileScreen() throws Exception
    {
	if (styleFileScreen == null)
	    styleFileScreen = loadStyleFileScreen();
	return styleFileScreen;
    }

    private static String loadStyleFileScreen() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("style-screen");
	return nodeList.item(0).getFirstChild().getNodeValue();
    }

    /**
     * @return fichier css pour impression
     * @throws Exception si ça fail
     */
    public static String getStyleFilePrint() throws Exception
    {
	if (styleFilePrint == null)
	    styleFilePrint = loadStyleFilePrint();
	return styleFilePrint;
    }

    private static String loadStyleFilePrint() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("style-print");
	return nodeList.item(0).getFirstChild().getNodeValue();
    }

    /**
     * @return nom affiché publiquement pour l'application
     * @throws Exception si ça fail
     */
    public static String getDisplayName() throws Exception
    {
	if (displayName == null)
	    displayName = loadDisplayName();
	return displayName;
    }

    private static String loadDisplayName() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("display-name");
	return nodeList.item(0).getFirstChild().getNodeValue();
    }

    private static String loadDefaultModuleName() throws Exception
    {
	NodeList nodeList = getDocument()
		.getElementsByTagName("default-module");
	return nodeList.item(0).getFirstChild().getNodeValue();
    }

    private static String loadDefaultActionName() throws Exception
    {
	NodeList nodeList = getDocument()
		.getElementsByTagName("default-action");
	return nodeList.item(0).getFirstChild().getNodeValue();
    }

    /**
     * @return mot de passe créé par default lors de déploiement
     * @throws Exception si ça fail
     */
    public static String getDefaultPassWord() throws Exception
    {
	if (defaultUserName == null)
	    defaultUserName = loadDefaultPassWord();
	return defaultUserName;
    }

    private static String loadDefaultUserName() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("default-user");
	Node node = nodeList.item(0);
	NamedNodeMap attributeList = node.getAttributes();
	return attributeList.getNamedItem("name").getFirstChild()
		.getNodeValue();
    }

    /**
     * @return utilisateur par default créé lors de déploiement
     * @throws Exception si ça fail
     */
    public static String getDefaultUserName() throws Exception
    {
	if (defaultPassWord == null)
	    defaultPassWord = loadDefaultUserName();
	return defaultPassWord;
    }

    private static String loadDefaultPassWord() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("default-user");
	Node node = nodeList.item(0);
	NamedNodeMap attributeList = node.getAttributes();
	return attributeList.getNamedItem("password").getFirstChild()
		.getNodeValue();
    }

    private static Boolean loadIsDisplayTitle() throws Exception
    {
	NodeList nodeList = getDocument()
		.getElementsByTagName("show-top-title");
	return Boolean.parseBoolean(nodeList.item(0).getFirstChild()
		.getNodeValue());
    }

    private static int loadPort() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("port");
	Node node = nodeList.item(0);
	String stringPort = node.getFirstChild().getNodeValue();
	return Integer.parseInt(stringPort);
    }

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
     * @return Nom du sgbd
     * @throws Exception si ça fail
     */
    public static String getDbmsName() throws Exception
    {
	if (dbmsName == null)
	    dbmsName = loadDbmsName();
	return dbmsName;
    }

    private static String loadDbmsName() throws Exception
    {
	NodeList nodeList = getDocument().getElementsByTagName("dmbs-name");
	Node node = nodeList.item(0);
	return node.getFirstChild().getNodeValue();
    }

    /**
     * @return nom du module par default
     * @throws Exception si ça fail
     */
    public static String getDefaultModuleName() throws Exception
    {
	if (defaultModuleName == null)
	    defaultModuleName = loadDefaultModuleName();
	return defaultModuleName;
    }

    /**
     * @return nom de l'action par default
     * @throws Exception si ça fail
     */
    public static String getDefaultActionName() throws Exception
    {
	if (defaultActionName == null)
	    defaultActionName = loadDefaultActionName();
	return defaultActionName;
    }

    /**
     * @return si on veut afficher le header du programme
     * @throws Exception is ça fail
     */
    public static boolean isDisplayTopTitle() throws Exception
    {
	if (isDisplayHeader == null)
	    isDisplayHeader = loadIsDisplayTitle();

	return isDisplayHeader;
    }
}
