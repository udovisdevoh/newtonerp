package newtonERP.orm.associations;

import java.util.TreeMap;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.TableAssociationException;

/**
 * Facade vers FlagPoolManager et ListOfValueManager
 * @author Guillaume Lacasse
 */
public class AccessorManager
{
    /**
     * Essaie d'ajouter un accessor quelconque (1 à plusieur, plusiseurs à
     * plusieurs selon la disponibilité des tables)
     * 
     * @param sourceEntity the source entity
     * @param foreignEntityDefinition entity of the foreign entity
     * @throws Exception a general exception
     */
    public static void addAccessor(AbstractOrmEntity sourceEntity,
	    AbstractOrmEntity foreignEntityDefinition) throws Exception
    {
	try
	{
	    ListOfValueManager.addListOfValue(sourceEntity,
		    foreignEntityDefinition);
	} catch (Exception exception)
	{

	    try
	    {
		FlagPoolManager.addFlagPool(sourceEntity,
			foreignEntityDefinition);
	    } catch (Exception exception2)
	    {
		exception2.printStackTrace();// Pour connaitre la source de la
		// 1ère exception
		throw new TableAssociationException(
			exception2.getMessage()
				+ "\nL'accessor n'a pas pu créer de listOfValue ni de flagPool.\nSi vous tentez de créer un accessor de plusieur à plusieur,\nsoyez-sur qu'une table intermédiaire existe ayant pour nom\nla concaténation des noms en ordre alphabetique\ndes deux tables nécéssitant une relation de plusieurs à plusieurs.\nIl faut également que cette table intermédiaire ait\npour colonnes chaque clef etrangère de chaque tables pour lesquelles\nvous voulez la relation de plusieurs à plusieurs.\nVeuillez utiliser la methode getForeignKeyName() pour ces tables.");
	    }
	}
	sourceEntity.getAccessorNameList().add(
		foreignEntityDefinition.getSystemName());
    }

    /**
     * @param sourceEntity the source entity
     * @param foreignEntityDefinition entity of the foreign entity
     * @throws Exception a general exception
     */
    public static final void addFlagPool(AbstractOrmEntity sourceEntity,
	    AbstractOrmEntity foreignEntityDefinition) throws Exception
    {
	FlagPoolManager.addFlagPool(sourceEntity, foreignEntityDefinition);
    }

    /**
     * @param entity Entité source
     * @param foreignEntity Entité cible
     * @throws Exception a general exception
     */
    public static final void addListOfValue(AbstractOrmEntity entity,
	    AbstractOrmEntity foreignEntity) throws Exception
    {
	ListOfValueManager.addListOfValue(entity, foreignEntity);
    }

    /**
     * @param abstractOrmEntity entité source
     * @return liste des accessors plusieurs à plusieurs pour l'entité source
     * @throws Exception si obtention de la liste d'accessor fail
     */
    public static final TreeMap<String, PluralAccessor> getPluralAccessorList(
	    AbstractOrmEntity abstractOrmEntity) throws Exception
    {
	return PluralAccessorManager.getPluralAccessorList(abstractOrmEntity);
    }

    /**
     * @param abstractOrmEntity entité source
     * @return liste des accessors 1 à 1 et plusieurs à 1 pour l'entité source
     * @throws Exception si obtention fail
     */
    public static final TreeMap<String, AbstractOrmEntity> getSingleAccessorList(
	    AbstractOrmEntity abstractOrmEntity) throws Exception
    {
	return SingleAccessorManager.getSingleAccessorList(abstractOrmEntity);
    }

    /**
     * @param abstractOrmEntity entité
     * @param accessorName nom de l'accessor
     * @return accessor
     * @throws Exception si obtention fail
     */
    public static AbstractOrmEntity getSingleAccessor(
	    AbstractOrmEntity abstractOrmEntity, String accessorName)
	    throws Exception
    {
	return SingleAccessorManager.getSingleAccessor(abstractOrmEntity,
		accessorName);
    }

    /**
     * @param sourceEntity entié de source
     * @param gateWayEntity entité de passerelle
     * @param externalEntity entié externe
     */
    public static void addGateWay(AbstractOrmEntity sourceEntity,
	    AbstractOrmEntity gateWayEntity, AbstractOrmEntity externalEntity)
    {
	GateWayManager.addGateWay(sourceEntity, gateWayEntity, externalEntity);
    }
}