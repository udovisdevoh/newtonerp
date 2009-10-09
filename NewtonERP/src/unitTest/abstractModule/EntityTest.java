/**
 * 
 */
package unitTest.abstractModule;

import java.util.Hashtable;

import junit.framework.TestCase;
import modules.testModule.entityDefinitions.TestEntity;

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
     */
    public void testGetEntityFromHashTableString()
    {
	Hashtable<String, String> hash = new Hashtable<String, String>();

	hash.put("age", "20");
	entity.setAge(20);
	hash.put("color", "red");
	entity.setColor("red");
	hash.put("testID", "3");
	entity.setTestID(3);
	hash.put("name", "joe blow");
	entity.setName("joe blow");
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
     */
    public void testGetEntityFromHashTableObject()
    {
	System.out.println("**********");
	Hashtable<String, Object> hash = new Hashtable<String, Object>();

	hash.put("age", 20);
	entity.setAge(20);
	hash.put("color", "red");
	entity.setColor("red");
	hash.put("testID", 3);
	entity.setTestID(3);
	hash.put("name", "joe blow");
	entity.setName("joe blow");
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
     */
    public void testGetHashTableFromEntity()
    {
	Hashtable<String, String> hash = new Hashtable<String, String>();

	hash.put("age", "20");
	entity.setAge(20);
	hash.put("color", "red");
	entity.setColor("red");
	hash.put("testID", "3");
	entity.setTestID(3);
	hash.put("name", "joe blow");
	entity.setName("joe blow");
	assertEquals(hash, entity.getHashTableFromEntity());

    }

}
