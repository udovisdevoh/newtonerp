package newtonERP.viewers.viewables;

import newtonERP.common.NaturalMap;

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
    public NaturalMap<String, String> getElements() throws Exception;

    /**
     * @return the label name
     */
    public String getLabelName();
}
