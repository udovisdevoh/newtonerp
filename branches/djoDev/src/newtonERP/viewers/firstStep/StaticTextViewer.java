package newtonERP.viewers.firstStep;

/**
 * Static text viewer
 * @author Guillaume Lacasse
 */
public class StaticTextViewer
{
	/**
	 * @param entity the entity to view
	 * @return the entity text
	 */
	public static String getHtmlCode(
			newtonERP.viewers.viewables.StaticTextViewable entity)
	{
		return "<pre>"
				+ entity.getText().replace("<", "&lt;").replace(">", "&gt;")
				+ "</pre>";
	}

}
