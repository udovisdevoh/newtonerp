package newtonERP.orm.fields.field;


// TODO: clean up that file

/**
 * Super class for entity fields used in the modules
 * 
 * @author CloutierJo, r3hallejo
 * @param <T> type de field
 */
public abstract class OldField<T> {
	protected String operator;

	/**
	 * @param forOrm si true, la méthode retourne une string formatté pour l'ORM si faux retourne un string visible pour
	 *        l'utilisateur, valeur par défaut false
	 * @return the data
	 */
	public String getDataString(Boolean forOrm) {
		if(calcul != null){
			data = calcul.calculate(fieldsRef);
		}
		if(data == null){
			return "";
		}
		if(forOrm){
			return addSlash(data.toString());
		}
		return data.toString();
	}

	@Override
	public String toString() {
		return "{" + getClass().getSimpleName() + ":" + getDataString() + "}";
	}

	/**
	 * Adds the slash.
	 * 
	 * @param str the str
	 * @return the string
	 */
	protected String addSlash(String str) {
		str = str.replace("'", "`");
		return str;
	}

}
