package newtonERP.orm.field.type;

import newtonERP.logging.Logger;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;

/**
 * Boolean field in the entities
 * 
 * @author CloutierJo, r3hallejo
 */
public class FieldBool extends Field<Boolean> {
	/**
	 * constructeur minimum
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 * @param data donne du champ
	 */
	public FieldBool(String name, String shortName, Boolean data) {
		super(name, shortName, data);
	}

	/**
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 */
	public FieldBool(String name, String shortName) {
		this(name, shortName, null);
	}

	@Override
	public String getDataString(Boolean forOrm) {
		if(data == null){
			return "";
		}

		if(forOrm){
			return addSlash(data.toString());
		}else if(data){
			return "Oui";
		}else{
			return "Non";
		}
	}

	/**
	 * @param data the data to set
	 */
	@Override
	public void setData(String data) {
		try{
			if(data.toLowerCase().equals("on") || data.toLowerCase().equals("true")){
				setDataType(true);
			}else{
				setDataType(Boolean.parseBoolean(data));
			}
		}catch(Exception e){
			setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (boolean): " + data);
			Logger.error(e.getMessage());
		}
	}

	@Override
	public void setOperator(String operator) {
		operator = operator.trim();

		if(operator.equals("=")){
			super.operator = operator;
		}else{
			throw new InvalidOperatorException("Opérateur ( " + operator + " ) invalide pour " + getSystemName());
		}
	}

	@Override
	public void setDefaultValue() {
		data = false;
	}

	@Override
	public void setData(Object data) {
		if(data instanceof Boolean){
			setDataType((Boolean) data);
		}else{
			throw new FieldNotCompatibleException(getShortName(), data);
		}
	}
}
