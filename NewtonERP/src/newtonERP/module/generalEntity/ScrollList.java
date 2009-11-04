package newtonERP.module.generalEntity;

import java.util.TreeMap;
import java.util.Vector;

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

    private Vector<TreeMap<String, String>> linkPairList;

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

    public Vector<TreeMap<String, String>> getLinkGroupList()
    {
	if (linkPairList == null)
	    linkPairList = new Vector<TreeMap<String, String>>();

	return linkPairList;
    }

    @Override
    public String getTitle()
    {
	return title;
    }

    public void addLinkGroup(String link1Name, String link1Url,
	    String link2Name, String link2Url)
    {
	TreeMap<String, String> linkGroup = new TreeMap<String, String>();
	linkGroup.put(link1Name, link1Url);
	linkGroup.put(link2Name, link2Url);

	getLinkGroupList().add(linkGroup);
    }
}