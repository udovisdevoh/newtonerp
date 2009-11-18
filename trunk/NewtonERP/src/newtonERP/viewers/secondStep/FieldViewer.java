package newtonERP.viewers.secondStep;

import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldBool;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldDateTime;
import newtonERP.orm.field.FieldDouble;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldText;

/**
 * Sert à formatter de l'argent
 * @author Guillaume
 * 
 */
public class FieldViewer
{
    /**
     * @param field field a afficher
     * @return les code html
     * @throws Exception remonte
     */
    public static String getHtmlCode(Field field) throws Exception
    {
	String html;
	if (field instanceof FieldBool)
	    html = boolViewer(field);
	else if (field instanceof FieldCurrency)
	    html = currencyViewer(field);
	else if (field instanceof FieldDouble || field instanceof FieldInt)
	    html = numberViewer(field);
	else if (field instanceof FieldDateTime)
	    html = dateTimeViewer(field);
	else if (field instanceof FieldText)
	    html = textViewer(field);
	else
	    html = otherViewer(field);

	return html;

    }

    private static String currencyViewer(Field field)
    {
	String moneyFormatStyle = " style=\"width:80px;text-align:right\"";
	String textFieldType = "";

	if (field.isHidden())
	    textFieldType = "password";
	else
	    textFieldType = "text";

	if (field.isReadOnly())
	    return "<input type='hidden' name='" + field.getShortName()
		    + "' value='" + field.getDataString()
		    + "' class='textField' />" + field.getDataString();

	return "<input" + moneyFormatStyle + " type='" + textFieldType
		+ "' name='" + field.getShortName() + "' value='"
		+ field.getDataString() + "' class='textField' />";
    }

    private static String textViewer(Field field)
    {
	if (!((FieldText) field).isVeryLong())
	    return "<textarea class='textField' name='"
		    + field.getShortName()
		    + "'>"
		    + field.getDataString()
		    + "</textarea><div class=\"printableText\">"
		    + field.getDataString().replace("'", "&#39;").replace("\n",
			    "<br />") + "</div></td></tr>";

	return "<textarea class='textFieldLong' name='"
		+ field.getShortName()
		+ "'>"
		+ field.getDataString()
		+ "</textarea><div class=\"printableText\">"
		+ field.getDataString().replace("'", "&#39;").replace("\n",
			"<br />") + "</div></td></tr>";
    }

    private static String dateTimeViewer(Field field)
    {
	return otherViewer(field);
    }

    private static String numberViewer(Field field)
    {
	return otherViewer(field);
    }

    private static String boolViewer(Field field)
    {
	String html = "";
	String yesIsChecked, noIsChecked;

	if ((Boolean) field.getData())
	{
	    yesIsChecked = " checked";
	    noIsChecked = "";
	}
	else
	{
	    yesIsChecked = "";
	    noIsChecked = " checked=\"checked\"";
	}

	html += "oui: <input type=\"radio\" name=\"" + field.getShortName()
		+ "\" value=\"true\"" + yesIsChecked + " />";
	html += " | non: <input type=\"radio\" name=\"" + field.getShortName()
		+ "\" value=\"false\"" + noIsChecked + " />";

	return html;
    }

    private static String otherViewer(Field field)
    {
	String textFieldType = "";
	String isReadOnly = "";

	if (field.isHidden())
	    textFieldType = "password";
	else
	    textFieldType = "text";

	if (field.isReadOnly())
	    return "<input type='hidden' name='" + field.getShortName()
		    + "' value='" + field.getDataString()
		    + "' class='textField' />"
		    + field.getDataString().replace("'", "&#39;");

	return "<input type='" + textFieldType + "' name='"
		+ field.getShortName() + "' value='"
		+ field.getDataString().replace("'", "&#39;")
		+ "' class='textField'" + isReadOnly + " />";
    }
}