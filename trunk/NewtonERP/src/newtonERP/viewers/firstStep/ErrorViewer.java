package newtonERP.viewers.firstStep;

import newtonERP.viewers.Viewer;

/**
 * Represents a errorPage for our ERP
 * @author Pascal Lemay
 */

public abstract class ErrorViewer
{
    /**
     * Gets the html code for the errorPage
     * 
     * @param errorMessage stacktrace de l'exception provenant du servlet
     * @return html
     */
    public static String getErrorPage(String errorMessage)
    {

	String errorPage = "";
	errorPage += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" >"
		+ "<head><title>ERROR</title>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
		+ Viewer.getEncoding()
		+ "\" /></head>"
		+ "<body><h1>Error</h1><p><pre>"
		+ errorMessage
		+ "</pre></p></body></html>";

	return errorPage;

    }
}
