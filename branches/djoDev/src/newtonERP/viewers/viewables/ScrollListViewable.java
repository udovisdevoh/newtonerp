package newtonERP.viewers.viewables;

import java.util.TreeMap;
import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.common.NaturalMap;

/**
 * Element graphique visible en tant que liste scrollable de lien ou de texte
 * avec une scrollbar
 * @author Guillaume
 */
public interface ScrollListViewable
{
    /**
     * @return le titre de la liste
     */
    public String getTitle();

    /**
     * @return liste des liens dans la liste (clef: nom visible, valeur: url)
     */
    public TreeMap<String, String> getLinkList();

    /**
     * @return liste de groupe de liens
     */
    public Vector<NaturalMap<String, String>> getLinkGroupList();

    /**
     * @return url du titre si c'est un lien
     */
    public String getTitleUrl();

    /**
     * @return liste des actionLink
     */
    public Vector<ActionLink> getActionLinkList();
}
