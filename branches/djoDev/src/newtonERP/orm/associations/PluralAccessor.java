package newtonERP.orm.associations;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;

/**
 * Accesseur multiple
 * @author Guillaume
 */
public class PluralAccessor implements Collection<AbstractOrmEntity>
{
    private AbstractOrmEntity internalEntityDefinition;

    private Vector<AbstractOrmEntity> entityList;

    /**
     * @param internalEntityDefinition définition d'entité interne
     */
    public PluralAccessor(AbstractOrmEntity internalEntityDefinition)
    {
	this.internalEntityDefinition = internalEntityDefinition;
    }

    /**
     * @param internalEntityDefinition définition d'entité interne
     * @param entityList liste d'entités
     */
    public PluralAccessor(AbstractOrmEntity internalEntityDefinition,
	    Vector<AbstractOrmEntity> entityList)
    {
	this(internalEntityDefinition);
	this.entityList = entityList;
    }

    /**
     * @return définition d'entité interne
     */
    public AbstractOrmEntity getInternalEntityDefinition()
    {
	return internalEntityDefinition;
    }

    private Vector<AbstractOrmEntity> getEntityList()
    {
	if (entityList == null)
	    entityList = new Vector<AbstractOrmEntity>();
	return entityList;
    }

    @Override
    public Iterator<AbstractOrmEntity> iterator()
    {
	return getEntityList().iterator();
    }

    @Override
    public boolean add(AbstractOrmEntity entity)
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
    public Object[] toArray()
    {
	return getEntityList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
	return getEntityList().toArray(a);
    }

    /**
     * @param i index
     * @return entité à l'index i
     */
    public AbstractOrmEntity get(int i)
    {
	return getEntityList().get(i);
    }
}
