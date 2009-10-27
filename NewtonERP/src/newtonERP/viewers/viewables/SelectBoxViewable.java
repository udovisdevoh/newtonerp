package newtonERP.viewers.viewables;

import java.util.Hashtable;

import newtonERP.orm.exceptions.OrmException;

/**
 * @author Guillaume Lacasse
 * 
 *         The select box view interface
 */
public interface SelectBoxViewable
{
    /**
     * @return the elements of the select box
     * @throws OrmException an exception that can occur in the orm
     */
    public Hashtable<String, String> getElements() throws OrmException;

    /**
     * @return the label name
     */
    public String getLabelName();
}
