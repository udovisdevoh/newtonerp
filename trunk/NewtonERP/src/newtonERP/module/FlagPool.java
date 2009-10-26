package newtonERP.module;

import newtonERP.viewers.viewables.CheckListViewable;

/**
 * @author Guillaume Lacasse cette classe sert à faire des liste de flag qu'on
 *         peut attribuer à une entité lorsque ces flags sont dans une table
 *         étrangère passant par une table intermédiaire. Exemple: attribution
 *         de droits pour un groupe (ça passe par GroupRight)
 */
public class FlagPool implements CheckListViewable
{
    private String visibleDescription;
    private AbstractOrmEntity intermediateEntityDefinition;
    private AbstractOrmEntity foreignEntityDefinition;
    private String intermediateKeyIn;
    private String intermediateKeyOut;
    private String foreignKey;
    private String[] foreignDescriptionUiControls;

    /**
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
    public FlagPool(String visibleDescription,
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
}
