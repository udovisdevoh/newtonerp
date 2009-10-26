package newtonERP.viewers.viewables;

import java.util.HashSet;
import java.util.TreeMap;

public interface CheckListViewable
{
    public String getVisibleDescription();

    public TreeMap<String, String> getAvailableElementList() throws Exception;

    public HashSet<String> getCheckedElementList() throws Exception;
}
