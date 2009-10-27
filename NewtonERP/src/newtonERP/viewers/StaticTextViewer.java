package newtonERP.viewers;

import newtonERP.viewers.viewables.StaticTextViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         Static text viewer
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
