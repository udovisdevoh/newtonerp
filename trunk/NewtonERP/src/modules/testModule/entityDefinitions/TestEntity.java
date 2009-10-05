package modules.testModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.Viewable;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Test entity
 */
public class TestEntity implements Ormizable, Viewable
{
    @SuppressWarnings("unused")
    private int index = 0;
    private String name = "marcel";
    private String age = "17";
    private String color = "bleu";

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	Hashtable<String, String> ormizableData = new Hashtable<String, String>();

	ormizableData.put("name", name);
	ormizableData.put("age", age);
	ormizableData.put("color", color);

	return ormizableData;
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	for (Object key : parameters.keySet())
	{
	    // To be continued!!!!!!!!!!
	    if (key.toString().equals("name"))
	    {
		setName((String) parameters.get(key));
	    }
	    else if (key.toString().equals("age"))
	    {
		setAge((String) parameters.get(key));
	    }
	    else if (key.toString().equals("color"))
	    {
		setColor((String) parameters.get(key));
	    }

	}

    }

    /**
     * @param index the index
     */
    public void setIndex(int index)
    {
	this.index = index;
    }

    /**
     * @param name the name
     */
    public void setName(String name)
    {
	this.name = name;
    }

    /**
     * @param age the age
     */
    public void setAge(String age)
    {
	this.age = age;
    }

    /**
     * @param color the color
     */
    public void setColor(String color)
    {
	this.color = color;
    }

    /**
     * @return the index
     */
    public int getIndex()
    {
	return index;
    }

    /**
     * @return the name
     */
    public String getName()
    {
	return name;
    }

    /**
     * @return the age
     */
    public String getAge()
    {
	return age;
    }

    /**
     * @return the color
     */
    public String getColor()
    {
	return color;
    }

}
