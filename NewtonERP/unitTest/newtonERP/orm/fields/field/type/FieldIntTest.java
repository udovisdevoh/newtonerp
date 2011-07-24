/**
 * 
 */
package newtonERP.orm.fields.field.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldIntTest {

	/** The field bool. */
	private FieldInt fieldInt;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		fieldInt = new FieldInt();
	}

	/**
	 * Tear down.
	 * 
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		// nothing to do
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataString1() {
		fieldInt.setDataString("1");
		assertTrue(1 == fieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataString5432() {
		fieldInt.setDataString("5432");
		assertTrue(5432 == fieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringMinus184() {
		fieldInt.setDataString("-184");
		assertTrue(-184 == fieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataString5() {
		fieldInt.setData(5);
		assertEquals("5", fieldInt.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringMinus23() {
		fieldInt.setData(-23);
		assertEquals("-23", fieldInt.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldInt.setDefaultValue();
		assertTrue(0 == fieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldInt() {
		FieldInt testFieldInt = new FieldInt();
		assertNotNull(testFieldInt);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldIntInt25() {
		FieldInt testFieldInt = new FieldInt(25);
		assertNotNull(testFieldInt);
		assertTrue(25 == testFieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldInt.setData(10);
		assertTrue(10 == fieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldInt.setData(-32);
		assertTrue(-32 == fieldInt.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldInt.setData(8);
		fieldInt.reset();
		assertNull(fieldInt.getData());
	}

}
