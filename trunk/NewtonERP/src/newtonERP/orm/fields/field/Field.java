package newtonERP.orm.fields.field;

import java.util.Map;

/**
 * Super class for entity fields used in the modules.
 * 
 * @author Jonatan Cloutier, r3hallejo
 */
public class Field extends AbstractFieldDecorator {

	/** The name. */
	private String name;

	/** The properties. */
	private Map<Class<? extends AbstractProperty>, AbstractProperty> properties;

	/**
	 * constructeur minimum.
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param next the next
	 */
	public Field(String name, InnerField<?> next) {
		this.name = name;
		this.next = next;
		root = this;
	}

	/**
	 * Adds the property.
	 * 
	 * @param property the property
	 */
	public void addProperty(AbstractProperty property) {
		property.preSetup(this);
		properties.put(property.getClass(), property);
		AbstractFieldDecorator decorator = property.getDecorator();
		if(decorator != null){
			decorator.next = next;
			next = decorator;
		}
		property.postSetup(this);
	}

	/**
	 * Gets the property.
	 * 
	 * @param propertyClass the property class
	 * @return the property
	 */
	public AbstractProperty getProperty(Class<? extends AbstractProperty> propertyClass) {
		return properties.get(propertyClass);
	}

	/**
	 * Gets the property.
	 * 
	 * @param propertyClass the property class
	 * @return the property
	 */
	public boolean isPropertySet(Class<? extends AbstractProperty> propertyClass) {
		return properties.containsKey(propertyClass);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the short name.
	 * 
	 * @return the short name
	 */
	public String getSystemName() {
		return name.replace(' ', '_');
	}
}
