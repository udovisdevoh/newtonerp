package newtonERP.orm.associations;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Sert à gérer les accesseurs passant par des accesseurs
 * @author Guillaume Lacasse
 */
public class GateWayManager
{
	/**
	 * @param sourceEntity entité de source
	 * @param gateWayEntity entité de passerelle
	 * @param externalEntity entité externe
	 */
	public static void addGateWay(
			newtonERP.module.AbstractOrmEntity sourceEntity,
			newtonERP.module.AbstractOrmEntity gateWayEntity,
			newtonERP.module.AbstractOrmEntity externalEntity)
	{
		GateWay gateWay = new GateWay(gateWayEntity, externalEntity);
		sourceEntity.getGateWayList().add(gateWay);
	}

	/**
	 * @param gateWay gateWay
	 * @param sourceEntity source entity
	 * @return external entity
	 */
	public static newtonERP.module.AbstractOrmEntity getExternalEntity(
			GateWay gateWay, newtonERP.module.AbstractOrmEntity sourceEntity)
	{
		AbstractOrmEntity gateWayEntity = gateWay.getGateWayEntity();
		gateWayEntity.setData(gateWayEntity.getPrimaryKeyName(),
				sourceEntity.getData(gateWayEntity.getForeignKeyName()));
		gateWayEntity = Orm.selectUnique(gateWayEntity);

		AbstractOrmEntity externalEntity = gateWay.getExternalEntity();

		String externalPrimaryKeyName = externalEntity.getPrimaryKeyName();
		String externalForeignKeyName = externalEntity.getForeignKeyName();
		int externalPrimaryKeyValue = (Integer) gateWayEntity
				.getData(externalForeignKeyName);

		externalEntity.setData(externalPrimaryKeyName, externalPrimaryKeyValue);
		externalEntity = Orm.selectUnique(externalEntity);

		return externalEntity;
	}

}
