package newtonERP.orm.associations;

import newtonERP.module.AbstractOrmEntity;

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

}
