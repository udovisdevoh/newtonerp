package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.GetRightList;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.ForwardEntity;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.serveur.Servlet;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a right
 */
public class Right extends AbstractOrmEntity
{
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du droit", getPrimaryKeyName()));
	fields.add(new FieldString("Nom de module", "moduleName"));
	fields.add(new FieldString("Nom de l'action", "actionName"));
	fields.add(new FieldString("Nom de l'entité", "entityName"));
	return new Fields(fields);
    }

    @Override
    public AbstractEntity getAfterDeleteReturnEntity()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity getUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	Right right = new Right();
	right.setCurrentModule(new UserRightModule());
	right.setData("moduleName", "aucun");
	right.setData("entityName", "aucun");
	right.setData("actionName", "aucun");
	// user.setSubmitAction(new BaseAction("Edit", this));

	((AbstractOrmEntity) right).newE();

	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetRightList()));
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException, FieldNotCompatibleException
    {
	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetRightList()));
    }
}
