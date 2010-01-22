package newtonERP.orm.associations;

import newtonERP.module.AbstractOrmEntity;

/**
 * Accesseur passant par un autre accesseur
 * @author Guillaume Lacasse
 */
public class GateWay
{
    private AbstractOrmEntity gateWayEntity;
    private AbstractOrmEntity externalEntity;

    /**
     * @param gateWayEntity entité de passerelle
     * @param externalEntity entité externe
     */
    public GateWay(AbstractOrmEntity gateWayEntity,
	    AbstractOrmEntity externalEntity)
    {
	this.gateWayEntity = gateWayEntity;
	this.externalEntity = externalEntity;
    }

    /**
     * @return entité de passerelle
     */
    public AbstractOrmEntity getGateWayEntity()
    {
	return gateWayEntity;
    }

    /**
     * @return entité externe
     */
    public AbstractOrmEntity getExternalEntity()
    {
	return externalEntity;
    }

    /**
     * @return whether the external entity's field should be in color
     */
    public boolean isColored()
    {
	return gateWayEntity.getFields().getField(
		externalEntity.getForeignKeyName()).isColored();
    }
}
