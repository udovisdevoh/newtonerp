package newtonERP.viewers;

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
	String html = "<div id=\"home\"><!-- rien --></div>";
	return html;
    }

}
