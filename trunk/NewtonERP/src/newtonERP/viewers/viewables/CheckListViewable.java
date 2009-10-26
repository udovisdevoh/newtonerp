package newtonERP.viewers.viewables;

import java.util.HashSet;
import java.util.Hashtable;

public interface CheckListViewable
{
    public String getVisibleDescription();

    public Hashtable<String, String> getAvailableElementList() throws Exception;

    public HashSet<String> getCheckedElementList() throws Exception;
}
