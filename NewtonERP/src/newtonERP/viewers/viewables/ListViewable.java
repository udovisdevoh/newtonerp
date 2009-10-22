package newtonERP.viewers.viewables;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;
import newtonERP.module.exception.EntityException;

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
     * @return Module à utiliser en ce moment
     */
    public Module getCurrentModule() throws EntityException;

    /**
     * @return Module à utiliser en ce moment
     */
    public void setCurrentModule(Module module) throws EntityException;

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
    public Vector<String> getColumnTitleList();

    /**
     * @return Liste de rangées
     */
    public Vector<Vector<String>> getRowValues();
}
