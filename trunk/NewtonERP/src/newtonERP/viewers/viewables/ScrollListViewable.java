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
    public String getTitle();

    /**
     * @return liste des liens dans la liste (clef: nom visible, valeur: url)
     */
    public TreeMap<String, String> getLinkList();
}
