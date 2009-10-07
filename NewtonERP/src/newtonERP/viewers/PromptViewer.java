package newtonERP.viewers;

import newtonERP.viewers.viewables.PromptViewable;

public class PromptViewer
{
    public static String getHtmlCode(PromptViewable entity)
	    throws ViewerException
    {
	String moduleName = entity.getSubmitModule().getClass().getSimpleName();
	String actionName = entity.getSubmitAction().getClass().getSimpleName();
	String formActionUrl = "/" + moduleName + "/" + actionName + "?user=";

	String html = "";
	html += "<h1>" + entity.getPromptMessage() + "</h1>";

	// /moduleName/action?=userName=...;

	html += "<form method=\"GET\" action=\"" + formActionUrl + "\">";

	String inputValue;
	for (String inputName : entity.getInputList().keySet())
	{
	    inputValue = entity.getInputList().get(inputName);

	    html += "<input type=\"text\" name=\"" + inputName + "\" value=\""
		    + inputValue + "\"><br>";
	}

	html += "<input type=\"submit\" value=\"" + entity.getButtonCaption()
		+ "\">";
	html += "</form>";

	return html;
    }
}
