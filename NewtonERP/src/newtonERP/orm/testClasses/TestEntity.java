package newtonERP.orm.testClasses;

import java.util.Hashtable;

import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmFieldNotFoundException;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Test entity
 */
public class TestEntity implements Ormizable
{
    private int index = 0;
    private String name = "marcel";
    private String age = "17";
    private String color = "bleu";

    @Override
    public Hashtable<String, String> getOrmizableData()
	    throws OrmFieldNotFoundException
    {
	Hashtable<String, String> ormizableData = new Hashtable<String, String>();

	ormizableData.put("name", name);
	ormizableData.put("age", age);
	ormizableData.put("color", color);

	return ormizableData;
    }

    @Override
    public String getTableName()
    {
	return "Employee";
    }

    @Override
    public String getSearchCriteria()
    {
	// Sera gnr par la vue selon la dfinition de l'entit du module
	// Pourrait retourner null s'il n'y en a pas et l'orm pourrait se fier
	// getOrmizableData
	return "name like %mar% AND age > 13 || color = bleu";
    }

    @Override
    public int getPrimaryKeyValue()
    {
	// TODO Auto-generated method stub
	return 0;
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

	}

    }

    /**
     * @param index
     */
    public void setIndex(int index)
    {
	this.index = index;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
	this.name = name;
    }

    /**
     * @param age
     */
    public void setAge(String age)
    {
	this.age = age;
    }

    /**
     * @param color
     */
    public void setColor(String color)
    {
	this.color = color;
    }
}
