package newtonERP.module;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.orm.Orm;
import newtonERP.viewers.viewables.CheckListViewable;

/**
 * @author Guillaume Lacasse cette classe sert à faire des liste de flag qu'on
 *         peut attribuer à une entité lorsque ces flags sont dans une table
 *         étrangère passant par une table intermédiaire. Exemple: attribution
 *         de droits pour un groupe (ça passe par GroupRight)
 */
public class FlagPool implements CheckListViewable
{
    private AbstractOrmEntity sourceEntityDefinition;
    private String sourceKeyName;
    private String sourceKeyValue;
    private String visibleDescription;
    private AbstractOrmEntity intermediateEntityDefinition;
    private AbstractOrmEntity foreignEntityDefinition;
    private String intermediateKeyIn;
    private String intermediateKeyOut;
    private String foreignKey;
    private String[] foreignDescriptionUiControls;

    /**
     * @param sourceEntityDefinition Definition de l'entité de source, exemple:
     *            groupe
     * @param visibleDescription Description visible
     * @param intermediateEntityDefinition Entité de table intermédiaire,
     *            exemple: GroupRight
     * @param intermediateKeyIn Colonne d'entré de table intermédiaire, exemple:
     *            groupID
     * @param intermediateKeyOut Colonne de sortie de table intermédiaire,
     *            exemple: rightID
     * @param foreignEntityDefinition entité de table étrangère, exemple: Right
     * @param foreignKey clef d'identification de table étrangère, exemple:
     *            PKrightID
     * @param foreignDescriptionUiControls liste de colonne de description de
     *            table étrangère, exemple: Action, Module
     */
    public FlagPool(AbstractOrmEntity sourceEntityDefinition,
	    String visibleDescription,
	    AbstractOrmEntity intermediateEntityDefinition,
	    String intermediateKeyIn, String intermediateKeyOut,
	    AbstractOrmEntity foreignEntityDefinition, String foreignKey,
	    String[] foreignDescriptionUiControls)
    {
	this.visibleDescription = visibleDescription;
	this.intermediateEntityDefinition = intermediateEntityDefinition;
	this.intermediateKeyIn = intermediateKeyIn;
	this.intermediateKeyOut = intermediateKeyOut;
	this.foreignEntityDefinition = foreignEntityDefinition;
	this.foreignKey = foreignKey;
	this.foreignDescriptionUiControls = foreignDescriptionUiControls;
    }

    @Override
    public String getVisibleDescription()
    {
	return visibleDescription;
    }

    @Override
    public Hashtable<String, String> getAvailableElementList() throws Exception
    {
	Vector<AbstractOrmEntity> entityList = Orm.select(
		foreignEntityDefinition, null);

	Hashtable<String, String> availableElementList = new Hashtable<String, String>();

	for (AbstractOrmEntity entity : entityList)
	{
	    availableElementList.put(entity.getDataString(foreignKey),
		    getForeignDescription(entity));
	}

	return availableElementList;
    }

    private String getForeignDescription(AbstractOrmEntity entity)
    {
	String description = "";
	for (String key : foreignDescriptionUiControls)
	    description += entity.getDataString(key) + " > ";
	return description.trim();
    }

    @Override
    public HashSet<String> getCheckedElementList() throws Exception

    {
	if (sourceKeyName == null || sourceKeyValue == null)
	    throw new Exception(
		    "Vous devez préablablement faire une query(clef, valeur) avant d'intéroger les éléments du flag pool");

	// TODO Auto-generated method stub
	return new HashSet<String>();
    }

    public void query(String sourceKeyName, String sourceKeyValue)
    {
	this.sourceKeyName = sourceKeyName;
	this.sourceKeyValue = sourceKeyValue;
    }
}
