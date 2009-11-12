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
	else if (field instanceof FieldDouble || field instanceof FieldInt)
	    html = numberViewer(field);
	else if (field instanceof FieldDateTime)
	    html = dateTimeViewer(field);
	else if (field instanceof FieldText)
	    html = textViewer(field);
	if (field instanceof FieldCurrency)
	    html = currencyViewer(field);

	else
	    html = otherViewer(field);

	return html;

    }

    private static String currencyViewer(Field field)
    {
	String moneyFormatStyle = " style=\"width:80px;text-align:right\"";
	String textFieldType = "";
	String isReadOnly = "";

	if (field.isHidden())
	    textFieldType = "password";
	else
	    textFieldType = "text";

	if (field.isReadOnly())
	    isReadOnly = "DISABLED";

	return "<input" + moneyFormatStyle + " type='" + textFieldType
		+ "' name='" + field.getShortName() + "' value='"
		+ field.getDataString() + "' class='textField'" + isReadOnly
		+ "> $";
    }

    private static String textViewer(Field field)
    {
	return "<textarea class='textField' name='" + field.getShortName()
		+ "'>" + field.getDataString() + "</textarea></td></tr>";
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
	    noIsChecked = " checked";
	}

	html += "oui: <input type=\"radio\" name=\"" + field.getShortName()
		+ "\" value=\"true\"" + yesIsChecked + ">";
	html += " | non: <input type=\"radio\" name=\"" + field.getShortName()
		+ "\" value=\"false\"" + noIsChecked + ">";

	html += "</td></tr>";

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
	    isReadOnly = "DISABLED";

	return "<input type='" + textFieldType + "' name='"
		+ field.getShortName() + "' value='" + field.getDataString()
		+ "' class='textField'" + isReadOnly + ">";
    }
}