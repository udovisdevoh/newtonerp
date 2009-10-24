package newtonERP.module;

import newtonERP.viewers.viewables.ForwardViewable;

public class ForwardEntity extends AbstractEntity implements ForwardViewable
{
    private String forwardingUrl;

    public ForwardEntity(String forwardingUrl)
    {
	this.forwardingUrl = forwardingUrl;
    }

    @Override
    public String getForwardingUrl()
    {
	return forwardingUrl;
    }
}
