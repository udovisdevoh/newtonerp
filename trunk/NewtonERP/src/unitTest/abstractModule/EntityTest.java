/**
 * 
 */
package unitTest.abstractModule;

import java.util.Hashtable;

import junit.framework.TestCase;
import modules.testModule.entityDefinitions.TestEntity;
import newtonERP.module.exception.FieldNotFoundException;

/**
 * @author djo
 * 
 */
public class EntityTest extends TestCase
{
    TestEntity entity;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	super.setUp();
	entity = new TestEntity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
	super.tearDown();
    }

    /**
     * Test method for
     * {@link newtonERP.module.AbstractEntity#setEntityFromHashTable(java.util.Hashtable)}
     * .
     * 
     * @throws FieldNotFoundException
     */
    public void testGetEntityFromHashTableString()
	    throws FieldNotFoundException
    {
	Hashtable<String, String> hash = new Hashtable<String, String>();

	hash.put("age", "20");
	entity.getFields().setData("age", "20");
	hash.put("color", "red");
	entity.getFields().setData("color", "red");
	hash.put("testID", "3");
	entity.getFields().setData("testID", "3");
	hash.put("name", "joe blow");
	entity.getFields().setData("name", "joe blow");
	TestEntity otherEntity = new TestEntity();
	try
	{
	    otherEntity.setEntityFromHashTable(hash);
	    assertEquals(entity, otherEntity);
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

    /**
     * Test method for
     * {@link newtonERP.module.AbstractEntity#setEntityFromHashTable(java.util.Hashtable)}
     * .
     * 
     * @throws FieldNotFoundException
     */
    public void testGetEntityFromHashTableObject()
	    throws FieldNotFoundException
    {
	System.out.println("**********");
	Hashtable<String, Object> hash = new Hashtable<String, Object>();

	hash.put("age", "20");
	entity.getFields().setData("age", "20");
	hash.put("color", "red");
	entity.getFields().setData("color", "red");
	hash.put("testID", "3");
	entity.getFields().setData("testID", "3");
	hash.put("name", "joe blow");
	entity.getFields().setData("name", "joe blow");
	TestEntity otherEntity = new TestEntity();
	try
	{
	    otherEntity.setEntityFromHashTable(hash);
	    assertEquals(entity, otherEntity);

	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
	System.out.println("**********");
    }

    /**
     * Test method for
     * {@link newtonERP.module.AbstractEntity#getHashTableFromEntity(newtonERP.module.AbstractEntity)}
     * .
     * 
     * @throws FieldNotFoundException
     */
    public void testGetHashTableFromEntity() throws FieldNotFoundException
    {
	Hashtable<String, String> hash = new Hashtable<String, String>();

	hash.put("age", "20");
	entity.getFields().setData("age", "20");
	hash.put("color", "red");
	entity.getFields().setData("color", "red");
	hash.put("testID", "3");
	entity.getFields().setData("testID", "3");
	hash.put("name", "joe blow");
	entity.getFields().setData("name", "joe blow");
	assertEquals(hash, entity.getHashTableFromEntity());

    }

}
