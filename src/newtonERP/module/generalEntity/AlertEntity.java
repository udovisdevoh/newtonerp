package newtonERP.module.generalEntity;

/**
 * @author Guillaume Lacasse
 * 
 *         Represents an alert entity
 */
public class AlertEntity extends newtonERP.module.AbstractEntity implements
		newtonERP.viewers.viewables.AlertViewable
{
	private String message;

	/**
	 * @param message the message of the alert
	 */
	public AlertEntity(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

}
