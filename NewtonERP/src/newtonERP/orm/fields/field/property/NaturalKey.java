package newtonERP.orm.fields.field.property;

import newtonERP.orm.fields.field.AbstractProperty;

/**
 * The property naturalKey, specified that this field is part of the natural key, the optional parameter position
 * specified the order of this field when the natural key is visible. If the position is not set the order is not
 * determinism and can change any time.
 * 
 * @author Jonatan Cloutier
 */
public class NaturalKey extends AbstractProperty {

	/** The position. */
	private int position;

	/**
	 * Instantiates a new natural key.
	 */
	public NaturalKey() {
		super();
		position = 0;
	}

	/**
	 * Instantiates a new natural key property.
	 * 
	 * @param position the position
	 */
	public NaturalKey(int position) {
		super();
		this.position = position;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
