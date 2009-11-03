package newtonERP.orm.associations;

import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.FlagPool;

/**
 * Cette classe s'occupe de la gestion des accessor plusieurs à plusieurs
 * @author Guillaume
 */
public class PluralAccessorManager
{

    /**
     * @param entity entité source
     * @return liste des accessors 1 à 1 et plusieurs à 1 pour l'entité source
     * @throws Exception si obtention de l'accessor multiple fail
     */
    public static final TreeMap<String, Vector<AbstractOrmEntity>> getPluralAccessorList(
	    AbstractOrmEntity entity) throws Exception
    {
	TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList = new TreeMap<String, Vector<AbstractOrmEntity>>();

	Vector<AbstractOrmEntity> pluralAccessor;
	FlagPool flagPool;

	for (String flagPoolName : entity.getFlagPoolList().keySet())
	{
	    flagPool = entity.getFlagPoolList().get(flagPoolName);
	    pluralAccessor = getPluralAccessor(entity, flagPool);

	    if (pluralAccessor != null)
		pluralAccessorList.put(flagPoolName, pluralAccessor);
	}
	return pluralAccessorList;
    }

    private static Vector<AbstractOrmEntity> getPluralAccessor(
	    AbstractOrmEntity entity, FlagPool flagPool) throws Exception
    {
	flagPool.query(entity.getPrimaryKeyName(), entity.getPrimaryKeyValue());

	return flagPool.getPluralForeignAccessor();
    }
}
