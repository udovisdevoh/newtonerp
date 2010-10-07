package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Entity defenition class representing a user
 * @author r3hallejo cloutierJo
 */
public class User extends newtonERP.module.AbstractOrmEntity
{
	/**
	 * vers les entités groups
	 */
	public User()
	{
		super();
		AccessorManager.addAccessor(this, new Groups());
		setVisibleName("Utilisateur");
	}

	public newtonERP.orm.field.Fields initFields()
	{
		Vector<Field<?>> fieldsData = new Vector<Field<?>>();
		fieldsData.add(new FieldInt("Numéro de user", getPrimaryKeyName()));
		fieldsData.add(new FieldString("Nom", "name"));
		FieldString pwd = new FieldString("Mot de passe", "password");
		pwd.setHidden(true);
		fieldsData.add(pwd);
		fieldsData.add(new FieldInt("Numéro de groupe", "groupsID"));
		return new Fields(fieldsData);
	}

	/**
	 * permet d'obtenir directement l'entity groups lie a cet user
	 * 
	 * @return le group lie
	 */
	public Groups getGroupsEntity()
	{
		return (Groups) getSingleAccessor("groupsID");
	}

}
