package newtonERP.orm.associations;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;

/**
 * Accesseur multiple
 * @author Guillaume
 */
public class PluralAccessor extends Collection
{
	private newtonERP.module.AbstractOrmEntity internalEntityDefinition;

	private newtonERP.module.AbstractOrmEntity entityList;

	/**
	 * @param internalEntityDefinition définition d'entité interne
	 */
	public PluralAccessor(
			newtonERP.module.AbstractOrmEntity internalEntityDefinition)
	{
		this.internalEntityDefinition = internalEntityDefinition;
	}

	/**
	 * @param internalEntityDefinition définition d'entité interne
	 * @param entityList liste d'entités
	 */
	public PluralAccessor(
			newtonERP.module.AbstractOrmEntity internalEntityDefinition,
			Vector<AbstractOrmEntity> entityList)
	{
		this(internalEntityDefinition);
		this.entityList = entityList;
	}

	/**
	 * @return définition d'entité interne
	 */
	public newtonERP.module.AbstractOrmEntity getInternalEntityDefinition()
	{
		return internalEntityDefinition;
	}

	private newtonERP.module.AbstractOrmEntity getEntityList()
	{
		if (entityList == null)
			entityList = new Vector<AbstractOrmEntity>();
		return entityList;
	}

	@Override
	public newtonERP.module.AbstractOrmEntity iterator()
	{
		return getEntityList().iterator();
	}

	@Override
	public boolean add(newtonERP.module.AbstractOrmEntity entity)
	{
		return getEntityList().add(entity);
	}

	@Override
	public boolean addAll(Collection<? extends AbstractOrmEntity> collection)
	{
		return getEntityList().addAll(collection);
	}

	@Override
	public void clear()
	{
		getEntityList().clear();
	}

	@Override
	public boolean contains(Object object)
	{
		return getEntityList().contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return getEntityList().containsAll(collection);
	}

	@Override
	public boolean isEmpty()
	{
		return getEntityList().isEmpty();
	}

	@Override
	public boolean remove(Object object)
	{
		return getEntityList().remove(object);
	}

	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return getEntityList().removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return getEntityList().retainAll(collection);
	}

	@Override
	public int size()
	{
		return getEntityList().size();
	}

	@Override
	public Object toArray()
	{
		return getEntityList().toArray();
	}

	@Override
	public T toArray(T a)
	{
		return getEntityList().toArray(a);
	}

	/**
	 * @param i index
	 * @return entité à l'index i
	 */
	public newtonERP.module.AbstractOrmEntity get(int i)
	{
		return getEntityList().get(i);
	}

}
