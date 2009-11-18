package modules.humanResources.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.humanResources.entityDefinitions.Employee;
import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * On cré un nouveau user pour un employé et on lui assigne
 * @author Guillaume Lacasse
 */
public class CreateUserForEmployee extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public CreateUserForEmployee() throws Exception
    {
	super(new Employee());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Employee employee = (Employee) entity;

	String userName = getUserName(employee);

	User user = null;

	user = tryGetExistingUser(userName);

	if (user == null)
	{
	    user = new User();
	    user.initFields();
	    user.setData("name", userName);
	    user.setData("password", "aaa");
	    user.setData(new Groups().getForeignKeyName(), 1);
	    user.newE();
	}

	employee.assign(user);
	employee.save();

	return employee.editUI(null);
    }

    private User tryGetExistingUser(String userName) throws Exception
    {
	User searchEntity = new User();
	searchEntity.setData("name", userName);
	Vector<AbstractOrmEntity> result = Orm.select(searchEntity);

	if (result.size() > 0)
	    return (User) result.get(0);
	return null;
    }

    private String getUserName(Employee employee)
    {
	return employee.getDataString("firstName")
		+ employee.getDataString("lastName");
    }
}
