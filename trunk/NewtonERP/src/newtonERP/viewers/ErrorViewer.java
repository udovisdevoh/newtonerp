package newtonERP.viewers;

/**
 * @author Pascal Lemay
 * 
 *         Represents a errorPage for our ERP
 */

public abstract class ErrorViewer
{
    public static String getErrorPage(String errorMessage)
    {

	String errorPage = "";
	errorPage += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" >"
		+ "<head><title>ERROR"
		+ "</title>"
		+ "<h1>Error</h1>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />";
	errorPage += "<p><pre>" + errorMessage + "</pre></p>";

	return errorPage;

    }

}
