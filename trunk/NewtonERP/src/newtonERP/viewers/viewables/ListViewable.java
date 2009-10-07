package newtonERP.viewers.viewables;

import java.util.HashSet;
import java.util.Hashtable;

import newtonERP.module.AbstractAction;

/**
 * @author r3lacasgu
 * 
 */
public interface ListViewable
{
    /**
     * @return Liste des boutons spéficiques (edit, delete) Clef: nom d'un
     *         bouton Valeur: Action du bouton
     */
    public Hashtable<String, AbstractAction> getSpecificActionButtonList();

    /**
     * @return Liste des boutons globaux (new, exit) Clef: nom d'un bouton
     *         Valeur: Action du bouton
     */
    public Hashtable<String, AbstractAction> getGlobalActionButtonList();

    /**
     * @return Titre des colonnes
     */
    public Iterable<String> getColumnTitleList();

    /**
     * @return Liste de rangées
     */
    public Iterable<HashSet<String>> getRowValues();
}