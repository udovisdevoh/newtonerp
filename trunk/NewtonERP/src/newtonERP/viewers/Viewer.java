package newtonERP.viewers;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.ListViewable;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author Guillaume Lacasse, Pascal Lemay
 * 
 * 	Represents the main viewer for our ERP
 */
public abstract class Viewer
{
    /**
     * Gets the html code by calling the same method in the right viewer
     * 
     * @param entity
     * @return the viewer
     * @throws ViewerException
     */
    public static String getHtmlCode(AbstractEntity entity)
	    throws ViewerException
    {
	if (entity instanceof PromptViewable)
	{
	    try
	    {
		return PromptViewer.getHtmlCode((PromptViewable) entity);
	    } catch (Exception e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	else if (entity instanceof ListViewable)
	    return ListViewer.getHtmlCode((ListViewable) entity);

	throw new ViewerException("Couldn't find proper viewer for entity");
    }
}