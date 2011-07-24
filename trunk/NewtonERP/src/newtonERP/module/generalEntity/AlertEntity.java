package newtonERP.module.generalEntity; 
 // TODO: clean up that file

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.AlertViewable;

/**
 * @author Guillaume Lacasse Represents an alert entity
 */
public class AlertEntity extends AbstractEntity implements AlertViewable {
	private String message;

	/**
	 * @param message the message of the alert
	 */
	public AlertEntity(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
