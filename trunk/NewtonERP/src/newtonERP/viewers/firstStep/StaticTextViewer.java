package newtonERP.viewers.firstStep;

import newtonERP.viewers.viewables.StaticTextViewable;

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
    public static String getHtmlCode(StaticTextViewable entity)
    {
	return "<pre>"
		+ entity.getText().replace("<", "&lt;").replace(">", "&gt;")
		+ "</pre>";
    }
}
