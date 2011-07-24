package newtonERP.orm.fields.field.type;

import newtonERP.orm.fields.field.InnerField;

/**
 * Boolean field in the entities.
 * 
 * @author Jonatan Cloutier, r3hallejo
 */
public class FieldBool extends InnerField<Boolean> {

	/**
	 * Instantiates a new field bool.
	 */
	public FieldBool() {
		super();
	}

	/**
	 * Instantiates a new field bool.
	 * 
	 * @param data the data
	 */
	public FieldBool(Boolean data) {
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataString() {
		if(data == null){
			return "";
		}

		if(data){
			return "True";
		}
		return "False";
	}

	/**
	 * Sets the data string.
	 * 
	 * @param data the data to set
	 */
	@Override
	public void setDataString(String data) {
		if(data.toLowerCase().equals("on") || data.toLowerCase().equals("true") || data.toLowerCase().equals("yes")){
			setDataType(true);
		}else{
			setDataType(false);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultValue() {
		data = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<Boolean> getTypeArgument() {
		return Boolean.class;
	}
}
