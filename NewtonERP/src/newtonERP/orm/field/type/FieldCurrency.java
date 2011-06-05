package newtonERP.orm.field.type;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import newtonERP.logging.Logger;

/**
 * Double field in the entities
 * 
 * @author CloutierJo
 */
public class FieldCurrency extends FieldDouble {
	private DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);

	/**
	 * constructeur minimum
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 * @param data donne du champ
	 */
	public FieldCurrency(String name, String shortName, Double data) {
		super(name, shortName, data);
		df.setGroupingUsed(false);
	}

	/**
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 */
	public FieldCurrency(String name, String shortName) {
		this(name, shortName, null);
	}

	@Override
	public String getDataString(Boolean forOrm) {

		if(forOrm){
			return super.getDataString(forOrm);
		}
		if(getCalcul() != null){
			data = super.getData();
		}
		if(data == null){
			return "";
		}
		return df.format(super.getData());
	}

	@Override
	public void setData(String data) {
		try{
			data = data.replaceAll("\\s", "");
			data = data.replaceAll("\\$", "");
			data = data.replaceAll("\\.", ",");
			data += " $";
			this.data = df.parse(data).doubleValue();
		}catch(Exception e){
			setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (Currency): " + data);
			Logger.error(e.getMessage());
		}
	}
}
