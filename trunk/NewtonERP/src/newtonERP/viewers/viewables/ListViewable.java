package newtonERP.viewers.viewables;

import java.util.HashSet;
import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;

/**
 * @author r3lacasgu
 * 
 */
public interface ListViewable
{
    /**
     * @return Titre principal de la liste
     */
    public String getTitle();

    /**
     * @return Module callé par le prompt
     */
    public Module getSubmitModule();

    /**
     * @return Action à affectuer quand on submit le prompt
     */
    public AbstractAction getSubmitAction();

    /**
     * @return Utilisateur actuel Cette methode sera effacée au sprint 2 car
     *         l'autentification sera faite dans le servlet
     */
    public String getCurrentUserName();

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
