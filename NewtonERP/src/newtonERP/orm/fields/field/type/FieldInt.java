package newtonERP.orm.fields.field.type;

import newtonERP.common.logging.Logger;
import newtonERP.orm.fields.field.InnerField;
import newtonERP.orm.fields.field.property.ErrorProperty;

/**
 * Integer field in the entities.
 * 
 * @author Jonatan Cloutier, r3hallejo
 */
public class FieldInt extends InnerField<Integer> {

	/**
	 * Instantiates a new field int.
	 */
	public FieldInt() {
		super();
	}

	/**
	 * Instantiates a new field int.
	 * 
	 * @param data the data
	 */
	public FieldInt(Integer data) {
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
				setData(0);
			}
			setData(Integer.parseInt(data));
		}catch(Exception e){
			root.addProperty(new ErrorProperty(
					"Le format de donnée entrée ne correspond pas avec le type de champ (int): " + data));
			Logger.error(e.getMessage());// TODO: change to send error back to front end
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultValue() {
		setData(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<Integer> getTypeArgument() {
		return Integer.class;
	}
}
