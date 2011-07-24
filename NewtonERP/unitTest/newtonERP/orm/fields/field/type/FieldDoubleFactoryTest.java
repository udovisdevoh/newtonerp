/**
 * 
 */
package newtonERP.orm.fields.field.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldDoubleFactoryTest {

	/** The field bool. */
	private Field fieldDouble;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		fieldDouble = FieldFactory.newField(FieldType.DOUBLE, "double test");
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
		fieldDouble.setDataString("1.2");
		assertTrue(1.2 == (Double) fieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataString5432() {
		fieldDouble.setDataString("5432.97643");
		assertTrue(5432.97643 == (Double) fieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringMinus184() {
		fieldDouble.setDataString("-184.46");
		assertTrue(-184.46 == (Double) fieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataString5() {
		fieldDouble.setData(5.85);
		assertEquals("5.85", fieldDouble.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringMinus23() {
		fieldDouble.setData(-23.2);
		assertEquals("-23.2", fieldDouble.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldDouble.setDefaultValue();
		assertTrue(0 == (Double) fieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldInt() {
		Field testFieldDouble = FieldFactory.newField(FieldType.DOUBLE, "double test");
		assertNotNull(testFieldDouble);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldDoubleDouble25() {
		Field testFieldDouble = FieldFactory.newField(FieldType.DOUBLE, "double test", 25.75);
		assertNotNull(testFieldDouble);
		assertTrue(25.75 == (Double) testFieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldDouble.setData(10.24);
		assertTrue(10.24 == (Double) fieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldDouble.setData(-32.57);
		assertTrue(-32.57 == (Double) fieldDouble.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldDouble.setData(8.3);
		fieldDouble.reset();
		assertNull(fieldDouble.getData());
	}

}
