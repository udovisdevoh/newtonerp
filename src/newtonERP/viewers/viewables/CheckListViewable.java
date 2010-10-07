package newtonERP.viewers.viewables;

import java.util.HashSet;
import java.util.TreeMap;

/**
 * The check list view interface
 * @author Guillaume Lacasse
 */
public interface CheckListViewable
{
	/**
	 * @return the visible description
	 */
	String getVisibleDescription();

	/**
	 * @return a tree map of the available elements
	 */
	TreeMap<String, String> getAvailableElementList();

	/**
	 * @return a hash set of cheked elements
	 */
	HashSet<String> getCheckedElementList();

}
