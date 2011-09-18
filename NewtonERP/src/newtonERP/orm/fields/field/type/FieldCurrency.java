package newtonERP.orm.fields.field.type;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import newtonERP.common.logging.Logger;
import newtonERP.orm.fields.field.property.ErrorProperty;

/**
 * Double field in the entities
 * 
 * @author Jonatan Cloutier
 */
public class FieldCurrency extends FieldDouble {

	/** The curency decimal formater. */
	private DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);

	/**
	 * Instantiates a new field currency.
	 */
	public FieldCurrency() {
		super();
		df.setGroupingUsed(false);
		df.setNegativePrefix("-");
		df.setNegativeSuffix(" $");
	}

	/**
	 * Instantiates a new field currency.
	 * 
	 * @param data the data
	 */
	public FieldCurrency(Double data) {
		super(data);
		df.setGroupingUsed(false);
		df.setNegativePrefix("-");
		df.setNegativeSuffix("");
	}

	@Override
	public String getDataString() {
		if(data == null){
			return "";
		}
		return df.format(super.getData());
	}

	@Override
	public void setDataString(String data) {
		try{
			String dataFormated = data;
			dataFormated = dataFormated.replaceAll("\\s", "");
			dataFormated = dataFormated.replaceAll("\\$", "");
			dataFormated = dataFormated.replaceAll("\\.", ",");
			dataFormated = dataFormated.trim();
			dataFormated += " $";
			setData(df.parse(dataFormated).doubleValue());
		}catch(Exception e){
			root.addProperty(new ErrorProperty(
					"Le format de donnée entrée ne correspond pas avec le type de champ (Currency): " + data));
			Logger.error(e.getMessage());// TODO: change to send error back to front end
		}
	}
}
