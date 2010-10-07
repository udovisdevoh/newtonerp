package newtonERP.module.generalEntity;

/**
 * @author Guillaume Lacasse
 */
public class ForwardEntity extends newtonERP.module.AbstractEntity implements
		newtonERP.viewers.viewables.ForwardViewable
{
	private String forwardingUrl;

	/**
	 * @param forwardingUrl the url to go to
	 */
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
