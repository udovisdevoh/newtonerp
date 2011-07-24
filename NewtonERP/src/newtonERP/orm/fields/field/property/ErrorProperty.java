package newtonERP.orm.fields.field.property;

import newtonERP.orm.fields.field.AbstractProperty;

/**
 * The property ErrorProperty. should be replace with throw.
 * 
 * @author Jonatan Cloutier
 */
@Deprecated
public class ErrorProperty extends AbstractProperty {

	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new error property.
	 * 
	 * @param errorMessage the error message
	 */
	public ErrorProperty(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
