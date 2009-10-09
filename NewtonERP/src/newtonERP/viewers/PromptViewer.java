package newtonERP.viewers;

import newtonERP.viewers.viewables.PromptViewable;

public class PromptViewer
{
    public static String getHtmlCode(PromptViewable entity)
	    throws ViewerException, Exception // fix this
    {
	System.out.println("getHtmlCode()");
	String html = "";
	try
	{
	    String moduleName = entity.getSubmitModule().getClass()
		    .getSimpleName();
	    String actionName = entity.getSubmitAction().getClass()
		    .getSimpleName();
	    String formActionUrl = "/" + moduleName + "/" + actionName
		    + "?user=" + entity.getCurrentUserName();

	    html += "<h1>" + entity.getPromptMessage() + "</h1>";

	    html += "<form method=\"GET\" action=\"" + formActionUrl + "\">";

	    html += "<table>";

	    String inputValue;
	    for (String inputName : entity.getInputList().keySet())
	    {
		inputValue = entity.getInputList().get(inputName);

		html += "<tr><td>" + inputName
			+ "</td><td><input type=\"text\" name=\"" + inputName
			+ "\" value=\"" + inputValue + "\"></td></tr>";
	    }

	    html += "<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\""
		    + entity.getButtonCaption() + "\"></td></tr>";

	    html += "<table>";

	    html += "</form>";
	} catch (Exception e)
	{
	    throw e;
	}

	return html;
    }
}
