/**
 * 
 */
package unitTest.module;

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
     * {@link modules.testModule.entityDefinitions.TestEntity#getOrmizableData()}
     * .
     */
    public void testGetOrmizableData()
    {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link modules.testModule.entityDefinitions.TestEntity#setOrmizableData(java.util.Hashtable)}
     * .
     */
    public void testSetOrmizableData()
    {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link newtonERP.module.AbstractEntity#getEntityFromHashTable(java.util.Hashtable)}
     * .
     */
    public void testGetEntityFromHashTable()
    {
	entity.setAge(20);
	entity.setColor("red");
	entity.setIndex(3);
	entity.setName("joe blow");
	try
	{
	    assertNotNull(entity.getHashTableFromEntity());
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Test method for
     * {@link newtonERP.module.AbstractEntity#getHashTableFromEntity(newtonERP.module.AbstractEntity)}
     * .
     */
    public void testGetHashTableFromEntity()
    {
	fail("Not yet implemented"); // TODO
    }

}
