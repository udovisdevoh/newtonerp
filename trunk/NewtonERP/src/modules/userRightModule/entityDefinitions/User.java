package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo cloutierJo
 * 
 *         Entity defenition class representing a user
 */
public class User extends AbstractOrmEntity implements PromptViewable
{
    private static Groups groupDefinition = new Groups();// Sert de référence

    // vers les entités
    // groups

    public Fields initFields()
    {
	Vector<Field> fieldsData = new Vector<Field>();// Renamé en fieldsData
	// pour enlever confusion
	fieldsData.add(new FieldInt("Numéro de user", getPrimaryKeyName()));
	fieldsData.add(new FieldString("Nom", "name"));
	fieldsData.add(new FieldString("Mot de passe", "password"));
	fieldsData.add(new FieldInt("Numéro de groupe", "groupsID"));

	addListOfValue("Groupe", "groupsID", "groupName", groupDefinition);
	addHiddenField("password");

	return new Fields(fieldsData);
    }

    /**
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lie
     * @throws OrmException remonte
     */
    public Groups getGroupsEntity() throws OrmException
    {
	Vector<String> search = new Vector<String>();
	search.add(groupDefinition.getPrimaryKeyName() + "="
		+ getFields().getField("groupsID"));

	return (Groups) Orm.select(new Groups(), search).get(0);
    }
}