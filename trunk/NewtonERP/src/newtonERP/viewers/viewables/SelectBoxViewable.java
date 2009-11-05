package newtonERP.viewers.viewables;

import java.util.Hashtable;

/**
 * The select box view interface
 * @author Guillaume Lacasse
 */
public interface SelectBoxViewable
{
    /**
     * @return the elements of the select box
     * @throws Exception an exception that can occur in the orm
     */
    public Hashtable<String, String> getElements() throws Exception;

    /**
     * @return the label name
     */
    public String getLabelName();
}
