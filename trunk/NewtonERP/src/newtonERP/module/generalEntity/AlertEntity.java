package newtonERP.module.generalEntity;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.AlertViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         Represents an alert entity
 */
public class AlertEntity extends AbstractEntity implements AlertViewable
{
    private String message;

    /**
     * @param message the message of the alert
     * @throws Exception si création fail
     */
    public AlertEntity(String message) throws Exception
    {
	this.message = message;
    }

    public String getMessage()
    {
	return message;
    }
}
