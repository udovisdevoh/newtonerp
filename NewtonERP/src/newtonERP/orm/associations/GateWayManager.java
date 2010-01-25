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
    public static void addGateWay(AbstractOrmEntity sourceEntity,
	    AbstractOrmEntity gateWayEntity, AbstractOrmEntity externalEntity)
    {
	GateWay gateWay = new GateWay(gateWayEntity, externalEntity);
	sourceEntity.getGateWayList().add(gateWay);
    }

    /**
     * @param gateWay gateWay
     * @param sourceEntity source entity
     * @return external entity
     * @throws Exception si ça fail
     */
    public static AbstractOrmEntity getExternalEntity(GateWay gateWay,
	    AbstractOrmEntity sourceEntity) throws Exception
    {
	AbstractOrmEntity gateWayEntity = gateWay.getGateWayEntity();
	gateWayEntity.setData(gateWayEntity.getPrimaryKeyName(), sourceEntity
		.getData(gateWayEntity.getForeignKeyName()));
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