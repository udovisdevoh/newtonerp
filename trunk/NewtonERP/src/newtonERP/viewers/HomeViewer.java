package newtonERP.viewers;

import newtonERP.Authentication;

/**
 * @author Pascal Lemay
 * 
 *         Represents HomeViewer for our ERP (Display the Home Page)
 */
public abstract class HomeViewer
{
    /**
     * Gets the html code for the HomePage
     * 
     * @return html
     */
    public static String getHomePage()
    {
	String html = "<h2>Bienvenue " + Authentication.getCurrentUserName()
		+ "</h2>";
	return html;
    }

}
