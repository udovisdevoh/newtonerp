package newtonERP.common;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * Collection générique de type Map semblable à Hashtable et TreeMap Sert à
 * avoir une liste de key-value avec un ordre utilisez la methode getKeyList()
 * @author Guillaume
 * @param <K> Clef
 * @param <V> Valeur
 */
public class NaturalMap<K, V> implements Map<K, V>
{
    private Vector<K> keyList;

    private Hashtable<K, V> hashtable;

    @Override
    public void clear()
    {
	getHashtable().clear();
	getKeyList().clear();
    }

    /**
     * @return Clefs par ordre d'insertion
     */
    public Vector<K> getKeyList()
    {
	if (keyList == null)
	    keyList = new Vector<K>();
	return keyList;
    }

    @Override
    public boolean containsKey(Object arg0)
    {
	return getHashtable().containsKey(arg0);
    }

    private Hashtable<K, V> getHashtable()
    {
	if (hashtable == null)
	    hashtable = new Hashtable<K, V>();
	return hashtable;
    }

    @Override
    public boolean containsValue(Object arg0)
    {
	return getHashtable().containsValue(arg0);
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public V get(Object arg0)
    {
	return getHashtable().get(arg0);
    }

    @Override
    public boolean isEmpty()
    {
	return getKeyList().isEmpty();
    }

    @Override
    public Set<K> keySet()
    {
	return getHashtable().keySet();
    }

    @Override
    public V put(K arg0, V arg1)
    {
	getKeyList().add(arg0);
	return getHashtable().put(arg0, arg1);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> arg0)
    {
	getKeyList().addAll(arg0.keySet());
	getHashtable().putAll(arg0);
    }

    @Override
    public V remove(Object arg0)
    {
	getKeyList().remove(arg0);
	return getHashtable().remove(arg0);
    }

    @Override
    public int size()
    {
	return getKeyList().size();
    }

    @Override
    public Collection<V> values()
    {
	return getHashtable().values();
    }
}
