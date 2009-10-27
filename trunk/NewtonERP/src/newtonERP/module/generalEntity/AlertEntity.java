package newtonERP.module.generalEntity;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.AlertViewable;

public class AlertEntity extends AbstractEntity implements AlertViewable
{
    private String message;

    public AlertEntity(String message)
    {
	this.message = message;
    }

    public String getMessage()
    {
	return message;
    }
}
