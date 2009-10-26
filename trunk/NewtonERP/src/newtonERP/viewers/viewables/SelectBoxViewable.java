package newtonERP.viewers.viewables;

import java.util.Hashtable;

import newtonERP.orm.exceptions.OrmException;

public interface SelectBoxViewable
{
    public Hashtable<String, String> getElements() throws OrmException;

    public String getLabelName();
}
