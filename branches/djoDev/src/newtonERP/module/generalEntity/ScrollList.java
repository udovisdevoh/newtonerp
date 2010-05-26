package newtonERP.module.generalEntity;

import java.util.TreeMap;
import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.common.NaturalMap;
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

    private Vector<NaturalMap<String, String>> linkPairList;

    private String titleUrl;

    private Vector<ActionLink> actionLinkList;

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

    public Vector<NaturalMap<String, String>> getLinkGroupList()
    {
	if (linkPairList == null)
	    linkPairList = new Vector<NaturalMap<String, String>>();

	return linkPairList;
    }

    @Override
    public String getTitle()
    {
	return title;
    }

    /**
     * @param link1Name Premier nom de lien
     * @param link1Url Premier url
     * @param link2Name 2e nom de lien
     * @param link2Url 2e url
     */
    public void addLinkGroup(String link1Name, String link1Url,
	    String link2Name, String link2Url)
    {
	NaturalMap<String, String> linkGroup = new NaturalMap<String, String>();
	linkGroup.put(link1Name, link1Url);
	linkGroup.put(link2Name, link2Url);

	getLinkGroupList().add(linkGroup);
    }

    /**
     * @param titleUrl url du titre (facultatif)
     */
    public void setTitleUrl(String titleUrl)
    {
	this.titleUrl = titleUrl;
    }

    @Override
    public String getTitleUrl()
    {
	return titleUrl;
    }

    /**
     * @param actionLink actionLink à ajouter
     */
    public void addActionLink(ActionLink actionLink)
    {
	getActionLinkList().add(actionLink);
    }

    /**
     * @return liste des actionLink
     */
    public Vector<ActionLink> getActionLinkList()
    {
	if (actionLinkList == null)
	    actionLinkList = new Vector<ActionLink>();
	return actionLinkList;
    }
}
