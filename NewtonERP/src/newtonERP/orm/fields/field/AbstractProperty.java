package newtonERP.orm.fields.field;

/**
 * The base Class Property.
 * 
 * @author Jonatan Cloutier
 */
public abstract class AbstractProperty {

	/**
	 * preSetup. this method is call before the property is added
	 * 
	 * @param field the field on witch it will be added
	 */
	protected void preSetup(Field field) {
		// empty implementation
	}

	/**
	 * postSetup. this method is call after the property is added
	 * 
	 * @param field the field on witch it will be added
	 */
	protected void postSetup(Field field) {
		// empty implementation
	}

	/**
	 * Gets the decorator.
	 * 
	 * @return the decorator
	 */
	protected AbstractFieldDecorator getDecorator() {
		// empty implementation
		return null;
	}
}
