package newtonERP.viewers.viewables;

import java.util.HashSet;
import java.util.TreeMap;

/**
 * @author Guillaume Lacasse
 * 
 *         The check list view interface
 */
public interface CheckListViewable
{
    /**
     * @return the visible description
     */
    public String getVisibleDescription();

    /**
     * @return a tree map of the available elements
     * @throws Exception a general exception
     */
    public TreeMap<String, String> getAvailableElementList() throws Exception;

    /**
     * @return a hash set of cheked elements
     * @throws Exception a general exception
     */
    public HashSet<String> getCheckedElementList() throws Exception;
}
