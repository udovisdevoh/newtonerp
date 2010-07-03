/**
 * 
 */
package unitTest.abstractModule;

import java.text.ParseException;
import java.util.Hashtable;

import junit.framework.TestCase;
import modules.testModule.entityDefinitions.TestEntity;
import newtonERP.module.exception.FieldNotFoundException;

/**
 * @author CloutierJo
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
    protected void setUp()
    {
	super.setUp();
	entity = new TestEntity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown()
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
	entity.getFields().setData("age", "20");
	hash.put("color", "red");
	entity.getFields().setData("color", "red");
	hash.put("testID", "3");
	entity.getFields().setData("testID", "3");
	hash.put("name", "joe blow");
	entity.getFields().setData("name", "joe blow");
	TestEntity otherEntity = new TestEntity();

	otherEntity.getFields().setFromHashTable(hash);
	assertEquals(entity, otherEntity);

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

	hash.put("age", "20");
	entity.getFields().setData("age", "20");
	hash.put("color", "red");
	entity.getFields().setData("color", "red");
	hash.put("testID", "3");
	entity.getFields().setData("testID", "3");
	hash.put("name", "joe blow");
	entity.getFields().setData("name", "joe blow");
	TestEntity otherEntity = new TestEntity();

	otherEntity.getFields().setFromHashTable(hash);
	assertEquals(entity, otherEntity);

    }
}
