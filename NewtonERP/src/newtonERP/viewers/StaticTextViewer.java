package newtonERP.viewers;

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
	return entity.getText().replace('<', ' ').replace('>', ' ');
    }
}
