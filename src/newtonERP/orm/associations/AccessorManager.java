package newtonERP.orm.associations;

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
	 */
	public static void addAccessor(
			newtonERP.module.AbstractOrmEntity sourceEntity,
			newtonERP.module.AbstractOrmEntity foreignEntityDefinition)
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
	 */
	public final static void addFlagPool(
			newtonERP.module.AbstractOrmEntity sourceEntity,
			newtonERP.module.AbstractOrmEntity foreignEntityDefinition)
	{
		FlagPoolManager.addFlagPool(sourceEntity, foreignEntityDefinition);
	}

	/**
	 * @param entity Entité source
	 * @param foreignEntity Entité cible
	 */
	public final static void addListOfValue(
			newtonERP.module.AbstractOrmEntity entity,
			newtonERP.module.AbstractOrmEntity foreignEntity)
	{
		ListOfValueManager.addListOfValue(entity, foreignEntity);
	}

	/**
	 * @param abstractOrmEntity entité source
	 * @return liste des accessors plusieurs à plusieurs pour l'entité source
	 */
	public final static PluralAccessor getPluralAccessorList(
			newtonERP.module.AbstractOrmEntity abstractOrmEntity)
	{
		return PluralAccessorManager.getPluralAccessorList(abstractOrmEntity);
	}

	/**
	 * @param abstractOrmEntity entité source
	 * @return liste des accessors 1 à 1 et plusieurs à 1 pour l'entité source
	 */
	public final static newtonERP.module.AbstractOrmEntity getSingleAccessorList(
			newtonERP.module.AbstractOrmEntity abstractOrmEntity)
	{
		return SingleAccessorManager.getSingleAccessorList(abstractOrmEntity);
	}

	/**
	 * @param abstractOrmEntity entité
	 * @param accessorName nom de l'accessor
	 * @return accessor
	 */
	public static newtonERP.module.AbstractOrmEntity getSingleAccessor(
			newtonERP.module.AbstractOrmEntity abstractOrmEntity,
			String accessorName)
	{
		return SingleAccessorManager.getSingleAccessor(abstractOrmEntity,
				accessorName);
	}

	/**
	 * @param sourceEntity entié de source
	 * @param gateWayEntity entité de passerelle
	 * @param externalEntity entié externe
	 */
	public static void addGateWay(
			newtonERP.module.AbstractOrmEntity sourceEntity,
			newtonERP.module.AbstractOrmEntity gateWayEntity,
			newtonERP.module.AbstractOrmEntity externalEntity)
	{
		GateWayManager.addGateWay(sourceEntity, gateWayEntity, externalEntity);
	}

}
