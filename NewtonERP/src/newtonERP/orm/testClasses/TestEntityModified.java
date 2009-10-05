package newtonERP.orm.testClasses;

import java.util.Hashtable;

import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * 
 * @author r3hallejo
 * 
 *         Test entity modified
 */
public class TestEntityModified implements Ormizable
{
    @SuppressWarnings("unused")
    private int index = 0;
    private String name = "Sylvain";
    private String age = "23";
    private String color = "vert lime";

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
}
