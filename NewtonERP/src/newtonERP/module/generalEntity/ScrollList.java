package newtonERP.module.generalEntity;

import java.util.TreeMap;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.ScrollListViewable;

/**
 * Entité comportant une scrollbar. Cette entité peut être vue comme une liste
 * HTML de lien ou de texte.
 * @author Guillaume Lacasse
 */
public class ScrollList extends AbstractEntity implements ScrollListViewable
{
    private String title;

    private TreeMap<String, String> linkList;

    /**
     * @param title titre
     * @throws Exception si création fail
     */
    public ScrollList(String title) throws Exception
    {
	super();
	this.title = title;
    }

    /**
     * @param name nom visible du lien
     * @param url url du lien
     */
    public void addLink(String name, String url)
    {
	if (linkList == null)
	    linkList = new TreeMap<String, String>();

	linkList.put(name, url);
    }

    @Override
    public TreeMap<String, String> getLinkList()
    {
	if (linkList == null)
	    linkList = new TreeMap<String, String>();
	return linkList;
    }

    @Override
    public String getTitle()
    {
	return title;
    }
}
