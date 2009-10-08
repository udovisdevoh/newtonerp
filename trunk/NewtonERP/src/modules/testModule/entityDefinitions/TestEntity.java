package modules.testModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Test entity
 */
public class TestEntity extends AbstractEntity implements Ormizable
{
    private int testID;
    private String name;
    private int age;
    private String color;

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	Hashtable<String, String> ormizableData = new Hashtable<String, String>();

	ormizableData.put("name", name);
	ormizableData.put("age", age + "");
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
		setAge((Integer) parameters.get(key));
	    }
	    else if (key.toString().equals("color"))
	    {
		setColor((String) parameters.get(key));
	    }

	}
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
    public void setAge(int age)
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
     * @return the name
     */
    public String getName()
    {
	return name;
    }

    /**
     * @return the age
     */
    public int getAge()
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

    /**
     * @return the testID
     */
    public int getTestID()
    {
	return testID;
    }

    /**
     * @param testID the testID to set
     */
    public void setTestID(int testID)
    {
	this.testID = testID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
	// TODO Auto-generated method stub
	return super.toString() + "testID=" + testID + ":name=" + name
		+ ":age=" + age + ":color=" + color;
    }

}
