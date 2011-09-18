package newtonERP.orm.fields.field;

/**
 * The Class AbstractFieldDecorator.
 * 
 * @author Jonatan Cloutier
 */
public abstract class AbstractFieldDecorator {

	/** The first decorator (Field). */
	protected Field root = null;

	/** The next decorator. */
	protected AbstractFieldDecorator next = null;

	/**
	 * Sets the data string.
	 * 
	 * @param data the data to set
	 */
	public void setDataString(String data) {
		next.setDataString(data);
	}

	/**
	 * Gets the data string.
	 * 
	 * @return the data sous forme de string visible par l'utilisateur
	 */
	public String getDataString() {
		return next.getDataString();
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the new data
	 */
	public void setData(Object data) {
		next.setData(data);
	}

	/**
	 * Gets the data.
	 * 
	 * @param <T> the generic type
	 * @return the data
	 */
	public <T> T getData() {
		return next.getData();
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return next.toString();
	}

	/**
	 * definie la valeur par defaut (devrais etre une valeur null).
	 */
	public void setDefaultValue() {
		next.setDefaultValue();
	}

	/**
	 * remet la valeur a null.
	 */
	public void reset() {
		next.reset();
	}
}
