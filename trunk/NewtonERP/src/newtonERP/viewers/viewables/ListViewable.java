package newtonERP.viewers.viewables;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import newtonERP.common.NaturalMap;
import newtonERP.module.AbstractAction;
import newtonERP.module.Module;
import newtonERP.module.exception.EntityException;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3lacasgu
 * 
 */
public interface ListViewable
{
    /**
     * @return Module à utiliser en ce moment
     * @throws Exception si obtention du module fail
     * @throws EntityException a general exception
     */
    public Module getCurrentModule() throws Exception;

    /**
     * @param module the module to set
     * @throws EntityException a general entity exception
     */
    public void setCurrentModule(Module module) throws EntityException;

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
     * @throws Exception maudites exceptions de Java
     * @throws OrmException an exception that can occur in the orm
     */
    public Vector<String> getColumnTitleList() throws Exception;

    /**
     * @return Liste de rangées
     * @throws Exception maudies exceptions de Java
     * @throws OrmException an exception that can occur in the orm
     */
    public Vector<NaturalMap<String, String>> getRowList() throws Exception;

    /**
     * @return Nom de la clef pour identifier une entité dans la liste
     */
    public String getKeyName();

    /**
     * @param rowNumber the row number to search in
     * @return valeur de la clef pour identifier une entité dans la liste
     */
    public String getKeyValue(int rowNumber);

    /**
     * @return the internal element name
     */
    public String getVisibleInternalElementName();

    /**
     * @return Liste de noms d'actions de boutons pour lequels l'utilisateur
     *         doit confirmer son choix
     */
    public Set<String> getButtonConfirmList();

    /**
     * @param fieldName nom du field
     * @return true si nom du champ doit être formatté en argent dans element de
     *         liste
     */
    public boolean isListElementColumnMatchCurrencyFormat(String fieldName);

    /**
     * @return liste des entité promptViewable
     */
    public Vector<PromptViewable> getViewableRowList();
}
