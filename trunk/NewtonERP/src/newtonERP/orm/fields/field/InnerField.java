package newtonERP.orm.fields.field;

// TODO: Auto-generated Javadoc

/**
 * Super class for entity fields used in the modules.
 * 
 * @param <T> the generic type
 * @author Jonatan Cloutier, r3hallejo
 */
public abstract class InnerField<T> extends AbstractFieldDecorator {

	/** The data. */
	protected T data;

	/**
	 * Instantiates a new inner field.
	 */
	public InnerField() {
		// nothing to do
	}

	/**
	 * constructeur minimum.
	 * 
	 * @param data donne du champ
	 */
	public InnerField(T data) {
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getDataString() {
		return data.toString();
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the data to set
	 */
	@Override
	public void setData(Object data) {
		setData(this, data);
	}

	/**
	 * Sets the data in a field.
	 * 
	 * @param <T> the generic type
	 * @param field the field
	 * @param data the data
	 */
	private static <T> void setData(InnerField<T> field, Object data) {
		field.setDataType(field.getTypeArgument().cast(data));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{" + getClass().getSimpleName() + ":" + getDataString() + "}";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		data = null;
	}

	/**
	 * Sets the data in the good type.
	 * 
	 * @param data the new data type
	 */
	protected void setDataType(T data) {
		this.data = data;
	}

	/**
	 * Gets the type argument.
	 * 
	 * @return the type argument
	 */
	protected abstract Class<T> getTypeArgument();
}
