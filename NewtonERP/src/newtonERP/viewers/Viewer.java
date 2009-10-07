package newtonERP.viewers;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.ListViewable;
import newtonERP.viewers.viewables.ProfileViewable;
import newtonERP.viewers.viewables.PromptViewable;

public abstract class Viewer
{
    public static String getHtmlCode(AbstractEntity entity)
	    throws ViewerException
    {
	if (entity instanceof PromptViewable)
	    return PromptViewer.getHtmlCode((PromptViewable) entity);
	else if (entity instanceof ProfileViewable)
	    return ProfileViewer.getHtmlCode((ProfileViewable) entity);
	else if (entity instanceof ListViewable)
	    return ListViewer.getHtmlCode((ListViewable) entity);

	throw new ViewerException("Couldn't find proper viewer for entity");
    }
}