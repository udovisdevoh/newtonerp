package newtonERP.orm.associations;

/**
 * Accesseur passant par un autre accesseur
 * @author Guillaume Lacasse
 */
public class GateWay
{
	private newtonERP.module.AbstractOrmEntity gateWayEntity;

	private newtonERP.module.AbstractOrmEntity externalEntity;

	/**
	 * @param gateWayEntity entité de passerelle
	 * @param externalEntity entité externe
	 */
	public GateWay(newtonERP.module.AbstractOrmEntity gateWayEntity,
			newtonERP.module.AbstractOrmEntity externalEntity)
	{
		this.gateWayEntity = gateWayEntity;
		this.externalEntity = externalEntity;
	}

	/**
	 * @return entité de passerelle
	 */
	public newtonERP.module.AbstractOrmEntity getGateWayEntity()
	{
		return gateWayEntity;
	}

	/**
	 * @return entité externe
	 */
	public newtonERP.module.AbstractOrmEntity getExternalEntity()
	{
		return externalEntity;
	}

	/**
	 * @return whether the external entity's field should be in color
	 */
	public boolean isColored()
	{
		return gateWayEntity.getFields()
				.getField(externalEntity.getForeignKeyName()).isColored();
	}

}
