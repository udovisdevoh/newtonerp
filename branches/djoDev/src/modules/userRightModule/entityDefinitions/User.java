package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Entity defenition class representing a user
 * @author r3hallejo cloutierJo
 */
public class User extends AbstractOrmEntity
{
    // vers les entités
    // groups

    /**
     * @throws Exception si création fail
     */
    public User() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Groups());
	setVisibleName("Utilisateur");
    }

    public Fields initFields() throws Exception
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
     * @throws Exception si obtention fail
     */
    public Groups getGroupsEntity() throws Exception
    {
	return (Groups) getSingleAccessor("groupsID");
    }
}