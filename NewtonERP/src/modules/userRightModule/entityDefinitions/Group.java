package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.ProfileViewable;

public class Group implements Ormizable, ProfileViewable
{
    int PKgroupID;
    String groupName;

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	// TODO Auto-generated method stub

    }

}
