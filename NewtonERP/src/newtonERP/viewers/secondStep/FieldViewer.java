package newtonERP.viewers.secondStep; 
 // TODO: clean up that file

import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldBool;
import newtonERP.orm.fields.field.type.FieldCurrency;
import newtonERP.orm.fields.field.type.FieldDateTime;
import newtonERP.orm.fields.field.type.FieldDouble;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldText;

/**
 * Sert à formatter de l'argent
 * 
 * @author Guillaume
 */
public class FieldViewer {
	/**
	 * @param field field a afficher
	 * @return les code html
	 */
	public static String getHtmlCode(Field<?> field) {
		String html;
		if(field instanceof FieldBool){
			html = boolViewer(field);
		}else if(field instanceof FieldCurrency){
			html = currencyViewer(field);
		}else if(field instanceof FieldDouble || field instanceof FieldInt){
			html = numberViewer(field);
		}else if(field instanceof FieldDateTime){
			html = dateTimeViewer(field);
		}else if(field instanceof FieldText){
			html = textViewer(field);
		}else{
			html = otherViewer(field);
		}

		return html;

	}

	private static String currencyViewer(Field<?> field) {
		String moneyFormatStyle = " style=\"width:80px;text-align:right\"";
		String textFieldType = "";

		if(field.isHidden()){
			textFieldType = "password";
		}else{
			textFieldType = "text";
		}

		if(field.isReadOnly()){
			return "<input type='hidden' name='" + field.getShortName() + "' value='" + field.getDataString()
			        + "' class='textField' />" + field.getDataString();
		}

		return "<input" + moneyFormatStyle + " type='" + textFieldType + "' name='" + field.getShortName()
		        + "' value='" + field.getDataString() + "' class='textField' />";
	}

	private static String textViewer(Field<?> field) {
		String longText = "";
		if(!((FieldText) field).isVeryLong()){
			longText = "textField";
		}else{
			longText = "textFieldLong";
		}

		return "<textarea class='" + longText + "' name='" + field.getShortName() + "'>" + field.getDataString()
		        + "</textarea><div class=\"printableText\">" + field.getDataString().replace("\n", "<br />")
		        + "</div></td></tr>";
	}

	private static String dateTimeViewer(Field<?> field) {
		return otherViewer(field);
	}

	private static String numberViewer(Field<?> field) {
		return otherViewer(field);
	}

	private static String boolViewer(Field<?> field) {
		String html = "";
		String yesIsChecked, noIsChecked;

		if(field.getData() != null && (Boolean) field.getData()){
			yesIsChecked = " checked";
			noIsChecked = "";
		}else{
			yesIsChecked = "";
			noIsChecked = " checked=\"checked\"";
		}

		if(field.isHidden()){
			html += "<input type=\"hidden\" name=\"" + field.getShortName() + "\" value=\"" + field.getData() + "\">";
		}else if(field.isReadOnly()){
			html += "<input type=\"hidden\" name=\"" + field.getShortName() + "\" value=\"" + field.getData() + "\">"
			        + field.getDataString();
		}else{
			html += "oui: <input type=\"radio\" name=\"" + field.getShortName() + "\" value=\"true\"" + yesIsChecked
			        + " />";
			html += " | non: <input type=\"radio\" name=\"" + field.getShortName() + "\" value=\"false\"" + noIsChecked
			        + " />";
		}

		return html;
	}

	private static String otherViewer(Field<?> field) {
		String textFieldType = "";
		String isReadOnly = "";

		if(field.isHidden()){
			textFieldType = "password";
		}else{
			textFieldType = "text";
		}

		if(field.isReadOnly()){
			return "<input type='hidden' name='" + field.getShortName() + "' value='" + field.getDataString()
			        + "' class='textField' />" + field.getDataString();
		}

		return "<input type='" + textFieldType + "' name='" + field.getShortName() + "' value='"
		        + field.getDataString() + "' class='textField'" + isReadOnly + " />";
	}
}
