package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo cloutierJo
 * 
 *         Entity defenition class representing a user
 */
public class User extends AbstractOrmEntity implements PromptViewable
{
    // vers les entités
    // groups

    /**
     * @throws Exception si création fail
     */
    public User() throws Exception
    {
	super();
	addHiddenField("password");
	AccessorManager.addAccessor(this, new Groups());
	setVisibleName("Utilisateur");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();// Renamé en fieldsData
	// pour enlever confusion
	fieldsData.add(new FieldInt("Numéro de user", getPrimaryKeyName()));
	fieldsData.add(new FieldString("Nom", "name"));
	fieldsData.add(new FieldString("Mot de passe", "password"));
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
	AbstractOrmEntity groupDefinition = new Groups();// Sert de référence
	Vector<String> search = new Vector<String>();
	search.add(groupDefinition.getPrimaryKeyName() + "="
		+ getFields().getField("groupsID"));

	return (Groups) Orm.select(new Groups(), search).get(0);
    }
}