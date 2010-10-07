package newtonERP.viewers.viewables;

import java.util.TreeMap;

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
	String getTitle();

	/**
	 * @return liste des liens dans la liste (clef: nom visible, valeur: url)
	 */
	TreeMap<String, String> getLinkList();

	/**
	 * @return liste de groupe de liens
	 */
	newtonERP.common.NaturalMap getLinkGroupList();

	/**
	 * @return url du titre si c'est un lien
	 */
	String getTitleUrl();

	/**
	 * @return liste des actionLink
	 */
	newtonERP.common.ActionLink getActionLinkList();

}
