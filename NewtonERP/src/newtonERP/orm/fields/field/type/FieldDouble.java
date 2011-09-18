package newtonERP.orm.fields.field.type;

import newtonERP.common.logging.Logger;
import newtonERP.orm.fields.field.InnerField;
import newtonERP.orm.fields.field.property.ErrorProperty;

/**
 * Double field in the entities.
 * 
 * @author Jonatan Cloutier
 */
public class FieldDouble extends InnerField<Double> {

	/**
	 * Instantiates a new field double.
	 */
	public FieldDouble() {
		super();
	}

	/**
	 * Instantiates a new field double.
	 * 
	 * @param data the data
	 */
	public FieldDouble(Double data) {
		super(data);
	}

	/**
	 * Sets the data string.
	 * 
	 * @param data the data to set
	 */
	@Override
	public void setDataString(String data) {
		try{
			if(data == null || data.equals("null")){
				setData(0.0);
			}else{
				setData(Double.parseDouble(data));
			}
		}catch(Exception e){
			root.addProperty(new ErrorProperty(
					"Le format de donnée entrée ne correspond pas avec le type de champ (Double): " + data));
			Logger.error(e.getMessage());// TODO: change to send error back to front end
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultValue() {
		setData(0.);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<Double> getTypeArgument() {
		return Double.class;
	}
}
