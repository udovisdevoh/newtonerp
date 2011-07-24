package newtonERP.orm.fields.field.type;

import newtonERP.orm.fields.field.InnerField;

/**
 * String field in the entities (String is a short text where text is a long text).
 * 
 * @author Jonatan Cloutier, r3hallejo
 */
public class FieldString extends InnerField<String> {

	/**
	 * Instantiates a new field string.
	 */
	public FieldString() {
		super();
	}

	/**
	 * Instantiates a new field string.
	 * 
	 * @param data the data
	 */
	public FieldString(String data) {
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultValue() {
		setDataType("");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataString(String data) {
		setDataType(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<String> getTypeArgument() {
		return String.class;
	}
}
