package newtonERP.viewers.viewerData;

import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;

/**
 * Modele d'une barre de recherche
 * @author Guillaume Lacasse
 */
public class SearchBar extends AbstractEntity
{
    private String targetUrl;
    private String currentSearchEntry;
    private int limit;
    private int offset;
    private Vector<String> possibleOrderList;
    private String currentOrder;

    /**
     * @param targetUrl url de destination de la recherche
     * @param currentSearchEntry contenu textuel courant de la rercherche
     * @param concernedEntity entité concernée par la recherche
     * @param currentOrder ordre actuel
     * @param limit limite de recherche
     * @param offset offset de recherche
     * @throws Exception si ça fail
     */
    public SearchBar(String targetUrl, String currentSearchEntry,
	    AbstractOrmEntity concernedEntity, String currentOrder, int limit,
	    int offset) throws Exception
    {
	super();
	this.targetUrl = targetUrl;
	this.currentSearchEntry = currentSearchEntry;
	this.currentOrder = currentOrder;
	this.limit = limit;
	this.offset = offset;
	possibleOrderList = buildPossibleOrderList(concernedEntity);
    }

    /**
     * @return Url de destination de la recherche
     */
    public String getTargetUrl()
    {
	return targetUrl;
    }

    /**
     * @return contenu textuel courant de la rercherche
     */
    public String getCurrentSearchEntry()
    {
	return currentSearchEntry;
    }

    /**
     * @return liste des ordres possibles
     */
    public Vector<String> getPossibleOrderNameList()
    {
	return possibleOrderList;
    }

    private Vector<String> buildPossibleOrderList(
	    AbstractOrmEntity concernedEntity)
    {
	Vector<String> newPossibleOrderList = new Vector<String>();

	for (Field<?> field : concernedEntity.getFields())
	    newPossibleOrderList.add(field.getShortName() + " ASC");

	for (Field<?> field : concernedEntity.getFields())
	    newPossibleOrderList.add(field.getShortName() + " DESC");

	return newPossibleOrderList;
    }

    /**
     * @return ordre actuel
     */
    public String getCurrentOrder()
    {
	return currentOrder;
    }

    /**
     * @return search offset
     */
    public int getOffset()
    {
	return offset;
    }

    /**
     * @return search limit
     */
    public int getLimit()
    {
	return limit;
    }
}
